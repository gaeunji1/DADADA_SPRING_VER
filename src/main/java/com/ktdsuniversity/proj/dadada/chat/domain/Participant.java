package com.ktdsuniversity.proj.dadada.chat.domain;

public class Participant {
    private final int userId;
    private final String nick;
    private int number;
    private boolean submitted;

    public Participant(int userId, String nick) {
        this.userId    = userId;
        this.nick      = nick;
        this.submitted = false;
    }

    public int getUserId()     { 
    	return userId;    
    }
    public String getNick()       {
    	return nick;
    }
    public int getNumber()    {
    	return number;    
    }
    public void setNumber(int number) {
    	this.number = number;
    }
    public boolean isSubmitted()  {
    	return submitted;
    }
    public void setSubmitted(boolean submitted) {
    	this.submitted = submitted; 
    }
}
