package io.github.untildawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AppAssetManager {
    private  AppAssetManager assetManager;

    private Skin skin = new Skin(Gdx.files.internal("Skin/neon.ui.json"));
    public Skin getSkin() {
        return this.skin;
    }

    public AppAssetManager getAssetManager() {
        if (this.assetManager == null) {
            this.assetManager = new AppAssetManager();
        }
        return this.assetManager;
    }
}
