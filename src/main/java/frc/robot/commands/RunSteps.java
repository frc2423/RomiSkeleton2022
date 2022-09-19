// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.RobotSystem;
import frc.robot.NtHelper;


public class RunSteps extends CommandBase {
    private Drivetrain drivetrain;
    private XboxController joystick;
    private int step;
    private Steps steps;
    private RobotSystem robot;
    private Timer timer = new Timer();

    public RunSteps(Drivetrain drive, XboxController joy) {
        drivetrain = drive;
        joystick = joy;
        step = 0;
        robot = new RobotSystem(drivetrain, timer);
        steps = new Steps(robot);
        addRequirements(drivetrain);
        timer.start();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        robot.setStep(1);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        updateVariables();
        switch (step) {
            case 1:
                steps.step1();
                break;
            case 2:
                steps.step2();
                break;
            case 3:
                steps.step3();
                break;
            case 4:
                steps.step4();
                break;
            default:
                break;
        }

        if (robot.getNextStep() > -1) {
            switch (robot.getNextStep()) {
                case 1:
                    steps.startStep1();
                    break;
                case 2:
                    steps.startStep2();
                    break;
                case 3:
                    steps.startStep3();
                    break;
                case 4:
                    steps.startStep4();
                    break;
            }
            step = robot.getNextStep();
            robot.setStep(-1);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    // update any variables to uses during steps
    private void updateVariables() {
        robot.isButtonAPressed = joystick.getAButton();
        robot.isButtonBPressed = joystick.getBButton();
        robot.isButtonXPressed = joystick.getXButton();
        robot.isButtonYPressed = joystick.getYButton();
        robot.wasButtonAReleased = joystick.getAButtonReleased();
        robot.wasButtonBReleased = joystick.getBButtonReleased();
        robot.wasButtonXReleased = joystick.getXButtonReleased();
        robot.wasButtonYReleased = joystick.getYButtonReleased();
        robot.angle = drivetrain.getGyroAngleZ();
        robot.distance = drivetrain.getAverageDistanceInch();
        robot.joystickXAxis = joystick.getRawAxis(0);
        robot.joystickYAxis = joystick.getRawAxis(1);
        robot.time = timer.get();
        NtHelper.setNumber("/robot/angle", robot.angle);
        NtHelper.setNumber("/robot/speed", robot.speed);
        NtHelper.setNumber("/robot/distance", robot.distance);
        NtHelper.setNumber("/robot/joystickXAxis", robot.joystickXAxis);
        NtHelper.setNumber("/robot/joystickYAxis", robot.joystickYAxis);
        NtHelper.setNumber("/robot/time", robot.time);
        NtHelper.setNumber("/robot/step", step);
        NtHelper.setBoolean("/robot/isButtonAPressed", robot.isButtonAPressed);
        NtHelper.setBoolean("/robot/isButtonBPressed", robot.isButtonBPressed);
        NtHelper.setBoolean("/robot/isButtonXPressed", robot.isButtonXPressed);
        NtHelper.setBoolean("/robot/isButtonYPressed", robot.isButtonYPressed);
        NtHelper.setBoolean("/robot/wasButtonAReleased", robot.wasButtonAReleased);
        NtHelper.setBoolean("/robot/wasButtonBReleased", robot.wasButtonBReleased);
        NtHelper.setBoolean("/robot/wasButtonXReleased", robot.wasButtonXReleased);
        NtHelper.setBoolean("/robot/wasButtonYReleased", robot.wasButtonYReleased);
    }
}
