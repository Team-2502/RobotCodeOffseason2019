package com.team2502.offseason2019;

public class Constants {


    public static class Physical
    {
        public static class Intake
        {
            public static final double CARGO_INTAKE_SPEED = 0.75;
            public static final double CARGO_OUTTAKE_SPEED = -1.0;
            public static final double HATCH_INTAKE_SPEED = 1.0;

            private Intake(){}
        }

        public static class Elevator
        {
            public static final double ELEVATOR_UP_SPEED = 0.3;
            public static final double ELEVATOR_DOWN_SPEED = -0.85;

            public static final double LOWER_POS_ROTATIONS = 2;
            public static final double MIDDLE_POS_ROTATIONS = 10;
            public static final double UPPER_POS_ROTATIONS = 20;

            public static final double POS_ERR_THRESHOLD = 0.5;

            private Elevator(){}
        }

        private Physical(){}
    }

    public static class Vision
    {
        public static final double ScaleHorizontalToTarget = 1.0 /27.0;
        public static final double TurningGain = 1.0;
        public static final double StopTurningDeg = 1.0;
        public static final double VisionThreadTime = 0.01;

        private Vision(){}
    }

}
