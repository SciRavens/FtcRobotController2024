package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "Test-Autonomous")
public class AutonTestApp extends LinearOpMode {
    public Robot robot;
    public SampleMecanumDrive drive;
    public Slider slider;
    public Arm arm;

    String curAlliance = "red";
    public int zone = 2;
    TrajectorySequence trajRedZone1;

    Leds leds;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        drive = robot.mDrive;
        slider = new Slider(robot, gamepad2);
        arm = new Arm(robot);
        //tag = new AprilTag(robot);
        leds = new Leds(robot);
        leds.setPattern(1);

        // Fold and open the claws for placing the pixels
        arm.setPosStarting(false);
        sleep(500);

        robot.telemetry.addData("Front Left: ", robot.motorFL.getCurrentPosition());
        robot.telemetry.addData("Front Right: ", robot.motorFR.getCurrentPosition());
        robot.telemetry.addData("Back Left: ", robot.motorBL.getCurrentPosition());
        robot.telemetry.addData("Back Right: ", robot.motorBR.getCurrentPosition());
        robot.telemetry.update();

        buildRedZone1Trajectory();
        waitForStart();

        if(isStopRequested()) {
            return;
        }

        if(opModeIsActive()) {
            robot.mDrive.followTrajectorySequence(trajRedZone1);
        }
        robot.telemetry.addData("Front Left: ", robot.motorFL.getCurrentPosition());
        robot.telemetry.addData("Front Right: ", robot.motorFR.getCurrentPosition());
        robot.telemetry.addData("Back Left: ", robot.motorBL.getCurrentPosition());
        robot.telemetry.addData("Back Right: ", robot.motorBR.getCurrentPosition());
        robot.telemetry.update();
        leds.setPattern(10);
        sleep(1000);
    }

    // Build Zone1 trajectory
    private void buildRedZone1Trajectory() {
        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);
        trajRedZone1 = drive.trajectorySequenceBuilder(startPose)
                .strafeLeft(100)
                //.forward(60)
                .build();
    }
}