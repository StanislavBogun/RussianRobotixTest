import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class CAuthenticator extends Authenticator {
    private String user;
    private String password;

    CAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }
}
