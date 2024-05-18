package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class Servo1Test extends OpMode {
    public static double pos = 0.5;
    Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo1");
    }

    @Override
    public void loop() {
        servo.setPosition(pos);
    }
}
