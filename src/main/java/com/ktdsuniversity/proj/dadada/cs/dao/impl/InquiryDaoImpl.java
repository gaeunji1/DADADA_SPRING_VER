package com.ktdsuniversity.proj.dadada.cs.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.cs.dao.InquiryDao;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryPaginationVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryUpdateRequestVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryWriteRequestVO;

@Repository
public class InquiryDaoImpl extends SqlSessionDaoSupport implements InquiryDao {

    private static final String NAME_SPACE = "com.ktdsuniversity.proj.dadada.cs.dao.impl.InquiryDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<InquiryVO> selectInquiryList(InquiryPaginationVO paginationVO) {
        return getSqlSession().selectList(NAME_SPACE + "selectInquiryList", paginationVO);
    }

    @Override
    public int selectInquiryCount(InquiryPaginationVO paginationVO) {
        return getSqlSession().selectOne(NAME_SPACE + "selectInquiryCount", paginationVO);
    }

    @Override
    public InquiryVO selectOneInquiry(int inquiryId) {
        return getSqlSession().selectOne(NAME_SPACE + "selectOneInquiry", inquiryId);
    }

    @Override
    public int insertNewInquiry(InquiryWriteRequestVO inquiryWriteRequestVO) {
        return getSqlSession().insert(NAME_SPACE + "insertNewInquiry", inquiryWriteRequestVO);
    }

    @Override
    public int updateInquiryReply(InquiryUpdateRequestVO inquiryUpdateRequestVO) {
        return getSqlSession().update(NAME_SPACE + "updateInquiryReply", inquiryUpdateRequestVO);
    }

    @Override
    public int updateInquiryStatus(int inquiryId, int status) {
        Map<String, Object> params = new HashMap<>();
        params.put("inquiryId", inquiryId);
        params.put("status", status);
        return getSqlSession().update(NAME_SPACE + "updateInquiryStatus", params);
    }

    @Override
    public int updateOneInquiry(InquiryUpdateRequestVO inquiryUpdateRequestVO) {
        return getSqlSession().update(NAME_SPACE + "updateOneInquiry", inquiryUpdateRequestVO);
    }

    @Override
    public int deleteOneInquiry(int inquiryId) {
        return getSqlSession().delete(NAME_SPACE + "deleteOneInquiry", inquiryId);
    }
    
    @Override
    public int countUserInquiries(int userId) {
        return getSqlSession().selectOne(NAME_SPACE + "countUserInquiries", userId);
    }
    
}
