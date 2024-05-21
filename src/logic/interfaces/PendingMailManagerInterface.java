package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.PendingMail;

public interface PendingMailManagerInterface {
    int insertPendingMail(PendingMail pendingMail) throws LogicException;
    int deletePendingMail(PendingMail pendingMail) throws LogicException;
    ArrayList<PendingMail> getPendingMailsByIdUser(int idUser) throws LogicException;
}
