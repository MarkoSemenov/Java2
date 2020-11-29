public class Cat implements RunAndJump {

    private String name;
    private String type;
    private int age;
    private double maxRun;
    private double maxJump;

    public Cat(String name, String type, int age, double maxJump, double maxRun) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public double jump() {
        System.out.println("Кот " + this.name + " прыгнул(-а)");
        return getMaxJump();
    }

    @Override
    public double run() {
        System.out.println("Кот " + this.name + " побежал(-а)");
        return getMaxRun();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMaxRun() {
        return maxRun;
    }

    public void setMaxRun(double maxRun) {
        this.maxRun = maxRun;
    }

    public double getMaxJump() {
        return maxJump;
    }

    public void setMaxJump(double maxJump) {
        this.maxJump = maxJump;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
