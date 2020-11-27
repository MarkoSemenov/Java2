public class Wall implements Barriers {

    private double height;

    public Wall(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Стена: " + getHeight() + " м.";
    }

    @Override
    public boolean running(RunAndJump ob) {
        return true;
    }

    @Override
    public boolean jumping(RunAndJump ob) {
        if (getHeight() < ob.jump()) {
            System.out.println((ob.toString() + " перепрыгнул(-а) через стену: " + getHeight() + " м."));
            return true;
        }
        System.out.println(ob.toString() + " не смог(-ла) перепрыгнуть через стену: " + getHeight() + " м.");
        return false;
    }
}
