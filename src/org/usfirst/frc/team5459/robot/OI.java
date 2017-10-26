package org.usfirst.frc.team5459.robot;

import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.HIDType;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.usfirst.frc.team5459.robot.commands.Climb;
import org.usfirst.frc.team5459.robot.commands.ExtendBucket;
import org.usfirst.frc.team5459.robot.commands.RetracktBucket;
import org.usfirst.frc.team5459.robot.commands.Shifter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	
	private XboxController driver;
	private XboxController operator;
	
	
	
	public OI(){
		driver = new XboxController(0);
		operator = new XboxController(1);
		Button bumperRight = new JoystickButton(operator, 5);
		Button bumperLeft = new JoystickButton(operator, 4);
		Button climber = new JoystickButton(operator, 6);
		Button shifter = new JoystickButton(driver, 5);
		bumperRight.whenPressed(new ExtendBucket());
		bumperLeft.whenPressed(new RetracktBucket());
		shifter.whenPressed(new Shifter());
		//climber.whenPressed(new Climb());
		
	}
	
	public double getLeftSpeed() {
		return this.driver.getY(Hand.kLeft);
	}
	
	public double getRightSpeed(){
		System.out.println(Robot.getMiddleRight().get());
		System.out.println(this.driver.getY(Hand.kRight));
		return this.driver.getY(Hand.kRight);
	}
	public boolean getRightTrig(){
		return this.driver.getTrigger(Hand.kRight);
	}
	public boolean getLeftTrig(){
		return this.driver.getTrigger(Hand.kLeft);
	}
	public boolean getClimber() {
		return operator.getAButton();
	}
}
