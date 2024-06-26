package logic.model;

public class EmailNotification {
    private static EmailNotification instance;
    private String email;
    private String messageSuccess;
    private String messageCancel;
    private String emailBody;
    private boolean sent;

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    private EmailNotification() {
    
    }
    public void setEmail(String email) {
        this.email = email;
        this.sent = false;
    }
    public String getEmail() {
        return this.email;
    }
    public void emailSent() {
        this.sent = true;
    }
    public boolean getSentStatus() {
        return this.sent;
    }

    public String getMessageSuccess() {
        return messageSuccess;
    }

    public void setMessageSuccess(String messageSuccess) {
        this.messageSuccess = messageSuccess;
    }    
    
    public static EmailNotification getInstance() {
        if (instance == null) {
            instance = new EmailNotification();
        }
        return instance;
    }
}
