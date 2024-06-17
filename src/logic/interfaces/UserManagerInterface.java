package logic.interfaces;

import logic.LogicException;
import logic.domain.User;
public interface UserManagerInterface {

    public int addUser(User user)  throws LogicException ;
    public User getUserById(int id)  throws LogicException ;
    public String getUserTypeById(int id) throws LogicException;
    public boolean checkEmailRegistered(String email) throws LogicException;
}
