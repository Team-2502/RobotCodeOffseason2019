package com.team2502.offseason2019.command.elevator;

import com.team2502.offseason2019.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToBottomCommand extends Command
{
    private double speed;

    /**
     * @param speed Percent voltage to run motors at
     */
    public ElevatorToBottomCommand(double speed)
    {
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
        return false;
    }

    @Override
    protected void end()
    {
        Robot.ELEVATOR.stopElevator();
    }
}
