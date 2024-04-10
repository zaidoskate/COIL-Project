package logic.interfaces;

import logic.domain.Collaboration;
import java.util.ArrayList;

public interface ColaborationManagerInterface {
    int addColaboration(Collaboration colaboration);
    Collaboration getColaborationById(int id);
    ArrayList<Collaboration> getColaborationsByArea(String area);
}
