package com.team2502.offseason2019.command.elevator;

import com.team2502.offseason2019.Constants;
import com.team2502.offseason2019.Robot;
import com.team2502.offseason2019.subsystem.ElevatorSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToLvlOneCommand extends Command
{

    private double speed;
    boolean withinThreshold = false;

    public ElevatorToLvlOneCommand()
    {
        this.speed = (Robot.ELEVATOR.getCurrentPos().equals(ElevatorSubsystem.ELEVATOR_POS.BOTTOM))?
                Constants.Physical.Elevator.ELEVATOR_UP_SPEED : Constants.Physical.Elevator.ELEVATOR_DOWN_SPEED;

        requires(Robot.ELEVATOR);
    }

    @Override
    protected void execute()
    {
        if(Robot.ELEVATOR.getEncoderPos() > Constants.Physical.Elevator.LVL_ONE_ROTATIONS - Constants.Physical.Elevator.POS_ERR_THRESHOLD &&
                Robot.ELEVATOR.getEncoderPos() < Constants.Physical.Elevator.LVL_ONE_ROTATIONS + Constants.Physical.Elevator.POS_ERR_THRESHOLD)
        {
            withinThreshold = true;
        }
        Robot.ELEVATOR.moveElevator(speed);
    }

    @Override
    protected boolean isFinished()
    {
        return (Robot.ELEVATOR.getCurrentPos().equals(ElevatorSubsystem.ELEVATOR_POS.LVL_ONE) || withinThreshold);
    }

    @Override
    protected void end()
    {
        Robot.ELEVATOR.setCurrentPos(ElevatorSubsystem.ELEVATOR_POS.LVL_ONE);
        Robot.ELEVATOR.stopElevator();
    }
}
