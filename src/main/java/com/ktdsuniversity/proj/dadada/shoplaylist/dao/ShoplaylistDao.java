package com.ktdsuniversity.proj.dadada.shoplaylist.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.proj.dadada.shoplaylist.vo.DeleteProductRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.PackageSearchVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistProductVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistVO;

public interface ShoplaylistDao {
	
	/**
	 * 새로운 패키지를 만들 때 사용할 패키지 ID 시퀀스 번호 조회
	 * @return
	 */
	public int getNextPackageId();
	
	/**
	 * 패키지 정보를 DB에 삽입
	 * @param shoplaylistVO
	 * @return
	 */
	public int insertPackage(ShoplaylistVO shoplaylistVO);
	
	/**
	 * 패키지 안에 상품을 하나씩 추가
	 * @param vo
	 * @return
	 */
	public int insertProductToPackage(ShoplaylistProductVO vo);
	
	/**
	 * user or admin 이 만든 패키지 목록 조회
	 * @param creatorType
	 * @return
	 */
	public List<ShoplaylistVO> selectPackagesByCreator(String creatorType);
	
	/**
	 * 패키지 전체 삭제 (상품 포함)
	 * @param packageId
	 * @return
	 */
	public int deletePackage(int packageId);
	
	/**
	 * 특정 패키지에서 상품 하나만 삭제
	 * @param packageId
	 * @param productId
	 * @return
	 */
	public int deleteProductFromPackage(DeleteProductRequestVO vo);
	
	/**
	 * 특정 패키지에 포함된 모든 상품을 삭제
	 * @param packageId
	 * @return
	 */
	public int deleteProductsByPackageId(int packageId);
	
	/**
	 * 모든 상품 목록 조회
	 * @return
	 */
	public List<ShoplaylistProductVO> selectAllProducts();
	
	/**
	 * 패키지의 상세 정보 조회
	 * @param packageId
	 * @return
	 */
	public ShoplaylistVO selectPackageDetail(int packageId);
	
	/**
	 * 특정 패키지에 포함된 상품 목록 조회
	 * @param packageId
	 * @return
	 */
	public List<ShoplaylistProductVO> selectProductsByPackageId(int packageId);
	
	/**
	 * 전체 패키지 수 조회
	 * @param searchVO
	 * @return
	 */
	int getPackageCount(PackageSearchVO searchVO);
	
	/**
	 * 패키지 목록 조회
	 * @param searchVO
	 * @return
	 */
	List<ShoplaylistVO> selectPackagesBySearch(PackageSearchVO searchVO);
	
	List<Integer> selectProductIdsByPackageIds(List<Integer> packageIds);
	
	public List<ShoplaylistVO> selectPackagesByMemberId(int mbrId);

	public int existsRecommend(Map<String, Integer> of);

	public void insertRecommend(Map<String, Integer> of);

	public void plusRecommendCnt(int packageId);
	
	List<Integer> selectRecommendedPackageIdsByMember(int mbrId);
}
