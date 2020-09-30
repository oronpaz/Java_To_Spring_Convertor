import org.junit.Test;
import static org.junit.Assert.*;

public class UsersRepositoryTest {

    private UsersRepository usersRepository = new UsersRepository();

    @Test
    public void isUserExistsTest() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName("User1");
        userDetails.setPassword("445533");
        usersRepository.addUser(userDetails);
        boolean actualResult = usersRepository.isUserNameExists(userDetails.getUserName());
        assertTrue(actualResult);
    }

    @Test
    public void addUserTest() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName("User1");
        userDetails.setPassword("445533");
        usersRepository.addUser(userDetails);
        assertEquals(usersRepository.getUsers().size(), 1);
        assertNotNull(usersRepository.getUsers().get(1));
    }


}
