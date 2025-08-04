package com.ktdsuniversity.proj.dadada.chat.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.chat.dao.ChatRoomDAO;
import com.ktdsuniversity.proj.dadada.chat.vo.ChatRoomParticipantVO;
import com.ktdsuniversity.proj.dadada.chat.vo.ChatRoomVO;

@Repository
public class ChatRoomDAOImpl implements ChatRoomDAO {
    private static final String NS =
      "com.ktdsuniversity.proj.dadada.chat.mapper.ChatRoomMapper.";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertRoom(ChatRoomVO room) {
        sqlSession.insert(NS + "insertRoom", room);
    }

    @Override
    public void insertParticipant(ChatRoomParticipantVO vo) {
        Map<String,Object> param = new HashMap<>();
        param.put("roomId", vo.getRoomId());
        param.put("userId", vo.getUserId());
        param.put("nick",   vo.getNick());
        sqlSession.insert(NS + "insertParticipant", param);
    }
}