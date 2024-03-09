package logic.interfaces;

import logic.domain.Belongs;
import logic.domain.User;
import logic.domain.Colaboration;
import java.util.ArrayList;
        
public interface BelongsManagerInterface {
    ArrayList<Colaboration> getColaborationsByUser(User user);
    ArrayList<User> getUsersByColaboration(Colaboration colaboration);
    int insertBelongs(Belongs belongs);
    
}
