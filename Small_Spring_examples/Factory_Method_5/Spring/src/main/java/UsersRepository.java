import java.util.HashMap;
import java.util.Map;

public class UsersRepository {
    private Map<Integer,UserDetails> users;
    private int index = 0;

    public UsersRepository() {
        users = new HashMap<Integer, UserDetails>();
    }

    public void addUser(UserDetails userDetails) {
        index++;
        users.put(index,userDetails);
    }

    public String getUsersList() {
        StringBuilder stringBuilder = new StringBuilder();
        users.forEach((integer, userDetails) -> stringBuilder.append(String.format("%d: %s\n", integer, userDetails.getUserName())));
        return stringBuilder.toString();
    }
}
