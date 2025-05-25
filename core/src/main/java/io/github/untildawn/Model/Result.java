package io.github.untildawn.Model;

public class Result {
    String massage;
    Boolean isSuccess;
    public Result(String massage, Boolean isSuccess) {
        this.massage = massage;
        this.isSuccess = isSuccess;
    }
    public String getMassage() {
        return massage;
    }
    public Boolean getSuccess() {
        return isSuccess;
    }
}
