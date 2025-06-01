package io.github.untildawn.Controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.untildawn.Model.MyGame;
import io.github.untildawn.Model.Result;
import io.github.untildawn.Model.User;
import io.github.untildawn.View.LoginMenuView;
import io.github.untildawn.View.SignUpMenuView;

public class LoginMenuCOntroller {
    private LoginMenuView view;

    public LoginMenuView getView() {
        return view;
    }

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public Result backToSignUp() {
        resetFields();
        return new Result("ok",true);
    }

    public Result forgotPass() {
        resetFields();
        return new Result("ok",true);
    }

    public Result login() {
        String username = view.getUsernameText().getText();
        String password = view.getPasswordText().getText();
        resetFields();
        User user = UserDataHandler.getUserByUsername(username);
        if (username.isEmpty() || password.isEmpty()) {
            return new Result("fill all the fields",false);
        }
        if (user == null) {
            return new Result("user not found",false);
        }
        if (!password.equals(user.getPassword())) {
            return new Result("wrong password",false);
        }
        new MyGame(user);
        return new Result("ok",true);
    }

    public void resetFields() {;
        view.getUsernameText().setText("");
        view.getPasswordText().setText("");
        view.getUserError().setText("");
        view.getPassError().setText("");
    }
}
