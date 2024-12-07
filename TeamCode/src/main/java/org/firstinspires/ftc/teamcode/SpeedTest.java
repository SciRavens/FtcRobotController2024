package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SpeedTest")
public class SpeedTest extends LinearOpMode {
    public Robot robot;
    public Wrist wrist;
    public Arm arm;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        wrist = new Wrist(robot);
        arm = new Arm(robot);

        waitForStart();
        while(opModeIsActive()) {
            wrist.operate();
            arm.operate();
            actuate();
        }
    }

    private void actuate()
    {
        if (gamepad2.a) {
            arm.target = robot.arm_pos_sample;
            wrist.target = robot.wrist_pos_sample;
        } else if (gamepad2.b) {
            arm.target = robot.arm_pos_starting;
            wrist.target = robot.wrist_pos_starting;
        }
    }

}

