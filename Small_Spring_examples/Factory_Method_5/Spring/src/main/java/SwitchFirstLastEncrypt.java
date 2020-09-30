import java.util.Arrays;

public class SwitchFirstLastEncrypt extends Encrypt{
    public SwitchFirstLastEncrypt() {
        super("Switch First and Last digits", 2);
    }

    @Override
    public void encrypt(UserDetails userDetails) {
        char[] password = userDetails.getPassword().toCharArray();

        char temp = password[0];
        password[0] = password[password.length - 1];
        password[password.length - 1] = temp;
        userDetails.setPassword(Arrays.toString(password));
    }
}
