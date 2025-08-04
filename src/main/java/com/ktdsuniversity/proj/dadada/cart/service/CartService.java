package com.ktdsuniversity.proj.dadada.cart.service;

import java.util.List;
import com.ktdsuniversity.proj.dadada.cart.vo.CartVO;

public interface CartService {
	void addOrUpdateCart(CartVO cartVO);
	void setCartProductQty(CartVO cartVO);
	List<CartVO> getCartProductListByMbrId(int mbrId);
	int findCartIdByMbrId(int mbrId);
	List<CartVO> selectCartProductsByCartId(int cartId);
	void deleteCartProduct(CartVO cartVO);
	void clearCartByMemberId(int mbrId);
}