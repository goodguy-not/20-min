package io.github.untildawn.Controller;

import io.github.untildawn.Model.Result;
import io.github.untildawn.Model.User;
import io.github.untildawn.View.ForgotPassMenuView;

public class ForgotPassMenuController {
    private ForgotPassMenuView view;

    public ForgotPassMenuController() {}
    public void setView(ForgotPassMenuView view) {
        this.view = view;
    }

    public Result confirmButten() {
        String usernaem = view.getUsernameText().getText();
        String security = view.getPasswordText().getText();
        resetFields();
        if(usernaem.isEmpty() || security.isEmpty()) {
            return new Result("fill all the fields",false);
        }
        User user = UserDataHandler.getUserByUsername(usernaem);
        if(user == null) {
            return new Result("user not found",false);
        }
        if (!security.equals(user.getSecurity())) {
            return new Result("security question doesn't match",false);
        }
        return new Result( "your password : " + user.getPassword(),true);
    }


    public Result goToLoginButten() {
        resetFields();
        return new Result("ok",true);
    }

    private void resetFields() {
        view.getUsernameText().setText("");
        view.getPasswordText().setText("");
        view.getPasswordError().setText("");
        view.getUserError().setText("");
    }

}
