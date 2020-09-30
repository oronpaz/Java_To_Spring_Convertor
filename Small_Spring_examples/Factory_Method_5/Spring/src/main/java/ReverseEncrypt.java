import java.util.Arrays;

public class ReverseEncrypt extends Encrypt{
    public ReverseEncrypt() {
        super("Reverse", 1);
    }
    @Override
    public void encrypt(UserDetails userDetails) {
        char[] password = userDetails.getPassword().toCharArray();

        int indexStart = 0;
        int indexEnd = password.length - 1;

        while (indexStart < indexEnd)
        {
            char temp = password[indexStart];
            password[indexStart] = password[indexEnd];
            password[indexEnd] = temp;
            indexStart++;
            indexEnd--;
        }

        userDetails.setPassword(Arrays.toString(password));
    }
}
