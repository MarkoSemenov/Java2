public class Treadmill implements Barriers{

    private double treadmillLength;
    private String treadmillType;

    public Treadmill(int treadmillLength, String treadmillType) {
        this.treadmillLength = treadmillLength;
        this.treadmillType = treadmillType;
    }

    public double getTreadmillLength() {
        return treadmillLength;
    }

    public void setTreadmillLength(double treadmillLength) {
        this.treadmillLength = treadmillLength;
    }

    public String getTreadmillType() {
        return treadmillType;
    }

    public void setTreadmillType(String treadmillType) {
        this.treadmillType = treadmillType;
    }

    @Override
    public String toString() {
        return getTreadmillType();
    }

    @Override
    public boolean running(RunAndJump ob) {
       if (getTreadmillLength() <= ob.run()) {
           System.out.println((ob.toString() + " пробежал(-а): " + getTreadmillLength() + " км."));
           return true;
       }
        System.out.println(ob.toString() + " не смог(-ла) пробежать: " + getTreadmillType());
        return false;
    }

    @Override
    public boolean jumping(RunAndJump ob) {
        return true;
    }
}
