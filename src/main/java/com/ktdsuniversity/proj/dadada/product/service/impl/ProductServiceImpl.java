package com.ktdsuniversity.proj.dadada.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.proj.dadada.product.dao.ProductDao;
import com.ktdsuniversity.proj.dadada.product.service.ProductService;
import com.ktdsuniversity.proj.dadada.product.vo.ProductSearchRequestVO;
import com.ktdsuniversity.proj.dadada.product.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public boolean createNewProduct(ProductVO productVO) {
        return productDao.insertProduct(productVO) > 0;
    }

    @Override
    public List<ProductVO> getAllProducts() {
        return productDao.selectAllProducts();
    }

    @Override
    public ProductVO getProductById(int productId) {
        return productDao.selectProductById(productId);
    }

    @Override
    public List<ProductVO> getPagedProductList(ProductSearchRequestVO productSearchRequestVO) {
        return productDao.selectPagedProductList(productSearchRequestVO);
    }

    @Override
    public int getProductTotalCount(ProductSearchRequestVO productSearchRequestVO) {
        return productDao.getProductTotalCount(productSearchRequestVO);
    }
    
    @Override
    public boolean deleteProductById(int prdId) {
        return productDao.deleteProductById(prdId) > 0;
    }

	@Override
	public double getProductPrice(int prdId) {
		return productDao.getProductPrice(prdId);
	}

}
