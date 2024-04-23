package gui;

import logic.model.UserData;

public class SessionManager {
    private static SessionManager instance;
    private UserData userData;

    private SessionManager() {
    }
    
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(UserData userData) {
        this.userData = userData;
    }

    public void logout() {
        this.userData = null;
    }

    public boolean isLoggedIn() {
        return userData != null;
    }

    public UserData getUserData() {
        return userData;
    }
}
