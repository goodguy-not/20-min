package io.github.untildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.untildawn.Controller.AudioManager;
import io.github.untildawn.Controller.ForgotPassMenuController;
import io.github.untildawn.Main;
import io.github.untildawn.Model.AppAssetManager;
import io.github.untildawn.Model.Result;

import javax.swing.event.ChangeListener;

public class ForgotPassMenuView implements Screen {
    private static ForgotPassMenuView instance;

    private Stage stage;
    private Skin skin;
    private ForgotPassMenuController controller;

    private Label stageLabel;
    private Label usernameLabel, passwordLabel;
    private TextField usernameText, passwordText;
    private Label userError, passwordError;
    private Label showPasswordLabel;
    private TextButton goToLogin,show;
    private Image background;


    private ForgotPassMenuView(ForgotPassMenuController controller) {
        this.controller = controller;
        this.skin = AppAssetManager.getAssetManager().getSkin();
        controller.setView(this);
        this.stageLabel = new Label("Forget password", skin);
        this.usernameLabel = new Label("username", skin);
        this.passwordLabel = new Label("your best friend name", skin);
        this.usernameText = new TextField("", skin);
        this.passwordText = new TextField("", skin);
        this.userError = new Label("", skin);
        this.passwordError = new Label("", skin);
        this.goToLogin = new TextButton("Login", skin);
        this.showPasswordLabel = new Label("", skin);
        this.show = new TextButton("confirm", skin);
        this.background = AppAssetManager.getAssetManager().getImage();
    }


    public static ForgotPassMenuView getInstance() {
        if (instance == null) {
            instance = new ForgotPassMenuView(new ForgotPassMenuController());
            return instance;
        }
        return instance;
    }

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        show.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AudioManager.clickSound();
                confirmMethod(controller.confirmButten());
            }
        });
        goToLogin.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AudioManager.clickSound();
                goToLoginMethod(controller.goToLoginButten());
            }
        });
        addActors();
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
        addActors();
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

    private void addActors() {
        stage.addActor(background);
        stage.addActor(stageLabel);
        stage.addActor(usernameText);
        stage.addActor(usernameLabel);
        stage.addActor(passwordLabel);
        stage.addActor(passwordText);
        stage.addActor(passwordError);
        stage.addActor(userError);
        stage.addActor(showPasswordLabel);
        stage.addActor(show);
        stage.addActor(goToLogin);

    }

    private void setActors() {
        float labelX = stage.getWidth() / 40;
        float textX = stage.getWidth() / 36;
        float errorX = stage.getWidth() / 33;
        float yPadding = stage.getHeight() / 35;
        float yOffset = stage.getHeight() / 12;
        float textWidth = stage.getWidth()/4;

        background.setSize(stage.getWidth(), stage.getHeight());

        stageLabel.setPosition(20,stage.getHeight() - stageLabel.getHeight());

        usernameLabel.setPosition(labelX,(stage.getHeight() * 6)/ 7);
        usernameText.setPosition(textX,usernameLabel.getY() - yPadding - usernameText.getHeight());
        usernameText.setWidth(textWidth);
        userError.setPosition(errorX,usernameText.getY() - yPadding - userError.getHeight());
        userError.setColor(Color.RED);

        passwordLabel.setPosition(labelX,userError.getY() - yOffset - passwordLabel.getHeight());
        passwordText.setPosition(textX,passwordLabel.getY() - yPadding - passwordText.getHeight());
        passwordText.setWidth(textWidth);
        passwordError.setPosition(errorX,passwordText.getY() - yPadding - passwordError.getHeight());
        passwordError.setColor(Color.RED);


        showPasswordLabel.setPosition(labelX,passwordError.getY() - yOffset- showPasswordLabel.getHeight());
        showPasswordLabel.setWidth(textWidth);

        goToLogin.setPosition(stage.getWidth()/7, stage.getHeight() / 20);
        show.setPosition((stage.getWidth() * 4)/7, stage.getHeight() / 20);
    }
    private void goToLoginMethod(Result result) {
        if (result.getSuccess()){
            Main.getMain().setScreen(LoginMenuView.getInstance());
        }
    }

    private void confirmMethod(Result result) {
        if (result.getSuccess()){
            showPasswordLabel.setText(result.getMassage());
        }
        if (result.getMassage().equals("fill all the fields")){
            userError.setText("fill all the fields");
        }
        if (result.getMassage().equals("user not found")){
            userError.setText(result.getMassage());
        }
        if (result.getMassage().equals("security question doesn't match")){
            passwordError.setText(result.getMassage());
        }
    }

    public Stage getStage() {
        return stage;
    }

    public ForgotPassMenuController getController() {
        return controller;
    }

    public Skin getSkin() {
        return skin;
    }

    public Label getStageLabel() {
        return stageLabel;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public TextField getUsernameText() {
        return usernameText;
    }

    public TextField getPasswordText() {
        return passwordText;
    }

    public Label getUserError() {
        return userError;
    }

    public Label getPasswordError() {
        return passwordError;
    }

    public Label getShowPasswordLabel() {
        return showPasswordLabel;
    }

    public TextButton getGoToLogin() {
        return goToLogin;
    }

    public TextButton getShow() {
        return show;
    }
}
