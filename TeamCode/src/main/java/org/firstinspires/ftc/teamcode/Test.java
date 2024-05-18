package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp
public class Test extends OpMode {
    Arm arm;

    @Override
    public void init() {
        arm = new Arm(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    public static double velocity = 10, acceleration = 3, deceleration = 1;
    AsymmetricMotionProfile profile = new AsymmetricMotionProfile(velocity,acceleration,deceleration);
    public static double cx = 200, cy = 0, cz = 0;
    public static double x = 200, y = 0, z = 0;
    public static double d;
    public static boolean startMove = false;
    public static boolean moving = false;

    @Override
    public void loop() {
        if(startMove){
            d = Math.sqrt((cx-x)*(cx-x) + (cy-y)*(cy-y) + (cz-z)*(cz-z));
            profile.setMotion(0,d,0);
            startMove = false;
            moving = true;
        }
        if(profile.getTimeToMotionEnd() == 0 && moving){
            cx = x;
            cy = y;
            cz = z;
            moving = false;
        }
        double t = 0;
        if(moving){
            t = profile.getPosition()/d;
            arm.setPoint(cx + t * (x-cx), cy + t * (y-cy), cz + t * (z-cz));
        }
        else {
            arm.setPoint(cx, cy, cz);
        }
        profile.update();

        telemetry.addData("x", cx + t * (x-cx));
        telemetry.addData("y", cy + t * (y-cy));
        telemetry.addData("z", cz + t * (z-cz));
        telemetry.addData("moving", moving);
        telemetry.addData("Time left", profile.getTimeToMotionEnd());
        arm.telemetry(telemetry);
        telemetry.update();
    }
}
