package com.team2502.offseason2019.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.offseason2019.OI;
import com.team2502.offseason2019.RobotMap;
import com.team2502.offseason2019.command.DriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/* This subsystem encompasses the 6 wheel tank drive train powered by 4 CIMs
        should be very similar to our drivetrains in previous years
 */
public class DrivetrainSubsystem extends Subsystem {

    private final WPI_TalonSRX frontRight;
    private final WPI_TalonSRX frontLeft;

    public enum TeleopMode {
        VELOCITY,
        VOLTAGE
    }

    private TeleopMode teleopMode = TeleopMode.VELOCITY;

    public DrivetrainSubsystem() {
        frontRight = new WPI_TalonSRX(RobotMap.Motor.DRIVE_FRONT_RIGHT);
        frontLeft = new WPI_TalonSRX(RobotMap.Motor.DRIVE_FRONT_LEFT);

        WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.Motor.DRIVE_BACK_RIGHT);
        WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.Motor.DRIVE_BACK_LEFT);

        backRight.follow(frontRight);
        backLeft.follow(frontLeft);

        frontLeft.setInverted(true);
    }

    public void runTeleop() {
        double left = OI.JOYSTICK_DRIVE_LEFT.getY();
        double right = OI.JOYSTICK_DRIVE_RIGHT.getY();

        switch(teleopMode) {
            case VELOCITY: {
                runVelocity(left, right);
                break;
            }
            case VOLTAGE: {
                runVoltage(left, right);
                break;
            }
        }
    }

    /**
     * @param left
     * Velocity for the left side of the drive train
     *
     * @param right
     * Velocity for the right side of the drive train
     */
    public void runVelocity(double left, double right) {
        frontLeft.set(ControlMode.PercentOutput, left);
        frontRight.set(ControlMode.PercentOutput, right);
    }

    /**
     * @param left
     * Voltage for the left side of the drive train
     *
     * @param right
     * Voltage for the right side of the drive train
     */
    public void runVoltage(double left, double right) {
        frontLeft.set(ControlMode.PercentOutput, left);
        frontRight.set(ControlMode.PercentOutput, right);
    }

    /**
     * Stops the robot by setting the voltage for both sides to 0
     * @see DrivetrainSubsystem#runVoltage(double, double)
     */
    public void stopWheels() {
        runVoltage(0, 0);
    }

    public TeleopMode getTeleopMode() {
        return teleopMode;
    }

    public void setTeleopMode(TeleopMode teleopMode) {
        this.teleopMode = teleopMode;
    }

    @Override
    protected void initDefaultCommand() {setDefaultCommand(new DriveCommand()); }
}