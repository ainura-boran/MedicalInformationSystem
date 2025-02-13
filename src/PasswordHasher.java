import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public static void main(String[] args) {
        String password = "alimkhanuly";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Hashed Password: " + hashedPassword);
    }
}