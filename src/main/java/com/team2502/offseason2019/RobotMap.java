package com.team2502.offseason2019;

/**
 * The RobotMap class defines the IDs which are used in OI
 */
public class RobotMap {

    public static final class Joystick {

        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_DRIVE_LEFT = 1;
        public static final int JOYSTICK_FUNCTION = 2;
        public static final int JOYSTICK_SIDE_PANEL = 3;

        private Joystick() { }
    }

    public static final class Button
    {
        public static final int CARGO_INTAKE_IN = 6;
        public static final int CARGO_INTAKE_OUT = 4;

        public static final int HATCH_INTAKE_IN = 5;
        public static final int HATCH_INTAKE_OUT = 3;

        public static final int INTAKE_DEPLOY = 1;

        public static final int RAISE_ELEVATOR = 12;
        public static final int LOWER_ELEVATOR = 11;

        private Button() { }
    }

    public static final class Motor
    {
        // TODO All motors are correct except for intake motors
        public static final int INTAKE_CARGO = 3;
        public static final int INTAKE_HATCH = 4;

        public static final int ELEVATOR_BOTTOM = 0;
        public static final int ELEVATOR_TOP = 1;

        public static final int DRIVE_FRONT_RIGHT = 1;
        public static final int DRIVE_FRONT_LEFT = 6;
        public static final int DRIVE_BACK_RIGHT = 2;
        public static final int DRIVE_BACK_LEFT = 5;

        private Motor() { }
    }

    public class Solenoid
    {
        public static final int INTAKE_DEPLOY = 1;

        private Solenoid() { }
    }

}
