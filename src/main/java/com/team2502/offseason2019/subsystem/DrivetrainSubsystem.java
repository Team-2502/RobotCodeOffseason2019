package com.team2502.offseason2019.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.github.ezauton.core.action.require.BaseResource;
import com.github.ezauton.core.action.require.Resource;
import com.github.ezauton.core.actuators.VelocityMotor;
import com.github.ezauton.core.localization.UpdateableGroup;
import com.github.ezauton.core.localization.estimators.TankRobotEncoderEncoderEstimator;
import com.github.ezauton.core.localization.sensors.EncoderWheel;
import com.github.ezauton.core.robot.implemented.TankRobotTransLocDriveable;
import com.github.ezauton.core.robot.subsystems.TranslationalLocationDriveable;
import com.github.ezauton.core.trajectory.geometry.ImmutableVector;
import com.github.ezauton.wpilib.motors.MotorControllers;
import com.github.ezauton.wpilib.motors.TypicalMotor;
import com.team2502.offseason2019.Constants;
import com.team2502.offseason2019.OI;
import com.team2502.offseason2019.RobotMap;
import com.team2502.offseason2019.command.teleop.DriveCommand;
import com.team2502.offseason2019.subsystem.interfaces.DriveTrain;
import edu.wpi.first.wpilibj.command.Subsystem;

/* This subsystem encompasses the 6 wheel tank drive train powered by 4 CIMs
        should be very similar to our drivetrains in previous years
 */
public class DrivetrainSubsystem extends Subsystem implements DriveTrain
{

    // Sets which side of the drivetrain is inverted
    private static final boolean INVERT_RIGHT_WHEELS = false;

    private final WPI_TalonSRX frontRight;
    private final WPI_TalonSRX frontLeft;

    private final TankRobotTransLocDriveable driveable;
    private final TankRobotEncoderEncoderEstimator locEst;

    private final TypicalMotor left;
    private final TypicalMotor right;

    private final EncoderWheel leftWheel;
    private final EncoderWheel rightWheel;

    private final BaseResource baseResource = new BaseResource();
    private final UpdateableGroup updateableGroup;

    public enum TeleopMode {
        VELOCITY,
        VOLTAGE
    }

    /**
     * Default Teleop mode
     * @see DrivetrainSubsystem#setTeleopMode(TeleopMode)
     */
    private TeleopMode teleopMode = TeleopMode.VOLTAGE;

    public DrivetrainSubsystem() {
        frontRight = new WPI_TalonSRX(RobotMap.Motor.DRIVE_FRONT_RIGHT);
        frontLeft = new WPI_TalonSRX(RobotMap.Motor.DRIVE_FRONT_LEFT);

        WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.Motor.DRIVE_BACK_RIGHT);
        WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.Motor.DRIVE_BACK_LEFT);

        backRight.follow(frontRight);
        backLeft.follow(frontLeft);

        if(INVERT_RIGHT_WHEELS) {
            frontRight.setInverted(true);
        }else {
            frontLeft.setInverted(true);
        }

        left = MotorControllers.fromSeveralCTRE(frontLeft, 0, backLeft);
        right = MotorControllers.fromSeveralCTRE(frontRight, 0, backRight);

        leftWheel = new EncoderWheel(left,6);
        rightWheel = new EncoderWheel(right,6);

        locEst = new TankRobotEncoderEncoderEstimator(leftWheel, rightWheel, () -> Constants.Physical.Drivetrain.LATERAL_WHEEL_DISTANCE);

        driveable = new TankRobotTransLocDriveable(left, right, locEst, locEst, () -> Constants.Physical.Drivetrain.LATERAL_WHEEL_DISTANCE);

        updateableGroup = new UpdateableGroup(locEst);
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
                runMotorsVoltage(left, right);
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
        frontLeft.set(ControlMode.Velocity, left);
        frontRight.set(ControlMode.Velocity, right);
    }

    /**
     * @param left
     * Voltage for the left side of the drive train
     *
     * @param right
     * Voltage for the right side of the drive train
     */
    @Override
    public void runMotorsVoltage(double left, double right) {
        frontLeft.set(ControlMode.PercentOutput, left);
        frontRight.set(ControlMode.PercentOutput, right);
    }

    /**
     * Stops the robot by setting the voltage for both sides to 0
     * @see DrivetrainSubsystem#runMotorsVoltage(double, double)
     */
    public void stopWheels() {
        runMotorsVoltage(0, 0);
    }

    public TeleopMode getTeleopMode() {
        return teleopMode;
    }

    public void setTeleopMode(TeleopMode teleopMode) {
        this.teleopMode = teleopMode;
    }

    @Override
    public boolean driveTowardTransLoc(double speed, ImmutableVector loc)
    {
        return driveable.driveTowardTransLoc(speed, loc);
    }

    @Override
    public boolean driveSpeed(double speed)
    {
        return driveable.driveSpeed(speed);
    }


    @Override
    public VelocityMotor getLeft()
    {
        return left;
    }

    @Override
    public VelocityMotor getRight()
    {
        return right;
    }

    @Override
    public TankRobotEncoderEncoderEstimator getLocEstimator()
    {
        return locEst;
    }

    @Override
    public TankRobotEncoderEncoderEstimator getRotEstimator()
    {
        return locEst;
    }

    @Override
    public TankRobotEncoderEncoderEstimator getVelocityEstimator()
    {
        return locEst;
    }

    @Override
    public Resource getResource()
    {
        return baseResource;
    }

    @Override
    public boolean update()
    {
        return updateableGroup.update();
    }

    @Override
    protected void initDefaultCommand() {setDefaultCommand(new DriveCommand()); }
}