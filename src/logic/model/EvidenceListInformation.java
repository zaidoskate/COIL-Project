package logic.model;

public class EvidenceListInformation {
    private static EvidenceListInformation instance;
    private int idFolder;
    private int idCollaboration;
    private boolean allFolders;

    private EvidenceListInformation() {
        
    }
    public int getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(int idSearch) {
        this.idFolder = idSearch;
    }

    public int getIdCollaboration() {
        return idCollaboration;
    }

    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }

    public boolean getAllFolders() {
        return allFolders;
    }

    public void setAllFolders(boolean allFolders) {
        this.allFolders = allFolders;
    }
    public static EvidenceListInformation getInstance() {
        if (instance == null) {
            instance = new EvidenceListInformation();
        }
        return instance;
    }
}
