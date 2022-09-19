// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.RobotSystem;

public class Steps {

    RobotSystem robot;

    public Steps(RobotSystem robotSystem) {
        robot = robotSystem;
    }

    public void startStep1() {
        robot.resetTimer();

    }

    public void step1() {
        robot.drive(0.0, .5);
        // if (robot.time > 5) {
        //     robot.setStep(2);
        // }
    }

    public void startStep2() {
        robot.resetTimer();
        robot.resetGyro();
    }

    public void step2() {
        robot.drive(0,0.5);
        if (robot.angle > 90) {
            robot.setStep(1);
        }
    }
    

    public void startStep3() {

    }

    public void step3() {
        
    }

    public void startStep4() {
    }

    public void step4() {
    }

}
