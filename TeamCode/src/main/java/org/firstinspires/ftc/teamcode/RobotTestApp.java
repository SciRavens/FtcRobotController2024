package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "RobotTestApp")
public class RobotTestApp extends LinearOpMode {
    public Robot robot;
    public DriveTrain DT;
    public Slider slider;
    public Arm arm;
    public Wrist wrist;
    public Claw claw;

    RevBlinkinLedDriver.BlinkinPattern pattern;
    Leds leds;

    int target_pos = 0;
    boolean inAutoOp = false;
private int cur = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        //DT = new DriveTrain(robot, gamepad1);
        //slider = new Slider(robot, gamepad2);
        arm = new Arm(robot, gamepad2);
        //wrist = new Wrist(robot, gamepad2);
        //claw = new Claw(robot);

        //leds = new Leds(robot);
        //leds.setPattern(0);
        slider_auto_init();
        waitForStart();
        //leds.setPattern(cur);
        while(opModeIsActive()) {
            arm_operate();
            slider_operate();
        }
    }

    private void slider_auto_init()
    {
        robot.motorSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.motorSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorSlider.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorSlider.setTargetPositionTolerance(5);
    }

    private void slider_auto_pos(int target)
    {
        robot.motorSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorSlider.setTargetPosition(target);
        robot.motorSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorSlider.setPower(-1.0);
        target_pos = target;
    }
    private void slider_operate() {
        if (gamepad2.dpad_up) {
            slider_auto_pos(-300);
            inAutoOp = true;
        } else if (gamepad2.dpad_down) {
            slider_auto_pos(0);
            inAutoOp = true;
        } else if (gamepad2.dpad_left) {
            slider_auto_pos(-600);
            inAutoOp = true;
        } else if (gamepad2.dpad_right) {
            slider_auto_pos(-1000);
            inAutoOp = true;
        } else if (gamepad2.left_stick_y != 0) {
            slider.controlOp(gamepad2.left_stick_y);
            inAutoOp = false;
        } else if (inAutoOp == false) {
            slider.controlOp(0);
        }
        robot.telemetry.addData("Current Position: ", robot.motorSlider.getCurrentPosition());
        robot.telemetry.addData("Target Position: ", target_pos);
        robot.telemetry.update();
    }
    private void arm_operate()
    {
        if (gamepad2.a) {
            arm.setPosAbsolute(1.0);
        } else if (gamepad2.b) {
            arm.setPosAbsolute(0.0);
        } else if(gamepad2.y) {
            arm.setPosAbsolute(0.5);
        } else if(gamepad2.x) {
            arm.setPosAbsolute(0.25);
        }
    }

    private void claw_operate() {
        if (gamepad2.left_trigger > 0.9) {
            claw.open();
        } else if (gamepad2.right_trigger > 0.9) {
            claw.open();
        } else {
            claw.close();
        }
    }
    private void leds_operate() {
        if (gamepad2.right_bumper || gamepad1.right_bumper) {
            cur = (cur + 1) % leds.patterns.length;
            leds.setPattern(cur);
            telemetry.addData("SETTING COLOR", leds.patterns[cur].toString());
            telemetry.update();
        }
    }
}

