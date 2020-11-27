public class Robot implements RunAndJump {

    private String name;
    private String type;
    private double maxRun;
    private double maxJump;

    public Robot(String name, String type, double maxJump, double maxRun) {
        this.name = name;
        this.type = type;
        this.maxJump = maxJump;
        this.maxRun  = maxRun;
    }

    @Override
    public double jump() {
        System.out.println("Робот " + this.name + " прыгнул(-а)");
        return getMaxJump();
    }

    @Override
    public double run() {
        System.out.println("Робот " + this.name + " побежал(-а)");;
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
