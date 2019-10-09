package com.team2502.offseason2019;

import com.team2502.offseason2019.command.autonomous.DoNothingCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.TimeUnit;

public class AutoPicker
{

    private static SendableChooser<AutoMode> autoChooser;

    public enum AutoMode
    {
        DO_NOTHING("Do Nothing", DoNothingCommand::new);

        public final CommandFactory commandFactory;

        public final String name;

        AutoMode(String name, CommandFactory commandFactory)
        {
            this.commandFactory = commandFactory;
            this.name = name;
        }

        public Command getInstance()
        {
            return commandFactory.getInstance();
        }
    }

    static void putToSmartDashboard()
    {
        autoChooser = new SendableChooser<>();

        for(int i = 0; i < AutoMode.values().length; i++)
        {
            AutoMode mode = AutoMode.values()[i];
            if(i == 0) { autoChooser.setDefaultOption(mode.name, mode); }
            else { autoChooser.addOption(mode.name, mode); }
        }

        SmartDashboard.putData("auto_modes", autoChooser);
    }

    static Command getAutoInstance() { return autoChooser.getSelected().getInstance(); }

    @FunctionalInterface
    public interface CommandFactory
    {
        Command getInstance();
    }
}
