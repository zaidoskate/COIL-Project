package logic.interfaces;

import logic.LogicException;
import logic.domain.ExternalProfessor;
public interface ExternalProfessorManagerInterface {
    int insertExternalProfessor(ExternalProfessor externalProfessor) throws LogicException;
}
