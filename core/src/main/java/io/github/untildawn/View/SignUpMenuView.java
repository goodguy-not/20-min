package io.github.untildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.untildawn.Controller.AudioManager;
import io.github.untildawn.Controller.SignUpMenuController;
import io.github.untildawn.Model.AppAssetManager;
import io.github.untildawn.Model.Result;

public class SignUpMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private SignUpMenuController controller;
    // UI elements
    private Label stageLabel;
    private TextField usernameText, passwordText, securityQuestionText;
    private Label usernameLabel, usernameErrorLabel;
    private Label passwordLabel, passwordErrorLabel;
    private Label securityQuestionLabel, securityQuestionErrorLabel;
    private TextButton signUpButton, goToLoginButton, enterAsGuestButton;
    private Texture defaultAvatartexture;
    private Sprite defaultAvatarSprite;
    private Label defaultAvatarLabel;

    public SignUpMenuView(SignUpMenuController signUpMenuController) {
        this.skin = AppAssetManager.getAssetManager().getSkin();
        this.controller = signUpMenuController;
        controller.setview(this);
        stageLabel = new Label("Sign Up Menu", skin);
        usernameText = new TextField("", skin);
        passwordText = new TextField("", skin);
        securityQuestionText = new TextField("", skin);
        usernameLabel = new Label("Username:", skin);
        usernameErrorLabel = new Label("", skin);
        passwordLabel = new Label("Password:", skin);
        passwordErrorLabel = new Label("", skin);
        securityQuestionLabel = new Label("Your best friend name:", skin);
        securityQuestionErrorLabel = new Label("", skin);
        signUpButton = new TextButton("Sign Up", skin);
        goToLoginButton = new TextButton("Login", skin);
        enterAsGuestButton = new TextButton("Guest", skin);
        defaultAvatartexture = new Texture(Gdx.files.internal("Images/Avatar/T_Shana_Portrait.png"));
        defaultAvatarSprite = new Sprite(defaultAvatartexture);
        defaultAvatarLabel = new Label("default avatar", skin);
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        AudioManager.getInstance().playBackgroundMusic(AppAssetManager.getAssetManager().music2);
        signUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AudioManager.clickSound();
                signUpButtonMethod(controller.signUpButten());
            }
        });
        goToLoginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AudioManager.clickSound();
                goToLoginButtonMethod(controller.gotoLogin());
            }
        });
        enterAsGuestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AudioManager.clickSound();
                enterAsGuestButtonMethod(controller.enterAsGuest());
            }
        });
        setActors();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Adjust positions of actors instead of clearing the stage
        stage.clear();
        stage.getViewport().update(width, height, true);
        setActors();
        // Adjust positions of all UI elements after resizing
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void setActors() {
        float xlabel = stage.getWidth()/30;
        float xTextLabel = stage.getWidth()/20;
        float yPadding = stage.getHeight()/100;
        Image image = AppAssetManager.getAssetManager().getImage();
        image.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(image);
        // === Add elements directly to the stage ===
        stageLabel.setPosition(20, stage.getHeight() - stageLabel.getHeight());  // Top-left
        stage.addActor(stageLabel);  // Add Stage label
        // === Positioning UI elements ===
        usernameLabel.setPosition(xlabel, stage.getHeight()/20 * 18);
        usernameText.setPosition(xTextLabel, usernameLabel.getY()  - yPadding - usernameText.getHeight());
        usernameText.setWidth(stage.getWidth()/4);
        stage.addActor(usernameText);
        stage.addActor(usernameLabel);
        // Add avatar image
        Image avatarImage = new Image(defaultAvatarSprite);
        avatarImage.setSize(stage.getWidth()/8, stage.getHeight()/5);
        avatarImage.setPosition(stage.getWidth() - avatarImage.getWidth() - stage.getWidth()/10, stage.getHeight() - avatarImage.getHeight() - stage.getHeight()/10);
        stage.addActor(avatarImage);
        defaultAvatarLabel.setPosition(avatarImage.getX(), avatarImage.getY() -  yPadding - defaultAvatarLabel.getHeight());
        defaultAvatarLabel.setWidth(avatarImage.getWidth());
        stage.addActor(defaultAvatarLabel);

        // Add the username error label next to the avatar
        usernameErrorLabel.setPosition(xTextLabel, usernameText.getY() - usernameErrorLabel.getHeight());
        usernameErrorLabel.setColor(Color.RED);
        stage.addActor(usernameErrorLabel);

        // === Password Fields ===
        passwordLabel.setPosition(xlabel, stage.getHeight()/20 * 13);
        passwordText.setPosition(xTextLabel, passwordLabel.getY() - yPadding - passwordText.getHeight());
        passwordText.setWidth(stage.getWidth()/4);
        passwordErrorLabel.setPosition(xTextLabel, passwordText.getY() - passwordErrorLabel.getHeight());
        passwordErrorLabel.setColor(Color.RED);
        stage.addActor(passwordLabel);
        stage.addActor(passwordErrorLabel);
        stage.addActor(passwordText);

        // === Security Question ===
        securityQuestionLabel.setPosition(xlabel, stage.getHeight()/20 * 8);
        securityQuestionText.setPosition(xTextLabel, securityQuestionLabel.getY() - yPadding - securityQuestionText.getHeight());
        securityQuestionText.setWidth(stage.getWidth()/4);
        securityQuestionErrorLabel.setPosition(xTextLabel, securityQuestionText.getY() - securityQuestionErrorLabel.getHeight());
        securityQuestionErrorLabel.setColor(Color.RED);
        stage.addActor(securityQuestionLabel);
        stage.addActor(securityQuestionErrorLabel);
        stage.addActor(securityQuestionText);

        // === Bottom Buttons ===
        enterAsGuestButton.setPosition(xTextLabel, stage.getHeight()/30);  // Bottom-left
        goToLoginButton.setPosition(stage.getWidth()/6 * 2, stage.getHeight()/30);  // To the right of the first button
        signUpButton.setPosition(stage.getWidth()/6 * 4, stage.getHeight()/30);  // To the right of the second button

        stage.addActor(enterAsGuestButton);
        stage.addActor(goToLoginButton);
        stage.addActor(signUpButton);
    }
    private void signUpButtonMethod(Result result) {

    }

    private void goToLoginButtonMethod(Result result) {

    }

    private void enterAsGuestButtonMethod(Result result) {

    }

}
