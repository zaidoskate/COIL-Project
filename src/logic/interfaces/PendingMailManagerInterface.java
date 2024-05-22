package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.PendingMail;

/**
 *
 * @author chuch
 */
public interface PendingMailManagerInterface {

    /**
     *
     * @param pendingMail
     * @return
     * @throws LogicException
     */
    public int insertPendingMail(PendingMail pendingMail) throws LogicException;

    /**
     *
     * @param pendingMail
     * @return
     * @throws LogicException
     */
    public int deletePendingMail(PendingMail pendingMail) throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    public ArrayList<PendingMail> getPendingMailsByIdUser(int idUser) throws LogicException;
}
