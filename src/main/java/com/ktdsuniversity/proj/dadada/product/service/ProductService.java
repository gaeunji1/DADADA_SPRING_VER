package com.ktdsuniversity.proj.dadada.product.service;

import java.util.List;

import com.ktdsuniversity.proj.dadada.product.vo.ProductSearchRequestVO;
import com.ktdsuniversity.proj.dadada.product.vo.ProductVO;

public interface ProductService {

    public boolean createNewProduct(ProductVO productVO);

    public List<ProductVO> getAllProducts();

    public ProductVO getProductById(int productId);

    public List<ProductVO> getPagedProductList(ProductSearchRequestVO productSearchRequestVO);

    public int getProductTotalCount(ProductSearchRequestVO productSearchRequestVO);
    
    boolean deleteProductById(int prdId);
    
    public double getProductPrice(int prdId);
}
