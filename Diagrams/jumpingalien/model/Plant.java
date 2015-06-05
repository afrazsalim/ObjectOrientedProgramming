package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;
/**
 * 
 * @author Afraz Salim && Niels van Belle
 * @version 2.0
 * @invar |isValidPixelLeftX(getPixelLeftX())
 * @invar |isValidPixelBottomY(getPixelBottomY())
 * @invar |isValidVelocityX(getVelocityX())
 * @invar |isValidSprites(getSprites())
 * @invar |isValidTime(getTime())
 */
public class Plant extends DefaultWithoutProgram {
	


	/**
	 * 
	 * @param x
	 * 		The left x co for the new plant
	 * @param y
	 * 		The bottom y co for the new plant
	 * @param sprites
	 * 		The array of sprites for the new plant
	 * @effect super(x, y, sprites)
	 * @post new.getInitialVelocityX() == 0.5
	 * @post new.getMaxVelocityX() == 0.5
	 * @post new.getInitialAccelerationX() == 0
	 * @post new.getCurrentActionTime() == 0.5
	 * @post new.getNumberOfPossibleActions() == 2
	 * @post new.getHitPoints() == 1 
	 */
	public Plant(int x, int y, Sprite[] sprites) {
		super(x, y, sprites);
		this.setInitialVelocityX(0.5);
		this.setMaxVelocityX(0.5);
		this.setInitialAccelerationX(0.0);
		this.setCurrentActionTime(0.5);
		this.setNumberOfPossibleActions(2);
		this.setHitPoints(1);
	}
	
	/**
	 * 
	 * @param x
	 * 		The left x co for the new plant
	 * @param y
	 * 		The bottom y co for the new plant
	 * @param sprites
	 * 		The array of sprites for the new plant
	 * @param program
	 * 		The program for the new plant
	 * @effect Plant(x, y, sprites)
	 * @post if (!program.isWellFormed())
	 * 			then new.getProgram() == null
	 * 		else
	 * 			then new.getProgram() == program
	 * 				program.getObject() == this
	 */
	public Plant(int x, int y, Sprite[] sprites, Program program){
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
	 * @post if this.actionNeedUpdate()
	 * 			then new.getActionTime() == 0
	 * 				if this.isMovingLeft()
	 * 					then new.isMovingRight()
	 * 				else
	 * 					then new.isMovingLeft()
	 */
	@Model @Override
	protected void updateAction() {
		if (this.actionNeedUpdate()){
			this.setActionTime(0.0);
			if (this.isMovingLeft()) {
				this.startMoveRight();
			}
			else {
				this.startMoveLeft();
			}
		}
	}
	
	/**
	 * 
	 * @param timee
	 * 			The time for the calculations and updates for this plant
	 * @param world
	 * 			The world for this plant
	 * @effect updateTime(time)
	 * @post new.getWorld() == world
	 * @effect while timee > 0
	 * 				if !this.isDeath()
	 * 					then setPassableDirections()
	 * 						isHit(checkCollision(world.getMazub(),-1))
	 * 						updateLocationAndVelocity(this.getMinimumDt(timee))
	 * 						setActionTime(this.getActionTime() + this.getMinimumDt(timee))
	 * 						timee -= this.getMinimumDt(timee)
	 * 				else
	 * 					then setTimeDeath(this.getTimeDeath() + timee)
	 * 						timee == 0
	 * 						if this.getTimeDeath() >= 0.6
	 * 							then this.terminate()
	 * 							then world.setInvisibleWorldObject(this)
	 */
	@Model @Override
	protected void advanceTime(double timee, World world) {
		double time = timee;
		double timePast = timee;
		double dt;
		this.updateTime(time);
		this.setWorld(world);
		while (timePast > 0.0) {
			if (!this.isDeath()) {
				this.setPassableDirections();
				dt = this.getMinimumDt(timePast);
				Mazub mazub = this.getWorld().getMazub();
				if (this.checkCollisionObject(mazub)  && !this.isDeath() && mazub.canEat()) {
					this.blockDirectionOnCollision(mazub);
					mazub.setHitPoints(mazub.getHitPoints() + 50);
					this.addHitPointsEnemy(-1);
				}
				this.updateLocationAndVelocity(dt);
				this.setActionTime(this.getActionTime() + dt);
			}
			else {
				dt = timePast;
				this.setTimeDeath(this.getTimeDeath() + dt);
				if (this.getTimeDeath() >= 0.6) {
					this.terminate();
					this.getWorld().setInvisibleWorldObject(this);
				}
			}
			timePast = timePast - dt;
		}
	}
	
	public void jump() {
	}
	
}