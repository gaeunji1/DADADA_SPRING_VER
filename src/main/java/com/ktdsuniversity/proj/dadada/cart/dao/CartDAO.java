package com.ktdsuniversity.proj.dadada.cart.dao;

import java.util.List;

import com.ktdsuniversity.proj.dadada.cart.vo.CartVO;

public interface CartDAO {
	int findCartIdByMbrId(int mbrId);
	int createCart(int mbrId);
	int countCartProduct(CartVO cartVO);
	int insertCartProduct(CartVO cartVO);
	int updateCartProductQty(CartVO cartVO);
	int deleteCartProduct(CartVO cartVO);
	int setCartProductQty(CartVO cartVO);

	List<CartVO> selectCartProductsByCartId(int cartId);
	int deleteAllCartProducts(int cartId);




}
