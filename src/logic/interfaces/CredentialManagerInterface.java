package logic.interfaces;

import logic.LogicException;
import logic.domain.Credential;

/**
 *
 * @author chuch
 */
public interface CredentialManagerInterface {

    /**
     *
     * @param credential
     * @return
     * @throws LogicException
     */
    public int insertCredential(Credential credential)  throws LogicException ;

    /**
     *
     * @param credential
     * @return
     * @throws LogicException
     */
    public int getIdUserByCredential(Credential credential)  throws LogicException ;

    /**
     *
     * @param user
     * @return
     * @throws LogicException
     */
    public int countCredentialsByUser(String user) throws LogicException ;
}
