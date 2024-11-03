package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SciRavens-TeleOp")
public class RobotTeleop extends LinearOpMode {
    public Robot robot;
    public DriveTrain DT;
    public Slider slider;
    public Arm arm;
    public Wrist wrist;
    public Claw claw;

    RevBlinkinLedDriver.BlinkinPattern pattern;
    Leds leds;
private int cur = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        DT = new DriveTrain(robot, gamepad1);
        slider = new Slider(robot, gamepad2);
        arm = new Arm(robot, gamepad2);
        wrist = new Wrist(robot, gamepad2);
        claw = new Claw(robot);

        leds = new Leds(robot);
        leds.setPattern(0);

        waitForStart();
        leds.setPattern(cur);
        while(opModeIsActive()) {
            DT.drive();
            slider.operate();
            arm_wrist_operate();
            claw_operate();
            leds_operate();
        }
    }

    private void arm_wrist_operate()
    {
        if (gamepad2.a) {
            //slider.LowBasket();
            arm.setPosSpecimen();
            wrist.setPosSample();
        } else if (gamepad2.b) {
            //slider.LowChamber();
            arm.setPosSample();
            wrist.setPosDrop();
        } else if(gamepad2.y) {
            //slider.HighBasket();
            arm.setPosFold();
            wrist.setPosBasket();
        } else if(gamepad2.x) {
            //slider.HighChamber();
            wrist.setPosSpecimen();
        }
    }

//    private void slider_pos() {
//        if (gamepad2.dpad_up) {
//            slider.LowBasket();
//        } else if (gamepad2.dpad_down) {
//            slider.LowChamber();
//        } else if (gamepad2.dpad_left) {
//            slider.HighBasket();
//        } else if (gamepad2.dpad_right) {
//            slider.HighChamber();
//        }
//
//    }

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

