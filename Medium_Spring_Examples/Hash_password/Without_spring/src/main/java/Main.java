public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepository();

        try {
            RegisterManager registerManager = new RegisterManager(usersRepository);
            registerManager.run();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
