package com.team2502.offseason2019;


import com.team2502.offseason2019.command.intake.ActiveIntakeCommand;
import com.team2502.offseason2019.command.intake.DeployIntakeCommand;
import com.team2502.offseason2019.subsystem.ActiveIntakeSubsystem;
import com.team2502.offseason2019.command.ElevatorCommand;
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

    public static final Button ELEV_UP = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.RAISE_ELEVATOR);
    public static final Button ELEV_DOWN = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.LOWER_ELEVATOR);

    static
    {
        // INTAKE
        CARGO_INTAKE_IN.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.CARGO,Constants.Physical.Intake.CARGO_INTAKE_SPEED));
        CARGO_INTAKE_OUT.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.CARGO,-Constants.Physical.Intake.CARGO_INTAKE_SPEED));
        HATCH_INTAKE_IN.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.HATCH,-Constants.Physical.Intake.HATCH_INTAKE_SPEED));
        HATCH_INTAKE_OUT.whileHeld(new ActiveIntakeCommand(ActiveIntakeSubsystem.TakeIn.HATCH,Constants.Physical.Intake.HATCH_INTAKE_SPEED));
      
        INTAKE_DEPLOY.whenPressed(new DeployIntakeCommand());
      
        ELEV_UP.whileHeld(new ElevatorCommand(Constants.Physical.Elevator.ELEVATOR_UP_SPEED));
        ELEV_DOWN.whileHeld(new ElevatorCommand(Constants.Physical.Elevator.ELEVATOR_DOWN_SPEED));
    }

    /**
     * Workaround for Java's lazy-loading of static classes
     * <p>
     * When this is called, Java loads the static bits of this class and runs the static init constructor above.
     */
    public static void init() {}
}
