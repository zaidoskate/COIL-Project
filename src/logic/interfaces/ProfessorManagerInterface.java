/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Professor;

public interface ProfessorManagerInterface {
    int insertProfessor(Professor professor) throws LogicException;
    ArrayList<String> getUniversityFromAProfessor(int idUser) throws LogicException;
}
