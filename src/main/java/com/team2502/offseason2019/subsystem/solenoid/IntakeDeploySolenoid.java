package com.team2502.offseason2019.subsystem.solenoid;

import com.team2502.offseason2019.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeDeploySolenoid extends Subsystem
{

    private final Solenoid intakeDeployer;
    private boolean isEnabled;

    public IntakeDeploySolenoid()
    {
        intakeDeployer = new Solenoid(RobotMap.Solenoid.INTAKE_DEPLOY);
        isEnabled = false;
    }

    /**
     * Toggle whether the intake deployment solenoid is enabled.
     */
    public void toggleEnabled(){
        intakeDeployer.set(isEnabled = !isEnabled);
    }

    /**
     * Set whether the intake deployment solenoid is enabled.
     * @param isEnabled Whether the solenoid is enabled.
     */
    public void setEnabled(boolean isEnabled){
        intakeDeployer.set(this.isEnabled = isEnabled);
    }

    @Override
    protected void initDefaultCommand() {}
}
