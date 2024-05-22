package logic.interfaces;

import logic.LogicException;
import logic.domain.ExternalProfessor;

/**
 *
 * @author zaido
 */
public interface ExternalProfessorManagerInterface {

    /**
     *
     * @param externalProfessor
     * @return
     * @throws LogicException
     */
    int insertExternalProfessor(ExternalProfessor externalProfessor) throws LogicException;

    /**
     *
     * @param idUniversity
     * @return
     * @throws LogicException
     */
    ExternalProfessor getExternalProfessorByIdUniversity(int idUniversity) throws LogicException;
}
