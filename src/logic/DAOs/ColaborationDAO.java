package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.domain.Colaboration;
import logic.interfaces.ColaborationManagerInterface;

public class ColaborationDAO implements ColaborationManagerInterface {
    private final DatabaseConnection databaseConnection;
    public ColaborationDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertColaboration(Colaboration colaboration) {
        
    }
    
    @Override
    public Colaboration getColaborationById(int id) {
        
    }
    
    @Override
    public Colaboration getColaborationByIdOffer(int idOffer) {
        
    }
    
    @Override
    public ArrayList<Colaboration> getColaborationsByArea(int id) {
        
    }
}
