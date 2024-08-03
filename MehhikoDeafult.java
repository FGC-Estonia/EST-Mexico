
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannel.Mode;



/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Mexico_ElevatorSafeguard")

public class Mexico_ElevatorSafeguard extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx ne = null;
    private DcMotorEx nw = null;
    private DcMotorEx se = null;
    private DcMotorEx sw = null;
    private DcMotor lift1 = null;
    private DcMotor lift2 = null;
    private DcMotor press = null;
    DigitalChannel touchSensor;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        touchSensor = hardwareMap.digitalChannel.get("touch_sensor");
        touchSensor.setMode(Mode.INPUT);
        ne  = hardwareMap.get(DcMotorEx.class, "ne");
        nw = hardwareMap.get(DcMotorEx.class, "nw");
        se  = hardwareMap.get(DcMotorEx.class, "se");
        sw = hardwareMap.get(DcMotorEx.class, "sw");
        lift1 = hardwareMap.get(DcMotor.class, "lift1");
        lift2 = hardwareMap.get(DcMotor.class, "lift2");
        press = hardwareMap.get(DcMotor.class, "aivarvinne");
        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double ypower = gamepad1.left_stick_y*300;
            double xpower  =  -gamepad1.left_stick_x*300;
            double turn = -gamepad1.right_stick_x*0.6;
            boolean isSwitchActive = !touchSensor.getState();

            ne.setPower(xpower + turn);
            sw.setPower(-xpower + turn);
            nw.setPower(ypower + turn);
            se.setPower(-ypower + turn);
            if(gamepad1.left_bumper){
                lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                lift1.setPower(1);
                lift2.setPower(-1);
            }else if(gamepad1.left_trigger > 0.5){
                lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                lift1.setPower(-1);
                lift2.setPower(1);
            }
            else{
                lift1.setTargetPosition(lift1.getCurrentPosition());
                lift2.setTargetPosition(lift2.getCurrentPosition());
                lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(gamepad1.right_trigger > 0.5){
                press.setPower(0.6);
            }
            else if(gamepad1.right_bumper){
                press.setPower(-0.2);
            }


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "x (%.2f), y (%.2f)", ne.getVelocity(), ypower);
            telemetry.addData("Switch Status", isSwitchActive ? "Active" : "Inactive");
            telemetry.update();
        }
    }
}
