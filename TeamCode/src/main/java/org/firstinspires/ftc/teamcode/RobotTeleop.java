package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "SciRavens-TeleOp")
public class RobotTeleop extends LinearOpMode {
    public Robot robot;
    public DriveTrain DT;
    public Slider slider;
    public Arm arm;
    public Wrist wrist;
    public Claw claw;
    public ClawAngle clawAngle;
    public DcMotorEx par0, par1, perp;

    RevBlinkinLedDriver.BlinkinPattern pattern;
    Leds leds;
private int cur = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        DT = new DriveTrain(robot, gamepad1);
        slider = new Slider(robot, gamepad2);
        arm = new Arm(robot);
        wrist = new Wrist(robot);
        claw = new Claw(robot);
        clawAngle = new ClawAngle(robot);

        par0 = hardwareMap.get(DcMotorEx.class, "rightFront");
        par1 = hardwareMap.get(DcMotorEx.class, "leftRear");
        perp = hardwareMap.get(DcMotorEx.class, "rightRear");

        leds = new Leds(robot);
        leds.setPattern(0);
        arm.setPosStarting(false);
        wrist.setPosStarting(false);
        waitForStart();
        leds.setPattern(cur);
        while(opModeIsActive()) {
            DT.drive();
            arm.operate();
            wrist.operate();
//            slider_operate();
            slider_joystick();
            arm_wrist_operate();
            claw_operate();
            leds_operate();
            get_ticks();
            robot.telemetry.update();
        }
    }

    private void arm_wrist_operate()
    {
        if (gamepad2.dpad_down) {
            arm.setPosSample(true);
            wrist.setPosSample(true);
            //arm.setSCTarget(robot.arm_pos_sample);
            //wrist.setSCTarget(robot.wrist_pos_sample);
        } else if (gamepad2.y) {
            arm.setPosBasket(true);
            wrist.setPosBasket(true);
            //arm.setSCTarget(robot.arm_pos_basket);
            //wrist.setSCTarget(robot.wrist_pos_basket);
        } else if(gamepad2.x) {
            arm.setPosStarting(false);
            wrist.setPosStarting(false);
            //arm.setSCTarget(robot.arm_pos_starting);
            //wrist.setSCTarget(robot.wrist_pos_starting);
        } else if(gamepad2.b) {
            arm.setPosSpecimen(true);
            wrist.setPosSpecimen(true);
            //arm.setSCTarget(robot.arm_pos_specimen);
            //wrist.setSCTarget(robot.wrist_pos_specimen);
        }
        else if(gamepad2.a){
            arm.setPosSampleTwo(true);
            wrist.setPosSampleTwo(true);
            //arm.setSCTarget(robot.arm_pos_sample_two);
            //wrist.setSCTarget(robot.wrist_pos_sample_two);
        }
        else if(gamepad2.dpad_up) {
            // TBD: fix this
            arm.setSCTarget(robot.arm_pos_chamber);
            wrist.setSCTarget(robot.wrist_pos_high_chamber);
        }
        else if(gamepad2.dpad_right) {
            clawAngle.setHorizontal();
        }
        else if(gamepad2.dpad_left) {
            clawAngle.setVertical();
        }
    }



    public void slider_operate() {
        slider.autoOpCompletionCheck();
        if (gamepad2.dpad_down) {
            // Go to Low Chamber
            slider.LowChamber();
        } else if (gamepad2.dpad_up) {
            // Go to High Basket
            slider.HighBasket();
        } else if  (gamepad2.dpad_right) {
            // Move to Low Basket
            slider.LowBasket();
        } else if (gamepad2.dpad_left) {
            // Move to High Chamber
            slider.HighChamber();
        }
    }

    public void slider_joystick() {
        if (gamepad2.left_stick_y != 0) {
            slider.manualOp(gamepad2.left_stick_y);
        } else {
            slider.manualDefaultStop();
        }
    }


    public void get_ticks() {

        robot.telemetry.addData("rightFront ticks", par0.getCurrentPosition());
        robot.telemetry.addData("leftRear ticks", par1.getCurrentPosition());
        robot.telemetry.addData("rightRear ticks", perp.getCurrentPosition());
        robot.telemetry.update();
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

