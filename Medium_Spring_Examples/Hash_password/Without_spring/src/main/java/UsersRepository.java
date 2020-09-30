import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UsersRepository {
    private Map<Integer,UserDetails> users;
    private int index = 0;

    public UsersRepository() {
        users = new HashMap<>();
    }

    public void addUser(UserDetails userDetails) {
        index++;
        users.put(index,userDetails);
    }

    public Map<Integer,UserDetails> getUsers() {
        return users;
    }

    public boolean isUserNameExists(String userName) {
        Collection<UserDetails> usersDetails = users.values();
        for(UserDetails userDetails : usersDetails) {
            if(userDetails.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
