/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.LogicException;
import logic.domain.ExternalProfessor;

/**
 *
 * @author chima
 */
public interface ExternalProfessorManagerInterface {
    int insertExternalProfessor(ExternalProfessor externalProfessor) throws LogicException;
    ExternalProfessor getExternalProfessorByIdUniversity(int idUniversity);
}
