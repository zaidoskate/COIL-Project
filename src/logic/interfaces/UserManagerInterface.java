package logic.interfaces;

import logic.domain.User;

public interface UserManagerInterface {
    int InsertUser(User user);
    User getUserById(int id);
    User getUserByName(String name);
    
}
