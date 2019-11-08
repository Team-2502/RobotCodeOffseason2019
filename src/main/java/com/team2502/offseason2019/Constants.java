package com.team2502.offseason2019;

import com.github.ezauton.core.pathplanning.purepursuit.LookaheadBounds;
import com.team2502.offseason2019.subsystem.interfaces.DriveTrain;

public class Constants {


    public static class Physical
    {

        public static class Drivetrain
        {
            public static final double LATERAL_WHEEL_DISTANCE = 0; //TODO Find value

            private Drivetrain(){}
        }


        public static class Intake
        {
            public static final double CARGO_INTAKE_SPEED = 0.75;
            public static final double CARGO_OUTTAKE_SPEED = -1.0;
            public static final double HATCH_INTAKE_SPEED = 1.0;

            private Intake(){}
        }

        public static class Elevator
        {
            public static final double ELEVATOR_UP_SPEED = -0.3;
            public static final double ELEVATOR_DOWN_SPEED = 0.85;

            public static final double LOWER_POS_ROTATIONS = 2;
            public static final double MIDDLE_POS_ROTATIONS = 10;
            public static final double UPPER_POS_ROTATIONS = 20;

            public static final double POS_ERR_THRESHOLD = 0.5;

            private Elevator(){}
        }

        private Physical()
        {}
    }

    public static class Autonomous
    {
        public static LookaheadBounds getLookaheadBounds(DriveTrain dt) {
            return new LookaheadBounds(1, 8, 3, 10, dt.getVelocityEstimator());
        }
        private Autonomous() { }
    }

}
