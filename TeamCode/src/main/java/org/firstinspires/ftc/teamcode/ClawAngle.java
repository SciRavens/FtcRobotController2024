package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Servo;


public class ClawAngle {
    Servo servo;
    private boolean horizontal = true;
    double horizontal_pos;
    double vertical_pos;


    public ClawAngle(Robot robot) {
        this.horizontal_pos = robot.claw_horizontal;
        this.vertical_pos = robot.claw_vertical;
        this.servo = robot.servoCR;
        this.servo.setPosition(horizontal_pos);
        horizontal = true;
    }

    public void setHorizontal()
    {
        if(!horizontal) {
            servo.setPosition(horizontal_pos);
            horizontal = true;
        }
    }
    public void setVertical()
    {
        if (horizontal) {
            servo.setPosition(vertical_pos);
            horizontal = false;
        }
    }

    public void setPosAbsolute(double pos) {
        servo.setPosition(pos);
    }

    /*
    public class HoriztontalClawAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setHorizontal();
            return false;
        }
    }
    public Action horizontalClawAction() {
        return new HoriztontalClawAction();
    }

    public class VerticalClawAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setVertical();
            return false;
        }
    }
    public Action verticalClawAction() {
        return new VerticalClawAction();
    }
     */
}
