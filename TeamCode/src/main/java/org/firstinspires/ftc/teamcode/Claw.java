package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    Servo servoLeft;
    Servo servoRight;
    private boolean closed = true;
    double left_close, left_open;
    double right_close, right_open;
    double left_close_wide, right_close_wide;


    public Claw(Robot robot) {
        this.servoLeft = robot.servoCL;
        this.left_close = robot.claw_left_close;
        this.left_open = robot.claw_left_open;
        this.servoRight = robot.servoCR;
        this.right_close = robot.claw_right_close;
        this.right_open = robot.claw_right_open;
        this.left_close_wide = robot.claw_left_wide_close;
        this.right_close_wide = robot.claw_right_wide_close;
        servoLeft.setPosition(left_close);
        servoRight.setPosition(right_close);
    }

    public void open()
    {
        if(closed) {
            servoLeft.setPosition(left_open);
            servoRight.setPosition(right_open);
            closed = false;

        }
    }

    public void close_wide()
    {
        if (!closed) {
            servoLeft.setPosition(left_close_wide);
            servoRight.setPosition(right_close_wide);
            closed = true;
        }
    }
    public void close()
    {
        if (!closed) {
            servoLeft.setPosition(left_close);
           servoRight.setPosition(right_close);
            closed = true;
        }
    }
}
