package com.ktdsuniversity.proj.dadada.shoplaylist.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.shoplaylist.dao.ShoplaylistDao;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.DeleteProductRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.PackageSearchVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistProductVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistVO;

@Repository
public class ShoplaylistDaoImpl extends SqlSessionDaoSupport implements ShoplaylistDao {

	private static final String NAME_SPACE = "com.ktdsuniversity.proj.dadada.shoplaylist.dao.ShoplaylistDao.";
	 
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int getNextPackageId() {
		return this.getSqlSession().selectOne(NAME_SPACE + "getNextPackageId");
	}

	@Override
	public int insertPackage(ShoplaylistVO shoplaylistVO) {
		return this.getSqlSession().insert(NAME_SPACE + "insertPackage", shoplaylistVO);
	}

	@Override
	public int insertProductToPackage(ShoplaylistProductVO shoplaylistProductVO) {
		return this.getSqlSession().insert(NAME_SPACE + "insertProductToPackage", shoplaylistProductVO);
	}

	@Override
	public List<ShoplaylistVO> selectPackagesByCreator(String creatorType) {
		return this.getSqlSession().selectList(NAME_SPACE + "selectPackagesByCreator", creatorType);
	}

	@Override
	public int deletePackage(int packageId) {
		return this.getSqlSession().delete(NAME_SPACE + "deletePackage", packageId);
	}

	@Override
	public int deleteProductFromPackage(DeleteProductRequestVO deleteProductRequestVO) {
		return this.getSqlSession().delete(NAME_SPACE + "deleteProductFromPackage", deleteProductRequestVO);
	}

	@Override
	public List<ShoplaylistProductVO> selectAllProducts() {
		return this.getSqlSession().selectList(NAME_SPACE + "selectAllProducts");
	}

	@Override
	public ShoplaylistVO selectPackageDetail(int packageId) {
		return this.getSqlSession().selectOne(NAME_SPACE + "selectPackageDetail", packageId);
	}

	@Override
	public List<ShoplaylistProductVO> selectProductsByPackageId(int packageId) {
		return this.getSqlSession().selectList(NAME_SPACE + "selectProductsByPackageId", packageId);
	}

	@Override
	public int deleteProductsByPackageId(int packageId) {
		return this.getSqlSession().delete(NAME_SPACE + "deleteProductsByPackageId", packageId);
	}

	@Override
	public int getPackageCount(PackageSearchVO packageSearchVO) {
		return this.getSqlSession().selectOne(NAME_SPACE + "getPackageCount", packageSearchVO);
	}

	@Override
	public List<ShoplaylistVO> selectPackagesBySearch(PackageSearchVO packageSearchVO) {
		return this.getSqlSession().selectList(NAME_SPACE + "selectPackagesBySearch", packageSearchVO);
	}

	@Override
	public List<Integer> selectProductIdsByPackageIds(List<Integer> packageIds) {
		return this.getSqlSession().selectList(NAME_SPACE + "selectProductIdsByPackageIds", packageIds);
	}


	@Override
	public List<ShoplaylistVO> selectPackagesByMemberId(int mbrId) {
		return this.getSqlSession().selectList(NAME_SPACE + "selectPackagesByMemberId", mbrId);
	}
    @Override
    public int existsRecommend(Map<String,Integer> param) {
        return getSqlSession().selectOne(
                NAME_SPACE + "existsRecommend", param);
    }

    @Override
    public void insertRecommend(Map<String,Integer> param) {
         getSqlSession().insert(
                NAME_SPACE + "insertRecommend", param);
    }

    @Override
    public void plusRecommendCnt(int packageId) {
         getSqlSession().update(
                NAME_SPACE + "plusRecommendCnt", packageId);
    }

    @Override
    public List<Integer> selectRecommendedPackageIdsByMember(int mbrId) {
        // TODO 채워주기
        return this.getSqlSession()
                   .selectList(NAME_SPACE + "selectRecommendedPackageIdsByMember", mbrId);
    }


}
