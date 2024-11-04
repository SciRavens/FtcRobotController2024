package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.apache.commons.math3.analysis.function.Floor;

public class Slider extends EncoderMotorOps {
    private Robot robot;
    private Gamepad gamepad;
    private double manual_speed_factor = 1.0;
    static final private double auto_power = 0.5;
    static final private int pos_max = 7800;
    static final private int pos_min = 0;
    private int pos_middle = 3900;
   //private int pos_auton = 1000;

    private int pos_auton = 1000;
    private boolean verbose = false;
    private int motor_ticks = 1425;

    private int rev_ticks = 250;

    private int Floor_ticks = 0;
    private int LowBasket_ticks = 1483;
    private int LowChamber_ticks = 249;
    private int HighBasket_ticks = 1988;
    private int HighChamber_ticks = 1244;

    public Slider(Robot robot, Gamepad gamepad)
    {
        super(robot, robot.motorSlider, pos_min, pos_max, auto_power, true);
        this.robot = robot;
        this.gamepad = gamepad;
    }

    public void Floor(){autoOp(Floor_ticks);}
    public void LowBasket() {autoOp(LowBasket_ticks);}
//    Move slider height to Low basket
    public void LowChamber() {autoOp(LowChamber_ticks);}
//    Move slider height to Low Chamber
    public void HighBasket() {autoOp(HighBasket_ticks);}
//    Move slider height to Low basket
    public void HighChamber() {
        autoOp(HighChamber_ticks);
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
        manualOp(power);
        logUpdate();
    }

    public void operate() {
        autoOpCompletionCheck();
        if (gamepad.dpad_down) {
            // Go to the bottom
            autoOp(pos_min);
        } else if (gamepad.dpad_up) {
            // Goto the middle
            autoOp(pos_middle);
        } else if (gamepad.left_stick_y != 0) {
            manualOp(gamepad.left_stick_y * manual_speed_factor);
        } else {
            manualDefaultStop();
        }
        logUpdate();
    }
}
