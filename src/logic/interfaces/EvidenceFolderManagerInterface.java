package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.EvidenceFolder;

/**
 *
 * @author chuch
 */
public interface EvidenceFolderManagerInterface {

    /**
     *
     * @param evidenceFolder
     * @return
     * @throws logic.LogicException
     */
    public int insertEvidenceFolder (EvidenceFolder evidenceFolder) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws logic.LogicException
     */
    public ArrayList<EvidenceFolder> getEvidenceFoldersByIdCollaboration(int idCollaboration) throws LogicException;
    public int checkEvidenceFolderNameByCollaboration(String nameFolder, int idCollaboracion) throws LogicException;
}
