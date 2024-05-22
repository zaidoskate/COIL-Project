package logic.interfaces;

import logic.LogicException;
import logic.domain.UvProfessor;

/**
 *
 * @author zaido
 */
public interface UvProfessorManagerInterface {

    /**
     *
     * @param uvProfessor
     * @return
     * @throws LogicException
     */
    int insertUvProfessor(UvProfessor uvProfessor) throws LogicException;

    /**
     *
     * @param personalNumber
     * @return
     * @throws LogicException
     */
    int countUvProfessorByPersonalNumber(String personalNumber) throws LogicException;

    /**
     *
     * @param region
     * @return
     * @throws LogicException
     */
    int getCollaborationCountByProfessorRegion(String region) throws LogicException;

    /**
     *
     * @param idAcademicArea
     * @return
     * @throws LogicException
     */
    int getCollaborationCountByProfessorAcademicArea(int idAcademicArea) throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    UvProfessor getUvProfessorByIdUser(int idUser) throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    String getDepartmentNameBelonging(int idUser) throws LogicException;
}
