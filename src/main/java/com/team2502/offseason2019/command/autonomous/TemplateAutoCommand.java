package com.team2502.offseason2019.command.autonomous;

import com.github.ezauton.core.action.ActionGroup;
import com.github.ezauton.core.action.BackgroundAction;
import com.github.ezauton.core.action.PurePursuitAction;
import com.github.ezauton.core.pathplanning.PP_PathGenerator;
import com.github.ezauton.core.pathplanning.Path;
import com.github.ezauton.core.pathplanning.purepursuit.PPWaypoint;
import com.github.ezauton.core.pathplanning.purepursuit.PurePursuitMovementStrategy;
import com.github.ezauton.wpilib.command.CommandCreator;
import com.team2502.offseason2019.AutoPicker;
import com.team2502.offseason2019.Constants;
import com.team2502.offseason2019.Robot;
import edu.wpi.first.wpilibj.command.Command;

import java.util.concurrent.TimeUnit;

public interface TemplateAutoCommand extends AutoPicker.CommandFactory
{

    ActionGroup group = new ActionGroup();

    PPWaypoint[] waypoints = new PPWaypoint.Builder()
            .add(0,0,1000,130000, -120000)
            .buildArray();


    PP_PathGenerator pathGen = new PP_PathGenerator(waypoints);
    Path path = pathGen.generate(0.05);

    PurePursuitMovementStrategy ppMoveStrat = new PurePursuitMovementStrategy(path, 0.001);
    PurePursuitAction ppAction = new PurePursuitAction(10, TimeUnit.MILLISECONDS, ppMoveStrat, Robot.DRIVETRAIN.getLocEstimator(), Constants.Autonomous.getLookaheadBounds(Robot.DRIVETRAIN), Robot.DRIVETRAIN);

    @Override
    default Command getInstance()
    {
        group.with(new BackgroundAction(10, TimeUnit.MILLISECONDS, Robot.DRIVETRAIN::update));
        group.addSequential(ppAction);
        return new CommandCreator(group, Robot.ACTION_SCHEDULER);
    }
}
