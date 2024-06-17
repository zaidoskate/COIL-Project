package logic.interfaces;

import logic.LogicException;
import logic.domain.Credential;

public interface CredentialManagerInterface {
    public int insertCredential(Credential credential)  throws LogicException ;
    public int getIdUserByCredential(Credential credential)  throws LogicException ;
    public int countCredentialsByUser(String user) throws LogicException ;
}
