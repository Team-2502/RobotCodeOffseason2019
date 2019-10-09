package com.team2502.offseason2019.command.teleop.intake;

import com.team2502.offseason2019.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class CargoBrakeModeCommand extends Command {
    public CargoBrakeModeCommand(){
        requires(Robot.ACTIVE_INTAKE);
    }

    @Override
    protected void execute() {
        if(Robot.ACTIVE_INTAKE.cargoInCarriage){
            Robot.ACTIVE_INTAKE.runCargo(0.1);
        }
    }

    @Override
    protected void end() {}

    @Override
    protected boolean isFinished() {
        return false;
    }
}
