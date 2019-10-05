package com.team2502.offseason2019.subsystem.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/*
Heavy inspiration from 4607's 2019-Comp-Bot
 */
public class Limelight {

    private NetworkTable networkTable;

    public enum ledMode {
        currentPipeline, // 0 use the LED Mode set in the current pipeline
        off,             // 1 force off
        blink,           // 2 force blink
        on               // 3 force on
    }

    public enum camMode {
        vision, // 0 Vision processor
        driver  // 1 Driver Camera (Increases exposure, disables vision processing)
    }

    public enum pipeline {
        k0, k1, k2, k3, k4, k5, k6, k7, k8, k9
    }

    public enum stream {
        standard,    // 0 Standard - Side-by-side streams if a webcam is attached to Limelight
        pipMain,     // 1 PiP Main - The secondary stream is placed in the lower-right corner of the primary stream
        pipSecondary // 2 PiP Secondary - The primary stream is placed in the lower-right corner of the secondary stream
    }

    public enum snapshot {
        stop, // 0	Stop taking snapshots
        start // 1	Take two snapshots per second
    }
    private void defaultConfiguration() {
        setLedMode(ledMode.currentPipeline);
        setCamMode(camMode.vision);
        setPipeline(pipeline.k0);
        setSnapshot(snapshot.stop);
        setStream(stream.standard);
    }

    public Limelight() {
        networkTable = NetworkTableInstance.getDefault().getTable("limelight");
        defaultConfiguration();
    }

    public void setLedMode(ledMode value) {
        networkTable.getEntry("ledMode").setValue(value.ordinal());
    }

    public double getLedMode() {
        return networkTable.getEntry("ledMode").getDouble(-1.0);
    }

    public void setCamMode(camMode value) {
        networkTable.getEntry("camMode").setValue(value.ordinal());
    }

    public double getCamMode() {
        return networkTable.getEntry("camMode").getDouble(-1.0);
    }

    public void setPipeline(pipeline value) {
        networkTable.getEntry("pipeline").setValue(value.ordinal());
    }

    public double getPipeline() {
        return networkTable.getEntry("pipeline").getDouble(-1.0);
    }

    public void setStream(stream value) {
        networkTable.getEntry("stream").setValue(value.ordinal());
    }

    public double getStream() {
        return networkTable.getEntry("stream").getDouble(-1.0);
    }

    public void setSnapshot(snapshot value) {
        networkTable.getEntry("snapshot").setValue(value.ordinal());
    }

    public double getSnapshot() {
        return networkTable.getEntry("snapshot").getDouble(-1.0);
    }

    /******************************************************************************************************************************
     ** VISION TARGET
     **   boolean  foundTarget            Whether the limelight has any valid targets (0 or 1)
     **   double   horizontalToTargetDeg  Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
     **   double   verticalToTargetDeg    Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
     **   double   targetAreaPercent      Target Area (0% of image to 100% of image)
     **   double   skewDeg                Skew or rotation (-90 degrees to 0 degrees)
     **   double   latencyMs              The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency
     **   double   shortSidePixels        Sidelength of shortest side of the fitted bounding box (pixels)
     **   double   longSidePixels         Sidelength of longest side of the fitted bounding box (pixels)
     **   double   horizontalPixels       Horizontal sidelength of the rough bounding box (0 - 320 pixels)
     **   double   verticalPixels         Vertical sidelength of the rough bounding box (0 - 320 pixels)
     **   double   getTruePipeline        True active pipeline index of the camera (0 .. 9)
     ******************************************************************************************************************************/
    public boolean foundTarget() {
        return (networkTable.getEntry("tv").getDouble(0.0) == 1.0) ? true : false;
    }

    public double horizontalToTargetDeg() {
        return networkTable.getEntry("tx").getDouble(0.0);
    }

    public double verticalToTargetDeg() {
        return networkTable.getEntry("ty").getDouble(0.0);
    }

    public double targetAreaPercent() {
        return networkTable.getEntry("ta").getDouble(0.0);
    }

    public double skewDeg() {
        return networkTable.getEntry("ts").getDouble(0.0);
    }

    public double latencyMs() {
        return networkTable.getEntry("tl").getDouble(0.0);
    }

    public double shortSidePixels() {
        return networkTable.getEntry("tshort").getDouble(0.0);
    }

    public double longSidePixels() {
        return networkTable.getEntry("tlong").getDouble(0.0);
    }

    public double horizontalPixels() {
        return networkTable.getEntry("thor").getDouble(0.0);
    }

    public double verticalPixels() {
        return networkTable.getEntry("tvert").getDouble(0.0);
    }

    public double getTruePipeline() {
        return networkTable.getEntry("getpipe").getDouble(0.0);
    }

    // Unused networkTable entries
    // tx0: Raw Screenspace X
    // ty0: Raw Screenspace Y
    // ta0: Area (0% of image to 100% of image)
    // ts0: Skew or rotation (-90 degrees to 0 degrees)
    // tx1: Raw Screenspace X
    // ty1: Raw Screenspace Y
    // ta1: Area (0% of image to 100% of image)
    // ts1: Skew or rotation (-90 degrees to 0 degrees)
    // tx2: Raw Screenspace X
    // ty2: Raw Screenspace Y
    // ta2: Area (0% of image to 100% of image)
    // ts2: Skew or rotation (-90 degrees to 0 degrees)
    // cx0: Crosshair A X in normalized screen space
    // cy0: Crosshair A Y in normalized screen space
    // cx1: Crosshair B X in normalized screen space
    // cy1: Crosshair B Y in normalized screen space

    public double[] getCornersX() {
        double[] defValue = {0.0, 0.0, 0.0, 0.0};
        return networkTable.getEntry("tcornx").getDoubleArray(defValue);
    }

    public double[] getCornersY() {
        double[] defValue = {0.0, 0.0, 0.0, 0.0};
        return networkTable.getEntry("tcorny").getDoubleArray(defValue);
    }

}
