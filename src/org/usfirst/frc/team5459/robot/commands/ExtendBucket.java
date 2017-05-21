package org.usfirst.frc.team5459.robot.commands;

import org.usfirst.frc.team5459.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ExtendBucket extends InstantCommand {

    public ExtendBucket() {
        super();
        requires(Robot.bucketSubsystem);;
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.bucketSubsystem.extendBucket();
    }

}
