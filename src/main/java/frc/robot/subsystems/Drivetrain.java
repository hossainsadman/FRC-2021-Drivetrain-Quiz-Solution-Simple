// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
	/** Creates a new Drivetrain subsystem. */

	//Variable Shadowing
    private final CANSparkMax rightMotor;
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightFollowerMotor;
    private final CANSparkMax leftFollowerMotor;

	public Drivetrain(int leftMaster, int leftFollower, int rightMaster, int rightFollower) {
		rightMotor = new CANSparkMax(rightMaster, MotorType.kBrushless);
        leftMotor = new CANSparkMax(leftMaster, MotorType.kBrushless);
        rightFollowerMotor = new CANSparkMax(rightFollower, MotorType.kBrushless);
        leftFollowerMotor = new CANSparkMax(leftFollower, MotorType.kBrushless);

		leftFollowerMotor.follow(leftMotor);
        rightFollowerMotor.follow(rightMotor);
		
        leftMotor.stopMotor();
        rightMotor.stopMotor();

        leftMotor.setInverted(true);
        rightMotor.setInverted(false);
	}

	public void setMotors(double left, double right) {
		leftMotor.set(left);
		rightMotor.set(right);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
