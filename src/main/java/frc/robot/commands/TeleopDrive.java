// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TeleopDrive extends CommandBase {
	// Variable Shadowing
	private final Drivetrain drivetrain;
	private final GenericHID controller;
	private final int X_AXIS;
	private final int Y_AXIS;

	// Settings
	private static final double DEADZONE = 0.15;

	// States
	private static boolean reverseDrive = false;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public TeleopDrive(Drivetrain drivetrain, GenericHID controller, int fwdRevAxis, int leftRightAxis) {
		this.drivetrain = drivetrain;
		this.controller = controller;
		this.Y_AXIS = fwdRevAxis;
		this.X_AXIS = leftRightAxis;
		addRequirements(drivetrain);
	}

	public static void toggleReverseDrive() {
		reverseDrive = !reverseDrive;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
        double x = controller.getRawAxis(X_AXIS);
		double y = -controller.getRawAxis(Y_AXIS);
		if (!(Math.abs(x) > DEADZONE)) {
			x = 0;
		}
		if (!(Math.abs(y) > DEADZONE)) {
			y = 0;
		}

		x = Math.copySign(x * x, x);
		y = Math.copySign(y * y, y);
		

		if (reverseDrive) {
			y = -y;
		}
		
		double l = y + x;
		double r = y - x;

		drivetrain.setMotors(l, r);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drivetrain.setMotors(0, 0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
