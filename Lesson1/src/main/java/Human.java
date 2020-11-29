public class Human implements RunAndJump {

    private String name;
    private int age;
    private double maxRun;
    private double maxJump;


    public Human(String name, int age, double maxJump, double maxRun) {
        this.name = name;
        this.age = age;
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    @Override
    public double jump() {
        System.out.println(this.name + " прыгнул(-а)");
        return getMaxJump();
        }


    @Override
    public double run() {
        System.out.println(this.name + " побежал(-а)");
        return getMaxRun();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMaxJump() {
        return maxJump;
    }

    public void setMaxJump(double maxJump) {
        this.maxJump = maxJump;
    }

    public double getMaxRun() {
        return maxRun;
    }

    public void setMaxRun(double maxRun) {
        this.maxRun = maxRun;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
