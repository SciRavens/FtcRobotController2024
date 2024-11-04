package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Wrist {
    private Robot robot;
    private Gamepad gamepad;
    static private double pos_sample  = 0.6;

    static private double pos_specimen = 0.35;
    static private double pos_basket = 0.5;
    static private double pos_chamber  = 0.5;

    public Wrist(Robot robot, Gamepad gamepad) {
        this.robot = robot;
        this.gamepad = gamepad;
        //arm_pixel();
    }

    public void setPosSample()
    {
        robot.servoWrist.setPosition(pos_sample);
    }

    public void setPosSpecimen()
    {
        robot.servoWrist.setPosition(pos_specimen);
    }

    public void setPosBasket()
    {
        robot.servoWrist.setPosition(pos_basket);
    }

    public void setPosChamber()
    {
        robot.servoWrist.setPosition(pos_chamber);
    }

    public void setPosAbsolute(double pos)
    {
        robot.servoWrist.setPosition(pos);
    }
}
