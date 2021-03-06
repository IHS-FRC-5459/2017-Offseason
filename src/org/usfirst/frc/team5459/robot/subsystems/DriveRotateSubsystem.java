package org.usfirst.frc.team5459.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DriveRotateSubsystem extends PIDSubsystem {

    // Initialize your subsystem here
    public DriveRotateSubsystem(double degree) {
    	super(0, 0, 0);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(degree);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
