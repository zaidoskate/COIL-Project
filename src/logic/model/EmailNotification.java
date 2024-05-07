package logic.model;

public class EmailNotification {
    private static EmailNotification instance;
    private String email;
    private String messageSuccess;
    private String messageCancel;
    private boolean sent;

    private EmailNotification() {
    }
    
    public static EmailNotification getInstance() {
        if (instance == null) {
            instance = new EmailNotification();
        }
        return instance;
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

    public String getMessageCancel() {
        return messageCancel;
    }

    public void setMessageCancel(String messageCancel) {
        this.messageCancel = messageCancel;
    }
}
