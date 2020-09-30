import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class RegisterManager {
    private UsersRepository usersRepository;
    private Scanner scanner = new Scanner(System.in);
    private HashExecutorFactory hashExecutorFactory;

    public RegisterManager(UsersRepository usersRepository) throws IOException {
        this.usersRepository = usersRepository;
        this.hashExecutorFactory = createFactory();
    }

    public void run() throws Exception {
        System.out.println("Welcome to register app\n");

        while(true) {
            printUsersList();
            UserDetails userDetails = getUserDetails();
            try {
                validateUserNameIsFree(userDetails);
            }
            catch (Exception ex) {
                System.out.println("User name already exists, try again\n");
                continue;
            }

            HashExecutor hashExecutor = hashExecutorFactory.getEncoder();
            userDetails.setPassword(hashExecutor.hash(userDetails.getPassword()));
            saveToDB(userDetails);
            System.out.println("User register successfully\n");
        }
    }

    private void printUsersList() {
        Map<Integer,UserDetails> users = usersRepository.getUsers();
        if(!users.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Users In System:\n");
            users.forEach((integer, userDetails) -> stringBuilder.append(String.format("%s\n", userDetails.getUserName())));
            System.out.println(stringBuilder.toString());
        }
    }

    private void validateUserNameIsFree(UserDetails userDetails) throws Exception {
        if(usersRepository.isUserNameExists(userDetails.getUserName())) {
            throw new Exception("User name is already exists in system, choose another one");
        }
    }

    private void saveToDB(UserDetails userDetails) {
        usersRepository.addUser(userDetails);
    }

    private UserDetails getUserDetails() {
        System.out.println("Welcome to registration process\n");
        System.out.println("Please insert user name\n");
        String userName = scanner.nextLine();
        System.out.println("Please insert password\n");
        String password = scanner.nextLine();
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(userName);
        userDetails.setPassword(password);
        return userDetails;
    }

    private HashExecutorFactory createFactory() throws IOException {
        String hashMethod = getHashMethodFromConfigurationFile();
         HashExecutorFactory hashExecutorFactory = new HashExecutorFactory();
        hashExecutorFactory.setHashMethod(hashMethod);
        hashExecutorFactory.setEncryptsMap(GetMapElements());
        return hashExecutorFactory;
    }

    @ConfigurationField
    private String getHashMethodFromConfigurationFile() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if(inputStream != null ) {
            properties.load(inputStream);
        }
        else {
            throw new FileNotFoundException();
        }
        return properties.getProperty("hash.method");
    }

    private Map<HashMethod, HashExecutor> GetMapElements() {
        Map<HashMethod, HashExecutor> executors = new EnumMap<>(HashMethod.class);
        executors.put(HashMethod.SHA512, createSha512Hash());
        executors.put(HashMethod.ADLER32, createAdler32Hash());
        executors.put(HashMethod.CRC32, createCrc32Hash());
        return executors;
    }

    private HashExecutor createCrc32Hash() {
      return new Crc32Hash("SHA512", 3);
    }

    private HashExecutor createAdler32Hash() {
        return new Adler32Hash("Adler32", 2);
    }

    private HashExecutor createSha512Hash() {
        return new SHA512Hash("SHA512", 3);
    }

}
