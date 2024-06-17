package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.EvidenceFolder;
public interface EvidenceFolderManagerInterface {
    public int insertEvidenceFolder (EvidenceFolder evidenceFolder) throws LogicException;
    public ArrayList<EvidenceFolder> getEvidenceFoldersByIdCollaboration(int idCollaboration) throws LogicException;
    public int checkEvidenceFolderNameByCollaboration(String nameFolder, int idCollaboracion) throws LogicException;
}
