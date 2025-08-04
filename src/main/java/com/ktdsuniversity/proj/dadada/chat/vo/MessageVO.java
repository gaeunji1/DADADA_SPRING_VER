package com.ktdsuniversity.proj.dadada.chat.vo;

public class MessageVO {
    private String roomId;
    private int userId;
    private int number;
    private String nickname;

    public MessageVO() { }
    public MessageVO(String roomId, int userId, int number, String nickname) {
        this.roomId   = roomId;
        this.userId   = userId;
        this.number   = number;
        this.nickname = nickname;
    }
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}