package com.team2502.offseason2019.command;

import com.team2502.offseason2019.Robot;
import com.team2502.offseason2019.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

    public DriveCommand() {
        this(DrivetrainSubsystem.TeleopMode.VOLTAGE);
    }

    public DriveCommand(DrivetrainSubsystem.TeleopMode teleopMode) {
        requires(Robot.DRIVETRAIN);
        Robot.DRIVETRAIN.setTeleopMode(teleopMode);
    }

    @Override
    protected void execute() {
        Robot.DRIVETRAIN.runTeleop();
    }

    @Override
    protected void end() {
        Robot.DRIVETRAIN.stopWheels();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}