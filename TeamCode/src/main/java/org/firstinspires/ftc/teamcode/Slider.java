package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
//import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;

public class Slider extends EncoderMotorOps {
    private Robot robot;
    private Gamepad gamepad;
    private double manual_speed_factor = 1.0;
    static final private double auto_power = 1;
    static final private int pos_max = 7800;
    static final private int pos_min = 0;



    public Slider(Robot robot, Gamepad gamepad)
    {
        super(robot, robot.motorSlider, pos_min, pos_max, auto_power, true);
        this.robot = robot;
        this.gamepad = gamepad;
    }

    public void setPower(double power) {
        robot.motorSlider.setPower(power);
    }
    public int getCurrentPosition() {
        return robot.motorSlider.getCurrentPosition();
    }

//  Move slider to Intial Pose
    public void InitialPose() {autoOp(robot.slider_Intial_Pose_ticks);}
    public void LowBasket() {autoOp(robot.slider_LowBasket_ticks);}
//    Move slider height to Low basket
    public void LowChamber() {autoOp(robot.slider_LowChamber_ticks);}
//    Move slider height to Low Chamber
    public void HighBasket() {autoOp(robot.slider_HighBasket_ticks);}
//    Move slider height to Low basket
    public void HighChamber() {
        autoOp(robot.slider_HighChamber_ticks);
    }

    public void setPosAbsolute(int ticks) {
        autoOp(ticks);
        logUpdate();
    }
    public void fold() {
        autoOp(0);
    }

    public void controlOp(double power)
    {
        manualOp(power * manual_speed_factor);
        logUpdate();
    }

    /*
    public class SliderFold implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(-auto_power);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos > 0) {
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
//        robot.telemetry.addData("Arm Curr Pos:", robot.servoArm.getPosition());
//        robot.telemetry.addData("Arm Target:", this.target);
//        robot.telemetry.addData("Arm Speed Control: ", speed_control);
    }
    public Action sliderFoldAction() {
        return new SliderFold();
    }

    private int counter = 0;

    public class SliderInitialPose implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(-auto_power);
                initialized = true;
            }

            double pos = getCurrentPosition();
            robot.telemetry.addData("Slider Position:", pos);
            robot.telemetry.addData("Slider Counter:", counter++);
            robot.telemetry.update();
            packet.put("sliderPos", pos);
            if (pos > robot.slider_Intial_Pose_ticks) {
                setPower(-auto_power);
                robot.telemetry.addData("Slider Position:", pos);
                robot.telemetry.addData("Slider Counter:", counter++);
                robot.telemetry.update();
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderInitialPoseAction() {
        return new SliderInitialPose();
    }

    public class SliderLowChamber implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(auto_power);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_LowChamber_ticks) {
                setPower(auto_power);
                robot.telemetry.addData("Slider Position:", pos);
                robot.telemetry.addData("Slider Counter:", counter++);
                robot.telemetry.update();
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderLowChamberAction() {
        return new SliderHighChamber();
    }

    public class SliderHighChamber implements Action {
        private boolean initialized = false;
        private int counter = 0;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(auto_power);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_HighChamber_ticks) {
                setPower(auto_power);
                robot.telemetry.addData("Slider Position:", pos);
                robot.telemetry.addData("Slider Counter:", counter++);
                robot.telemetry.update();
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderHighChamberAction() {
        return new SliderHighChamber();
    }

    public class SliderHighBasket implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(auto_power);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_HighBasket_ticks) {
                setPower(auto_power);
                robot.telemetry.addData("Slider Position:", pos);
                robot.telemetry.addData("Slider Counter:", counter++);
                robot.telemetry.update();
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderHighBasketAction() {
        return new SliderHighBasket();
    }

    public class SliderLowBasket implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(auto_power);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_LowBasket_ticks) {
                setPower(auto_power);
                robot.telemetry.addData("Slider Position:", pos);
                robot.telemetry.addData("Slider Counter:", counter++);
                robot.telemetry.update();
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderLowBasketAction() {
        return new SliderLowBasket();
    }

    public class SliderChamberAutonAction implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(auto_power);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_ChamberAuton_ticks) {
                setPower(auto_power);
                robot.telemetry.addData("Slider Position:", pos);
                robot.telemetry.addData("Slider Counter:", counter++);
                robot.telemetry.update();
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderChamberAutonAction() {
        return new SliderChamberAutonAction();
    }

     */
}
