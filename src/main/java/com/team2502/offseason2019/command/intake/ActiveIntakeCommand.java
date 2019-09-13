package com.team2502.offseason2019.command.intake;

import com.team2502.offseason2019.Robot;
import com.team2502.offseason2019.subsystem.ActiveIntakeSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ActiveIntakeCommand extends Command
{
    private final ActiveIntakeSubsystem.TakeIn takeIn;
    private final double speed;

    public ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn takeIn, double speed)
    {
        this.takeIn = takeIn;
        this.speed = speed;
        requires(Robot.ACTIVE_INTAKE);
    }

    @Override
    protected void execute()
    {
        Robot.ACTIVE_INTAKE.runIntake(takeIn, speed);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        Robot.ACTIVE_INTAKE.stopIntake();
    }
}
