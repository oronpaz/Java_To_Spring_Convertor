import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Adler32Hash extends HashExecutor {

    public Adler32Hash(String method, int strong) {
        super(method, strong);
    }

    @Override
    public String hash(String password) {
        return Hashing.adler32().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
