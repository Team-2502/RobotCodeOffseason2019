package com.team2502.offseason2019.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.offseason2019.RobotMap;
import com.team2502.offseason2019.command.ElevatorCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/* This subsystem encompasses the cascading elevator, powered by a CIMple Gearbox with 2 NEOs
    An example of a similar subsystem can be located in RobotCode2018
    Moving the elevator to level 2/3 with a single button press would be nice but is not currently a priority
 */
public class
ElevatorSubsystem extends Subsystem {

    public final CANSparkMax elevatorTop;
    public final CANSparkMax elevatorBottom;

    public ElevatorSubsystem() {
        elevatorTop = new CANSparkMax(RobotMap.Motor.ELEVATOR_TOP, CANSparkMaxLowLevel.MotorType.kBrushless);
        elevatorBottom = new CANSparkMax(RobotMap.Motor.ELEVATOR_BOTTOM, CANSparkMaxLowLevel.MotorType.kBrushless);

        elevatorBottom.getEncoder();
        elevatorTop.getEncoder();

        elevatorTop.follow(elevatorBottom);

        elevatorBottom.setOpenLoopRampRate(0.4);
        elevatorBottom.setInverted(true);

        elevatorBottom.getEncoder().setPosition(0);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void moveElevator(double speed) {
        elevatorBottom.set(speed);
        System.out.println("Elevator Position: " + elevatorBottom.getEncoder().getPosition());
    }

    public void stopElevator() {
        elevatorBottom.set(0);
    }

    public double getEncoderVel()
    {
        return elevatorBottom.getEncoder().getVelocity();
    }

    public void setEncoderPos(double pos){
        elevatorBottom.getEncoder().setPosition(pos);
    }

    public double getEncoderPos(){
        return elevatorBottom.getEncoder().getPosition();
    }
}
