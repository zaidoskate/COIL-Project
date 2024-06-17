package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.ExternalAccountRequestData;
public interface ExternalAccountRequestManagerInterface {
    public int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;
    public int deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;
    public ArrayList<ExternalAccountRequestData> getExternalAccountRequestsData() throws LogicException;
    public ExternalAccountRequest getExternalAccountRequestById(int idExternalAccountRequest) throws LogicException;
    public boolean checkEmailRegistered(String email) throws LogicException;
}
