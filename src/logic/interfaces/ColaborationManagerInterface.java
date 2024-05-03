package logic.interfaces;

import logic.domain.Collaboration;
import logic.LogicException;
import java.util.ArrayList;

public interface ColaborationManagerInterface {
    int addColaboration(Collaboration colaboration) throws LogicException;
    int startCollaboration(int idCollaboration) throws LogicException;
    int updateEndDateByIdCollaboration(int idCollaboration, String date) throws LogicException;
    Collaboration getColaborationById(int id) throws LogicException;
    ArrayList<Collaboration> getAllCollaborations() throws LogicException;
    ArrayList<Collaboration> getActiveCollaborations() throws LogicException;
}
