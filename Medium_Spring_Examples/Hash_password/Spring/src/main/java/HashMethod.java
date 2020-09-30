public enum HashMethod {
    SHA512("sha512"),
    ADLER32("adler32"),
    CRC32("crc32");

    private String hashName;

    HashMethod(String hashName) {
        this.hashName = hashName;
    }

    public String getEncryptName() {
        return hashName;
    }
}
