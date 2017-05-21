package org.usfirst.frc.team5459.robot.commands;

import org.usfirst.frc.team5459.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto extends Command {
	int i = 0;
    public Auto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.setSpeedLeft(0.5);
    	Robot.driveSubsystem.setSpeedRight(0.5);
    	i++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	if (i > 100) {
			return true;
		}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setSpeedLeft(0);
    	Robot.driveSubsystem.setSpeedRight(0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
