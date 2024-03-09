package logic.interfaces;

import logic.domain.Credential;

public interface CredentialManagerInterface {
    int insertCredential(Credential credential);
    int getIdUserByCredential(Credential credential);
}
