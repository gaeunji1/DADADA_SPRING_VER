package com.ktdsuniversity.proj.dadada.chat.service;

import com.ktdsuniversity.proj.dadada.chat.domain.ChatRoom;
import com.ktdsuniversity.proj.dadada.chat.domain.Participant;
import com.ktdsuniversity.proj.dadada.chat.vo.ChatRoomVO;
import com.ktdsuniversity.proj.dadada.chat.vo.MessageVO;

public interface RoomService {
    ChatRoomVO createRoom(int productId);

    boolean enter(int roomId, Participant p);

    void submit(MessageVO msg);
    
    void updateParticipantRank(int eventRoomId, int memberId, int rank);

    public ChatRoom getChatRoom(int roomId);
    
    public ChatRoom findAvailableRoom(int productId);
}