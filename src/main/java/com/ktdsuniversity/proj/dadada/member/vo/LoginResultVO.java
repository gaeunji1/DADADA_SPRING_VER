package com.ktdsuniversity.proj.dadada.member.vo;

public class LoginResultVO {
    private boolean loggedIn;
    private String errorMessage;

    public LoginResultVO() {}

    public LoginResultVO(boolean loggedIn, String errorMessage) {
        this.loggedIn = loggedIn;
        this.errorMessage = errorMessage;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
