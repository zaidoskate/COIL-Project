package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.ExternalAccountRequestData;

/**
 *
 * @author chuch
 */
public interface ExternalAccountRequestManagerInterface {

    /**
     *
     * @param externalAccountRequest
     * @return
     * @throws LogicException
     */
    public int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;

    /**
     *
     * @param externalAccountRequest
     * @return
     * @throws LogicException
     */
    public int deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    public ArrayList<ExternalAccountRequestData> getExternalAccountRequestsData() throws LogicException;

    /**
     *
     * @param idExternalAccountRequest
     * @return
     * @throws LogicException
     */
    public ExternalAccountRequest getExternalAccountRequestById(int idExternalAccountRequest) throws LogicException;
}
