package com.ktdsuniversity.proj.dadada.chat.vo;

public class ChatRoomParticipantVO {
    private int roomId;
    private int    userId;
    private String nick;

    public ChatRoomParticipantVO() {}

    public ChatRoomParticipantVO(int roomId, int userId, String nick) {
        this.roomId = roomId;
        this.userId = userId;
        this.nick   = nick;
    }

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}