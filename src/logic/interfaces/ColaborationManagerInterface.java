package logic.interfaces;

import logic.domain.Colaboration;
import java.util.ArrayList;

public interface ColaborationManagerInterface {
    int insertColaboration(Colaboration colaboration);
    Colaboration getColaborationById(int id);
    Colaboration getColaborationByIdOffer(int idOffer);
    ArrayList<Colaboration> getColaborationsByArea(int id);
}
