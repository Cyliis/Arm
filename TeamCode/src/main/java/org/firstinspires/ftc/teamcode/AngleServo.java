package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class AngleServo {
    private final double zeroPos;
    private final double range;
    private final Servo servo;

    public double angle;

    public AngleServo(Servo servo, double zeroPos, double range){
        this.servo = servo;
        this.range = range;
        this.zeroPos = zeroPos;
    }

    public void setAngle(double angle){
        this.angle = angle;
        servo.setPosition(zeroPos + angle/range);
    }
}
