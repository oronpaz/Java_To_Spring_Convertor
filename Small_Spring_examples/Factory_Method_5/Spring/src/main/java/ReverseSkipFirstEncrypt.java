import java.util.Arrays;

public class ReverseSkipFirstEncrypt extends Encrypt{
    public ReverseSkipFirstEncrypt() {
        super("Reverse and skip first element", 3);
    }

    @Override
    public void encrypt(UserDetails userDetails) {
        char[] password = userDetails.getPassword().toCharArray();
        int indexStart = 1;
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
