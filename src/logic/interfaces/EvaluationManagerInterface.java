/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.Evaluation;
/**
 *
 * @author zaido
 */
public interface EvaluationManagerInterface {
    public int insertEvaluation(Evaluation evaluation);
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration);
    
}
