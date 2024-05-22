package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.UvAccountRequest;

/**
 *
 * @author chuch
 */
public interface UvAccountRequestManagerInterface {

    /**
     *
     * @param uvAccountRequest
     * @return
     * @throws LogicException
     */
    public int insertUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException;

    /**
     *
     * @param uvAccountRequest
     * @return
     * @throws LogicException
     */
    public int deleteUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    public ArrayList<UvAccountRequest> getUvAccountRequests() throws LogicException;
}
