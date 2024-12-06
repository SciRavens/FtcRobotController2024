package org.firstinspires.ftc.teamcode;
//import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;


import com.qualcomm.robotcore.hardware.Gamepad;

public class Wrist {
    private Robot robot;
    public double target;
    private double speed = 0.005;

    public boolean speed_control = false;

    public Wrist(Robot robot) {

        this.robot = robot;
        this.target = robot.servoWrist.getPosition();
    }

    public void setPosStarting(boolean sc_on){
        if (sc_on) {
            setSCTarget(robot.wrist_pos_starting);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_starting);
        }
    }
    public void setPosSample(boolean sc_on)
    {
        if (sc_on) {
            setSCTarget(robot.wrist_pos_sample);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_sample);
        }
    }
    public void setPosSampleTwo(boolean sc_on)
    {
        if (sc_on) {
            setSCTarget(robot.wrist_pos_sample_two);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_sample_two);
        }
    }
    public void setPosSpecimen(boolean sc_on)
    {
        if (sc_on) {
            setSCTarget(robot.wrist_pos_specimen);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_specimen);
        }
    }
    public void setPosHighChamber(boolean sc_on) {
        if (sc_on) {
            setSCTarget(robot.wrist_pos_high_chamber);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_high_chamber);
        }
    }
   // public void setPosLowChamber() {robot.servoWrist.setPosition(robot.wrist_pos_low_chamber);}

    public void setPosBasket(boolean sc_on)
    {
        if (sc_on) {
            setSCTarget(robot.wrist_pos_basket);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_basket);
        }
    }

    public void setPosChamberAuton(boolean sc_on)
    {
        if (sc_on) {
            setSCTarget(robot.wrist_pos_chamber_auton);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_chamber_auton);
        }
    }

    public void setChamberWristPush(boolean sc_on) {
        if (sc_on) {
            setSCTarget(robot.wrist_pos_autonomous_chamber);
        } else {
            robot.servoWrist.setPosition(robot.wrist_pos_autonomous_chamber);
        }
    }

    public void setPosAbsolute(double pos)
    {
        robot.servoWrist.setPosition(pos);
    }

    public void setSCTarget(double target) {
        speed_control = true;
        this.target = target;
    }

    public void operate() {
        if (speed_control) {
            double curr_pos = robot.servoWrist.getPosition();
            if (Math.abs(target - curr_pos) > speed) {
                double next_pos = curr_pos + speed * ((target > curr_pos) ? 1 : -1);
                robot.servoWrist.setPosition(next_pos);
            } else {
                speed_control = false;
            }
        }
        robot.telemetry.addData("Wrist Curr Pos:", robot.servoWrist.getPosition());
        robot.telemetry.addData("Wrist Target:", this.target);
        robot.telemetry.addData("Wrist Speed Control: ", speed_control);
    }

    /*
    public class WristStartingFoldAction implements Action {
        @Override
        public boolean run (@NonNull TelemetryPacket packet) {
            setPosStarting(false);
            return false;
        }
    }

    public Action setStartingFoldAction() {
        return new WristStartingFoldAction();
    }

    public class WristHighChamberAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosHighChamber(false);
            return false;
        }
    }
    public Action setHighChamberAction() {
        return new WristHighChamberAction();
    }

    public class WristChamberAutonomousAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosChamberAuton(false);
            return false;
        }
    }
    public Action setLowChamberAction() {
        return new WristChamberAutonomousAction();
    }


    public class WristSpecimenAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSpecimen(false);
            return false;
        }
    }
    public Action setSpecimenAction() {
        return new WristSpecimenAction();
    }

    public class WristSampleAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSample(false);
            return false;
        }
    }
    public Action setSampleAction() {
        return new WristSampleAction();
    }

    public class WristChamberAutonAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosBasket(false);
            return false;
        }
    }
    public Action setChamberAutonAction() {
        return new WristChamberAutonAction();
    }


    public class WristBasketAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSpecimen(false);
            return false;
        }
    }
    public Action setBasketAction() {
        return new WristBasketAction();
    }

    public class setWristStartingAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosStarting(false);
            return false;
        }
    }
    public Action setWristStartingAction() {
        return new setWristStartingAction();
    }



     */

}
