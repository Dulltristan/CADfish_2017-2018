import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Tristan Jibben on 11/26/2017.
 */
@TeleOp(name="Driver control", group="TeleOp")
public class DriverControl extends OpMode {

    private DcMotor FrontRight;
    // Motor for Front right wheel, other variables named similarly are also part of this naming system
    private DcMotor FrontLeft;
    private DcMotor BackLeft;
    private DcMotor BackRight;
    private double power;
    //variable for changing drive train speed


    @Override
    public void init() {
        FrontLeft = hardwareMap.get(DcMotor.class, "Front Left");
        FrontRight = hardwareMap.get(DcMotor.class, "Front Right");
        BackLeft = hardwareMap.get(DcMotor.class, "Back Left");
        BackRight = hardwareMap.get(DcMotor.class, "Back Right");
        //declaring variables for phones to understand

    }

    @Override
    public void loop() {
        if (gamepad1.left_bumper) {

            power = 0.25;


        }
        else {
            power = 1.0;
            // if the left bumper is pressed, drive train speed is quartered, else it is at full speed
        }


//declaring temporary variables for holonomic formulas only
        double gamepad1LeftY = -gamepad1.left_stick_y * power;
        double gamepad1LeftX = gamepad1.left_stick_x * power;
        double gamepad1RightX = gamepad1.right_stick_x * power;

        // holonomic formulas

        double fl = gamepad1LeftY - gamepad1LeftX + gamepad1RightX;
        double fr = -gamepad1LeftY - gamepad1LeftX + gamepad1RightX;
        double br = gamepad1LeftY + gamepad1LeftX + gamepad1RightX;
        double bl = -gamepad1LeftY + gamepad1LeftX + gamepad1RightX;


        // write the values to the motors
        FrontRight.setPower(fl);
        FrontLeft.setPower(fr);
        BackLeft.setPower(bl);
        BackRight.setPower(br);

    }
}
