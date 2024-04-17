package logic.interfaces;

import logic.LogicException;
import logic.domain.Credential;

public interface CredentialManagerInterface {
    int insertCredential(Credential credential)  throws LogicException ;
    Credential getCredentialByUser(String user)  throws LogicException ;
}
