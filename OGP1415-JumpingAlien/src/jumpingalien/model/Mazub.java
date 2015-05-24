package jumpingalien.model;

import java.util.stream.Stream;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

/**
 * A class of Mazub that represents an object so called alien, involving an
 * array of sprites the horizontal pixels and the vertical pixel.
 * 
 * 
 * @invar |isInWorld()
 * @invar |isValidPixelLeftX(getPixelLeftX())
 * @invar |isValidPixelBottomY(getPixelBottomY())
 * @invar |isValidVelocityX(getVelocityX())
 * @invar |isValidSprites(getSprites())
 * @invar |isValidTime(getTime())
 * 
 * @author Niels Van Belle & Afraz Salim
 * @version 2.0
 */
public class Mazub extends WorldObjects{
	/**
	 * 
	 * @param pixelLeftX
	 *            The given LeftPixel of the Mazub.
	 * @param pixelBottomY
	 *            The bottom y-co pixel of the mazub.
	 * @param sprites
	 *       An array consisting of mazub sprites.
	 * @pre..The given Left pixel of the mazub should be a valid pixel.it
	 *       should be between the bounds of the world.
	 *       |(isValidePixelLeftX(pixelLeftX)
	 * @pre..The given BottomY pixel of the mazub should be a valid pixel.it
	 *       should also between the vertical bounds of the world.
	 *       |(isValidePixelBottomY(pixelBottomY)
	 * @post. LeftpixelX of the mazub will be initialized with the given value
	 *       if and only if the precondition holds.
	 *       |super.setLeftPixelX(pixelLeftX);
	 * @post BottomYpixel of the mazub will be initialized with the given value
	 *       if and only if the precondition holds.
	 *       |super.setBottomPixelY(pixelBottomY);
	 * @post An array sprites will be initialized with given sprites.
	 *       |super.sprites = sprites;
	 * @post The initialVelocity for the X co-ordinate will be initialized with
	 *       the given value. |this.setInitialVelocityX(1.0);
	 * @post The max velocity of the x co-ordinates will be initialized with the
	 *       given value in parameter. |this.setMaxVelocityX(3.0);
	 * @post The initialAcceleration for the horizontal movement will be
	 *       initialized with the given value.
	 *       |this.setInitialAccelerationX(0.9);
	 * @throws IndexOutOfBoundException
	 *             Throws MazubOutOfWorldException if the pre-condition applied
	 *             on pixelleftx does not hold, it means whenever the left pixel
	 *             is out of the game world.
	 *             |if (!(isValidePixelLeftX(pixelLeftX)))
	 *             |throw new IndexOutOfBoundException("Pixel X is out of world.");
	 * @throws IndexOutOfBoundException
	 *             Throws MazubOutOfWorldException if the pre-condition applied
	 *             on PixelBottomY does not hold, it means whenever the
	 *             pixelBottomy is out of the game world, it throws an
	 *             exception. 
	 *             |if (!(isValidePixelBottomY(pixelBottomY)))
	 *             |throw new IndexOutOfBoundException("Pixel Y is out of world.");
	 */
	@Raw
	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws MazubOutOfWorldException {
		super(pixelLeftX, pixelBottomY, sprites);
		this.setInitialVelocityX(1.0);
		this.setMaxVelocityX(3.0);
		this.setInitialAccelerationX(0.9);
		this.setInitialVelocityY(8.0);
		this.initialize();
		this.setMaxHitpoints(500);
	}

	protected void initialize() {
		this.setHitPoints(100);
	}

	/**
	 * Returns an index according to the action of the alien.
	 * 
	 * @post If the alien is ducking and is moving to the right side of the
	 *       world or it's last move was to the right side of the world and this
	 *       move was in the last second. Then the index 6 will be chosen. 
	 *       |if (this.isDucking() && (this.isMovingRight() ||    (this.wasLastMoveRight() | && wasLastMoveXWithinLastSecond())))
	 *       |this.setCurrentIndex(6);
	 * @post If the alien is ducking and moving to the left side of the world or
	 *       it's last move was to the left side of the world and the last move
	 *       was in the last second. Then the index 7 will be chosen.
	 *       |if(this.isDucking && (this.IsMovingLeft() || (this.wasLastMoveLeft()
	 *       | && wasLastMoveXWithinLastSecond()))) 
	 *       |this.setCurrentIndex(7);
	 * @post If the alien is ducking, but not moving horizontal. Then the index
	 *       1 will be chosen. |if (this.isDucking() && !this.isMovingX())
	 *       |this.setCurrentIndex(1);
	 * @post If the alien is jumping and at the same time it's moving to the
	 *       right side of the world then the index 4 wil l be chosen.
	 *        |if (this.isMovingY() && this.isMovingRight())
	 *       |this.setCurrentIndex(4);
	 * @post if the alien is moving to the left side of the world and it's also
	 *       jumping then index 5 will be chosen. 
	 *       |if (this.isMovingY() && this.IsMovingLeft()) 
	 *       |this.setCurrentIndex(5);
	 * @post If the alien is not ducking and not moving horizontal and was not
	 *       moving horizontal in the last second, the index 0 will be chosen.
	 *       |if (!this.isMovingX() && !this.isDucking() && !wasLastMoveXWithinLastSecond()) 
	 *       |this.setCurrentIndex(0);
	 * @post If the last movement was to the right side of the world and in the
	 *       last second then the sprite from 2nd index will be chosen as
	 *       current sprite. 
	 *       |if (this.wasLastMoveRight() &&  wasLastMoveXWithinLastSecond())
	 *       | this.setCurrentIndex(2);
	 * @post if the last movement was to the left side of the world and in the
	 *       last second then the index 3 will be chosen as current index.
	 *       |if (this.wasLastMoveLeft() && wasLastMoveXWithinLastSecond())
	 *       |this.setCurrentIndex(3);
	 * @post If the alien is moving horizontal, the sprites of running cycle
	 *       will be used.
	 *        |if(this.isMovingX())
	 *        |this.updateSpriteRunning();
	 */
	private void setCurrentSprite() {
		if (this.isDucking()) {
			if (this.isMovingRight() || (this.wasLastMoveRight() && wasLastMoveXWithinLastSecond()))
				this.setCurrentIndex(6);
			else if (this.isMovingLeft() || (this.wasLastMoveLeft() && wasLastMoveXWithinLastSecond()))
				this.setCurrentIndex(7);
			else
				this.setCurrentIndex(1);
		} else if (this.isMovingY()) {
			if (this.isMovingRight())
				this.setCurrentIndex(4);
			else if (this.isMovingLeft())
				this.setCurrentIndex(5);
			else
				this.setCurrentIndex(0);
		} else if (!this.isMovingX()) {
			if (!wasLastMoveXWithinLastSecond())
				this.setCurrentIndex(0);
			else if (this.wasLastMoveRight())
				this.setCurrentIndex(2);
			else if (this.wasLastMoveLeft())
				this.setCurrentIndex(3);
		} else {
			this.updateSpriteRunning();
		}
	}

	



	/**
	 * A checker to check whether the alien is moving on horizontal plane.
	 * 
	 * @return Returns true if and only if the alien,s movement is to the right
	 *         side or to the left side.
	 *          |(this.isMovingRight()) ||(this.IsMovingLeft()
	 */
	public boolean isMovingX() {
		return (this.isMovingRight()) || (this.isMovingLeft());
	}

	/**
	 * Makes the alien to move on the horizontal plane to the right side.
	 * 
	 * @pre... In order to start to move to the right side the alien must not be
	 *         moving. |!(this.isMovingX())
	 * @effect... The new state of the alien will be changed to moving to right
	 *          state.
	 *          |this.setIsMovingRight(true)
	 * @post... The new velocity of the alien will be it,s initial velocity so
	 *          that alien begins to move with it,s initial horizontal velocity.
	 *          |this.setVelocityX(this.getInitialVelocityX())
	 * @effect.. Sets the last movement to the right side at true.
	 *          |this.setLastMoveRight(true);
	 * @effect. Sets the last movement of the alien to left side at false.
	 *          |this.setLastMoveLeft(false);
	 * @post... Alien,s horizontal acceleration will be set with it,s initial
	 *          acceleration.
	 *          |this.setAccelerationX(this.getInitialAccelerationX());
	 * @post..  The alien,s direction will be changed to the positive x-axis. 
	 *          |this.setDirection((int)Math.cos(0))
	 * @post    The alien's image is changed to the first image of the running
	 *          cycle animation to right 
	 *          |this.setCurrentIndex(this.startRunningCycleRight())
	 */
	public void startMoveRight() {
		assert (!isMovingRight());
		super.startMoveRight();
		this.setWasMovingRight(true);
		this.setLastMoveRight(true);
		this.setLastMoveLeft(false);
		this.setCurrentIndex(this.startRunningCycleRight());
	}

	/**
	 * Ends the movement of the alien to the right side.
	 * 
	 * @pre..  In order to end the alien,s movement to the right side, alien must
	 *         be moving to the right side. |if(this.isMovingRight())
	 * @post..  The alien state will be changed to not moving to the right side.
	 *          |this.setIsMovingRight(false)
	 * @post... Sets the alien,s horizontal velocity to to 0
	 *          |this.setVelocityX(0.0)
	 * @post... Sets the horizontal acceleration of the alien to 0
	 *          |this.setAccelerationX(0.0);
	 * @post... Time after the last movement will be set to zero.
	 *          |this.setTimeLastMoveX(0.0)
	 * @effect  The new state of the object will be set to false for moving right.
	 *          |this.setWasMovingRight(false);
	 * 
	 */
	public void endMoveRight() {
		assert (this.wasMovingRight());
		if (this.isMovingRight()) {
			this.setIsMovingRight(false);
			this.setVelocityX(0.0);
			this.setAccelerationX(0.0);
			this.setTimeLastMoveX(0.0);
			this.setWasMovingRight(false);
		}
	}
	
	/**
	 * A checker to check if the object was moving to right side.
	 * @return
	 *      Returns true if and only if the object was moving to the right side.
	 *      |result = (this.wasMovingRight)
	 */
	private boolean wasMovingRight() {
		return this.wasMovingRight;
	}
	
	/**
	 * A setter to set the current object,s state if it was moving to the right.
	 * @param wasMoving
	 *        The given parameter with which values will be initialized.
	 *        |new.wasMovingright = wasMoving
	 */
	private void setWasMovingRight(boolean wasMoving) {
		this.wasMovingRight = wasMoving;
	}
	
	/**
	 * A provate variable to register if the object was moving to the left side.
	 */
	private boolean wasMovingRight;

	/**
	 * A setter to set the set the last movement of the alien to the right side.
	 * 
	 * @param lastMoveRight
	 *            A boolean parameter holding the value of lastMovement was to
	 *            the right side or not.
	 * @post... The new value of the lastMoveRight will be equal to the given
	 *          value as parameter. |new.lastMoveRight = lastMoveRight
	 */
	private void setLastMoveRight(boolean lastMoveRight) {
		this.lastMoveRight = lastMoveRight;
	}

	/**
	 * Checks whether the last move was to the right side or not.
	 * 
	 * @return True if and only if the lastMovement was to the right side.
	 *         |result = (this.lastMoveRight)
	 */
	private boolean wasLastMoveRight() {
		return this.lastMoveRight;
	}

	/**
	 * A private variable to register the last movement of the alien to the
	 * right side.
	 */
	private boolean lastMoveRight;

	/**
	 * Alien starts moving to the right side of the world.
	 * 
	 * @pre...  In order to start to move to the left side the alien must not be
	 *          moving. |!(this.isMovingX())
	 * @effect. Alien,s state will changed from not moving to the left side to
	 *          move to the left side. |this.setIsMovingLeft(true);
	 * @post... Alien,s horizontal velocity will be set equal to it,s initial
	 *          horizontal velocity.
	 *          |this.setVelocityX(this.getInitialVelocityX());
	 * @post... Alien,s direction will be changed to the negative x-axis.
	 *          |this.setDirection((int)Math.cos(Math.PI));
	 * @post... Alien,s horizontal acceleration will be set with it,s initial
	 *          acceleration.
	 *          |this.setAccelerationX(this.getInitialAccelerationX());
	 * @effect..Registers the last movement was to the left side of the world.
	 *          |this.setLastMoveLeft(true);
	 * @effect. Sets the last movement to the right at false.
	 *          |this.setLastMoveRight(false);
	 * @post The alien's image is changed to the first image of the running
	 *          cycle animation to left
	 *         |this.setCurrentIndex(this.startRunningCycleLeft());
	 */
	@Override
	public void startMoveLeft() {
		assert(!isMovingLeft());
		super.startMoveLeft();
		this.setWasMovingLeft(true);
		this.setLastMoveLeft(true);
		this.setLastMoveRight(false);
		this.setCurrentIndex(this.startRunningCycleLeft());
	}

	/**
	 * Alien stops moving to left.
	 * 
	 * @pre.. In order to end the alien,s movement to the left side, alien must
	 *        be moving to the left side. |if(this.isMovingLeft())
	 * @effects The alien,s state will be changed to false to move left.
	 *          |this.setIsMovingLeft(false);
	 * @post... The alien,s velocity will be set to zero.
	 *          |this.setVelocityX(0.0);
	 * @post... The alien,s acceleration will be set to zero.
	 *          |this.setAccelerationX(0.0);
	 * @post... Time after the last movement will be set to zero.
	 *          |this.setTimeLastMoveX(0.0);
	 */
	public void endMoveLeft() {
		assert(this.wasMovingLeft());
		if (this.isMovingLeft()) {
			this.setIsMovingLeft(false);
			this.setVelocityX(0.0);
			this.setAccelerationX(0.0);
			this.setTimeLastMoveX(0.0);
			this.setWasMovingLeft(false);
		}
	}
	
	
	/**
	 * A boolean checker to check if the object was moving.
	 * @return
	 *      Returns true if and only if the object was moving.
	 *      |result = (this.wasMovingLeft)
	 */
	private boolean wasMovingLeft() {
		return this.wasMovingLeft;
	}
	
	
	/**
	 * A setter to register the object,s state if it was moving or not.
	 * @param wasMoving
	 *        The given parameter with which the value would be initialized.
	 *        |new.wasMovingLeft = wasMoving;
	 */
	private void setWasMovingLeft(boolean wasMoving) {
		this.wasMovingLeft = wasMoving;
	}
	
	/**
	 * A private variable to register if the object was moving.
	 */
	private boolean wasMovingLeft;

	/**
	 * A setter to set the last left movement of the alien.
	 * 
	 * @param lastMoveLeft
	 *          A parameter, holding the new value of lastMoveLeft with which
	 *          the variable will be initialized.
	 * @post... The new value of the lastMoveLeft will be equal to the given
	 *          value as parameter.
	 *          |new.lastMoveLeft = lastMoveLeft
	 */
	private void setLastMoveLeft(boolean lastMoveLeft) {
		this.lastMoveLeft = lastMoveLeft;
	}

	/**
	 * A checker to check whether the last movement was to the left side.
	 * 
	 * @return True if and only if the last movement of the alien was to the
	 *         left side of the word.
	 *         |result = (this.lastMoveLeft)
	 */
	private boolean wasLastMoveLeft() {
		return this.lastMoveLeft;
	}

	/**
	 * A private variable to store the left movement of the alien in the last
	 * second.
	 */
	private boolean lastMoveLeft;

	/**
	 * A checker to check whether the last movement within one second was to the
	 * left side.
	 * 
	 * @return True if and only if the last movement within the last second was
	 *         to the left side. 
	 *         |if (this.getTimeLastMoveX() <= 1.0)
	 *         |return true
	 */
	private boolean wasLastMoveXWithinLastSecond() {
		if (this.getTimeLastMoveX() > 1.0)
			return false;
		return true;
	}

	/**
	 * A setter to set the time after the last movement.
	 * 
	 * @param time
	 *          A parameter holding the value to initialize the the variable
	 *          timeLastMoveX.
	 * @post... The new value of the variable holding the last time movement
	 *          will be equal to the given value. 
	 *          |new.timeLastMoveX = time
	 */
	private void setTimeLastMoveX(double time) {
		this.timeLastMoveX = time;
	}

	/**
	 * A getter to get the time after making the last movement.
	 * 
	 * @return Returns the time after making the last movement of the alien.
	 *         |result = (this.timeLastMoveX)
	 */
	private double getTimeLastMoveX() {
		return this.timeLastMoveX;
	}

	/**
	 * A variable to register the time after making the last movement.
	 */
	private double timeLastMoveX;


	/**
	 * Checks whether alien is jumping or not.
	 * 
	 * @return True if and only if the alien is jumping or the alien is falling.
	 *         |result = (this.isJumping() || this.isFalling())
	 */
	public boolean isMovingY() {
		return (this.isJumping() || this.isFalling());
	}

	/**
	 * Alien jumps a vertical distance.
	 * 
	 * @effect. Changes the current state of alien from not jumping to
	 *          jumping. 
	 *          |this.setIsJumping(true);
	 * @post... Alien,s vertical velocity will be set equal to it,s initial
	 *          velocity. |this.setVelocityY(this.getInitialVelocityY());
	 * @post... Alien,s acceleration will be equal to to it,s initial vertical
	 *          acceleration. |this.setVelocityY(this.getInitialVelocityY());
	 * @throws  InvalidActionException
	 *           Throws an InvalidActionException if the alien is already
	 *           jumping. 
	 *           |if(this.isJumping()) 
	 *           |throw new InvalidActionException
	 *           |("Can,t jump cause the alien is already juming");
	 * 
	 */
	public void jump() throws InvalidActionException {
		try {
			if (this.isJumping())
				throw new InvalidActionException("Can,t jump cause the alien is already juming");
			if (!this.onObject() && !doesHorizontalPixelOverlapImpassable(this.getPixelBottomY()))
			throw new InvalidActionException("Mazub is not located at solid ground");
			this.setIsJumping(true);
			this.setVelocityY(this.getInitialVelocityY());
			this.setAccelerationY(this.getInitialAccelerationY());
			this.setWasJumping(true);
		} catch (InvalidActionException exc) {
			this.setWasJumping(true);
		}
	}
	

	/**
	 * A checker to check whether an object stands on other or not.
	 * @return
	 *      if the to be compared object stand on shrak it returns true.
	 *      |result = ((this.collideObject(shark))
	 * @return
	 *      if the object stands on slime then it returns true.
	 *      |result = ((this.collideObject(slime))
	 * @return 
	 *      if the object stands on the plant then it returns true.
	 *      |result = ((this.collideObject(plant))
	 * @return
	 *      Returns true if th obejct stands on Buzam
	 *      |result = ((this.collideObject(this.getWorld().getBuzam())
	 */
	public boolean onObject() {
		for (WorldObjects object: this.getWorld().getCollectionLiving()) {
			if (this != object && this.checkCollisionObject(object))
				return true;
		}
		return false;
	}

	/**
	 * Ends the jump of the alien.
	 * 
	 * @effect..Alien,s state will be changed to false.
	 *          |this.setIsJumping(false);
	 * @post..  Alien,s velocity will be set at 0. 
	 *          |this.setVelocityY(0);
	 * @post..  Alien will fall if the alien is not located at the the bottom of the world. 
	 *          |this.fall();
	 * @throws InvalidActionException
	 *             Throws an InvalidActionException if the alien is not jumping.
	 *             |if(this.isJumping())
	 *             |throw new InvalidActionException("Can,t end jump, cause alien is not jumping.");
	 */
	public void endJump() throws InvalidActionException {
		if(!this.wasJumping())
			throw new InvalidActionException("Can,t end jump, cause alien is not jumping nor falling.");
		this.setIsJumping(false);
		if (this.getVelocityY() > 0)
			this.setVelocityY(0);
		this.setWasJumping(false);
		this.startFall();
	}
	
	/**
	 * A setter to set the object was jumping.
	 * @param wasJumping
	 *        Sets the object as it was jumping.
	 *        |new.wasJumping = wasJumping
	 */
	private void setWasJumping(boolean wasJumping) {
		this.wasJumping = wasJumping;
	}
	
	/**
	 * A checker to check whether an object was jumping or not.
	 * @return
	 *      True if and only id the object was jumping.
	 *      |result = (this.wasJumping)
	 */
	public boolean wasJumping() {
		return this.wasJumping;
	}
	
	/**
	 * A private variable to store if the object was jumping.
	 */
	private boolean wasJumping;


	/**
	 * Alien starts ducking.
	 * 
	 * @effect..Alien,s state will be changed from not ducking to the ducking.
	 *          |this.setIsDucking(true);
	 * @post... Alien,s max velocity will set to 1.0 |this.setMaxVelocityX(1.0);
	 * @post... Alien,s current velocity will be set to 1.0
	 *          |this.setVelocityX(1.0);
	 * @throws InvalidActionException
	 *          Throws an invalidActionException if the alien is already ducking.
	 *          |if(this.isDucking()) 
	 *          |throw new InvalidActionException("Already Ducking")
	 */
	public void startDuck() throws InvalidActionException {
		this.setIsDucking(true);
		this.setMaxVelocityX(1.0);
		if (this.getVelocityX() > 1.0)
			this.setVelocityX(1.0);
	}
	/**
	 * Alien ends Ducking.
	 * 
	 * @effect..Alien,s state will be changed to not ducking.
	 *          |this.setIsDucking(false)
	 * @post... Resets the alien,s max velocity to the initial max velocity.
	 *          |this.setMaxVelocityX(3.0)
	 * @throws InvalidActionException
	 *             Throws InvalidActionException when method is called while the
	 *             alien is not ducking. |if(!(this.isDucking())) |throw new
	 *             ModelException("Alien is not ducking");
	 */
	public void endDuck() throws InvalidActionException {
		int width = this.getSprites()[0].getWidth();
		int height = this.getSprites()[0].getHeight();
		World world = this.getWorld();
		try {
			if ((this.getWorld().isImpassablePassAble(this.getPixelLeftX() + (width - 2), this.getPixelBottomY()
					+ (height- 2))))
				throw new InvalidActionException("Not a proper place to end duck");
			if ((world.isImpassablePassAble(this.getPixelLeftX() + 1, this.getPixelBottomY() + (height - 2))))
				throw new InvalidActionException("Not a proper place to end duck");
			this.setIsDucking(false);
			this.setMaxVelocityX(3.0);
			if (this.isMovingX())
				this.setAccelerationX(this.getInitialAccelerationX());
		} catch (InvalidActionException exc) {
			this.setIsDucking(true);
			this.endDuckingAtRightLocation(true);
		}
	}

	/**
	 * A setter to set the value of object as it ends ducking.
	 * @param ducking
	 *        The given variable containing the new value to update the old value.
	 *        |new.endDucking = ducking
	 */
	private void endDuckingAtRightLocation(boolean ducking) {
		this.endDucking = ducking;
	}

	/**
	 * A checker to check if the object has end ducking.
	 * @return
	 *      Returns true if and only if the object is not  ducking.
	 *      |result = (this.endDucking)
	 */
	public boolean getEndDucking() {
		return this.endDucking;
	}
	
	/**
	 * A private variable to register the endDucking value
	 */
	private boolean endDucking;

	/**
	 * A boolean method to check whether the alien is ducking or not.
	 * 
	 * @return Returns true if and only if the alien is ducking. |result =
	 *         (this.isDucking)
	 */
	public boolean isDucking() {
		return this.isDucking;
	}

	/**
	 * A setter to set the current state of the alien as ducking.
	 * 
	 * @param isDucking
	 *            The parameter holding the new boolean value.
	 * @post... The new value of the variable isDucking will be equal to the
	 *          value, given as parameter. |new.isDucking = isDucking
	 */
	public void setIsDucking(boolean isDucking) {
		this.isDucking = isDucking;
	}

	/**
	 * A private boolean variable to register the Ducking state of the alien.
	 */
	private boolean isDucking;


	
	/**
	 * A method to advance time of the the world as the time passes.
	 * @post... The time of the Alien class will be updated at everyinstant when this 
	 *          method will be called.
	 *          |this.updateTime(time);
	 * @pre...  The world to be setted should be an effective world.
	 *          |assert(canHaveAsWorld(world))
	 * @post..  Sets the world of the world and the world should not be null.
	 *          |this.setWorld(world);
	 * @post..  if the given time is greater than zero and the object whose time is being 
	 *          update is not dead, if the object is falling and it,s bottom pixels overlap with the 
	 *          ground then object will stop falling.
	 *          |if (this.isFalling()) 
	 *          |if (this.getBottomGeologicalfeature() == GROUND)
	 *          |this.stopFall();
	 *          |this.setIsJumping(false);
	 * @effect. If the object ends ducking at the right place then the object,s
	 *          state will be changed to not ducking. 
	 *          |if (this.isRightPlaceToEndDucking())
	 *          |this.endDuckingAtRightLocation(false);
	 *          |this.endDuck();
	 * @effect. if the object is not on ground nor on an object then 
	 *          the object will start falling.
	 *          |if ( (!(this.getBottomGeologicalfeature() == GROUND)) && !(this.onObject()))
	 *          |this.startFall();
	 * @effect. if the obejct,s immune time is greater than 6.0 then object,s state
	 *          will be changed to not immune.
	 *          |if (this.getImmuneTime() >= 0.6)
	 *          |this.setIsImmune(false);
	 * @post.   if the objects stands in magma for more than 2.0 second then it will lose it hitpoints.
	 *          |if (this.getImmuneTimeMagma() >= 0.2)
	 *          |this.setIsImmuneMagma(true);
	 *          |this.addHitPointsProperty(-50);
	 * @post.   if the object stands in water for more than 2.0 seconds then the object will loose
	 *          it,s hitpoints as 2 points/ second.
	 *          |if ((objectsFeature == WATER)
	 *          |this.addHitPointsProperty(-2);
	 *          |this.setImmuneTimeWater(0.0);
	 * @post.   if the object collides with plant and the plant is not dead and object
	 *          can eat plant then object will acquire 50 hitpoints when making contact with plant.
	 *          The plant will be destroyed.
	 *          |this.setHitPoints(this.getHitPoints() + 50);
	 *          |plant.addHitPointsEnemy(-1);
	 * @post.   if the objects collide with each other then the proper actions will be taken.
	 *          |this.collideObjectsActions(0, 0, -50, -50, -50, -50);
	 * @post.   The current location and veloctiy will be updated if the equired conditions
	 *          are fullfilled.
	 *          |this.updateLocationAndVelocity(dt);
	 * @effect  Object,s current sprite will be updated.
	 *          |this.setCurrentSprite();
	 * @post... The running time of the sprites will be updated.
	 *          |this.setSpriteRunningTime(this.getSpriteRunningTime() + dt);
	 * @post..  The time of the last movement when the objec made the last movement will be 
	 *          updated.
	 *          |this.setTimeLastMoveX(this.getTimeLastMoveX() + dt);
	 * @effect  if the given time is less than 0 then the object,s state will be 
	 *          changed to dead.If the death time exceeds 0.6 second then the object will be removed.
	 *          |if (this.getTimeDeath() >= 0.6)
	 *		    |this.getWorld().setInvisibleWorldObject(this);	
	 */
	@Override
	protected void advanceTime(double timee, World world) {
		double time = timee;
		double timePast = timee;
		double dt;
		this.updateTime(time);
		this.setWorld(world);
		while (timePast > 0.0) {
			if (!this.isDeath()) {		
				int objectsFeature = this.getMainGeologicalfeature();
				this.setPassableDirections();	
				if (this.isFalling()) {
					if (this.getBottomGeologicalfeature() == GROUND) {
						this.stopFall();
						this.setIsJumping(false);
					}
				}
				if (this.getEndDucking()) {
					if (this.isRightPlaceToEndDucking()) {
						this.endDuckingAtRightLocation(false);
						this.endDuck();
					}
				}
				if ( (!(this.getBottomGeologicalfeature() == GROUND)) && !(this.onObject())) {
					this.startFall();
				}
				dt = this.getMinimumDt(timePast);
				this.setImmuneTime(this.getImmuneTime() + dt);
				if (this.getImmuneTime() >= 0.6)
					this.setIsImmune(false);
				if (objectsFeature == MAGMA) {
					if (this.isImmuneMagma()) {
						this.setImmuneTimeMagma(this.getImmuneTimeMagma() + dt);
						if (this.getImmuneTimeMagma() >= 0.2) {
							this.setIsImmuneMagma(false);
						}
					}
					else {
						this.setIsImmuneMagma(true);
						this.setImmuneTimeMagma(0.0);
						this.addHitPointsProperty(-50);
					}
				}
				if (objectsFeature != WATER)
					this.setImmuneTimeWater(0.0);
				else if ((objectsFeature == WATER)) {
					if (this.getImmuneTimeWater() >= 0.2) {
						this.addHitPointsProperty(-2);
						this.setImmuneTimeWater(0.0);
					}
					else
						this.setImmuneTimeWater(this.getImmuneTimeWater() + dt);
				}
				for (Plant plant: this.getWorld().getCollectionPlant()) {
					if (this.checkCollisionObject(plant)  && !plant.isDeath() && this.canEat()) {
						this.blockDirectionOnCollision(plant);
						this.setHitPoints(this.getHitPoints() + 50);
						plant.addHitPointsEnemy(-1);
					}
				}
				this.collideObjectsActions();
				this.updateLocationAndVelocity(dt);
				this.setCurrentSprite();
				this.setSpriteRunningTime(this.getSpriteRunningTime() + dt);
				this.setTimeLastMoveX(this.getTimeLastMoveX() + dt);
				this.setActionTime(this.getActionTime() + dt);
			}
			else {
				dt = timePast;
				this.setTimeDeath(this.getTimeDeath() + dt);
				if (this.getTimeDeath() >= 0.6) {
					this.getWorld().setInvisibleWorldObject(this);		
					this.terminate();
				}
			}
			timePast = timePast - dt;
		}
	}
	
	/**
	 * A method to 24ce time of this object.
	 * @param timee
	 *        The given time with which the old time will be updated.
	 * @param world
	 *        The given world in which the objects lives.
	 * @post.. Advances the time of this object.
	 *         |this.advanceTime(timee, world);
	 */
	public void advanceTimeMazub(double timee, World world) {
		this.advanceTime(timee, world);
	}
	
	@Override
	public void advanceTimeWithProgram(double timee, World world) {
		this.advanceTime(timee, world);
	}


	/**
	 * A boolean checker to check whether alien is at right place to end duck.
	 * @return
	 *       Returns true if and only if the alien,s top rightCorner and alien,s top left Corner does 
	 *       not represent a solid tile.
	 *       |if ((world.isImpassablePassAble(this.getPixelLeftX() + (width - 2), this.getPixelBottomY() + (height - 2))))
	 *       |return true
	 *       |if ((world.isImpassablePassAble(this.getPixelLeftX(), this.getPixelBottomY() + (height - 1))))
	 *       |return true;
	 */
	private boolean isRightPlaceToEndDucking() {
		int width = this.getSprites()[0].getWidth();
		int height = this.getSprites()[0].getHeight();
		if ((this.getWorld().isImpassablePassAble(this.getPixelLeftX() + (width - 2), this.getPixelBottomY() + (height - 2))))
			return false;
		if ((this.getWorld().isImpassablePassAble(this.getPixelLeftX(), this.getPixelBottomY() + (height - 1))))
			return false;
		return true;
	}


	/**
	 * Updates the sprites according to the current state of the alien.
	 * 
	 * @pre... Time should be greater than the sprites time after which the
	 *         sprites should be updated. 
	 *         |this.spriteRunningNeedUpdate()
	 * @post... If the current index is less than the first value of the running
	 *          cycle or greater or equal to the first value of the running
	 *          cycle incremented with the number of sprites for the running
	 *          cycle incremented with one, the new index will be the first
	 *          value of the running cycle. |if ((this.getCurrentIndex() <
	 *          startRunningCycle) || | (this.getCurrentIndex() >=
	 *          (startRunningCycle + this.getRunningCycle() - 1)))
	 *          |this.setCurrentIndex(startRunningCycle)
	 * @post... If the current index is one of the indexes for the sprites of
	 *          the running cycle and not the last one, the new index will be
	 *          the current incremented with one. |if ((this.getCurrentIndex()
	 *          >= startRunningCycle) || (this.getCurrentIndex() <
	 *          (startRunningCycle + this.getRunningCycle() - 1)))
	 *          |this.setCurrentIndex(this.getCurrentIndex() + 1);
	 */
	private void updateSpriteRunning() {
		if (this.spriteRunningNeedUpdate()) {
			this.setSpriteRunningTime(0.0);
			int startRunningCycle = this.startRunningCycleRight();
			if (this.isMovingLeft()) {
				startRunningCycle = this.startRunningCycleLeft();
			}
			if ((this.getCurrentIndex() < startRunningCycle)
					|| (this.getCurrentIndex() >= (startRunningCycle + this.getRunningCycle() - 1))) {
				this.setCurrentIndex(startRunningCycle);
			} else {
				this.setCurrentIndex(this.getCurrentIndex() + 1);
			}
		}
	}

	/**
	 * Returns the number of sprites in the alien,s running cycle.
	 * 
	 * @return Returns the number of sprites in the alien,s running cycle
	 *         |result = (this.sprites.length-numberImagesNotRunning)/2)
	 */
	private int getRunningCycle() {
		return (this.getSprites().length - numberImagesNotRunning) / 2;
	}

	/**
	 * Returns the number of the first sprite of the running cycle to right.
	 * 
	 * @return Returns the number of sprites while moving to the right side.
	 *         |result = (this.numberImagesNotRunning)
	 */
	private int startRunningCycleRight() {
		return this.numberImagesNotRunning;
	}

	/**
	 * Returns the number of the first sprite of the running cycle to left.
	 * 
	 * @return Returns the number of sprite while alien,s movement is to the
	 *         left side. 
	 *         |result =(this.numberImagesNotRunning + getRunningCycle())
	 */
	private int startRunningCycleLeft() {
		return this.startRunningCycleRight() + getRunningCycle();
	}

	/**
	 * A private variable to store the number of sprite independent of the
	 * running cycles
	 */
	private final int numberImagesNotRunning = 8;

	/**
	 * A checker to check whether the sprite time is greater than the time to
	 * update the sprite.
	 * 
	 * @return Returns true if and only if the sprite time is greater than or
	 *         equal to 75 Mileseconds. 
	 *         |if (this.getSpriteRunningTime() >= 0.075) 
	 *         |return true;
	 */
	private boolean spriteRunningNeedUpdate() {
		if (this.getSpriteRunningTime() >= 0.075)
			return true;
		return false;
	}

	/**
	 * A setter to set the sprite running time.
	 * 
	 * @param time
	 *            The parameter time with which the variable spriteRuningTime
	 *            will be initialized.
	 * @post... The new running time of the sprites will be equal to the given
	 *          time. |new.spriteRunningTime = time
	 */
	private void setSpriteRunningTime(double time) {
		this.spriteRunningTime = time;
	}

	/**
	 * A getter to get the sprite time.
	 * 
	 * @return Returns the running time of the spriteS. |result
	 *         =(this.spriteRunningTime)
	 */
	@Basic
	private double getSpriteRunningTime() {
		return this.spriteRunningTime;
	}

	/**
	 * A private variable to store the running time of the sprites.
	 */
	private double spriteRunningTime;
	
	/**
	 * A  checker to check if the Mazub can eat plant or not.
	 * @return
	 *        Returns if and only if the objec,ts hitpoints are less than it,s
	 *        maxhitpoints.
	 *        |result = (this.getHitPoints() < this.getMaxHitPoints())
	 */
	public boolean canEat() {
		return this.getHitPoints() < this.getMaxHitPoints();
	}
}

	

	
	
	