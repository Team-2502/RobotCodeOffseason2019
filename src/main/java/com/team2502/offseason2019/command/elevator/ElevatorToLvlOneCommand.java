package com.team2502.offseason2019.command.elevator;

import com.team2502.offseason2019.Constants;
import com.team2502.offseason2019.Robot;
import com.team2502.offseason2019.subsystem.ElevatorSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToLvlOneCommand extends Command
{

    private double speed;

    // The elevator's position in relation to it's target position
    private ElevatorSubsystem.TargetRelations relationToTarget;

    public ElevatorToLvlOneCommand()
    {
        // If at correct stage already, set speed to 0, otherwise set to either UP or DOWN speed based on it's current position
        switch(Robot.ELEVATOR.getRelationToTarget(ElevatorSubsystem.ElevatorLevel.LVL_ONE))
        {
            case WITHIN:
                speed = 0;
                break;
            case LESS:
                speed = Constants.Physical.Elevator.ELEVATOR_UP_SPEED;
                break;
            case GREATER:
                speed = Constants.Physical.Elevator.ELEVATOR_DOWN_SPEED;
                break;
        }

        requires(Robot.ELEVATOR);
    }

    @Override
    protected void execute()
    {
        // Sets relationToTarget to the current relation to the target range
        relationToTarget = Robot.ELEVATOR.getRelationToTarget(ElevatorSubsystem.ElevatorLevel.LVL_ONE);

        Robot.ELEVATOR.moveElevator(speed);
    }

    /**
     * @return True if elevator position is within the target range
     */
    @Override
    protected boolean isFinished()
    {
        return (relationToTarget.equals(ElevatorSubsystem.TargetRelations.WITHIN));
    }

    /**
     *  Only sets current elevator position to "LVL_ONE" if it has actually reached the lower level before command ends
     *  Stops elevator
     */
    @Override
    protected void end()
    {
        Robot.ELEVATOR.setCurrentPos(relationToTarget.equals(ElevatorSubsystem.TargetRelations.WITHIN)?
                ElevatorSubsystem.ElevatorLevel.LVL_ONE : ElevatorSubsystem.ElevatorLevel.OTHER);
        Robot.ELEVATOR.stopElevator();
    }
}
