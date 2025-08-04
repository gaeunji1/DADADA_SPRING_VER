package com.ktdsuniversity.proj.dadada.shoplaylist.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.proj.dadada.shoplaylist.dao.ShoplaylistDao;
import com.ktdsuniversity.proj.dadada.shoplaylist.service.ShoplaylistService;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.CreatePackageRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.DeleteProductRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.PackageSearchVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistProductVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistVO;

@Service
public class ShoplaylistServiceImpl implements ShoplaylistService {

	@Autowired
	private ShoplaylistDao shoplaylistDao;
	
	@Override
	@Transactional
	public boolean createPackageWithProducts(CreatePackageRequestVO createPackageRequestVO) {
		if (createPackageRequestVO == null || createPackageRequestVO.getShopplaylist() == null) {
			throw new IllegalArgumentException("패키지 정보가 누락되었습니다.");
		}
		

		List<Integer> productIds = createPackageRequestVO.getProductId();
		if (productIds == null || productIds.isEmpty()) {
			throw new IllegalArgumentException("상품이 선택되지 않았습니다.");
		}
		
		ShoplaylistVO shoplaylistVO = createPackageRequestVO.getShopplaylist();
		int newPackageId = shoplaylistDao.getNextPackageId();
		shoplaylistVO.setPackageId(newPackageId);
		
		boolean isInserted = shoplaylistDao.insertPackage(shoplaylistVO) > 0;
		if (!isInserted) {
			throw new RuntimeException("패키지 생성 실패");
		}
		
		int order = 1;
		for (Integer productId : productIds) {
			ShoplaylistProductVO shoplaylistProductVO = new ShoplaylistProductVO();
			shoplaylistProductVO.setPackageId(newPackageId);
			shoplaylistProductVO.setProductId(productId);
			shoplaylistProductVO.setSortOrder(order++);
			int result = shoplaylistDao.insertProductToPackage(shoplaylistProductVO);
			if (result <= 0) {
				throw new RuntimeException("상품 등록 실패: productId=" + productId);
			}
		}
		
		return true;
	}
	
	@Override
	public List<ShoplaylistVO> getPackagesByCreator(String creatorType) {
		return shoplaylistDao.selectPackagesByCreator(creatorType);
	}

	@Override
	@Transactional
	public boolean deletePackage(int packageId) {
		if (packageId <= 0) {
			throw new IllegalArgumentException("유효하지 않은 패키지 ID입니다.");
		}
		// 상품 먼저 삭제
		shoplaylistDao.deleteProductsByPackageId(packageId);
		
		// 패키지 삭제
		return shoplaylistDao.deletePackage(packageId) > 0;
	}

	@Override
	@Transactional
	public boolean deleteProductFromPackage(DeleteProductRequestVO deleteProductRequestVO) {
		if (deleteProductRequestVO == null || deleteProductRequestVO.getPackageId() <= 0 || deleteProductRequestVO.getProductId() <= 0) {
			throw new IllegalArgumentException("상품 삭제 요청 정보가 유효하지 않습니다.");
		}
		return shoplaylistDao.deleteProductFromPackage(deleteProductRequestVO) > 0;
	}

	@Override
	public List<ShoplaylistProductVO> getAllProducts() {
		return shoplaylistDao.selectAllProducts();
	}

	@Override
	public ShoplaylistVO getPackageDetail(int packageId) {
		ShoplaylistVO vo = shoplaylistDao.selectPackageDetail(packageId); // 패키지 정보 조회
		if (packageId <= 0) {
			throw new IllegalArgumentException("유효하지 않은 패키지 ID입니다.");
		}
		if (vo == null) {
			return null;
		}
		
		List<ShoplaylistProductVO> productList = shoplaylistDao.selectProductsByPackageId(packageId); // 포함된 상품 조회
		vo.setProductList(productList); 
		return vo;
	}

	@Override
	public int getPackageCount(PackageSearchVO packageSearchVO) {
		return shoplaylistDao.getPackageCount(packageSearchVO);
	}

	@Override
	public List<ShoplaylistVO> getPackagesBySearch(PackageSearchVO packageSearchVO) {
		return shoplaylistDao.selectPackagesBySearch(packageSearchVO);
	}

	@Override
	public List<Integer> getProductIdsByPackageIds(List<Integer> packageIds) {
		return shoplaylistDao.selectProductIdsByPackageIds(packageIds);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoplaylistVO> getPackagesByMemberId(int mbrId) {
		return shoplaylistDao.selectPackagesByMemberId(mbrId);
	}
	
	@Override
	public int recommend(int packageId, int mbrId) {

	    // 1) 자기 패키지인지 체크
	    ShoplaylistVO pkg = shoplaylistDao.selectPackageDetail(packageId);
	    if (pkg == null) return -1;                 // 없는 패키지
	    if (pkg.getMemId() == mbrId) return -2;     // 자기 패키지 추천 불가

	    // 2) 이미 추천했는지 체크
	    int exists = shoplaylistDao.existsRecommend(Map.of("packageId", packageId,
	                                                "mbrId",    mbrId));
	    if (exists > 0) return -3;                  // 중복 추천

	    // 3) 추천 기록 + 카운트 증가 (트랜잭션)
	    shoplaylistDao.insertRecommend(Map.of("packageId", packageId,
	                                   "mbrId",    mbrId));
	    shoplaylistDao.plusRecommendCnt(packageId);

	    return shoplaylistDao.selectPackageDetail(packageId).getRecommendCount();
	}
	
    @Override
    public List<Integer> getRecommendedPackageIdsByMember(int mbrId) {
        return shoplaylistDao.selectRecommendedPackageIdsByMember(mbrId);
    }
}
