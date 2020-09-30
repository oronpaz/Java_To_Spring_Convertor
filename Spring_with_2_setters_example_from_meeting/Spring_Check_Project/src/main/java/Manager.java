public class Manager {

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Hello from manager");
        System.out.println(String.format("Here are my details: \nname: %s, id: %d\n", name, id));
        System.out.println("Bye..");
    }
}
