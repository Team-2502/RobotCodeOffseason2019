package com.team2502.offseason2019.command;

import com.team2502.offseason2019.OI;
import com.team2502.offseason2019.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static com.team2502.offseason2019.subsystem.vision.VisionSubsystem.Status.lostTarget;

public class DriveWithTurningAssistCommand extends InstantCommand {

    private double throttle;
    private double turn;

    public DriveWithTurningAssistCommand() { requires(Robot.DRIVETRAIN); }

    @Override
    protected void initialize() {
        throttle = OI.JOYSTICK_DRIVE_RIGHT.getY();

        if(Robot.VISION.getStatus() == lostTarget){
            System.out.println("NO VISION TARGET");
            return;
        }

        turn = Robot.VISION.getOutput();
        Robot.DRIVETRAIN.runArcade(throttle, turn);

        SmartDashboard.putNumber("Turn", turn);
        System.out.println("Turn");
        System.out.println(turn);
        System.out.println("Throttle");
        System.out.println(throttle);

    }

}
