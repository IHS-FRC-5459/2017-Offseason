package org.usfirst.frc.team5459.robot.commands;

import org.usfirst.frc.team5459.robot.*;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class Climb extends InstantCommand {

    public Climb() {
        super();
        requires(Robot.climberSubsystem);
    }

    // Called once when the command executes
    @Override
	protected void initialize() {
    	Robot.climberSubsystem.climb();
    }

    @Override
    protected boolean isFinished() {
    	// TODO Auto-generated method stub
    	return true;
    }
    @Override
    protected void end() {
    	Robot.climberSubsystem.stop();
    };
}
