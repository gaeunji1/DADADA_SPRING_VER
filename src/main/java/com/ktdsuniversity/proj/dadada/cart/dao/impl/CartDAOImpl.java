package com.ktdsuniversity.proj.dadada.cart.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.cart.dao.CartDAO;
import com.ktdsuniversity.proj.dadada.cart.vo.CartVO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

@Repository
public class CartDAOImpl implements CartDAO {

    private static final String NAMESPACE = "com.ktdsuniversity.proj.dadada.cart.dao.CartDAO";

    @Autowired
    private SqlSession sqlSession;

    @Override
	public int findCartIdByMbrId(int mbrId) {
		return sqlSession.selectOne(NAMESPACE + ".findCartIdByMbrId", mbrId);
	}

    @Override
    public int createCart(int mbrId) {
        return sqlSession.insert(NAMESPACE + ".createCart", mbrId);
    }

    @Override
    public int countCartProduct(CartVO cartVO) {
        return sqlSession.selectOne(NAMESPACE + ".countCartProduct", cartVO);
    }

    @Override
    public int insertCartProduct(CartVO cartVO) {
        return sqlSession.insert(NAMESPACE + ".insertCartProduct", cartVO);
    }

    @Override
    public int updateCartProductQty(CartVO cartVO) {
        return sqlSession.update(NAMESPACE + ".updateCartProductQty", cartVO);
    }

    @Override
    public List<CartVO> selectCartProductsByCartId(int cartId) {
        return this.sqlSession.selectList(NAMESPACE + ".selectCartProductsByCartId",cartId);
    }

	@Override
	public int deleteCartProduct(CartVO cartVO) {
		return sqlSession.delete(NAMESPACE + ".deleteCartProduct", cartVO);
	}

	@Override
	public int setCartProductQty(CartVO cartVO) {
		return sqlSession.update(NAMESPACE + ".setCartProductQty", cartVO);	}

    @Override
    public int deleteAllCartProducts(int cartId) {
        return sqlSession.delete(NAMESPACE + ".deleteAllCartProducts", cartId);
    }
}
