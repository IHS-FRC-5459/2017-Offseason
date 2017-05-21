package org.usfirst.frc.team5459.robot.commands;

import org.usfirst.frc.team5459.robot.*;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class RetracktBucket extends InstantCommand {

    public RetracktBucket() {
        super();
        requires(Robot.bucketSubsystem);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.bucketSubsystem.retrackBucket();
    }

}
