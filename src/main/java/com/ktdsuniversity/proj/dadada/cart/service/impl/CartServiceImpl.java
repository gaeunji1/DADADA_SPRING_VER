package com.ktdsuniversity.proj.dadada.cart.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.proj.dadada.cart.dao.CartDAO;
import com.ktdsuniversity.proj.dadada.cart.service.CartService;
import com.ktdsuniversity.proj.dadada.cart.vo.CartVO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;

	@Override
	public void addOrUpdateCart(CartVO cartVO) {
		int cartId = cartDAO.findCartIdByMbrId(cartVO.getMbrId());

		if (cartId == 0) { 
			cartDAO.createCart(cartVO.getMbrId());
			cartId = cartDAO.findCartIdByMbrId(cartVO.getMbrId());
		}

		cartVO.setCartId(cartId);

		int existingCount = cartDAO.countCartProduct(cartVO);

		if (existingCount == 0) {
			cartDAO.insertCartProduct(cartVO);
		} else {
			cartDAO.updateCartProductQty(cartVO);
		}
	}

	@Override
	public void setCartProductQty(CartVO cartVO) {
		int cartId = cartDAO.findCartIdByMbrId(cartVO.getMbrId());
		if (cartId == 0) {
			cartDAO.createCart(cartVO.getMbrId());
			cartId = cartDAO.findCartIdByMbrId(cartVO.getMbrId());
		}
		cartVO.setCartId(cartId);
		cartDAO.setCartProductQty(cartVO);
	}

	@Override
	public List<CartVO> getCartProductListByMbrId(int mbrId) {
		int cartId = cartDAO.findCartIdByMbrId(mbrId);
		return cartDAO.selectCartProductsByCartId(cartId);
	}

	@Override
	public int findCartIdByMbrId(int mbrId) {
		return cartDAO.findCartIdByMbrId(mbrId);
	}

	@Override
	public List<CartVO> selectCartProductsByCartId(int cartId) {
		return cartDAO.selectCartProductsByCartId(cartId);
	}

	@Override
	public void deleteCartProduct(CartVO cartVO) {
		cartDAO.deleteCartProduct(cartVO);
	}
	
	 @Override
	    public void clearCartByMemberId(int mbrId) {
	        int cartId = cartDAO.findCartIdByMbrId(mbrId);
	        if (cartId != 0) {
	            cartDAO.deleteAllCartProducts(cartId);
	        }
	    }
}
