package logic.interfaces;

import logic.LogicException;
import logic.domain.User;

/**
 *
 * @author chuch
 */
public interface UserManagerInterface {

    /**
     *
     * @param user
     * @return
     * @throws LogicException
     */
    public int addUser(User user)  throws LogicException ;

    /**
     *
     * @param id
     * @return
     * @throws LogicException
     */
    public User getUserById(int id)  throws LogicException ;

    /**
     *
     * @param id
     * @return
     * @throws LogicException
     */
    public String getUserTypeById(int id) throws LogicException;
    
    public boolean checkEmailRegistered(String email) throws LogicException;
}
