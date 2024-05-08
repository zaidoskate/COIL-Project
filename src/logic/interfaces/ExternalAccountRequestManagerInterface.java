package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.ExternalAccountRequestData;

public interface ExternalAccountRequestManagerInterface {
    int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;
    int deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;
    ArrayList<ExternalAccountRequestData> getExternalAccountRequestsData() throws LogicException;
    ExternalAccountRequest getExternalAccountRequestById(int idExternalAccountRequest) throws LogicException;
}
