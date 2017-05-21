package org.usfirst.frc.team5459.robot.commands;

import org.usfirst.frc.team5459.robot.Robot;
import org.usfirst.frc.team5459.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.Command;


public class DriveCommand extends Command {
	
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	public DriveCommand() {
		super();
		requires(driveSubsystem);
		System.out.println("I am working");
	}
	
	
	@Override
	protected void execute() {
		
		driveSubsystem.setSpeedLeft(-Robot.oi.getLeftSpeed());
		
		driveSubsystem.setSpeedRight(-Robot.oi.getRightSpeed());
         
		super.execute();
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
