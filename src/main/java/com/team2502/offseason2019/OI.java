package com.team2502.offseason2019;

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

    static final Button ELEV_UP = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.RAISE_ELEVATOR);
    static final Button ELEV_DOWN = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Button.LOWER_ELEVATOR);

    static{
        //Buttons go here
        ELEV_UP.whileHeld(new ElevatorCommand(1.0));
        ELEV_DOWN.whileHeld(new ElevatorCommand(-0.5));

    }

    /**
     * Workaround for Java's lazy-loading of static classes
     * <p>
     * When this is called, Java loads the static bits of this class and runs the static init constructor above.
     */
    public static void init() {}
}
