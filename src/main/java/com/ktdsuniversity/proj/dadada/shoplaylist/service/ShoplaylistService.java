package com.ktdsuniversity.proj.dadada.shoplaylist.service;

import java.util.List;

import com.ktdsuniversity.proj.dadada.shoplaylist.vo.CreatePackageRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.DeleteProductRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.PackageSearchVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistProductVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistVO;

public interface ShoplaylistService {

    /* --------------------------------------------------
     * 패키지 CRUD
     * -------------------------------------------------- */
    boolean createPackageWithProducts(CreatePackageRequestVO vo);
    boolean deletePackage(int packageId);
    boolean deleteProductFromPackage(DeleteProductRequestVO vo);

    /* --------------------------------------------------
     * 패키지 조회
     * -------------------------------------------------- */
    List<ShoplaylistVO> getPackagesByCreator(String creatorType);
    ShoplaylistVO getPackageDetail(int packageId);
    int  getPackageCount(PackageSearchVO packageSearchVO);
    List<ShoplaylistVO> getPackagesBySearch(PackageSearchVO packageSearchVO);
    List<Integer> getProductIdsByPackageIds(List<Integer> packageIds);
    List<ShoplaylistVO> getPackagesByMemberId(int mbrId);
    List<ShoplaylistProductVO> getAllProducts();

    /* --------------------------------------------------
     * 추천 처리 (★ 변경된 부분)
     * -------------------------------------------------- */
    /**
     * 패키지 추천을 처리한다.
     * <p>리턴값</p>
     * <ul>
     *   <li>&gt;=0  : 추천 후 총 추천수</li>
     *   <li>-1     : 존재하지 않는 패키지</li>
     *   <li>-2     : 본인이 생성한 패키지라 추천 불가</li>
     *   <li>-3     : 이미 추천한 패키지(중복 추천)</li>
     * </ul>
     */
    int recommend(int packageId, int mbrId);
    
    List<Integer> getRecommendedPackageIdsByMember(int mbrId);
}