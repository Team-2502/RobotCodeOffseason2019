package com.team2502.offseason2019.subsystem;

/**
 * This subsystem consists of:
 *      A CIM connected to the cargo intake which is very similar to OBA
 *      A CIM connected to the hatch intake which spins the compliance wheels in to pickup a hatch and out to place a hatch
 * Both motors should not run at the same time
 */

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.offseason2019.RobotMap;
import com.team2502.offseason2019.command.intake.CargoBrakeModeCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ActiveIntakeSubsystem extends Subsystem
{
    private final WPI_TalonSRX cargoIntake;
    private final WPI_TalonSRX hatchIntake;

    public boolean cargoInCarriage;

    public enum TakeIn
    {
        CARGO,
        HATCH,
    }

    public ActiveIntakeSubsystem()
    {
        cargoIntake = new WPI_TalonSRX(RobotMap.Motor.INTAKE_CARGO);
        hatchIntake = new WPI_TalonSRX(RobotMap.Motor.INTAKE_HATCH);

        cargoIntake.setNeutralMode(NeutralMode.Brake);
        hatchIntake.setNeutralMode(NeutralMode.Brake);

        cargoInCarriage = false;
    }

    /**
     * Runs either the cargo intake or hatch intake exclusively.
     * @param takeIn Specifies whether to run CARGO intake or HATCH intake.
     * @param speed The speed (-1.0 to 1.0) at which to run either intake.
     */
    public void runIntake(TakeIn takeIn, double speed)
    {
        switch(takeIn)
        {
            case CARGO:
                runCargo(speed);
                break;
            case HATCH:
                runHatch(speed);
                break;
        }
    }

    /**
     * Runs the cargo intake at specified speed.
     * @param speed The speed (-1.0 to 1.0) at which to run the intake.
     */
    public void runCargo(double speed)
    {
        cargoInCarriage = speed > 0;

        cargoIntake.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Runs the hatch intake at specified speed.
     * @param speed The speed (-1.0 to 1.0) at which to run the intake.
     */
    public void runHatch(double speed)
    {
        cargoInCarriage = false;
        hatchIntake.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Stops the intake.
     */
    public void stopIntake()
    {
        runCargo(0);
        runHatch(0);
    }

    @Override
    protected void initDefaultCommand(){setDefaultCommand(new CargoBrakeModeCommand());}
}
