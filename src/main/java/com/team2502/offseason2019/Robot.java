/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.offseason2019;


import com.team2502.offseason2019.subsystem.ActiveIntakeSubsystem;
import com.team2502.offseason2019.subsystem.DrivetrainSubsystem;
import com.team2502.offseason2019.subsystem.solenoid.IntakeDeploySolenoid;
import com.team2502.offseason2019.subsystem.ElevatorSubsystem;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DrivetrainSubsystem DRIVETRAIN;
  public static ActiveIntakeSubsystem ACTIVE_INTAKE;
  public static IntakeDeploySolenoid INTAKE_DEPLOY;
  public static ElevatorSubsystem ELEVATOR;
  public static OI OI;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit()
  {
    DRIVETRAIN = new DrivetrainSubsystem();
    ACTIVE_INTAKE = new ActiveIntakeSubsystem();
    INTAKE_DEPLOY = new IntakeDeploySolenoid();
    ELEVATOR = new ElevatorSubsystem();
    OI = new OI();

    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() { }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
      Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void disabledInit() {
    INTAKE_DEPLOY.setEnabled(false);
  }

  @Override
  public void disabledPeriodic(){

  }
}
