// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController; 

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...


	private static final XboxController driverController = new XboxController(Constants.XBOX_DRIVER);

	// Variable Shadowing
	private final Drivetrain drivetrain;
	
	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		drivetrain = new Drivetrain(Constants.LEFT_CANSPARKMAX, Constants.LEFT_CANSPARKMAX_FOLLOWER,
		Constants.RIGHT_CANSPARKMAX, Constants.RIGHT_CANSPARKMAX_FOLLOWER);
		
		drivetrain.setDefaultCommand(new TeleopDrive(drivetrain, driverController,
				Constants.DRIVE_FWD_REV, Constants.DRIVE_LEFT_RIGHT));

		// Configure the button bindings
		configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be created by
	 * instantiating a {@link GenericHID} or one of its subclasses ({@link
	 * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
	 * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		Button reverseDriveButton = new JoystickButton(driverController, Constants.REVERSE_DRIVE_DIRECTION);
        
        reverseDriveButton.whenPressed(() -> {
            TeleopDrive.toggleReverseDrive();
        });
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return new InstantCommand();
	}
}
