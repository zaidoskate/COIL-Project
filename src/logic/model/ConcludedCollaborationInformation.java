package logic.model;

public class ConcludedCollaborationInformation {
    private static ConcludedCollaborationInformation instance;
    private int idCollaboration;
    private boolean downloadEvidences;
    private boolean changeVisibility;

    private ConcludedCollaborationInformation() {
        
    }

    public int getIdCollaboration() {
        return idCollaboration;
    }

    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }

    public boolean getDownloadEvidences() {
        return downloadEvidences;
    }

    public void setDownloadEvidences(boolean downloadEvidences) {
        this.downloadEvidences = downloadEvidences;
    }
    public boolean getChangeVisibility() {
        return changeVisibility;
    }

    public void setChangeVisibility(boolean changeVisibility) {
        this.changeVisibility = changeVisibility;
    }
    
    public static ConcludedCollaborationInformation getInstance() {
        if (instance == null) {
            instance = new ConcludedCollaborationInformation();
        }
        return instance;
    }
}
