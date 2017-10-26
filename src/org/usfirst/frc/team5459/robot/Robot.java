
package org.usfirst.frc.team5459.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5459.robot.commands.Auto;
import org.usfirst.frc.team5459.robot.commands.Climb;
import org.usfirst.frc.team5459.robot.commands.DriveCommand;
import org.usfirst.frc.team5459.robot.subsystems.BucketSubsystem;
import org.usfirst.frc.team5459.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team5459.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5459.robot.subsystems.ExampleSubsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private static NetworkTable data;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	private static CANTalon topRight;
	private static CANTalon middleRight;
	private static CANTalon bottomRight;
	private static CANTalon topLeft;
	private static CANTalon middleLeft;
	private static CANTalon bottomLeft;
	private static CANTalon climber;
	private static DoubleSolenoid shifter;
	private static DoubleSolenoid bucket;
	private static ADIS16448IMU imu;
	private static Ultrasonic xUltrasonic;
	private static Ultrasonic yUltrasonic;
	private static boolean winning = true;
	public static ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	public static BucketSubsystem bucketSubsystem = new BucketSubsystem();
	public static DriveSubsystem  driveSubsystem = new DriveSubsystem();
	public static OI oi;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		System.out.println("I am here");
		topRight = new CANTalon(1);
		middleRight = new CANTalon(3);
		bottomRight = new CANTalon(2);
		topLeft = new CANTalon(5);
		middleLeft = new CANTalon(6);
		bottomLeft = new CANTalon(7);
		climber = new CANTalon(4);
		shifter = new DoubleSolenoid(2,3);
		shifter.set(Value.kForward);
		bucket= new DoubleSolenoid(0, 1);
		climber.reverseOutput(true);
		//Setting Followers
		//topRight is Right Side Master (TalonSRX #1)0.062, 0.00062, 0.62)
		//middleRight.reset();
//		middleRight.setProfile(0);
//		middleRight.setPID( 0.0, 0.0, 0.0);//TODO: make multiple profiles
		
//		middleRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
//		middleRight.setEncPosition(0);
//		middleRight.setAllowableClosedLoopErr(10);
		middleRight.reverseSensor(true);
//		middleRight.configNominalOutputVoltage(+0f, -0f);
//		middleRight.configPeakOutputVoltage(+12f, -12f);
		topRight.changeControlMode(TalonControlMode.Follower);
		topRight.set(middleRight.getDeviceID());
		topRight.reverseOutput(true);
		bottomRight.changeControlMode(TalonControlMode.Follower); //TalonSRX #3
		bottomRight.set(middleRight.getDeviceID());
//		middleRight.setProfile(1);
//		middleRight.setPID( 0.0, 0.0, 0.0);
		//climber is the climber Motor (TalonSRX #4)
		//TopLeft is Right Side Master (TalonSRX #5)
		//middleLeft.reset();
		//middleLeft.setProfile(0);
//		middleLeft.setPID(0.0, 0.00, 0.0);
//		middleLeft.setEncPosition(0);
//		middleLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
//		middleLeft.configNominalOutputVoltage(+0f, -0f);
//		middleLeft.configPeakOutputVoltage(+12f, -12f);
		middleLeft.reverseSensor(false);
//		middleLeft.setAllowableClosedLoopErr(10);
		topLeft.changeControlMode(TalonControlMode.Follower); //TalonSRX #6
		topLeft.set(middleLeft.getDeviceID());
		topLeft.reverseOutput(true);
		bottomLeft.changeControlMode(TalonControlMode.Follower); //TalonSRX #7
		bottomLeft.set(middleLeft.getDeviceID());
//		middleLeft.setProfile(1);
//		middleLeft.setPID(0, 0, 0);
		//Sensors
		imu = new ADIS16448IMU();
		climberSubsystem = new ClimberSubsystem();
		bucketSubsystem = new BucketSubsystem();
		driveSubsystem = new DriveSubsystem();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new Auto();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Scheduler.getInstance().add(new DriveCommand());

		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	
		if (oi.getRightTrig()) {
			shifter.set(Value.kForward);
		} else if (oi.getLeftTrig()) {
			shifter.set(Value.kReverse);
		}
		System.out.println(shifter.get());
			
		if(oi.getClimber()){
			climber.set(1.0);
			
		}else {
			climber.set(0);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public static CANTalon getMiddleRight() {
		return middleRight;
	}

	public static CANTalon getMiddleLeft() {
		return middleLeft;
	}

	public static CANTalon getClimber() {
		return climber;
	}

	public static DoubleSolenoid getShifter() {
		return shifter;
	}

	public static DoubleSolenoid getBucket() {
		return bucket;
	}

	public static ADIS16448IMU getImu() {
		return imu;
	}

	public static Ultrasonic getxUltrasonic() {
		return xUltrasonic;
	}

	public static Ultrasonic getyUltrasonic() {
		return yUltrasonic;
	}

}
