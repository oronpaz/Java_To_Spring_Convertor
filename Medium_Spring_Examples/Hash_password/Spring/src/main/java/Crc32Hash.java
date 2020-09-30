import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Crc32Hash extends HashExecutor {


    public Crc32Hash(String method, int strong) {
        super(method, strong);
    }

    @Override
    public String hash(String password) {
        return Hashing.crc32().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
