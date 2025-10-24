import codewars.api.Robot;
import codewars.impl.cmd.RobotWithWorldCreation;
import java.util.Random;
import static codewars.api.Rotation.LEFT;
import static codewars.api.Rotation.RIGHT;

public class HomeWork {
    public static void main(String[] args) {
        Robot robot = new RobotWithWorldCreation("C:\\Users\\user\\Desktop\\18 oct\\map6.rmc");
        robot.setSpeed(9);

        Random random = new Random();

        while (robot.collectedItemsCount() < robot.totalItemsCount()) {
            int action = random.nextInt(10);

            if (action <= 7) {
                moveIfPossible(robot);
            } else {
                robot.rotate(action == 8 ? RIGHT : LEFT);
                moveIfPossible(robot);
            }
        }

        robot.finish();
    }

    private static void moveIfPossible(Robot robot) {
        if (!robot.hasObstacle()) {
            robot.move(1);
        }
    }
}
