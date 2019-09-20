package com.team2502.offseason2019;


import com.team2502.offseason2019.command.elevator.ElevatorToBottomCommand;
import com.team2502.offseason2019.command.elevator.ElevatorToLvlOneCommand;
import com.team2502.offseason2019.command.elevator.ElevatorToLvlThreeCommand;
import com.team2502.offseason2019.command.elevator.ElevatorToLvlTwoCommand;
import com.team2502.offseason2019.command.intake.ActiveIntakeCommand;
import com.team2502.offseason2019.command.intake.DeployIntakeCommand;
import com.team2502.offseason2019.subsystem.ActiveIntakeSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The Operator Interface class which defines all buttons and what they do when pressed
 */
public class OI {

    public static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_LEFT);
    public static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_RIGHT);
    public static final Joystick JOYSTICK_FUNCTION = new Joystick(RobotMap.Joystick.JOYSTICK_FUNCTION);
    public static final Joystick JOYSTICK_SIDE_PANEL = new Joystick(RobotMap.Joystick.JOYSTICK_SIDE_PANEL);

    public static final Button CARGO_INTAKE_IN = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.CARGO_INTAKE_IN);
    public static final Button CARGO_INTAKE_OUT = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.CARGO_INTAKE_OUT);
    public static final Button HATCH_INTAKE_IN = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.HATCH_INTAKE_IN);
    public static final Button HATCH_INTAKE_OUT = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.HATCH_INTAKE_OUT);

    public static final Button INTAKE_DEPLOY = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.INTAKE_DEPLOY);

    public static final Button ELEVATOR_TO_BOTTOM = new JoystickButton(JOYSTICK_SIDE_PANEL, RobotMap.Button.ELEVATOR_TO_BOTTOM);
    public static final Button ELEVATOR_TO_ONE = new JoystickButton(JOYSTICK_SIDE_PANEL, RobotMap.Button.ELEVATOR_TO_ONE);
    public static final Button ELEVATOR_TO_TWO = new JoystickButton(JOYSTICK_SIDE_PANEL, RobotMap.Button.ELEVATOR_TO_TWO);
    public static final Button ELEVATOR_TO_THREE = new JoystickButton(JOYSTICK_SIDE_PANEL, RobotMap.Button.ELEVATOR_TO_THREE);


    static
    {
        // INTAKE
        CARGO_INTAKE_IN.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.CARGO,Constants.Physical.Intake.CARGO_INTAKE_SPEED));
        CARGO_INTAKE_OUT.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.CARGO,Constants.Physical.Intake.CARGO_OUTTAKE_SPEED));
        HATCH_INTAKE_IN.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.HATCH,-Constants.Physical.Intake.HATCH_INTAKE_SPEED));
        HATCH_INTAKE_OUT.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.HATCH,Constants.Physical.Intake.HATCH_INTAKE_SPEED));
      
        INTAKE_DEPLOY.whenPressed(new DeployIntakeCommand());

        ELEVATOR_TO_BOTTOM.whenPressed(new ElevatorToBottomCommand());
        ELEVATOR_TO_ONE.whenPressed(new ElevatorToLvlOneCommand());
        ELEVATOR_TO_TWO.whenPressed(new ElevatorToLvlTwoCommand());
        ELEVATOR_TO_THREE.whenPressed(new ElevatorToLvlThreeCommand());
    }

    /**
     * Workaround for Java's lazy-loading of static classes
     * <p>
     * When this is called, Java loads the static bits of this class and runs the static init constructor above.
     */
    public static void init() {}
}
