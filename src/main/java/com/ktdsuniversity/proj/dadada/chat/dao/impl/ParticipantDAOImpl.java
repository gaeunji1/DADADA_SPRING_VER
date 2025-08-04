package com.ktdsuniversity.proj.dadada.chat.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ktdsuniversity.proj.dadada.chat.dao.ParticipantDAO;
import com.ktdsuniversity.proj.dadada.chat.vo.ParticipantRankVO;
import com.ktdsuniversity.proj.dadada.chat.vo.ParticipantVO;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {
    private static final String NS =
      "com.ktdsuniversity.proj.dadada.chat.dao.ParticipantDAO.";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertParticipant(ParticipantVO vo) {
        sqlSession.insert(NS + "insertParticipant", vo);
    }

    @Override
    public void updateParticipantRank(ParticipantRankVO rankVo) {
        sqlSession.update(NS + "updateParticipantRank", rankVo);
    }
}
