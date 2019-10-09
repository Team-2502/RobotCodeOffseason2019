package com.team2502.offseason2019.command.teleop.intake;

import com.team2502.offseason2019.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class DeployIntakeCommand extends InstantCommand
{
    public DeployIntakeCommand()
    {
        requires(Robot.INTAKE_DEPLOY);
    }

    @Override
    protected void execute()
    {
        Robot.INTAKE_DEPLOY.toggleEnabled();
    }
}
