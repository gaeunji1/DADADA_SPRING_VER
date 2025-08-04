// ChatRoomDAO.java
package com.ktdsuniversity.proj.dadada.chat.dao;

import org.springframework.stereotype.Repository;
import com.ktdsuniversity.proj.dadada.chat.vo.ChatRoomVO;
import com.ktdsuniversity.proj.dadada.chat.vo.ChatRoomParticipantVO;

@Repository
public interface ChatRoomDAO {
    void insertRoom(ChatRoomVO room);

    /**
     * 방에 참가자 추가
     * @param participantVO 방ID, 회원ID, 닉네임을 담은 VO
     */
    void insertParticipant(ChatRoomParticipantVO participantVO);
}