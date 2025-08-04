package com.ktdsuniversity.proj.dadada.product.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.beans.CustomBeanProvider;
import com.ktdsuniversity.proj.dadada.product.dao.ProductDao;
import com.ktdsuniversity.proj.dadada.product.vo.ProductQtyUpdateVO;
import com.ktdsuniversity.proj.dadada.product.vo.ProductSearchRequestVO;
import com.ktdsuniversity.proj.dadada.product.vo.ProductVO;

@Repository
public class ProductDaoImpl extends SqlSessionDaoSupport implements ProductDao {

    private final CustomBeanProvider customBeanProvider;

    private static final String NAME_SPACE = "com.ktdsuniversity.proj.dadada.product.dao.ProductDao.";

    ProductDaoImpl(CustomBeanProvider customBeanProvider) {
        this.customBeanProvider = customBeanProvider;
    }

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insertProduct(ProductVO productVO) {
        return getSqlSession().insert(NAME_SPACE + "insertProduct", productVO);
    }

    @Override
    public List<ProductVO> selectAllProducts() {
        return getSqlSession().selectList(NAME_SPACE + "selectAllProducts");
    }

    @Override
    public ProductVO selectProductById(int productId) {
        return getSqlSession().selectOne(NAME_SPACE + "selectProductById", productId);
    }

    @Override
    public List<ProductVO> selectPagedProductList(ProductSearchRequestVO productSearchRequestVO) {
        return getSqlSession().selectList(NAME_SPACE + "selectPagedProductList", productSearchRequestVO);
    }

    @Override
    public int getProductTotalCount(ProductSearchRequestVO productSearchRequestVO) {
        return getSqlSession().selectOne(NAME_SPACE + "getProductTotalCount", productSearchRequestVO);
    }
    
    @Override
    public int deleteProductById(int prdId) {
        return getSqlSession().delete(NAME_SPACE + "deleteProductById", prdId);
    }
    
    @Override
    public void decreaseProductQty(ProductQtyUpdateVO stock) {
        getSqlSession().update(NAME_SPACE + "decreaseProductQty", stock);
    }

	@Override
	public double getProductPrice(int prdId) {
		Double price = getSqlSession().selectOne(NAME_SPACE + "getProductPrice", prdId);
		return price != null ? price : 0.0;
	} 
}
