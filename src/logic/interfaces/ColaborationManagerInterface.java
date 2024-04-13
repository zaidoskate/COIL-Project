package logic.interfaces;

import logic.domain.Collaboration;
import java.util.ArrayList;

public interface ColaborationManagerInterface {
    int addColaboration(Collaboration colaboration);
    int updateEndDateByIdCollaboration(int idCollaboration, String date);
    Collaboration getColaborationById(int id);
    ArrayList<Collaboration> getAllCollaborations();
    ArrayList<Collaboration> getActiveCollaborations();
}
