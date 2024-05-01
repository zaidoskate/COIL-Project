package logic.model;

public class EmailAddress {
    private static EmailAddress instance;
    private String email;
    private boolean sent;

    private EmailAddress() {
    }
    
    public static EmailAddress getInstance() {
        if (instance == null) {
            instance = new EmailAddress();
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
}
