package jumpingalien.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;
/**
 * @author Afraz Salim && Niels van Belle
 * @version 2.0
 * @invar |isInWorld()
 * @invar |isValidPixelLeftX(getPixelLeftX())
 * @invar |isValidPixelBottomY(getPixelBottomY())
 * @invar |isValidVelocityX(getVelocityX())
 * @invar |isValidSprites(getSprites())
 * @invar |isValidTime(getTime())
 * 
 *
 **/
public class Shark extends DefaultWithoutProgram {
	/**
	 * 
	 * @param x
	 * 		The left x co of the new shark
	 * @param y
	 * 		The bottom y co of the new shark
	 * @param sprites
	 * 		The array of sprites of the new shark
	 * @effect super(x, y, sprites)
	 * @post new.getInitialVelocityX() == 0
	 * @post new.getMaxVelocityX() == 4
	 * @post new.getInitialAccelerationX() == 1.5
	 * @post new.getNumberOfPossibleActionsX() == 4
	 * @post new.getMinimumActionTime() == 1
	 * @post new.getMaximumActionTime() == 4
	 * @post new.getHitPoints() == 100
	 * @post new.getNumberOfActionsAfterJump() == 4
	 */
	public Shark(int x, int y, Sprite[] sprites) {
		super(x, y, sprites);
		this.setInitialVelocityX(0.0);
		this.setMaxVelocityX(4.0);
		this.setInitialAccelerationX(1.5);
		this.setNumberOfPossibleActions(4);
		this.setMinimumActionTime(1.0);
		this.setMaximumActionTime(4.0);
		this.setHitPoints(100);
		this.setNumberOfActionsAfterJump(4);
	}
	
	/**
	 * 
	 * @param x
	 * 		The left x co for the new shark
	 * @param y
	 * 		The bottom y co for the new shark
	 * @param sprites
	 * 		The array of sprites for the new shark
	 * @param program
	 * 		The program for the new shark
	 * @effect Plant(x, y, sprites)
	 * @post if (!program.isWellFormed())
	 * 			then new.getProgram() == null
	 * 		else
	 * 			then new.getProgram() == program
	 * 				program.getObject() == this
	 */
	public Shark(int x, int y, Sprite[] sprites, Program program) {
		this(x, y, sprites);
		if (!program.isWellFormed()) {
			this.setProgram(null);
		}
		else {
			this.setProgram(program);
			program.setObject(this);
		}
	}

	/**
	 * 
	 * @param numberOfActions
	 * 			The current number of actions after the last jump of this shark
	 * @post new.getNumberOfActionsAfterJump() == numberOfActions
	 */
	private void setNumberOfActionsAfterJump(int numberOfActions) {
		this.numberOfActionsAfterJump = numberOfActions;
	}
	
	/**
	 * 
	 * Return the current number of actions after the last jump of this shark
	 */
	@Basic @Model
	private int getNumberOfActionsAfterJump() {
		return this.numberOfActionsAfterJump;
	}
	
	/**
	 * Variable registering the current number of actions after the last jump of this shark
	 */
	private int numberOfActionsAfterJump;
	
	@Override
	protected void updateAction() {
		if (this.actionNeedUpdate()) {
			this.setActionTime(0.0);
			this.setCurrentActionTime(this.getRandomCurrentActionTime());
			if (this.isJumping()) {
				this.setIsJumping(false);
				this.setInitialVelocityY(0);
				this.setInitialAccelerationY(0);
				this.setVelocityY(0);
				this.setAccelerationY(0);
			}
			int geoBottom ;
			geoBottom = this.getBottomGeologicalfeature();
			if ( (geoBottom != AIR) && (geoBottom != MAGMA) ) {
				int geoMain ;
				geoMain = this.getMainGeologicalfeature();
				int action = this.getRandomAction();
				if (action < 2) {
					this.setVelocityY(0.0);
					if (geoMain == WATER) {
						Random rndAction = new Random();
						double accY = 0.4 * rndAction.nextDouble() - 0.2;
						this.setAccelerationY(accY);
					}
					else {
						this.setAccelerationY(0.0);					
					}
				}
				else {
					this.setInitialVelocityY(2.0);
					this.setInitialAccelerationY(0);
				}
				this.startAction(action);				
			}
			if (this.isJumping()) {
				this.setNumberOfPossibleActions(2);
				this.setNumberOfActionsAfterJump(0);
			}
			else {
				this.setNumberOfActionsAfterJump(this.getNumberOfActionsAfterJump() + 1);
				if (this.getNumberOfActionsAfterJump() > 4)
					this.setNumberOfPossibleActions(4);
			}
		}
	}
	
	/**
	 * @post if (this.getMainGeologicalFeature() == water)
	 * 			then new.getInitialVelocityY() == 2
	 * 				new.getInitialAccelerationY() == 0
	 * 				new.getIsJumping() == true
	 * 				new.getVelocityY() == new.getInitialVelocityY()
	 * 				new.getAccelerationY() == new.getInitialAccelerationY()
	 * 				new.getIsFalling() == true
	 */
	@Override
	public void jump() {
		if (this.getMainGeologicalfeature() == WATER) {
			this.setInitialVelocityY(2.0);
			this.setInitialAccelerationY(0);
			this.setIsJumping(true);
			this.setVelocityY(this.getInitialVelocityY());
			this.setAccelerationY(this.getInitialAccelerationY());
			this.setIsFalling(false);
		}
	}
	
	/**
	 * @post if (this.isJumping())
	 * 			then new.getIsJumping() == false
	 * 				new.getVelocityY() == 0
	 * 				new.getAccelerationY() == 0
	 */
	@Override
	public void stopJumping() {
		if ( this.isJumping() ) {
			this.setIsJumping(false);
			this.setVelocityY(0);
			this.setAccelerationY(0);			
		}
	}
	
	@Override
	protected void advanceTime(double timee, World world) {
		double time = timee;
		double timePast = timee;
		double dt;
		this.updateTime(time);
		this.setWorld(world);
		while (timePast > 0.0) {
			if (!this.isDeath()) {	
				int geoMain = this.getMainGeologicalfeature();
				this.setPassableDirections();
				
				if (!(this.isJumping()) && (this.getTopGeologicalfeature() != WATER)) {
					this.setAllowedUp(false);
				}			
				if (this.isFalling()) {
					if ( (this.getBottomGeologicalfeature() == GROUND) || (this.getTopGeologicalfeature() == WATER) ) {
						this.stopFall();
					}
				}
				if ( (!this.isFalling()) && ((this.getBottomGeologicalfeature() == AIR) || (this.getBottomGeologicalfeature() == MAGMA)) ) {
					this.startFall();
				}
				dt = this.getMinimumDt(timePast);
				this.setImmuneTime(this.getImmuneTime() + dt);
				if (this.getImmuneTime() >= 0.2)
					this.setIsImmune(false);
				this.collideObjectsActions();
				this.updateLocationAndVelocity(dt);
				if ( (geoMain == MAGMA) || (geoMain == AIR) ) {
					this.setInAirTime(this.getInAirTime()+dt);
				}
				else {
					this.setInAirTime(0);					
				}
				if ( this.getInAirTime()>=0.2 ) {
					this.addHitPointsProperty(-6);
					this.setInAirTime(0);					
				}
				this.setActionTime(this.getActionTime() + dt);
			}
			else {
				dt = timePast;
				this.setTimeDeath(this.getTimeDeath() + dt);
				if (this.getTimeDeath() >= 0.6) {
					world.setInvisibleWorldObject(this);	
					this.terminate();
				}
			}
			timePast = timePast - dt;
		}
	}
	
	/**
	 * 
	 * @param time
	 * 			The new time in air
	 * @post new.getInAirTime() == time
	 */
	private void setInAirTime(double time) {
		this.inAirTime = time;
	}
	
	/**
	 * 
	 * Return the current time in air
	 */
	@Model
	private double getInAirTime() {
		return this.inAirTime;
	}

	/**
	 * A private variable to store the  time in air.
	 */
	private double inAirTime = 0;
}


