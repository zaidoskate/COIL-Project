package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.PendingMail;
public interface PendingMailManagerInterface {
    public int insertPendingMail(PendingMail pendingMail) throws LogicException;
    public int deletePendingMail(PendingMail pendingMail) throws LogicException;
    public ArrayList<PendingMail> getPendingMailsByIdUser(int idUser) throws LogicException;
}
