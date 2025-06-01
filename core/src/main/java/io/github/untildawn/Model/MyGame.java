package io.github.untildawn.Model;

public class MyGame {
    private static MyGame game;
    private User user;

    public MyGame(User user) {
        if(game == null) {
            this.user = user;
            game = this;
        }
    }
    public static MyGame getGame() {
        return game;
    }
}
