import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class HashExecutorFactory {
    private Map<HashMethod, HashExecutor> encoderMap;
    private String hashMethod;

    private HashExecutor getEncoder(HashMethod hashMethod) {
        if (encoderMap.get(hashMethod) != null) {
            return encoderMap.get(hashMethod);
        }
        throw new IllegalArgumentException("Unknown Parser");
    }

    public HashExecutor getEncoder() throws Exception {
        for(HashMethod method : HashMethod.values()) {
            if(method.getEncryptName().equals(hashMethod)) {
                return getEncoder(method);
            }
        }

        throw new Exception("Encrypt method type is not supported\n");
    }

    public void setEncryptsMap(Map<HashMethod, HashExecutor> parserMap) {
        this.encoderMap = parserMap;
    }

    public void setHashMethod(String hashMethod) {
        this.hashMethod = hashMethod;
    }
}
