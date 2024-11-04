package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Gamepad;



public class Arm {
    private Robot robot;
    private Gamepad gamepad;
    static private double pos_sample = 0.25;
    static private double pos_basket = 0.5;
    static private double pos_specimen = 0.27;

    static private double pos_chamber = 0.45;

    //    static private double pos_whitepixel = 0.215;
    public Arm(Robot robot, Gamepad gamepad) {
        this.robot = robot;
        this.gamepad = gamepad;
        //arm_pixel();
    }

    public void setPosSample() {
        robot.servoArm.setPosition(pos_sample);
    }

    public void setPosBasket() {
        robot.servoArm.setPosition(pos_basket);
    }

    public void setPosSpecimen() {
        robot.servoArm.setPosition(pos_specimen);
    }

    public void setPosChamber() {
        robot.servoArm.setPosition(pos_chamber);
    }

    public void setPosAbsolute(double pos) {
        robot.servoArm.setPosition(pos);
    }

}


