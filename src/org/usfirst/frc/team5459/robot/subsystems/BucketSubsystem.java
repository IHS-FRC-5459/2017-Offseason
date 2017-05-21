package org.usfirst.frc.team5459.robot.subsystems;

import org.usfirst.frc.team5459.robot.Robot;
import org.usfirst.frc.team5459.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BucketSubsystem extends Subsystem {

    private DoubleSolenoid bucket = Robot.getBucket();

    public void extendBucket(){
    	bucket.set(Value.kForward);
    }
    
    public void retrackBucket(){
    	bucket.set(Value.kReverse);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

