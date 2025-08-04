package com.ktdsuniversity.proj.dadada.product.dao;

import java.util.List;

import com.ktdsuniversity.proj.dadada.product.vo.ProductQtyUpdateVO;
import com.ktdsuniversity.proj.dadada.product.vo.ProductSearchRequestVO;
import com.ktdsuniversity.proj.dadada.product.vo.ProductVO;

public interface ProductDao {

    public int insertProduct(ProductVO productVO);

    public List<ProductVO> selectAllProducts();

    public ProductVO selectProductById(int productId);

    public List<ProductVO> selectPagedProductList(ProductSearchRequestVO productSearchRequestVO);

    public int getProductTotalCount(ProductSearchRequestVO productSearchRequestVO);
    
    int deleteProductById(int prdId);
    
    public void decreaseProductQty(ProductQtyUpdateVO stock);
    
    public double getProductPrice(int prdId);
}
