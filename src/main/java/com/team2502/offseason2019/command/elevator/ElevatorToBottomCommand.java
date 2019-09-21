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

    /**
     * @return True if the motors aren't going fast enough, signalling that the elevator is at the bottom
     */
    @Override
    protected boolean isFinished()
    {
        return (shouldReset = (Robot.ELEVATOR.getEncoderVel() > Constants.Physical.Elevator.ENCODER_RESET_THRESHOLD));
    }

    /**
     *  Only resets encoders and sets current elevator position to "BOTTOM" if it has actually reached the bottom before command ends
     *  Stops elevator
     */
    @Override
    protected void end()
    {
        if(shouldReset)
        {
            Robot.ELEVATOR.setEncoderPos(0);
            Robot.ELEVATOR.setCurrentPos(ElevatorSubsystem.ElevatorLevel.BOTTOM);
        }
        else
        {
            Robot.ELEVATOR.setCurrentPos(ElevatorSubsystem.ElevatorLevel.OTHER);
        }
        Robot.ELEVATOR.stopElevator();
    }
}
