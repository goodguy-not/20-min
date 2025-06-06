package io.github.untildawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.untildawn.Controller.SignUpMenuController;
import io.github.untildawn.Controller.UserDataHandler;
import io.github.untildawn.View.SignUpMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        UserDataHandler.ensureLocalUserDataExists();
        setScreen(SignUpMenuView.getInstance());
    }
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
     }

    public static Main getMain() {
        return main;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
