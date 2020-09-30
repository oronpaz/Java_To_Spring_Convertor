import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class SHA512Hash extends HashExecutor {

    public SHA512Hash(String method, int strong) {
        super(method, strong);
    }

    @Override
    public String hash(String password) {
        return Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
