/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import java.util.ArrayList;
import logic.domain.Evaluation;
import java.sql.SQLException;
import logic.LogicException;
/**
 *
 * @author zaido
 */
public interface EvaluationManagerInterface {
    public int insertEvaluationForApprovedOffer(Evaluation evaluation) throws LogicException;
    public int insertEvaluationForDeclinedOffer(Evaluation evaluation) throws LogicException;
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration) throws LogicException;
    public ArrayList<Evaluation> getEvaluationByIdCoordinator(int idCoordinator) throws LogicException;
    
}
