/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.UvProfessor;

public interface UvProfessorManagerInterface {
    int insertUvProfessor(UvProfessor uvProfessor);
    UvProfessor getUvProfessorByIdUser(int id);
}
