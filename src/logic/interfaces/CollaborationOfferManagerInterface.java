/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.CollaborationOffer;
import java.util.ArrayList;
import logic.LogicException;
/**
 *
 * @author chima
 */
public interface CollaborationOfferManagerInterface {
    int insertColaborationOffer(CollaborationOffer colaborationOffer) throws LogicException;
    int deleteCollaborationOffer(int idCollaborationOffer) throws LogicException;
    int evaluateCollaborationOffer(int idCollaborationOffer, String decision) throws LogicException;
    ArrayList<CollaborationOffer> getApprovedCollaborationOffer() throws LogicException;
    ArrayList<CollaborationOffer> getUnapprovedCollaborationOffer() throws LogicException;
    CollaborationOffer getProfessorApprovedOffer(int idUser) throws LogicException;
    boolean professorHasOffer(int idUser) throws LogicException;
    //eliminar
    //actualizar estatus de colaboración 
    //obtener colaboraciones pendientes: regresar arraylist de collaborationoffer buscar por estatus
    //obtener oferta colaboracion: regresa collaboration offer buscar con id
}
