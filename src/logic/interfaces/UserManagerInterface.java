package logic.interfaces;

import logic.domain.User;

public interface UserManagerInterface {
    int addUser(User user);
    User getUserById(int id);
    User getUserByName(String name);
    
}
