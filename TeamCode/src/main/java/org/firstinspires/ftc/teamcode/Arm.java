package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm {
    private final AngleServo servo0, servo1, servo2;
    private final double range0=Math.toRadians(355), range1 = Math.toRadians(355) * (36.0/48.0), range2 = -Math.toRadians(355);
    private final double zero0 = 0.515, zero1 = 0.045, zero2 = 0.49 - Math.toRadians(90)/range2;

    private final double l1 = 150, l2 = 150;

    public Arm(HardwareMap hm){
        servo0 = new AngleServo(hm.get(Servo.class, "servo0"), zero0, range0);
        servo1 = new AngleServo(hm.get(Servo.class, "servo1"), zero1, range1);
        servo2 = new AngleServo(hm.get(Servo.class, "servo2"), zero2 , range2);
        setPoint(200,0,0);
    }

    public void setPoint(double x, double y, double z){
        servo0.setAngle(Math.atan2(y,x));
        double l = Math.sqrt(x*x + y*y + z*z);
        servo1.setAngle(Math.asin(z/l) + Math.acos((l*l+l1*l1-l2*l2)/(2.0*l*l1)));
        servo2.setAngle(Math.acos((l1*l1+l2*l2-l*l)/(2.0*l1*l2)));
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Base angle", servo0.angle);
        telemetry.addData("Joint one angle", servo1.angle);
        telemetry.addData("Joint two angle", servo2.angle);
    }

}
