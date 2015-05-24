package jumpingalien.model;

import java.util.ArrayList;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;
import jumpingalien.model.*;

/**
 * 
 * @author Niels Van Belle & Afraz Salim
 * @version 2.0
 * @invar |isInWorld()
 * @invar |isValidPixelLeftX(getPixelLeftX())
 * @invar |isValidPixelBottomY(getPixelBottomY())
 * @invar |isValidVelocityX(getVelocityX())
 * @invar |isValidSprites(getSprites())
 * @invar |isValidTime(getTime())
 * @invar isValideSchool(school)
 */
public class Slime extends DefaultWithoutProgram {

	/**
	 * 
	 * @param x
	 * 		The left x co of the new slime 
	 * @param y
	 * 		The bottom y co of the new slime
	 * @param sprites
	 * 		The array of sprites of the new slime
	 * @param school
	 * 		The school of the new slime
	 * @effect super(x, y, sprites)
	 * @post new.getInitialVelocityX() == 0
	 * @post new.getMaxVelocityX() 2.5
	 * @post new.getInitialAccelerartionX() == 0.7
	 * @post new.getSchool() == school
	 * @effect school.addToSchool(this)
	 * @post new.getNumberOfPossibleActions() == 2
	 * @post new.getMinimumActionTime() == 2
	 * @post new.getMaximumActionTime() == 6
	 * @post new.getHitPoints() == 100
	 */
	public Slime(int x, int y, Sprite[] sprites, School school) {
		super(x, y, sprites);
		this.setInitialVelocityX(0.0);
		this.setMaxVelocityX(2.5);
		this.setInitialAccelerationX(0.7);
		this.setSchool(school);
		school.addToSchool(this);
		this.setNumberOfPossibleActions(2);
		this.setMinimumActionTime(2.0);
		this.setMaximumActionTime(6.0);
		this.setHitPoints(100);
	}

	/**
	 * 
	 * @param x
	 * 		The left x co for the new slime
	 * @param y
	 * 		The bottom y co for the new slime
	 * @param sprites
	 * 		The array of sprites for the new slime
	 * @param program
	 * 		The program for the new slime
	 * @effect Slime(x, y, sprites)
	 * @post if (!program.isWellFormed())
	 * 			then new.getProgram() == null
	 * 		else
	 * 			then new.getProgram() == program
	 * 				program.getObject() == this
	 */
	public Slime(int x, int y, Sprite[] sprites, School school, Program program) {
		this(x, y, sprites, school);
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
	 * Return the current school of this slime
	 */
	@Basic
	public School getSchool() {
		return school;
	}

	/**
	 * 
	 * @param school
	 * 			The new school of this slime
	 * @pre school.isValideNumberOfSchools()
	 * @post new.getSchool() == school
	 */
	public void setSchool(School school) {
		assert this.isValideSchool(school);
		this.school = school;
	}
	
	/**
	 * Variable registering the current school of the slime
	 */
	private School school;
	
	/**
	 * 
	 * @param school
	 * 			The school that need to be tested
	 * @return if (this.isDeath())
	 * 				then result == 
	 * 					school == null
	 * 			else
	 * 				School.getNumberOfschools() < 10 && school != null
	 */
	private boolean isValideSchool(School school) {
		if (this.isDeath())
			return school == null;
		return School.getNumberOfschools() < 10 && school != null && !school.isTerminated();
	}
	
	/**
	 * 
	 * @return result == this.isValideSchool(this.getSchool()) && 
	 * 				(this.getSchool() == null || (this.getSchool().getSlimesOutOfSchool().contains(this)));
	 */
	private boolean hasProperSchool() {
		return this.isValideSchool(this.getSchool()) && (this.getSchool() == null || (this.getSchool().getSlimesOutOfSchool().contains(this)));
	}
	
	/**
	 * @param hitpoints
	 * 			The hit points to add
	 * @post new.getHitPoints() == this.getHitPoints + hitPoints + 1
	 * @effect for each slime in this.getSchool().getSlimesOutOfSchool()
	 * 				do slime.addHitPointsSlime(-1)
	 * @effect if (this.getHitPoints() == 0)
	 * 				then this.die()
	 * 					this.getSchool().removeFromSchool(this)
	 * 			else
	 * 				then  this.setImmuneTime(0.0);
	 *					this.setIsImmune(true);
	 */
	@Override
	protected void addHitPointsEnemy(int hitPoints) {
		this.setHitPoints(this.getHitPoints() + hitPoints + 1);
		for (Slime slime: this.getSchool().getSlimesOutOfSchool()) {
			slime.addHitPointsSlime(-1);
		}
		if (this.getHitPoints() == 0) {
			this.die();
			this.getSchool().removeFromSchool(this);
		}
		else {
			this.setImmuneTime(0.0);
			this.setIsImmune(true);
		}
	}
	
	/**
	 * @param hitpoints
	 * 			The hit points to add
	 * @post new.getHitPoints() == this.getHitPoints + hitPoints + 1
	 * @effect for each slime in this.getSchool().getSlimesOutOfSchool()
	 * 				do slime.addHitPointsSlime(-1)
	 * @effect if (new.getHitPoints() == 0)
	 * 				then this.die()
	 * 					this.getSchool().removeFromSchool(this)
	 */
	@Override
	protected void addHitPointsProperty(int hitPoints) {
		this.setHitPoints(this.getHitPoints() + hitPoints + 1);
		for (Slime slime: this.getSchool().getSlimesOutOfSchool())
			slime.addHitPointsSlime(-1);
		if (this.getHitPoints() == 0) {
			this.die();
			this.getSchool().removeFromSchool(this);
		}
	}
	
	/**
	 * @param hitpoints
	 * 			The hit points to add
	 * @post new.getHitPoints() == this.getHitPoints + hitPoints + 1
	 * @effect if (new.getHitPoints() == 0)
	 * 				then this.die()
	 * 					this.getSchool().removeFromSchool(this)
	 */
	protected void addHitPointsSlime(int hitPoints) {
		this.setHitPoints(this.getHitPoints() + hitPoints);
		if (this.getHitPoints() == 0) {
			this.die();
			this.getSchool().removeFromSchool(this);
		}
	}
	/**
	 * 
	 * @param slime
	 * 			The slime of collision
	 * @param firstSize
	 * 			Size of the school of the slime
	 * @param secondSize
	 * 			Size of the school of the slime of collision
	 * @effect for each slime in slime.getSchool().getSlimesOutOfSchool()
	 * 				do slime.setHitPoints(slime.getHitpoints() + 1)
	 * @effect slime.setHitPoints(slime.getHitpoints() - secondSize)
	 * @effect slime.getSchool().removeFromSchool(slime)
	 * @effect this.getSchool.addToSchool(slime)
	 * @effect for each slime in this.getSlimesOutOfSchool()
	 * 				slime.setHitPoints(slime.getHitPoints() - 1)
	 * @effect slime.setHitPoints(slime.getHitPoints() + firstSize)
	 */
	private void updateHitPointsChangeSchool(Slime slime, int firstSize, int secondSize) {
		for(Slime slimes: slime.getSchool().getSlimesOutOfSchool()){
			slimes.setHitPoints(slimes.getHitPoints() + 1);
		}
		slime.setHitPoints(slime.getHitPoints() - secondSize);
		slime.getSchool().removeFromSchool(slime);
		this.getSchool().addToSchool(slime);
		for (Slime slimes: slime.getSchool().getSlimesOutOfSchool()){
			slimes.setHitPoints(slimes.getHitPoints() - 1);
		}
		slime.setHitPoints(slime.getHitPoints() + firstSize);
	}
	
	/**
	 * @effect if (this.actionNeedUpdate())
	 * 			then setActionTime(0)
	 * 				setCurrentActionTime(this.getRandomCurrentActionTime())
	 * 				startAction(this.getRandomAction())
	 */
	@Override
	protected void updateAction() {
		if (this.actionNeedUpdate()) {
			this.setActionTime(0.0);
			this.setCurrentActionTime(this.getRandomCurrentActionTime());
			this.startAction(this.getRandomAction());
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
				if (this.isFalling()) {
					if ( this.getBottomGeologicalfeature() == GROUND ) {
						this.stopFall();
					}
				}
				if ( (!this.isFalling()) && (!(this.getBottomGeologicalfeature() == GROUND)) ) {
					this.startFall();
				}
				for (Slime slime: world.getCollectionOfSlimes()) {
					if (this.checkCollisionObject(slime) && !slime.isDeath()) {
						this.blockDirectionOnCollision(slime);
						if (!this.getSchool().equals(slime.getSchool())) {
							int firstSize = this.getSchool().sizeOfSchool();
							int secondSize = slime.getSchool().sizeOfSchool();
							if (firstSize > secondSize) {
								this.updateHitPointsChangeSchool(slime, firstSize, secondSize);
							}
							else if (secondSize > firstSize) {
								slime.updateHitPointsChangeSchool(this, secondSize, firstSize);
							}	
						}	
					}
				}
				dt = this.getMinimumDt(timePast);
				this.setImmuneTime(this.getImmuneTime() + dt);
				if (this.getImmuneTime() >= 0.6)
					this.setIsImmune(false);
				if ( ((geoMain == MAGMA) || (geoMain == WATER)) && !this.isImmune()) {
					this.addHitPointsProperty(-6);
				}
				this.collideObjectsActions();
				this.updateLocationAndVelocity(dt);
				this.setActionTime(this.getActionTime() + dt);
			}
			else {
				dt = timePast;
				this.setTimeDeath(this.getTimeDeath() + dt);
				if (this.getTimeDeath() >= 0.6) {
					this.setSchool(null);
					this.terminate();
					world.setInvisibleWorldObject(this);
				}
			}
			timePast = timePast - dt;
		}
	}
	
	public void jump() {
	}
}
	