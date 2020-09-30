import java.util.Map;

//refactor encryptor class name
public class EncryptFactory {
    private Map<EncryptMethod, Encrypt> encryptMap;

    public Encrypt createEncrypt(EncryptMethod parsConst) {
        Encrypt parser = encryptMap.get(parsConst);
        if (encryptMap.get(parsConst) != null) {
            return parser;
        }
        throw new IllegalArgumentException("Unknown Parser");
    }

    public Encrypt createEncrypt(String encryptMethodType) throws Exception {
        EncryptMethod encryptMethod;

        switch (encryptMethodType) {
            case "reverse_skip_first": {
                encryptMethod = EncryptMethod.REVERSE_SKIP_FIRST;
                break;
            }
            case "reverse" : {
                encryptMethod = EncryptMethod.REVERSE;
                break;
            }
            case "switch_first_last" : {
                encryptMethod = EncryptMethod.SWITCH_FIRST_LAST;
                break;
            }
            default: throw new Exception("Encrypt method type is not supported, sorry");
        }

        return createEncrypt(encryptMethod);
    }

    public void setEncryptsMap(Map<EncryptMethod, Encrypt> parserMap) {
        this.encryptMap = parserMap;
    }
}
