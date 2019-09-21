package com.team2502.offseason2019.command.elevator;

import com.team2502.offseason2019.Robot;
import com.team2502.offseason2019.subsystem.ElevatorSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ManualElevatorCommand extends Command
{
    private double speed;

    /**
     * @param speed Percent voltage to run motors at
     */
    public ManualElevatorCommand(double speed)
    {
        requires(Robot.ELEVATOR);
    }

    @Override
    protected void execute()
    {
        Robot.ELEVATOR.moveElevator(speed);
    }

    @Override
    protected boolean isFinished(){
        return false;
    }

    @Override
    protected void end()
    {
        Robot.ELEVATOR.setCurrentPos(ElevatorSubsystem.ElevatorLevel.OTHER);
        Robot.ELEVATOR.stopElevator();
    }
}
