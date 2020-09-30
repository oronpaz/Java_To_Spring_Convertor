import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RegisterManager {
    private UsersRepository usersRepository;
    private Scanner scanner = new Scanner(System.in);
    private HashExecutorFactory hashExecutorFactory;
    private String hashMethod;

    @Autowired
    public void initProperty(@Value("${hash.method}") String property) {
        this.hashMethod = property;
    }

    public RegisterManager(UsersRepository usersRepository, HashExecutorFactory hashExecutorFactory) {
        this.usersRepository = usersRepository;
        this.hashExecutorFactory = hashExecutorFactory;
    }

    public void run() throws Exception {
        System.out.println("Welcome to register app\n");
        this.hashExecutorFactory.setHashMethod(hashMethod);

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

}
