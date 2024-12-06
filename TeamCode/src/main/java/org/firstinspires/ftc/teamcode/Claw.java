package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
//import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;




public class Claw {
    Servo servo;
    private boolean closed = true;
    double close_pos;
    double open_pos;


    public Claw(Robot robot) {
        this.close_pos = robot.claw_close;
        this.open_pos = robot.claw_open;
        this.servo = robot.servoCL;
        this.servo.setPosition(close_pos);
        closed = true;
    }

    public void open()
    {
        if(closed) {
            servo.setPosition(open_pos);
            closed = false;

        }
    }
    public void close()
    {
        if (!closed) {
            servo.setPosition(close_pos);
            closed = true;
        }
    }


    public void setPosAbsolute(double pos) {
        servo.setPosition(pos);
    }

    /*
    public class CloseClawAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            close();
            return false;
        }
    }
    public Action closeClawAction() {
        return new CloseClawAction();
    }

    public class OpenClawAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            open();
            return false;
        }
    }
    public Action openClawAction() {
        return new OpenClawAction();
    }

     */
}
