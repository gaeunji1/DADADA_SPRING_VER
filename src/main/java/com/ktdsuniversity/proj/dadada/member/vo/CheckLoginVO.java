package com.ktdsuniversity.proj.dadada.member.vo;

public class CheckLoginVO {

    private boolean loggedIn;

    public CheckLoginVO() {
    }

    public CheckLoginVO(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
}
