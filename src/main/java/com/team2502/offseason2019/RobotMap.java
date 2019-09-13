package com.team2502.offseason2019;

/**
 * The RobotMap class defines the IDs which are used in OI
 */
public class RobotMap {

    public static final class Joystick {

        public static final int JOYSTICK_DRIVE_LEFT = 1;
        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_FUNCTION = 2;
        public static final int JOYSTICK_SIDE_PANEL = 3;

        private Joystick() { }
    }

    public static final class Button
    {
        public static final int RAISE_ELEVATOR = 3;
        public static final int LOWER_ELEVATOR = 4;

        private Button() { }
    }

    public static final class Motor
    {
        public static final int ELEVATOR_TOP = 8;
        public static final int ELEVATOR_BOTTOM = 9;

        public static final int DRIVE_FRONT_RIGHT = 0;
        public static final int DRIVE_FRONT_LEFT = 1;
        public static final int DRIVE_BACK_RIGHT = 2;
        public static final int DRIVE_BACK_LEFT = 3;

        private Motor() { }
    }

    public class Solenoid
    {
        private Solenoid() { }
    }

}
