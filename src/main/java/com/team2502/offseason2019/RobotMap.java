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
        //TODO finalize button ids
        public static final int CARGO_INTAKE_IN = 3;
        public static final int CARGO_INTAKE_OUT = 5;
        public static final int HATCH_INTAKE_IN = 4;
        public static final int HATCH_INTAKE_OUT = 6;

        public static final int INTAKE_DEPLOY = 1;

        private Button() { }
    }

    public static final class Motor
    {
        public static final int INTAKE_CARGO = 5;
        public static final int INTAKE_HATCH = 6;

        private Motor() { }
    }

    public class Solenoid
    {
        public static final int INTAKE_DEPLOY = 1;

        private Solenoid() { }
    }

}
