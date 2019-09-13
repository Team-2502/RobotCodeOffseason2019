package com.team2502.offseason2019.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.offseason2019.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/* TODO This subsystem encompasses the 6 wheel tank drive train powered by 4 CIMs
        should be very similar to our drivetrains in previous years
 */
public class DrivetrainSubsystem extends Subsystem {

    private final WPI_TalonSRX frontRight;
    private final WPI_TalonSRX frontLeft;

    public DrivetrainSubsystem() {
        frontRight = new WPI_TalonSRX(RobotMap.Motor.DRIVE_FRONT_RIGHT);
        frontLeft = new WPI_TalonSRX(RobotMap.Motor.DRIVE_FRONT_LEFT);

        WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.Motor.DRIVE_BACK_RIGHT);
        WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.Motor.DRIVE_BACK_LEFT);

        backRight.follow(frontRight);
        backLeft.follow(frontLeft);

//        Uncomment these two lines of code to invert the right wheels
//        frontRight.setInverted(true);
//        backRight.setInverted(true);
//
//        Uncomment these two lines of code to invert the left wheels
//        frontLeft.setInverted(true);
//        backLeft.setInverted(true);
    }

    /**
     *
     * @param left
     * @param right
     */
    public void runVelocity(double left, double right) {
        frontLeft.set(ControlMode.Velocity, left);
        frontRight.set(ControlMode.Velocity, right);
    }

    public void runVoltage(double left, double right) {
        frontLeft.set(ControlMode.PercentOutput, left);
        frontRight.set(ControlMode.PercentOutput, right);
    }

    public void stopWheels() {
        runVoltage(0, 0);
    }

    @Override
    protected void initDefaultCommand() { }
}