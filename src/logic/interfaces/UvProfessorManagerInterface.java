package logic.interfaces;

import logic.LogicException;
import logic.domain.UvProfessor;
public interface UvProfessorManagerInterface {
    int insertUvProfessor(UvProfessor uvProfessor) throws LogicException;
    int countUvProfessorByPersonalNumber(String personalNumber) throws LogicException;

    int getCollaborationCountByProfessorRegion(String region) throws LogicException;
    int getCollaborationCountByProfessorAcademicArea(int idAcademicArea) throws LogicException;
    UvProfessor getUvProfessorByIdUser(int idUser) throws LogicException;
    String getDepartmentNameBelonging(int idUser) throws LogicException;
}
