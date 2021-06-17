package ru.job4j.gc;
import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;


public class User {

    private boolean married;
    private String name;
    private int age;

    public User(String name) {
        this.name = name;
    }

    public User(boolean married, String name, int age) {
        this.married = married;
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize " + getClass().getSimpleName());
    }

    public boolean isMarried() {
        return married;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Runtime env = Runtime.getRuntime();
        User user1 = new User(null);
        User user2 = new User(true, "Tom", 27);
        System.out.println("User1 = " + sizeOf(user1));
        System.out.println("User2 = " + sizeOf(user2));
        System.out.println("Память занимаемая объектом без полей = " + sizeOf(new NotFields()));
        User[][] s = new User[1000][100];
        long size;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 100; j++) {
                size = env.totalMemory() - env.freeMemory();
                s[i][j] = new User("");
                System.out.println(String.format(
                        "Круг %s.%s : Занимаемая память %s из %s", i,
                        j, env.totalMemory() - env.freeMemory(),
                        env.totalMemory()
                ));
                s[i][j] = null;
                if (size > env.totalMemory() - env.freeMemory()) {
                    System.out.println("Размер кучи после чистки сборщиком мусора: "
                            + (env.totalMemory() - env.freeMemory()));
                    return;
                }
            }
        }
    }
}
