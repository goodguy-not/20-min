package io.github.untildawn.Model;

import com.badlogic.gdx.Input;

public class User {
    private String username;
    private String password;
    private String security;
    private int score;
    private int kills;
    private int survivalTime;
    private String avatar;
    private int reload;
    private int forward;
    private int backWard;
    private int left;
    private int right;
    private boolean sfx;
    private boolean blackAndWhite;
    private boolean autoReload;
    private String music;
    public User (String username, String password, String security) {
        this.username = username;
        this.password = password;
        this.security = security;
        this.score = 0;
        this.kills = 0;
        this.survivalTime = 0;
        this.avatar = "Shana";
        this.reload = Input.Keys.R;
        this.forward = Input.Keys.W;
        this.backWard = Input.Keys.S;
        this.left = Input.Keys.A;
        this.right = Input.Keys.D;
        this.sfx = true;
        this.blackAndWhite = false;
        this.autoReload = true;
        this.music = "Music2";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getSurvivalTime() {
        return survivalTime;
    }

    public void setSurvivalTime(int survivalTime) {
        this.survivalTime = survivalTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getReload() {
        return reload;
    }

    public void setReload(int reload) {
        this.reload = reload;
    }

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public int getBackWard() {
        return backWard;
    }

    public void setBackWard(int backWard) {
        this.backWard = backWard;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public boolean isSfx() {
        return sfx;
    }

    public void setSfx(boolean sfx) {
        this.sfx = sfx;
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
