package org.usfirst.frc.team5459.robot.commands;

import org.usfirst.frc.team5459.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToEncoderValueCommand extends Command {
	private double distance;
	private String output;
    public GoToEncoderValueCommand(double distance) {
        requires(Robot.driveSubsystem);
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.changeTalonProfile();
    	Robot.driveSubsystem.setTargetLeft(this.distance);
    	Robot.driveSubsystem.setTargetRight(this.distance);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 this.output = "RTerr:\t" + Robot.driveSubsystem.getRightError() + "\tLTerr:\t" + Robot.driveSubsystem.getLeftError();
    	 System.out.println(this.output);//This is for Debug
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.driveSubsystem.checkTargetLeft() && Robot.driveSubsystem.checkTargetRight() ) {
			return true;
		}else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.changeTalonProfile();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
