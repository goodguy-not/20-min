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
import io.github.untildawn.Controller.LoginMenuCOntroller;
import io.github.untildawn.Controller.SignUpMenuController;
import io.github.untildawn.Main;
import io.github.untildawn.Model.AppAssetManager;
import io.github.untildawn.Model.Result;

public class SignUpMenuView implements Screen {
    private static SignUpMenuView signUpMenuView;

    private Stage stage;
    private Skin skin;
    private  SignUpMenuController controller;
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


    public static SignUpMenuView getInstance(){
        if(signUpMenuView == null){
            signUpMenuView = new SignUpMenuView(new SignUpMenuController());
            return signUpMenuView;
        }
        else{
            return signUpMenuView;
        }
    }


    private SignUpMenuView(SignUpMenuController signUpMenuController) {
        this.skin = AppAssetManager.getAssetManager().getSkin();
        this.controller = signUpMenuController;
        this.controller.setview(this);
        this.stageLabel = new Label("Sign Up Menu", skin);
        this.usernameText = new TextField("", skin);
        this.passwordText = new TextField("", skin);
        this.securityQuestionText = new TextField("", skin);
        this.usernameLabel = new Label("Username:", skin);
        this.usernameErrorLabel = new Label("", skin);
        this.passwordLabel = new Label("Password:", skin);
        this.passwordErrorLabel = new Label("", skin);
        this.securityQuestionLabel = new Label("Your best friend name:", skin);
        this.securityQuestionErrorLabel = new Label("", skin);
        this.signUpButton = new TextButton("Sign Up", skin);
        this.goToLoginButton = new TextButton("Login", skin);
        this.enterAsGuestButton = new TextButton("Guest", skin);
        this.defaultAvatartexture = new Texture(Gdx.files.internal("Images/Avatar/T_Shana_Portrait.png"));
        this.defaultAvatarSprite = new Sprite(defaultAvatartexture);
        this.defaultAvatarLabel = new Label("default avatar", skin);
    }
    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
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
        // === Positioning UI elements ===
        usernameLabel.setPosition(xlabel, stage.getHeight()/20 * 18);
        usernameText.setPosition(xTextLabel, usernameLabel.getY()  - yPadding - usernameText.getHeight());
        usernameText.setWidth(stage.getWidth()/4);
        // Add avatar image
        Image avatarImage = new Image(defaultAvatarSprite);
        avatarImage.setSize(stage.getWidth()/8, stage.getHeight()/5);
        avatarImage.setPosition(stage.getWidth() - avatarImage.getWidth() - stage.getWidth()/10, stage.getHeight() - avatarImage.getHeight() - stage.getHeight()/10);
        stage.addActor(avatarImage);
        defaultAvatarLabel.setPosition(avatarImage.getX(), avatarImage.getY() -  yPadding - defaultAvatarLabel.getHeight());
        defaultAvatarLabel.setWidth(avatarImage.getWidth());

        // Add the username error label next to the avatar
        usernameErrorLabel.setPosition(xTextLabel, usernameText.getY() - stage.getHeight() / 40 );
        usernameErrorLabel.setColor(Color.RED);


        // === Password Fields ===
        passwordLabel.setPosition(xlabel, stage.getHeight()/20 * 13);
        passwordText.setPosition(xTextLabel, passwordLabel.getY() - yPadding - passwordText.getHeight());
        passwordText.setWidth(stage.getWidth()/4);
        passwordErrorLabel.setPosition(xTextLabel, passwordText.getY() - stage.getHeight() / 40);
        passwordErrorLabel.setColor(Color.RED);

        // === Security Question ===
        securityQuestionLabel.setPosition(xlabel, stage.getHeight()/20 * 8);
        securityQuestionText.setPosition(xTextLabel, securityQuestionLabel.getY() - yPadding - securityQuestionText.getHeight());
        securityQuestionText.setWidth(stage.getWidth()/4);
        securityQuestionErrorLabel.setPosition(xTextLabel, securityQuestionText.getY() - securityQuestionErrorLabel.getHeight()* 5);
        securityQuestionErrorLabel.setColor(Color.RED);

        // === Bottom Buttons ===
        enterAsGuestButton.setPosition(xTextLabel, stage.getHeight()/30);  // Bottom-left
        goToLoginButton.setPosition(stage.getWidth()/6 * 2, stage.getHeight()/30);  // To the right of the first button
        signUpButton.setPosition(stage.getWidth()/6 * 4, stage.getHeight()/30);  // To the right of the second button
        stage.addActor(stageLabel);
        stage.addActor(usernameText);
        stage.addActor(usernameLabel);
        stage.addActor(usernameErrorLabel);
        stage.addActor(defaultAvatarLabel);
        stage.addActor(passwordLabel);
        stage.addActor(passwordText);
        stage.addActor(passwordErrorLabel);
        stage.addActor(securityQuestionLabel);
        stage.addActor(securityQuestionText);
        stage.addActor(securityQuestionErrorLabel);
        stage.addActor(enterAsGuestButton);
        stage.addActor(goToLoginButton);
        stage.addActor(signUpButton);

    }
    private void signUpButtonMethod(Result result) {
        if (result.getSuccess()){
            Main.getMain().setScreen(LoginMenuView.getInstance());
        }
        else if(result.getMassage().equals("fill all the fields")){
            usernameErrorLabel.setText("Fill all the fields");
        }
        else if(result.getMassage().equals("this username is taken!")){
            usernameErrorLabel.setText("this username is taken!");
        }
        else if (result.getMassage().equals("Your password should contain 8 chars, lowercase and uppercase letter and special chars")){
            passwordErrorLabel.setText("at least 8 chars, lowercase, uppercase and special chars");
        }
    }

    private void goToLoginButtonMethod(Result result) {
        Main.getMain().setScreen(LoginMenuView.getInstance());
    }

    private void enterAsGuestButtonMethod(Result result) {

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public SignUpMenuController getController() {
        return controller;
    }

    public void setController(SignUpMenuController controller) {
        this.controller = controller;
    }

    public Label getStageLabel() {
        return stageLabel;
    }

    public void setStageLabel(Label stageLabel) {
        this.stageLabel = stageLabel;
    }

    public TextField getUsernameText() {
        return usernameText;
    }

    public void setUsernameText(TextField usernameText) {
        this.usernameText = usernameText;
    }

    public TextField getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(TextField passwordText) {
        this.passwordText = passwordText;
    }

    public TextField getSecurityQuestionText() {
        return securityQuestionText;
    }

    public void setSecurityQuestionText(TextField securityQuestionText) {
        this.securityQuestionText = securityQuestionText;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public Label getUsernameErrorLabel() {
        return usernameErrorLabel;
    }

    public void setUsernameErrorLabel(Label usernameErrorLabel) {
        this.usernameErrorLabel = usernameErrorLabel;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(Label passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public Label getPasswordErrorLabel() {
        return passwordErrorLabel;
    }

    public void setPasswordErrorLabel(Label passwordErrorLabel) {
        this.passwordErrorLabel = passwordErrorLabel;
    }

    public Label getSecurityQuestionLabel() {
        return securityQuestionLabel;
    }

    public void setSecurityQuestionLabel(Label securityQuestionLabel) {
        this.securityQuestionLabel = securityQuestionLabel;
    }

    public Label getSecurityQuestionErrorLabel() {
        return securityQuestionErrorLabel;
    }

    public void setSecurityQuestionErrorLabel(Label securityQuestionErrorLabel) {
        this.securityQuestionErrorLabel = securityQuestionErrorLabel;
    }

    public TextButton getSignUpButton() {
        return signUpButton;
    }

    public void setSignUpButton(TextButton signUpButton) {
        this.signUpButton = signUpButton;
    }

    public TextButton getGoToLoginButton() {
        return goToLoginButton;
    }

    public void setGoToLoginButton(TextButton goToLoginButton) {
        this.goToLoginButton = goToLoginButton;
    }

    public TextButton getEnterAsGuestButton() {
        return enterAsGuestButton;
    }

    public void setEnterAsGuestButton(TextButton enterAsGuestButton) {
        this.enterAsGuestButton = enterAsGuestButton;
    }

    public Texture getDefaultAvatartexture() {
        return defaultAvatartexture;
    }

    public void setDefaultAvatartexture(Texture defaultAvatartexture) {
        this.defaultAvatartexture = defaultAvatartexture;
    }

    public Sprite getDefaultAvatarSprite() {
        return defaultAvatarSprite;
    }

    public void setDefaultAvatarSprite(Sprite defaultAvatarSprite) {
        this.defaultAvatarSprite = defaultAvatarSprite;
    }

    public Label getDefaultAvatarLabel() {
        return defaultAvatarLabel;
    }

    public void setDefaultAvatarLabel(Label defaultAvatarLabel) {
        this.defaultAvatarLabel = defaultAvatarLabel;
    }
}
