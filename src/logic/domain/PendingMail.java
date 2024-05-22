package logic.domain;

/**
 *
 * @author chuch
 */
public class PendingMail {
    private int idEmail;
    private int idUser;
    private String content;
    private String destinationEmail;
    private String subject;
    
    /**
     *
     * @return
     */
    public int getIdEmail() {
        return idEmail;
    }

    /**
     *
     * @param idEmail
     */
    public void setIdEmail(int idEmail) {
        this.idEmail = idEmail;
    }

    /**
     *
     * @return
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public String getDestinationEmail() {
        return destinationEmail;
    }

    /**
     *
     * @param destinationEmail
     */
    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        PendingMail pendingMailToCompare = (PendingMail) object;
        if(pendingMailToCompare.getIdEmail()!= this.getIdEmail()) {
            return false;
        }
        if(pendingMailToCompare.getIdUser()!= this.getIdUser()) {
            return false;
        }
        if(!pendingMailToCompare.getDestinationEmail().equals(this.getDestinationEmail())) {
            return false;
        }
        if(!pendingMailToCompare.getSubject().equals(this.getSubject())) {
            return false;
        }
        return true;
    }
}
