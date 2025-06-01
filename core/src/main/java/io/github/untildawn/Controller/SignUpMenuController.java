package io.github.untildawn.Controller;

import io.github.untildawn.Model.Result;
import io.github.untildawn.Model.SignUpMenuRegex;
import io.github.untildawn.Model.User;
import io.github.untildawn.View.SignUpMenuView;

public class SignUpMenuController {

    private  SignUpMenuView view;

    public void setview(SignUpMenuView temp) {
        this.view = temp;
    }

    public SignUpMenuView getView() {
        return this.view;
    }

    public Result signUpButten() {
        resetFields();
        String username = view.getUsernameText().getText();
        String password = view.getPasswordText().getText();
        String security =  view.getSecurityQuestionText().getText();
        if(username.isEmpty() || password.isEmpty() || security.isEmpty()){
            return new Result("fill all the fields",false);
        }
        User temp = UserDataHandler.getUserByUsername(username);
        if (temp != null) {
            return new Result("this username is taken!",false);
        }
        if (!isPasswordValid(password)) {
            return new Result("Your password should contain 8 chars, lowercase and uppercase letter and special chars",false);
        }
        User user = new User(username, password, security);
        UserDataHandler.addUser(user);
        return new Result("user created",true);
    }


    public Result gotoLogin() {
        resetFields();
        return new Result("login",true);
    }

    public Result enterAsGuest() {
        resetFields();
        return null;
    }
    public boolean isPasswordValid(String pass) {
        if(SignUpMenuRegex.password.getMatcher(pass) == null){
            return false;
        }
        return true;
    }

    private void resetFields() {
        view.getUsernameText().setText("");
        view.getPasswordText().setText("");
        view.getSecurityQuestionText().setText("");
        view.getPasswordErrorLabel().setText("");
        view.getSecurityQuestionErrorLabel().setText("");
        view.getPasswordErrorLabel().setText("");
    }
}
