

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class RegisterUtils {
    private UsersRepository usersRepository;
    private Scanner scanner = new Scanner(System.in);
    private EncryptFactory encryptFactory;

    public RegisterUtils(UsersRepository usersRepository, EncryptFactory encryptFactory) {
        this.usersRepository = usersRepository;
        this.encryptFactory = encryptFactory;
    }

    public void run() throws Exception {

        while(true) {
            System.out.println(String.format("Welcome to register app\nUsers list:\n%s", usersRepository.getUsersList()));
            UserDetails userDetails = getUserDetails();
            String encryptMethodType = getEncryptMethodTypeFromConfigurationFile();
            Encrypt encrypt = encryptFactory.createEncrypt(encryptMethodType);
            encrypt.encrypt(userDetails);
            saveToDB(userDetails);
        }

    }

    private void saveToDB(UserDetails userDetails) {
        usersRepository.addUser(userDetails);
    }

    private String getEncryptMethodTypeFromConfigurationFile() throws Exception {
        Path path = Paths.get("/application.properties");
        try {
            List<String> lines = Files.readAllLines(path);
            for(String line : lines) {
                if(line.contains("encrypt_method")) {
                    return getEncryptMethodTypeFromLine(line);
                }
            }
            throw new Exception("Encrypt method type wasn't defined in application.properties");

        }
        catch (Exception ex) {
            throw new Exception("Configuration file isn't exist");
        }
    }

    private String getEncryptMethodTypeFromLine(String s) {
        return s.split("=")[1];
    }

    private UserDetails getUserDetails() {
        System.out.println("Welcome to registration process\n");
        System.out.println("Please insert user name\n");
        String userName = scanner.nextLine();
        System.out.println("Please insert password\n");
        String password = scanner.nextLine();
        return new UserDetails(userName, password);
    }
}
