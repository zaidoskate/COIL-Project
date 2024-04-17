package logic.interfaces;

import logic.LogicException;
import logic.domain.User;

public interface UserManagerInterface {
    int addUser(User user)  throws LogicException ;
    User getUserById(int id)  throws LogicException ;
    
}
