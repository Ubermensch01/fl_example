package com.fieldlens.entities;


import com.fieldlens.tools.Config;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/13/17
 * Time: 11:59 PM
 */
public class Credentials {
    public static Credentials DEFAULT_USER;
    private String username;
    private String password;

    static {
        DEFAULT_USER = new Credentials(
                Config.instance().getProperty("username"),
                Config.instance().getProperty("password"));
    }

    Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("Username: %s Password: %s", username, password);
    }
}
