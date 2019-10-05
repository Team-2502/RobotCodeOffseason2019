package com.team2502.offseason2019.subsystem.vision;

import com.team2502.offseason2019.Constants;
import edu.wpi.first.wpilibj.Notifier;

/*
Heavy inspiration from 4607's 2019-Comp-Bot
 */
public class VisionSubsystem {

    public enum Status {
        reachedTarget,
        lostTarget,
        targeting
    }

    private double turningErrorDeg = 0.0;
    private double turn = 0.0;
    private Status status = Status.lostTarget;

    private Limelight limelight = new Limelight();

    private Runnable VisionProcessor = new Runnable() {
        @Override
        public void run() {
            synchronized(this) {

                turningErrorDeg = limelight.horizontalToTargetDeg();
                turn = turningErrorDeg * Constants.Vision.ScaleHorizontalToTarget * Constants.Vision.TurningGain;
                if (!limelight.foundTarget()) {
                    status = Status.lostTarget;
                } else if (Math.abs(turningErrorDeg) < Constants.Vision.StopTurningDeg) {
                    status = Status.reachedTarget;
                } else {
                    status = Status.targeting;
                }

            }
        }
    };

    public Notifier VisionThread = new Notifier(VisionProcessor);

    public synchronized Status getStatus() {
        return status;
    }

    public synchronized double getOutput() {
        return turn;
    }

    public synchronized double getLedMode() {
        return limelight.getLedMode();
    }

    public VisionSubsystem() { }

}