package com.team2502.offseason2019.command.elevator;

import com.team2502.offseason2019.Constants;
import com.team2502.offseason2019.Robot;
import com.team2502.offseason2019.subsystem.ElevatorSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToBottomCommand extends Command
{

    private double speed;

    private boolean shouldReset;

    public ElevatorToBottomCommand()
    {
        this.speed = Constants.Physical.Elevator.ELEVATOR_DOWN_SPEED;

        requires(Robot.ELEVATOR);
    }

    @Override
    protected void execute()
    {
        Robot.ELEVATOR.moveElevator(speed);
    }

    @Override
    protected boolean isFinished()
    {
        return (shouldReset = (Robot.ELEVATOR.getEncoderVel() > Constants.Physical.Elevator.ENCODER_RESET_THRESHOLD));
    }

    @Override
    protected void end()
    {
        Robot.ELEVATOR.setEncoderPos(shouldReset? 0 : Robot.ELEVATOR.getEncoderPos());
        Robot.ELEVATOR.setCurrentPos(ElevatorSubsystem.ELEVATOR_POS.BOTTOM);
        Robot.ELEVATOR.stopElevator();
    }
}
