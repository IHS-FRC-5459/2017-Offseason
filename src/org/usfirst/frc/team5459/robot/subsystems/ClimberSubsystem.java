package org.usfirst.frc.team5459.robot.subsystems;

import org.usfirst.frc.team5459.robot.Robot;
import org.usfirst.frc.team5459.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

    private CANTalon climber = Robot.getClimber();
    
    public void climb(){
    	climber.set(1.0);
    }

    public void stop(){
    	climber.set(0);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

