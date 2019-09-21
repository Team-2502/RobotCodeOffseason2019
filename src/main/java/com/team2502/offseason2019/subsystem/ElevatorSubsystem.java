package com.team2502.offseason2019.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.offseason2019.Constants;
import com.team2502.offseason2019.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/* This subsystem encompasses the cascading elevator, powered by a CIMple Gearbox with 2 NEOs
    An example of a similar subsystem can be located in RobotCode2018
    Moving the elevator to level 2/3 with a single button press would be nice but is not currently a priority
 */
public class ElevatorSubsystem extends Subsystem {

    public final CANSparkMax elevatorTop;
    public final CANSparkMax elevatorBottom;

    private ElevatorLevel currentPos;

    public ElevatorSubsystem() {
        elevatorTop = new CANSparkMax(RobotMap.Motor.ELEVATOR_TOP, CANSparkMaxLowLevel.MotorType.kBrushless);
        elevatorBottom = new CANSparkMax(RobotMap.Motor.ELEVATOR_BOTTOM, CANSparkMaxLowLevel.MotorType.kBrushless);

        elevatorBottom.getEncoder();
        elevatorTop.getEncoder();

        elevatorTop.follow(elevatorBottom);

        elevatorBottom.setOpenLoopRampRate(0.75);
        elevatorBottom.setInverted(true);

        elevatorBottom.getEncoder().setPosition(0);

        currentPos = ElevatorLevel.BOTTOM;
    }

    public enum ElevatorLevel
    {
        BOTTOM,
        LVL_ONE,
        LVL_TWO,
        LVL_THREE,
        OTHER
    }

    public enum TargetRelations
    {
        LESS,
        WITHIN,
        GREATER
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

    public ElevatorLevel getCurrentPos()
    {
        return currentPos;
    }

    public void setCurrentPos(ElevatorLevel pos)
    {
        this.currentPos = currentPos;
    }


    /**
     * Method to get the relation of the elevator's current position to it's target
     * @param targetLevel The target level of the elevator
     * @return Whether the current position is GREATER than, WITHIN, or LESS than the target range
     */
    public TargetRelations getRelationToTarget(ElevatorLevel targetLevel)
    {
        double targetRotations = 0;

        switch(targetLevel)
        {
            case LVL_ONE:
                targetRotations = Constants.Physical.Elevator.LVL_ONE_ROTATIONS;
                break;
            case LVL_TWO:
                targetRotations = Constants.Physical.Elevator.LVL_TWO_ROTATIONS;
                break;
            case LVL_THREE:
                targetRotations = Constants.Physical.Elevator.LVL_THREE_ROTATIONS;
                break;
        }

        if(getEncoderPos() > targetRotations - Constants.Physical.Elevator.POS_ERR_THRESHOLD &&
            getEncoderPos() < targetRotations + Constants.Physical.Elevator.POS_ERR_THRESHOLD)
        {
            return TargetRelations.WITHIN;
        }
        else if(getEncoderPos() <= targetRotations - Constants.Physical.Elevator.POS_ERR_THRESHOLD)
        {
            return TargetRelations.LESS;
        }
        else
        {
            return TargetRelations.GREATER;
        }
    }
}
