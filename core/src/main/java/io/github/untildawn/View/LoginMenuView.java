package io.github.untildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.untildawn.Controller.LoginMenuCOntroller;
import io.github.untildawn.Model.AppAssetManager;

public class LoginMenuView implements Screen {
    private static LoginMenuView loginMenuView;
    private Stage stage;
    private Skin skin;
    private LoginMenuCOntroller controller;
    private TextField usernameText, passwordText;
    private Label usernameLabel, passwordLabel;
    private TextButton loginButton,backToSignUPButton, forgotPassButton;
    private Label stageLabel;
    private Label userError,passError;
    private LoginMenuView(LoginMenuCOntroller controller) {
        this.controller = controller;
        controller.setView(this);
        this.skin = AppAssetManager.getAssetManager().getSkin();
        this.skin = AppAssetManager.getAssetManager().getSkin();
        this.usernameText = new TextField("", skin);
        this.passwordText = new TextField("", skin);
        this.loginButton = new TextButton("Login", skin);
        this.backToSignUPButton = new TextButton("Sign Up", skin);
        this.forgotPassButton = new TextButton("Forgot Pass", skin);
        this.stageLabel = new Label("Login Menu", skin);
        this.usernameLabel = new Label("Username", skin);
        this.passwordLabel = new Label("Password", skin);
        this.userError = new Label("", skin);
        this.passError = new Label("", skin);
    }
    public static LoginMenuView getInstance() {
        if (loginMenuView == null) {
            loginMenuView = new LoginMenuView(new LoginMenuCOntroller());
            return loginMenuView;
        }
        else{
            return loginMenuView;
        }
    }
    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        setActors();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(v, 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.clear();
        stage.getViewport().update(width, height, true);
        setActors();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void setActors() {
        float xLabel = stage.getWidth() / 30f;
        float xText = stage.getWidth() / 28f;
        float xError = stage.getWidth() / 26f;
        float yPadding = stage.getHeight() / 60f;

        Image image = AppAssetManager.getAssetManager().getImage();
        image.setSize(stage.getWidth(), stage.getHeight());


        stageLabel.setPosition(10, stage.getHeight() - stageLabel.getHeight());

        usernameLabel.setPosition(xLabel, (stage.getHeight() * 17)/20);
        usernameText.setPosition(xText, usernameLabel.getY() - yPadding - usernameText.getHeight());
        usernameText.setSize(stage.getWidth()/4, usernameText.getHeight());
        userError.setPosition(xError, usernameText.getY() - yPadding - userError.getHeight());
        userError.setColor(Color.RED);


        passwordLabel.setPosition(xLabel, (stage.getHeight() * 11 ) / 20);
        passwordText.setPosition(xText, passwordLabel.getY() - yPadding - passwordText.getHeight());
        passwordText.setSize(stage.getWidth()/4, passwordText.getHeight());
        passError.setPosition(xError , passwordText.getY() - yPadding - passError.getHeight());
        passError.setColor(Color.RED);


        backToSignUPButton.setPosition(stage.getWidth()/30, stage.getHeight() / 10);
        forgotPassButton.setPosition((stage.getWidth()* 13)/30, stage.getHeight() / 10);
        loginButton.setPosition((stage.getWidth() * 23)/30, stage.getHeight() / 10);


        stage.addActor(image);
        stage.addActor(loginButton);
        stage.addActor(backToSignUPButton);
        stage.addActor(forgotPassButton);
        stage.addActor(usernameLabel);
        stage.addActor(usernameText);
        stage.addActor(userError);
        stage.addActor(passwordLabel);
        stage.addActor(passwordText);
        stage.addActor(passError);
        stage.addActor(usernameLabel);
        stage.addActor(stageLabel);
    }
}
