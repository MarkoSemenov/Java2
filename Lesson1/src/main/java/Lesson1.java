public class Lesson1 {

    public static void main(String[] args) {

        RunAndJump[] participants = new RunAndJump[6];
        Barriers[] barriers = new Barriers[6];

        for (int j = 0; j < barriers.length ; j++) {
            int random = (int)(Math.random() * 10);
            if (random >= 5){
            barriers[j] = new Treadmill(random, "Беговая дорожка FX"+random);
            } else barriers[j] = new Wall (random);

        }

        participants[0] = new Human("Sam", 28, 2.1, 8);
        participants[1] = new Human("Olivia", 18, 1.5, 12);
        participants[2] = new Robot("R2D2", "Prototype800", 20, 50);
        participants[3] = new Cat("Simon", "British", 5, 2.2, 10.3);
        participants[4] = new Cat("Kitti", "Siamese", 8, 2, 6.7);
        participants[5] = new Robot("iRobo", "Jumper4000", 800, 10);

        for (RunAndJump participant : participants) {
            for (Barriers i : barriers) {
                if (!(i.running(participant)) || !(i.jumping(participant))) {
                    System.out.println(participant.toString() + " выбывает из соревнования");
                    break;
                }

            }

        }

    }
}
