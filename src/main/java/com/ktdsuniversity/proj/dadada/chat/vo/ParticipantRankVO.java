package com.ktdsuniversity.proj.dadada.chat.vo;

public class ParticipantRankVO {
    private int eventRoomId;
    private int memberId;
    private int rank;

    public ParticipantRankVO() {}

    public ParticipantRankVO(int eventRoomId, int memberId, int rank) {
        this.eventRoomId = eventRoomId;
        this.memberId    = memberId;
        this.rank        = rank;
    }

	public int getEventRoomId() {
		return eventRoomId;
	}

	public void setEventRoomId(int eventRoomId) {
		this.eventRoomId = eventRoomId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}