package io.github.untildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import io.github.untildawn.Model.AppAssetManager;

import java.util.HashMap;

public class AudioManager {
    private static final AudioManager instance = new AudioManager();

    private Music backgroundMusic;
    private float musicVolume = 1.0f;
    private float sfxVolume = 1.0f;

    private final HashMap<String, Sound> soundEffects = new HashMap<>();
    public static AudioManager getInstance() {
        return instance;
    }

    // === MUSIC ===
    public void playBackgroundMusic(String path) {
        if (backgroundMusic != null) backgroundMusic.dispose();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(musicVolume);
        backgroundMusic.play();
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) backgroundMusic.stop();
    }

    public void setMusicVolume(float volume) {
        musicVolume = clamp(volume);
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(musicVolume);
        }
    }

    // === SFX ===
    public void playSFX(String path) {
        if (!soundEffects.containsKey(path)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
            soundEffects.put(path, sound);
        }
        soundEffects.get(path).play(sfxVolume);
    }

    public void setSFXVolume(float volume) {
        sfxVolume = clamp(volume);
    }

    // === Utils ===
    public void dispose() {
        if (backgroundMusic != null) backgroundMusic.dispose();
        for (Sound s : soundEffects.values()) s.dispose();
        soundEffects.clear();
    }

    private float clamp(float volume) {
        return Math.max(0f, Math.min(1f, volume));
    }
    public static void clickSound(){
        AudioManager.getInstance().playSFX(AppAssetManager.getAssetManager().click);
    }
}

