package io.github.untildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.untildawn.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataHandler {
    private static final Gson gson = new Gson();
    private static final String LOCAL_FILE_NAME = "Users.json";
    private static final String ASSETS_FILE_PATH = "JsonData/Users.json";

    // Ensure the local file exists by copying from assets if missing
    public static void ensureLocalUserDataExists() {
        FileHandle localFile = Gdx.files.local(LOCAL_FILE_NAME);
        if (!localFile.exists()) {
            FileHandle internalFile = Gdx.files.internal(ASSETS_FILE_PATH);
            localFile.writeString(internalFile.readString(), false);
            Gdx.app.log("UserDataHandler", "Copied Users.json from assets to local.");
        }
    }

    public static List<User> loadUsers() {
        ensureLocalUserDataExists();  // Always ensure the file exists
        FileHandle file = Gdx.files.local(LOCAL_FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        return gson.fromJson(file.readString(), new TypeToken<List<User>>(){}.getType());
    }

    public static void saveUsers(List<User> users) {
        FileHandle file = Gdx.files.local(LOCAL_FILE_NAME);
        file.writeString(gson.toJson(users), false); // overwrite
    }

    public static void addUser(User user) {
        List<User> users = loadUsers();
        users.add(user);
        saveUsers(users);
    }

    public static User getUserByUsername(String username) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static boolean updateUserField(String username, String fieldName, Object newValue) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                switch (fieldName) {
                    case "username":
                        if (newValue instanceof String) user.setUsername((String) newValue); break;
                    case "password":
                        if (newValue instanceof String) user.setPassword((String) newValue); break;
                    case "security":
                        if (newValue instanceof String) user.setSecurity((String) newValue); break;
                    case "score":
                        if (newValue instanceof Integer) user.setScore((Integer) newValue); break;
                    case "kills":
                        if (newValue instanceof Integer) user.setKills((Integer) newValue); break;
                    case "survivalTime":
                        if (newValue instanceof Integer) user.setSurvivalTime((Integer) newValue); break;
                    case "avatar":
                        if (newValue instanceof String) user.setAvatar((String) newValue); break;
                    case "reload":
                        if (newValue instanceof Integer) user.setReload((Integer) newValue); break;
                    case "forward":
                        if (newValue instanceof Integer) user.setForward((Integer) newValue); break;
                    case "backWard":
                        if (newValue instanceof Integer) user.setBackWard((Integer) newValue); break;
                    case "left":
                        if (newValue instanceof Integer) user.setLeft((Integer) newValue); break;
                    case "right":
                        if (newValue instanceof Integer) user.setRight((Integer) newValue); break;
                    case "sfx":
                        if (newValue instanceof Boolean) user.setSfx((Boolean) newValue); break;
                    case "blackAndWhite":
                        if (newValue instanceof Boolean) user.setBlackAndWhite((Boolean) newValue); break;
                    case "autoReload":
                        if (newValue instanceof Boolean) user.setAutoReload((Boolean) newValue); break;
                    case "music":
                        if (newValue instanceof String) user.setMusic((String) newValue); break;
                    default:
                        return false; // Unknown field
                }
                saveUsers(users);
                return true;
            }
        }
        return false; // User not found
    }
}
