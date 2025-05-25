package io.github.untildawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AppAssetManager {
    private  static AppAssetManager assetManager;
    private Skin skin;
    private Image image;

    public final String click = "Sounds/sfx/click.wav";
    public final String music1 = "Sounds/BackGroundSound/Music1.wav";
    public final String music2 = "Sounds/BackGroundSound/Music2.wav";
    public final String music3 = "Sounds/BackGroundSound/Music3.wav";



    public AppAssetManager(){
        this.skin = new Skin(Gdx.files.internal("Skin/pixthulhu-ui.json"));
        Texture temp = new Texture(Gdx.files.internal("Images/Background/bg.png"));
        this.image = new Image(temp);
    }
    public Skin getSkin() {
        return this.skin;
    }

    public static AppAssetManager getAssetManager() {
        if (assetManager == null) {
            assetManager = new AppAssetManager();
        }
        return assetManager;
    }
    public Image getImage() {
        return this.image;
    }
}
