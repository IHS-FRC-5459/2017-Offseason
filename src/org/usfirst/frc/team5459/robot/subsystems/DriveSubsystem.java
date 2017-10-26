package org.usfirst.frc.team5459.robot.subsystems;

import org.usfirst.frc.team5459.robot.Robot;
import org.usfirst.frc.team5459.robot.RobotMap;
import org.usfirst.frc.team5459.robot.commands.DriveCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon rightController ;
	private CANTalon leftController ;
	private double scaleFactor = 0.8;
	private DoubleSolenoid shifter;

	public DriveSubsystem() {
		rightController = Robot.getMiddleRight();
		leftController = Robot.getMiddleLeft();
		shifter = Robot.getShifter();
	}
	
	public void changeTalonProfile(){
		if (rightController.getP() == 0 || leftController.getP() == 0) {
			rightController.setProfile(0);
			leftController.setProfile(0);
		}else {
			rightController.setProfile(1);
			leftController.setProfile(1);
		}
	}
	public void setSpeedRight(double power){
		rightController.changeControlMode(TalonControlMode.PercentVbus );
		
		
		rightController.set(-power * scaleFactor);
	}
	
	public void setSpeedLeft(double power){
		leftController.changeControlMode(TalonControlMode.PercentVbus);
		
		leftController.set(power * scaleFactor); 
		
	}
	
    public void setTargetLeft(double distance){
    	leftController.changeControlMode(TalonControlMode.Position);
    	distance *= scaleFactor;
    	distance += leftController.getPosition();
    	leftController.set(distance);
    }
    
    
    public void setTargetRight(double distance){
    	rightController.changeControlMode(TalonControlMode.Position);
    	distance *= scaleFactor;
    	distance += rightController.getPosition();
    	rightController.set(distance);
    }
    
    public boolean checkTargetRight(){
    	if (rightController.getClosedLoopError() < 10) {
			return true;
		}else {
			return false;
		}
    }
    
    
    public boolean checkTargetLeft(){
    	if (leftController.getClosedLoopError() < 10) {
			return true;
		}else {
			return false;
		}
    }
    
    public void shift(){
    	if (shifter.get().equals(Value.kForward)) {
			shifter.set(Value.kReverse);
		} else {
			shifter.set(Value.kForward);
		}
    }
    
    public int getRightError(){
    	return rightController.getClosedLoopError();
    }
    
    public int getLeftError(){
    	return leftController.getClosedLoopError();
    }
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new DriveCommand());
		
	}
}

