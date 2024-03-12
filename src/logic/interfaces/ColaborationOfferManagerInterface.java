/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;
import java.util.ArrayList;
import logic.domain.ColaborationOffer;
import logic.domain.User;
/**
 *
 * @author chima
 */
public interface ColaborationOfferManagerInterface {
    int insertColaborationOffer(ColaborationOffer colaborationOffer); 
    ArrayList<ColaborationOffer> getColaborationOfferByIdUser (ColaborationOffer colaborationOffer);
}
