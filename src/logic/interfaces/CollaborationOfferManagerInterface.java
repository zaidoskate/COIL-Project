/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.CollaborationOffer;
/**
 *
 * @author chima
 */
public interface CollaborationOfferManagerInterface {
    int insertColaborationOffer(CollaborationOffer colaborationOffer);
    //eliminar
    //actualizar estatus de colaboración 
    //obtener colaboraciones pendientes: regresar arraylist de collaborationoffer buscar por estatus
    //obtener oferta colaboracion: regresa collaboration offer buscar con id
}
