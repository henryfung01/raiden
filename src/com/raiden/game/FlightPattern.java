package com.raiden.game;

import java.util.ArrayList;

public class FlightPattern {
	private ArrayList<Movement> movements;
	private int currentMovement;
	private int currentTime;
	private int totalDuration;
	
	private boolean loop;
	private boolean active;

	public FlightPattern() {
		loop = true;
		active = true;

		movements = new ArrayList<Movement>();
		totalDuration = 0;

		currentTime = 0;
		currentMovement = 0;
	}
	
	public FlightPattern(FlightPattern flightPattern) {
		loop = flightPattern.loop;
		active = true;
		movements = new ArrayList<Movement>(flightPattern.movements);
		totalDuration = flightPattern.totalDuration;
		currentTime = 0;
		currentMovement = 0;
	}

	public void addMovement(int angle, int duration, Direction turnDirection) {
		totalDuration += duration;
		movements.add(new Movement(angle, totalDuration, turnDirection));
	}
	
	
	public void setMovements(ArrayList<Movement> newMovements, int duration) {
		totalDuration = duration;
		this.movements = newMovements;
	}

	public void update(float elapsedTime) {
		if (movements.size() > 1) {
			currentTime += (int)elapsedTime;
			if (currentTime >= totalDuration) {
				if (loop){
					currentTime = currentTime % totalDuration;
					currentMovement = 0;
				} else {
					active = false;
				}

			}

			while (currentTime > getMovement(currentMovement).endTime && active) {
				currentMovement++;
			}
		}
	}

	public int getCurrentAngle() {
		if (movements.size() == 0) {
			return 0;
		} else {
			return getMovement(currentMovement).angle;
		}
	}
	
	public Direction getCurrentDirection() {
		if (movements.size() == 0) {
			return null;
		} else {
			return getMovement(currentMovement).turnDirection;
		}
	}

	private Movement getMovement(int i) {
		return movements.get(i);
	}
	
	public class Movement {

		int angle;
		int endTime;
		Direction turnDirection;

		public Movement(int angle, int endTime, Direction turnDirection) {
			this.angle = angle;
			this.endTime = endTime;
			this.turnDirection = turnDirection;
		}
	}
}