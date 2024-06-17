package logic.domain;

public class PendingMail {
    private int idEmail;
    private int idUser;
    private String content;
    private String destinationEmail;
    private String subject;
    
    public int getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(int idEmail) {
        this.idEmail = idEmail;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Override
    public boolean equals(Object object) {
        PendingMail pendingMailToCompare = (PendingMail) object;
        if (pendingMailToCompare.getIdEmail()!= this.getIdEmail()) {
            return false;
        }
        if (pendingMailToCompare.getIdUser()!= this.getIdUser()) {
            return false;
        }
        if (!pendingMailToCompare.getDestinationEmail().equals(this.getDestinationEmail())) {
            return false;
        }
        return true;
    }
}
