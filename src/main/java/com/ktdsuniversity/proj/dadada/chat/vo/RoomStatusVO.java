package com.ktdsuniversity.proj.dadada.chat.vo;

import java.util.List;

public class RoomStatusVO {
    private String roomId;
    private String status;       // WAITING, CLOSING, ACTIVE
    private int currentCount;
    private long remainMillis;   // 남은 시간(ms)
    private List<String> participants;

    public RoomStatusVO(String roomId,
                        String status,
                        int currentCount,
                        long remainMillis,
                        List<String> participants) {
        this.roomId        = roomId;
        this.status        = status;
        this.currentCount  = currentCount;
        this.remainMillis  = remainMillis;
        this.participants  = participants;
    }

    public String getRoomId() {
    	return roomId;
    }
    public String getStatus() {
    	return status; 
    }
    public int    getCurrentCount() {
    	return currentCount; 
    }
    public long   getRemainMillis() {
    	return remainMillis; 
    }
    public List<String> getParticipants() {
    	return participants; 
    }
}