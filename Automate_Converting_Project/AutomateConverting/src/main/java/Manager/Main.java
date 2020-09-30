package Manager;

public class Main {
    public static void main(String[] args) {
        Manager manager = Manager.getInstance();
            try {
                manager.start(args[0]);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

