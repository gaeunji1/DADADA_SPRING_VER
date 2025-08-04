package com.ktdsuniversity.proj.dadada.chat.vo;

import java.util.Map;

public class RoomResultVO {
    private int roomId;
    private int winnerId;
    private String winnerNick;
    private Integer    winningNumber;
    private String payUrl;
    private Map<String, Integer> submissions;

    public RoomResultVO(
        int roomId,
        int winnerId,
        String winnerNick,
        int winningNumber,
        String payUrl,
        Map<String, Integer> allSubs
    ) {
        this.roomId         = roomId;
        this.winnerId       = winnerId;
        this.winnerNick     = winnerNick;
        this.winningNumber  = winningNumber;
        this.payUrl         = payUrl;
        this.submissions    = allSubs;
    }

    public int getRoomId()        { 
    	return roomId; 
    }
    public int getWinnerId()      { 
    	return winnerId;
    }
    public String getWinnerNick()    {
    	return winnerNick; 
    }
    public Integer getWinningNumber() {
    	return winningNumber; 
    }
    public String getPayUrl()        { 
    	return payUrl; 
    }
    public Map<String, Integer> getSubmissions() {
    	return submissions; 
    }
}