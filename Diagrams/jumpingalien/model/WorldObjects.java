package jumpingalien.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.security.auth.x500.X500Principal;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;
/**
 * 
 * 
 * @author Afraz Salim & Niels Van Belle
 * 
 * @version 2.0	
 * @invar |isInWorld()
 * @invar |isValidPixelLeftX(getPixelLeftX())
 * @invar |isValidPixelBottomY(getPixelBottomY())
 * @invar |isValidVelocityX(getVelocityX())
 * @invar |isValidSprites(getSprites())
 * @invar |isValidTime(getTime())
 *
 */
public abstract class WorldObjects {
	
	/**
	 * 
	 * @param x
	 * 		The left x co for the new object
	 * @param y
	 * 		The bottom y co for the new object
	 * @param sprites
	 * 		The array of sprites for new object
	 * @effect setPixelLeftX(x)
	 * @effect setPixelBottomY(y)
	 * @effect setSprites(sprites)
	 * @throws IndexOutOfBoundsException
	 * 				!isValidPixelLeftX(x) || !isValidPixelBottomY(y)
	 * @throws IllegalArgumentException
	 * 				!isValidSprites(sprites)
	 */
	@Model
	protected WorldObjects(int x, int y, Sprite[] sprites) {
		if(!(isValidePixelLeftX(x)))
		  throw new IndexOutOfBoundsException("pixel leftX is out of world");
		if(!(isValidePixelBottomY(y)))
		     throw new IndexOutOfBoundsException("Pixel bottom y is out of world");
		if(!(isValidSprites(sprites)))
			throw new IllegalArgumentException("Sprites are null");
		this.setSprites(sprites);
		this.setPixelLeftX(x);
		this.setPixelBottomY(y);
	}


	/**
	 * 
	 * Return the x co of this object
	 */
	@Basic
	public int getPixelLeftX() {
		return (int) pixelLeftX;
	}
	
	/**
	 * 
	 * @param pixelLeftX
	 * 		The new x co for this object
	 * @post new.getPixelLeftX() == pixelLeftX
	 * @throws MazubOutOfWorldException
	 * 				!isValidPixelLeftX(pixelLeftX)
	 */
	protected void setPixelLeftX(int pixelLeftX) throws MazubOutOfWorldException{
		try {
			if (!(isValidePixelLeftX(pixelLeftX)))
				throw new MazubOutOfWorldException("Pixel X is out of world.");
			this.pixelLeftX = pixelLeftX;
		} catch (MazubOutOfWorldException exc) {
			world.setInvisibleWorldObject(this);
		}
	}
	
	/**
	 * Variable registering the current x co of this object
	 */
	private double pixelLeftX;
	
	/**
	 * Checker to check whether the leftpixel representing the x-co of the alien
	 * is vaid pixel or not.
	 * 
	 * @param pixelLeftX
	 *            The given value to be checked.
	 * @return True if and only if the pixelleftx is not smaller than zero and
	 *         not greater than the width of the world decreased with the width of the sprite
	 *         |result == 
	 *         |(pixelLeftX >= 0) && pixelLeftX < this.getWorld().getWidthInPixel() - this.getCurrentSprite().getWidth()
	 *
	 */
	public boolean isValidePixelLeftX(int pixelLeftX) {
		if (this.getWorld() != null && ((pixelLeftX < 0) || 
				(pixelLeftX > this.getWorld().getWidthInPixel() - this.getCurrentSprite().getWidth())))
			return false;
		return true;
	}
	
	/**
	 * 
	 * Return the y co of this object
	 */
	@Basic
	public int getPixelBottomY() {
		return (int) pixelBottomY;
	}
	
	/**
	 * 
	 * @param pixelBottomY
	 * 		The new y co for this object
	 * @post new.getPixelBottomY() == pixelBottomY
	 * @throws MazubOutOfWorldException
	 * 				!isValidePixelBottomY(pixelBottomY)
	 */
	protected void setPixelBottomY(int pixelBottomY) throws MazubOutOfWorldException{
		try {
			if (!(isValidePixelBottomY(pixelBottomY)))
				throw new MazubOutOfWorldException("Pixel Y is out of world.");
			this.pixelBottomY = pixelBottomY;
		} catch (MazubOutOfWorldException exc) {
			world.setInvisibleWorldObject(this);
		}
	}
	
	/**
	 * Variable registering the current y co of this object
	 */
	private double pixelBottomY;
	
	/**
	 * checks whether the given locations are valid or not.
	 * 
	 * @param pixelBottomY
	 *            Parameter holding the value of alien,s bottom y-coordinate.
	 *            |pixelBottomY
	 * @return Returns true if and only if the given values are greater than
	 *         zero and less than the world height reduced with the sprite height.
	 *        	result == pixelBottomY >= 0 && pixelBottomY < this.getWorld.getHeightInPixel() - this.getCurrentSprite().getHeight()
	 */
	public boolean isValidePixelBottomY(int pixelBottomY) {
		if (this.getWorld() != null && ((pixelBottomY < 0) || 
				(pixelBottomY > this.getWorld().getHeightInPixel() - this.getCurrentSprite().getHeight())))
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param pixelY
	 * 		The y co of the pixels that need to be checked 
	 * @return result ==
	 * 		for each xCo in this.getPixelLeftX() + 1..this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1
	 * 		(world.isImpassablePassAble(xCo, pixelY)
	 */
	@Model
	public boolean doesHorizontalPixelOverlapImpassable(int pixelY) {
		int pixelLeftX = this.getPixelLeftX();
		int width = this.getCurrentSprite().getWidth() - 1;
		for (int pixelX = pixelLeftX +1 ; pixelX < pixelLeftX + width ; pixelX++) {
			if (this.getWorld().isImpassablePassAble(pixelX, pixelY))
				return true;
		}
		return false;
	}
	
	/**\
	 * 
	 * @param pixelX
	 * 		The x co of the pixels that need to be checked 
	 * @return result ==
	 * 		for each yCo in this.getPixelBottomY() + 1..this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1
	 * 			(world.isImpassablePassAble(pixelX, yCo)
	 */
	@Model
	private boolean doesVerticalPixelOverlapImpassable(int pixelX) {
		int pixelBottomY = this.getPixelBottomY() + 1;
		int height = this.getCurrentSprite().getHeight() - 1;
		for (int pixelY = pixelBottomY; pixelY < pixelBottomY + height - 1; pixelY++) {
			if (this.getWorld().isImpassablePassAble(pixelX, pixelY))
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Return the array of sprites for this object
	 */
	@Basic @Model
	protected Sprite[] getSprites() {
		return sprites;
	}

	/**
	 * 
	 * @param sprites
	 * 		The new array of sprites for this object
	 * @post new.getSprites() == sprites
	 */
	@Model
	protected void setSprites(Sprite[] sprites) {
		if (!this.isValidSprites(sprites))
			throw new InvalidSpriteException("Sprite in sprites with invalid width and/or height");
		this.sprites = sprites;
	}
	
	/**
	 * Variable registering the current array of sprites for this object
	 */
	private Sprite[] sprites;

	
	/**
	 * A method which returns the current sprite of the alien.
	 * 
	 * @pre.. The width of the to be returning alien should be valid width.
	 *        |assert
	 *        (this.isValidWidth(this.sprites[this.getCurrentIndex()].getWidth
	 *        ()));
	 * @pre... The height of to be returning alien should be a valid height.
	 *         |assert (this.isValidHeight(this.sprites[this.getCurrentIndex()].
	 *         getHeight()));
	 * @pre... The current index should be a valid index of the array sprite.
	 *         |assert ((this.getCurrentIndex() >= 0) && (this.getCurrentIndex()
	 *         < this.sprites.length));
	 * @return Returns an image of the alien according to the given index.
	 *         result = (sprite) |this.sprites[this.getCurrentIndex()];
	 */
	@Basic
	public Sprite getCurrentSprite() {
		assert(!(sprites[this.getCurrentIndex()]  == null));
		return this.sprites[this.getCurrentIndex()];
	}
	
	private boolean isValidSprites(Sprite[] sprites) {
		int length = sprites.length;
		boolean validSprite = true;
		for (int index = 0; index < length; index++) {
			if (!this.isValidWidth(sprites[index].getWidth()) || !this.isValidHeight(sprites[index].getHeight())) {
					validSprite = false;
			}
		}
		return validSprite;
	}

	/**
	 * Checks the height of the sprite.
	 * 
	 * @param height
	 *            Parameter height, holding the value the of alien,s sprite,s
	 *            height.
	 * @return Return true if and only if the height is greater than zero or
	 *         equal to zero. |if(height >= 0) |return true
	 */
	private boolean isValidHeight(int height) {
		if (height < 0)
			return false;
		return true;
	}

	/**
	 * Check whether the width of the alien,s sprite is valid width.
	 * 
	 * @param width
	 *            Parameter width, holding the value of the alien,s width,to be
	 *            checked.
	 * @return Returns true if and only if the alien,s width is greater than or
	 *         equal to zero. |if(width >= 0) |return true
	 */
	private boolean isValidWidth(int width) {
		if (width < 0)
			return false;
		return true;
	}

	
	/**
	 * 
	 * @param index
	 * 		The new index in the array of sprites for this object 
	 * @pre index < this.getSprites.length()
	 * @post new.getCurrentIndex() == index
	 */
	protected void setCurrentIndex(int index) {
		assert ((this.getCurrentIndex() >= 0) && (this.getCurrentIndex() < this.sprites.length));
		this.currentIndex = index;
	}
	
	/**
	 * 
	 * Return the current index in the array of sprites for this object
	 */
	@Basic @Model
	protected int getCurrentIndex() {
		return this.currentIndex;
	}
	
	/**
	 * Variable registering the current index in array of sprites for this object
	 */
	private int currentIndex;
	
	/**
	 * 
	 * @param velocityX
	 * 		The new horizontal velocity for this object
	 * @post new.getVelocityX() == velocityX
	 */
	@Model
	public void setVelocityX(double velocityX) {
		try{
			if(!(isValidVelocityX(velocityX)))
				throw new InvalidActionException("Not a valid velocity");
			this.velocityX = velocityX;
		} catch(InvalidActionException exc){
			if(velocityX > this.getMaxVelocityX()) {
				this.setVelocityX(this.getMaxVelocityX());
			}
			else
				throw exc;
		}
	}
	
	/**
	 * 
	 * Return the current horizontal velocity of this object
	 */
	@Basic
	public double getVelocityX(){
		return this.velocityX;
	}
	
	/**
	 * Variable registering the current horizontal velocity of this object
	 */
	private double velocityX;
	
	/**
	 * 
	 * @param initialVelocityX
	 * 		The initial horizontal velocity for this object
	 * @post new.getInitialVelocityX() == initialVelocityX
	 */
	protected void setInitialVelocityX(double initialVelocityX) {
		this.initialVelocityX = initialVelocityX;
	}
	
	/**
	 * 
	 * Return the initial horizontal velocity of this object
	 */
	@Basic @Model
	protected double getInitialVelocityX() {
		return this.initialVelocityX;
	}
	
	/**
	 * Variable registering the initial horizontal velocity of this object
	 */
	private double initialVelocityX;
	
	/**
	 * 
	 * @param maxVelocityX
	 * 		The maximal horizontal velocity for this object
	 * @post new.getInitialVelocityX() == initialVelocityX
	 */
	protected void setMaxVelocityX(double maxVelocityX) {
		this.maxVelocityX = maxVelocityX;
	}

	/**
	 * 
	 * Return the maximal horizontal velocity of this object
	 */
	@Basic @Model
	protected double getMaxVelocityX(){
		return this.maxVelocityX;
	}
	
	/**
	 * Variable registering the maximal horizontal velocity of this object
	 */
	private double maxVelocityX;
	
	/**
	 * 
	 * @param acceleration
	 * 		The acceleration for the calculation of the velocity
	 * @param time
	 * 		The time for the calculation of the velocity
	 * @post if isValidVelocityX(this.getVelocityX() + acceleration * time)
	 * 			then new.getVelocityX() == this.getVelocityX() + acceleration * time
	 * 		else if this.getVelocityX() + acceleration * time > this.getMaxVelocityX()
	 * 			then new.getVelocityX() == this.getVelocityX() + acceleration * time
	 * @throws InvalidActionException
	 * 		isNaN(this.getVelocityX() + acceleration * time)
	 */
	@Model
	protected void updateVelocityX(double acceleration, double time) throws InvalidActionException {
		setVelocityX(this.getVelocityX() + acceleration * time);
	}
	
	/**
	 * 
	 * @param velocityX
	 * 			The horizontal velocity that need to be tested
	 * @return result == 
	 * 			(!(velocityX > this.getMaxVelocityX()) && !(isNaN(velocityX)))
	 */
	@Model
	private boolean isValidVelocityX(double velocityX) {
		if((velocityX > this.getMaxVelocityX()))
			return false;
		if((Double.isNaN(velocityX)))
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param velocityY
	 * 		The new vertical velocity for this object
	 * @post new.getVelocityY() == velocityY
	 */
	@Model
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	/**
	 * 
	 * Return the current vertical velocity of this object
	 */
	@Basic
	public double getVelocityY() {
		return this.velocityY;
	}
	
	/**
	 * Variable registering the current vertical velocity of this object
	 */
	private double velocityY;

	/**
	 * 
	 * Return the initial vertical velocity of this object
	 */
	@Basic @Model
	protected double getInitialVelocityY() {
		return this.initialVelocityY;
	}
	
	/**
	 * 
	 * @param initialVelocityY
	 * 		The initial vertical velocity for this object
	 * @post new.getInitialVelocityY() == initialVelocityY
	 */
	protected void setInitialVelocityY(double initialVelocityY) {
		this.initialVelocityY = initialVelocityY;
	}
	
	/**
	 * Variable registering the initial vertical velocity of this object
	 */
	private double initialVelocityY;
	
	/**
	 * 
	 * @param acceleration
	 * 		The acceleration for the calculation of the velocity
	 * @param time
	 * 		The time for the calculation of the velocity
	 * @pre ! isNaN(acceleration)
	 * @pre ! isNan(time)
	 * @post new.getVelocityY == this.getVelocityY()+this.getAccelerationY()*time
	 */
	@Model
	protected void updateVelocityY(double acceleration, double time) {
        this.setVelocityY(this.getVelocityY()+this.getAccelerationY()*time);
	}
	
	/**
	 * 
	 * @param accelerationX
	 * 		The new horizontal acceleration for this object
	 * @post new.getAccelerationX() == accelerationX
	 */
	@Model
	public void setAccelerationX(double accelerationX) {
		if (Double.isNaN(accelerationX)) {
			this.accelerationX = 0;
		}
		else
			this.accelerationX = accelerationX;
	}
	
	/**
	 * 
	 * Return the current horizontal acceleration of this object
	 */
	@Basic @Model
	public double getAccelerationX() {
		return this.accelerationX;
	}
	
	/**
	 * Variable registering the current horizontal acceleration of this object
	 */
	private double accelerationX;
	
	/**
	 * 
	 * @param initialAccelerationX
	 * 		The initial horizontal acceleration for this object
	 * @post new.getInitialAccelerationX() == initialAccelerationX
	 */
	protected void setInitialAccelerationX(double initialAccelerationX) {
	       this.initialAccelerationX = initialAccelerationX;		
		}
	
	/**
	 * 
	 * Return the initial horizontal acceleration of this object
	 */
	@Basic @Model
	protected double getInitialAccelerationX(){
		return this.initialAccelerationX;
	}
	
	/**
	 * Variable registering the initial horizontal acceleration of this object
	 */
	private double initialAccelerationX;
	
	/**
	 * 
	 * @param accelerationY
	 * 		The new vertical acceleration for this object
	 * @post new.getAccelerationY() == accelerationY
	 */
	@Model
	public void setAccelerationY(double accelerationY) {
		if (Double.isNaN(accelerationY)) {
			this.accelerationY = 0;
		}
		else
			this.accelerationY = accelerationY;	
	}
	
	/**
	 * 
	 * Return the current vertical acceleration of this object
	 */
	@Basic @Model
	public double getAccelerationY(){
		return this.accelerationY;
	}
	
	/**
	 * Variable registering the current vertical acceleration of this object
	 */
	private double accelerationY;
	
	/**
	 * 
	 * Return the initial horizontal acceleration of this object
	 */
	@Basic @Model
	protected double getInitialAccelerationY(){
		return this.initialAccelerationY;
	}
	
	/**
	 * Variable registering the initial vertical acceleration of this object
	 */
	private double initialAccelerationY = -10.0;

	/**
	 * 
	 * @param direction
	 * 		The new direction (1 for right, -1 for left) for this object 
	 * @pre (direction == 1 || direction == -1) 
	 * @post new.getDirection() == direction
	 */
	@Model
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	/**
	 * 
	 * Return the direction (1 for right, -1 for left) for this object 
	 */
	@Basic @Model
	public int getDirection(){
		return this.direction;
	}
	
	/**
	 * Variable registering the current direction (1 for right, -1 for left) for this object
	 */
	private int direction;
	
	/**
	 * 
	 * @return result ==
	 * 			random integer between 0..this.getNumberOfPossibleActions()-1
	 */
	protected int getRandomAction() {
		Random action = new Random();
		return action.nextInt(this.getNumberOfPossibleActions());
	}
	
	/**
	 * 
	 * @param numberOfPossibleActions
	 * 		The number of possible actions of this object
	 * @pre numberOfPossibleActions < 4
	 * @post new.getNumberOfPossibleActions() == numberOfPossibleActions
	 * 			
	 */
	@Model
	protected void setNumberOfPossibleActions(int numberOfPossibleActions) {
		this.numberOfPossibleActions = numberOfPossibleActions;
	}
	
	/**
	 * 
	 * Return number of possible actions of this object 
	 */
	@Basic @Model
	protected int getNumberOfPossibleActions() {
		return this.numberOfPossibleActions;
	}
	
	/**
	 * Variable registering the current number of possible actions of this object
	 */
	private int numberOfPossibleActions = 2;
	
	/**
	 * @post if !this.isMovingRight()
	 * 			then !new.isMovingLeft()
	 * 				new.isMovingRight()
	 * 				new.getVelocityX() == this.getInitialVelocityX()
	 * 				new.getAccelerationX() == this.getInitialAccelerationX()
	 * 				new.getDirection() == 1
	 * 				new.getCurrentIndex() == 1 
	 */
	@Model
	public void startMoveRight() {
		assert !this.isMovingRight();
		this.setIsMovingLeft(false);
		this.setIsMovingRight(true);
		this.setVelocityX(this.getInitialVelocityX());
		this.setAccelerationX(this.getInitialAccelerationX());
		this.setDirection((int)Math.cos(0));
		this.setCurrentIndex(1);
	}
	
	/**
	 * 
	 * @param isMovingRight
	 * 			The boolean value for the moving to right of this object
	 * @post new.isMovingRight() == isMovingRight
	 */
	public void setIsMovingRight(boolean isMovingRight){
		this.isMovingRight = isMovingRight;
	}
	
	/**
	 * 
	 * Return the boolean value for the moving to right of this object
	 */
	@Basic @Model
	public boolean isMovingRight() {
		return this.isMovingRight;
	}
	
	/**
	 * Variable registering the boolean value for the moving to right of this object
	 */
	private boolean isMovingRight;
	
	/**
	 * @post if !this.isMovingLeft()
	 * 			then new.isMovingLeft()
	 * 			    !new.isMovingRight()
	 * 				 new.getVelocityX() == this.getInitialVelocityX()
	 * 				 new.getAccelerationX() == this.getInitialAccelerationX()
	 * 				 new.getDirection() == 0
	 * 				 new.getCurrentIndex() == 0 
	 */
	@Model
	public void startMoveLeft() {
		assert (!this.isMovingLeft());
		this.setIsMovingRight(false);
		this.setIsMovingLeft(true);
		this.setVelocityX(this.getInitialVelocityX());
		this.setAccelerationX(this.getInitialAccelerationX());
		this.setDirection((int)Math.cos(Math.PI));
		this.setCurrentIndex(0);
	}
	
	/**
	 * 
	 * @param isMovingLeft
	 * 			The boolean value for the moving to left of this object
	 * @post new.isMovingLeft() == isMovingLeft
	 */
	public void setIsMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}
	
	/**
	 * 
	 * Return the boolean value for the moving to left of this object
	 */
	@Basic @Model
	public boolean isMovingLeft() {
		return this.isMovingLeft;
	}
	
	/**
	 * Variable registering the boolean value for the moving to left of this object
	 */
	private boolean isMovingLeft;
	
	/**
	 * 
	 * Return the boolean value for the jumping of this object
	 */
	@Basic @Model
	public boolean isJumping(){
		return this.isJumping;
	}
	
	/**
	 * 
	 * @param isJumping
	 * 			The boolean value for the jumping of this object
	 * @post new.isJumping() == isJumping
	 */
	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	/**
	 * Variable registering the boolean value for the jumping of this object
	 */
	private boolean isJumping;
	
	public abstract void jump() throws InvalidActionException;
	
	/**
	 * 
	 * Return the boolean value for the falling of this object
	 */
	@Basic @Model
	protected boolean isFalling() {
		return this.isFalling;
	}
	
	/**
	 * 
	 * @param isFalling
	 * 			The boolean value for the falling of this object
	 * @post new.isFalling() == isFalling
	 */
	protected void setIsFalling(boolean isFalling) {
			this.isFalling = isFalling;
	}
	
	/**
	 * Variable registering the boolean value for the falling of this object
	 */
	private boolean isFalling;

	/**
	 * 
	 * @param time
	 * 		The time for this object
	 * @post new.getTime() == time
	 */
	private void setTime(double time) {
		this.time = time;
	}
	
	/**
	 * 
	 * Return the current time for this object
	 */
	@Basic @ Model
	protected double getTime() {
		return this.time;
	}
	
	/**
	 * Variable registering the current time for this object
	 */
	private double time;
	
	/**
	 * 
	 * @param time
	 * 		The time that need to be updated
	 * @post if isValidTime(time)
	 * 			then new.getTime() == time
	 * 		else if isValideTime(this.getTime())
	 * 			then new.getTime() == this.getTime()
	 * @throws InvalidActionException
	 * 			!(isValideTime(this.getTime())
	 */
	protected void updateTime(double time) throws InvalidActionException {
		try{
			if (!(isValidTime(time)))
					throw new InvalidActionException("Time is less than zero, greater than 0.2 second or NaN");
				this.setTime(time);
			}
			catch(InvalidActionException exc){
			  if(isValidTime(this.getTime()))
				this.setTime(this.getTime());
			  else
				  throw exc;
			}
	}
	
	/**
	 * 
	 * @param time
	 * 			The time that need to be tested
	 * @return result ==
	 * 				((time >= 0) && (time < 0.2))
	 */
	@Model
	private boolean isValidTime(double time) {
		return ((time >= 0) && (time < 0.2) && !Double.isNaN(time));
	}
	
	/**
	 * 
	 * @return result ==
	 * 				(this.getActionTime() >= this.getCurrentActionTime())
	 */
	@Model
	protected boolean actionNeedUpdate() {
		return (this.getActionTime() >= this.getCurrentActionTime());
	}
	
	/**
	 * 
	 * @param time
	 * 			The time of action to be set for this object
	 * @pre !isNaN(time)
	 * @post new.getActionTime() == time
	 */
	@Model
	protected void setActionTime(double time) {
		this.actionTime = time;
	}
	
	/**
	 * 
	 * Return the current time of action for this object
	 */
	@Basic @Model
	protected double getActionTime() {
		return this.actionTime;
	}
	
	/**
	 * Variable registering the current time of action for this object
	 */
	private double actionTime;
	
	/**
	 * 
	 * @param time
	 * 			The action time to be set for this object
	 * @pre !isNaN(time)
	 * @post new.getCurrentActionTime() == time
	 */
	protected void setCurrentActionTime(double time) {
		this.currentActionTime = time;
	}
	
	/**
	 * 
	 * Return the current action time for this object
	 */
	@Basic @Model
	private double getCurrentActionTime() {
		return this.currentActionTime;
	}
	
	/**
	 * Variable registering the current action time for this object
	 */
	private double currentActionTime;
	
	/**
	 * 
	 * @return result == 
	 * 				random double between this.getMinimumActionTime()..(this.getMaximumActionTime() - this.getMinimumActionTime()) * random.nextDouble()
	 */
	protected double getRandomCurrentActionTime() {
		Random random = new Random();
		return this.getMinimumActionTime() + (this.getMaximumActionTime() - this.getMinimumActionTime()) * random.nextDouble();
	}
	
	/**
	 * 
	 * @param time
	 * 			The minimum action time for this object
	 * @pre !isNaN(time)
	 * @post new.getMinimumActionTime() == time
	 */
	protected void setMinimumActionTime(double time) {
		this.minimumActionTime = time;
	}
	
	/**
	 * 
	 * Return the minimum action time for this object
	 */
	@Basic @Model
	private double getMinimumActionTime() {
		return this.minimumActionTime;
	}
	
	/**
	 * Variable registering the minimum action time for this object
	 */
	private double minimumActionTime;
	
	/**
	 * 
	 * @param time
	 * 			The maximum action time for this object
	 * @pre !isNaN(time)
	 * @post new.getMaximumActionTime() == time
	 */
	protected void setMaximumActionTime(double time) {
		this.maximumActionTime = time;
	}
	
	/**
	 * 
	 * Return the maximum action time for this object
	 */
	@Basic @Model
	private double getMaximumActionTime() {
		return this.maximumActionTime;
	}
	
	/**
	 * Variable registering the maximum action time for this object
	 */
	private double maximumActionTime;
	
	/**
	 * 
	 * @param world
	 * 			The world for this object
	 * @pre      canHaveAsWorld(world)
	 * @post     new.getWorld() == world
	 */
	protected void setWorld(World world) {
		assert this.canHaveAsWorld(world);
	     this.world = world;		
		}
	
	/**
	 * Variable registering the world for this object
	 */
	private World world;
	
	/**
	 * 
	 * @param object
	 * 			The object that need to be tested for same x co as this object
	 * @return result ==
	 * 				((this.isMovingRight() && 
	 *				(this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1 == object.getPixelLeftX())) ||
	 *				(this.isMovingLeft() &&
	 *				(this.getPixelLeftX() == object.getPixelLeftX()+ + object.getCurrentSprite().getWidth() - 1)))
	 */
	@Model
	protected boolean hasSameX(WorldObjects object) {
		return (((this.isMovingRight() || object.isMovingLeft()) && 
				(this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1 == object.getPixelLeftX())) ||
				((this.isMovingLeft() || object.isMovingRight()) &&
				(this.getPixelLeftX() == object.getPixelLeftX()+ + object.getCurrentSprite().getWidth() - 1)));
	}
	
	/**
	 * 
	 * @param object
	 * 			The object that need to be tested for horizontal collision
	 * @return if (this.hasSameX(object))
	 * 					for each yCo1 in this.getPixelBottomY()..this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1
	 * 						for each yCo2 in object.getPixelBottomY()..object.getPixelBottomY() + object.getCurrentSprite().getHeight() - 1
	 * 							result == 
	 * 								(yCo1 == yCo2)
	 * 			else
	 * 				result ==
	 * 					false
	 */
	protected boolean collideX(WorldObjects object) {
		if (hasSameX(object)) {
				int firstBottomY = this.getPixelBottomY();
				int firstTopY = firstBottomY + this.getCurrentSprite().getHeight() - 1;
				int secondBottomY = object.getPixelBottomY();
				int secondTopY = secondBottomY + object.getCurrentSprite().getHeight() - 1;
				for (int firstY = firstBottomY; firstY < firstTopY; firstY++) {
					for (int secondY = secondBottomY; secondY < secondTopY; secondY++) {
						if (firstY == secondY)
							return true;
					}
				}
		}
		return false;
	}
	
	/**
	 * 
	 * @param object
	 * 			The object that need to be tested for same bottom co
	 * @return result ==
	 * 				(this.getVelocityY() <= 0.0 &&
	 *				(this.getPixelBottomY() == object.getPixelBottomY() + object.getCurrentSprite().getHeight() - 1))
	 */
	protected boolean hasSameBottom(WorldObjects object) {
		return ((this.getVelocityY() <= 0.0 &&
				(this.getPixelBottomY() == object.getPixelBottomY() + object.getCurrentSprite().getHeight() - 1)));
	}
	
	/**
	 * 
	 * @param object
	 * 			The object that need to be tested for the same top co
	 * @return result ==
	 * 				(this.getVelocityY() > 0) &&
	 *				(this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1 == object.getPixelBottomY())
	 */
	private boolean hasSameTop(WorldObjects object) {
		return ((this.getVelocityY() > 0) &&
				(this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1 == object.getPixelBottomY()));
	}
	
	/**
	 * 
	 * @param object
	 * 			The object that need to be tested for same y co as this object
	 * @return result == 
	 * 			((this.getVelocityY() <= 0.0 &&
	 *			(this.getPixelBottomY() == object.getPixelBottomY() + object.getCurrentSprite().getHeight() - 1)) ||
	 *			(this.getVelocityY() > 0) &&
	 *			(this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1 == object.getPixelBottomY()))
	 * 				
	 */
	protected boolean hasSameY(WorldObjects object) {
		return (this.hasSameBottom(object) || this.hasSameTop(object));
	}
	
	/**
	 * 
	 * @param object
	 * 			The object that need to be tested for vertical collision
	 * @return if (this.hasSameX(object))
	 * 					for each xCo1 in this.getPixelLeftX() + 1..this.getLeftX() + this.getCurrentSprite().getWidth() - 3
	 * 						for each xCo2 in object.getPixelLeftX() + 1..object.getPixelLeftX() + object.getCurrentSprite().getWidth() - 3
	 * 							result == 
	 * 								(xCo1 == xCo2)
	 * 			else
	 * 				result ==
	 * 					false
	 */
	protected boolean collideY(WorldObjects object) {
		if (hasSameY(object)) {
			int firstLeftX = this.getPixelLeftX() + 1;
			int firstRightX = firstLeftX + this.getCurrentSprite().getWidth() - 3;
			int secondLeftX = object.getPixelLeftX() + 1;
			int secondRightX = secondLeftX + object.getCurrentSprite().getWidth() - 3;
			for (int firstX = firstLeftX; firstX < firstRightX; firstX++) {
				for (int secondX = secondLeftX; secondX < secondRightX; secondX++) {
					if (firstX == secondX)
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param object
	 * 			The alien that need to be tested for same x co as this object
	 * @return result ==
	 * 				( this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1 == object.getPixelLeftX() ) ||
	 *		   		( this.getPixelLeftX() == object.getPixelLeftX()+ + object.getCurrentSprite().getWidth() - 1 )
	 */
	protected boolean hasSameX(Mazub object) {
		return ( this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1 == object.getPixelLeftX() ) ||
			   ( this.getPixelLeftX() == object.getPixelLeftX()+ + object.getCurrentSprite().getWidth() - 1 );
	}
	
	/**
	 * 
	 * @param object
	 * 			The alien that need to be tested for horizontal collision
	 * @return if (this.hasSameX(object))
	 * 					for each yCo1 in this.getPixelBottomY()..this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1
	 * 						for each yCo2 in object.getPixelBottomY()..object.getPixelBottomY() + object.getCurrentSprite().getHeight() - 1
	 * 							result == 
	 * 								(yCo1 == yCo2)
	 * 			else
	 * 				result ==
	 * 					false
	 */
	protected boolean collideX(Mazub object) {
		if (hasSameX(object)) {
				int firstBottomY = this.getPixelBottomY();
				int firstTopY = firstBottomY + this.getCurrentSprite().getHeight() - 1;
				int secondBottomY = object.getPixelBottomY();
				int secondTopY = secondBottomY + object.getCurrentSprite().getHeight() - 1;
				for (int firstY = firstBottomY; firstY < firstTopY; firstY++) {
					for (int secondY = secondBottomY; secondY < secondTopY; secondY++) {
						if (firstY == secondY)
							return true;
					}
				}
		}
		return false;
	}
	
	/**
	 * 
	 * @return result ==
	 * 				((this.isMovingLeft() && this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX())) ||
	 *				(this.isMovingRight() && this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1)))
	 */
	protected boolean isImpassableX() {
		return ((this.isMovingLeft() && this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX())) ||
				(this.isMovingRight() && this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1)));
			}

	/**
	 * 
	 * @return result ==
	 * 				((this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1)) || (this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY())))
	 */
	protected boolean isImpassableY() {
		return ((this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1)) || (this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY())));
			}
	
	/**
	 * 
	 * @param action
	 * 			The number indicating the action of the object
	 * @pre action < 4
	 * @effect if action == 0
	 * 			then !new.isJumping()
	 * 				new.isMovingLeft()
	 * 		else if action == 1
	 * 			then !new.isJumping()
	 * 				new.isMovingRight()
	 * 		else if action == 2
	 * 			then new.isMovingLeft()
	 * 				new.isJumping()
	 * 		else if action == 3
	 * 			then new.isMovingRight()
	 * 				isJumping()
	 */
	@Model
	protected void startAction(int action) {
		if (action == 0) {
			this.setIsJumping(false);
			if (!this.isMovingLeft())
				this.startMoveLeft();
		}
		else if (action == 1) {
			this.setIsJumping(false);
			if (!this.isMovingRight())
				this.startMoveRight();
		}
		else if (action == 2) {
			if (!this.isMovingLeft())
				this.startMoveLeft();
			this.jump();
		}
		else if (action == 3) {
			if (!this.isMovingRight())
				this.startMoveRight();
			this.jump();
		}
	}
	
	/**
	 * 
	 * @return result ==
	 * 				(this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1))
	 */
	protected boolean isImpassableTop() {
		return (this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1));
			}
	
	/**
	 * 
	 * @return result ==
	 * 				(this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY()))
	 */
	protected boolean isImpassableBottom() {
		return (this.doesHorizontalPixelOverlapImpassable(this.getPixelBottomY()));
			}

	/**
	 * @post if this.isJumping()
	 * 			then new.isFalling()
	 * 				!new.isJumping()
	 * 				new.getAccelerationY() == -10
	 * 		else
	 * 			then new.getAccelerationY() == 0
	 * @post if this.getVelocityY() > 0
	 * 			then new.getVelocityY() == 0
	 */
	protected void stopMovingUp() {
		if (this.isJumping)
		{
			this.setIsFalling(true);
			this.setIsJumping(false);
			this.setAccelerationY(-10);
		}
		else {
			if (this.getAccelerationY() > 0) {
				this.setAccelerationY(0.0);	
			}
		}
		if (this.getVelocityY() > 0) {
			this.setVelocityY(0.0);
		}
	}
	
	/**
	 * @post if this.isFalling()
	 * 			then !new.isFalling()
	 * 				new.getAccelerationY() == 0
	 * @post if this.getAccelerationY() < 0
	 * 			then new.getAccelerationY() == 0
	 * @post if this.getVelocityY() < 0
	 * 			new.getVelocityY() == 0
	 */
	protected void stopMovingDown() {
		if (this.isFalling) {
			this.setIsFalling(false);
			this.setAccelerationY(0);
		}
		if (this.getAccelerationY() < 0) {
			this.setAccelerationY(0.0);	
		}
		if (this.getVelocityY() < 0) {
			this.setVelocityY(0.0);
		}
	}
	/**
	 * 
	 * Return the current number of hitpoints of this object
	 */
	@Basic
	public int getHitPoints(){
		return this.hitPoints;
	}
	
	/**
	 * 
	 * @param points
	 * 			The current number of hit points for this object
	 * @post new.getHitPoints() == points
	 */
	protected void setHitPoints(int points) {
		if (points < 0)
			this.hitPoints = 0;
		else if (points > this.getMaxHitPoints())
			this.hitPoints = this.getMaxHitPoints();
		else
			this.hitPoints = points;
	}
	
	/**
	 * Variable registering current number of hit points of this object
	 */
	private int hitPoints;
	
	/**
	 * 
	 * Return the max hit points of the object
	 */
	protected int getMaxHitPoints() {
		return this.maxHitPoints;
	}
	
	/**
	 * 
	 * @param hitPoints
	 * 			The new max hit points for the object
	 * @post new.getMaxHitPoints() == hitPoints
	 */
	@Model
	protected void setMaxHitpoints(int hitPoints) {
		this.maxHitPoints =  hitPoints;
	}
	
	/**
	 * Variable storing the max number of hit points of the object, default Integer.Max_VALUE
	 */
	private int maxHitPoints = Integer.MAX_VALUE;
	
	/**
	 * 
	 * @param hitPoints
	 * 			The hit points to add
	 * @post new.getHitPoints() == this.getHitPoints() + hitPoints
	 * @effect if new.getHitPoints() == 0
	 * 				then this.die()
	 * 			else
	 * 				then this.setImmuneTime(0)
	 * 					this.setIsImmune(true)
	 */
	protected void addHitPointsEnemy(int hitPoints) {
		this.setHitPoints(this.getHitPoints() + hitPoints);
		if (this.getHitPoints() == 0) {
			this.die();
		}
		else {
			this.setImmuneTime(0.0);
			this.setIsImmune(true);
		}
	}
	
	/**
	 * 
	 * @param hitPoints
	 * 			The hit points to add
	 * @post new.getHitPoints() == this.getHitPoints() + hitPoints
	 * @effect if new.getHitpoints() ==0
	 * 				then this.die()
	 */
	protected void addHitPointsProperty(int hitPoints) {
		this.setHitPoints(this.getHitPoints() + hitPoints);
		if (this.getHitPoints() == 0) {
			this.die();
		}
	}
	
	/**
	 * @post new.isAllowedLeft() == false
	 * @post new.isAllowedRight() == false
	 * @post new.isAllowedUp() == false
	 * @post new.isAllowedDown() == false
	 * @post new.isDeath() == true
	 */
	protected void die() {
		this.setAllowedLeft(false);
		this.setAllowedRight(false);
		this.setAllowedUp(false);
		this.setAllowedDown(false);
		this.setIsDeath(true);
	}
	
	/**
	 * 
	 * Return the boolean value for the immunity of this object
	 */
	@Basic @Model
	public boolean isImmuneMagma() {
		return this.immunityMagma;
	}
	
	/**
	 * 
	 * @param isImmune
	 * 			The current boolean value for the immunity of this object
	 * @post new.isImmune() == isImmune
	 */
	protected void setIsImmuneMagma(boolean isImmuneMagma) {
		this.immunityMagma = isImmuneMagma;
	}
	
	/**
	 * Variable registering the immunity of this object
	 */
	private boolean immunityMagma;
	
	/**
	 * 
	 * Return the current time of immunity of this object
	 */
	@Basic @Model
	protected double getImmuneTimeMagma() {
		return this.immuneTimeMagma;
	}
	
	/**
	 * 
	 * @param time
	 * 			The current time of immunity of this object
	 * @pre !isNaN(time)
	 * @post new.getImmuneTime() == time
	 */
	protected void setImmuneTimeMagma(double time) {
		this.immuneTimeMagma = time;
	}
	
	/**
	 * Variable registering current time of immunity of this object
	 */
	private double immuneTimeMagma = 0.2;
	/**
	 * 
	 * Return the boolean value for the immunity of this object
	 */
	@Basic @Model
	public boolean isImmuneWater() {
		return this.immunityWater;
	}
	
	/**
	 * 
	 * @param isImmune
	 * 			The current boolean value for the immunity of this object
	 * @post new.isImmune() == isImmune
	 */
	protected void setIsImmuneWater(boolean isImmuneWater) {
		this.immunityWater = isImmuneWater;
	}
	
	/**
	 * Variable registering the immunity of this object
	 */
	private boolean immunityWater;
	
	/**
	 * 
	 * Return the current time of immunity of this object
	 */
	@Basic @Model
	protected double getImmuneTimeWater() {
		return this.immuneTimeWater;
	}
	
	/**
	 * 
	 * @param time
	 * 			The current time of immunity of this object
	 * @pre !isNaN(time)
	 * @post new.getImmuneTime() == time
	 */
	protected void setImmuneTimeWater(double time) {
		this.immuneTimeWater = time;
	}
	
	/**
	 * Variable registering current time of immunity of this object
	 */
	private double immuneTimeWater = 0.2;
	/**
	 * 
	 * Return the boolean value for the immunity of this object
	 */
	@Basic @Model
	public boolean isImmune() {
		return this.immunity;
	}
	
	/**
	 * 
	 * @param isImmune
	 * 			The current boolean value for the immunity of this object
	 * @post new.isImmune() == isImmune
	 */
	protected void setIsImmune(boolean isImmune) {
		this.immunity = isImmune;
	}
	
	/**
	 * Variable registering the immunity of this object
	 */
	private boolean immunity;
	/**
	 * 
	 * Return the current time of immunity of this object
	 */
	@Basic @Model
	protected double getImmuneTime() {
		return this.immuneTime;
	}
	
	/**
	 * 
	 * @param time
	 * 			The current time of immunity of this object
	 * @pre !isNaN(time)
	 * @post new.getImmuneTime() == time
	 */
	protected void setImmuneTime(double time) {
		this.immuneTime = time;
	}
	
	/**
	 * Variable registering current time of immunity of this object
	 */
	private double immuneTime = 0.6;
	
	/**
	 * 
	 * Return the current boolean value for the death of this object
	 */
	public boolean isDeath() {
		return this.death;
	}
	
	/**
	 * 
	 * @param isDeath
	 * 		The current boolean value for the death of this object
	 * @post new.isDeath() == isDeath	
	 */
	@Basic @Model
	protected void setIsDeath(boolean isDeath) {
		this.death = isDeath;
	}
	
	/**
	 * Variable registering boolean value for the death of this object
	 */
	private boolean death;
	
	/**
	 * 
	 * Return the current time since this object died
	 */
	@Basic @Model
	protected double getTimeDeath() {
		return this.deathTime;
	}
	
	/**
	 * 
	 * @param time
	 * 		The current time since the object died
	 * @pre !isNaN(time)
	 * @post new.getTimeDeath() == time
	 */
	@Model
	protected void setTimeDeath(double time) {
		this.deathTime = time;
	}
	
	/**
	 * Variable registering the current time since this object died
	 */
	private double deathTime;
	
	/**
	 * 
	 * Return the current boolean value for the eatabillity of this object
	 */
	@Basic
	public boolean isEatable() {
		return this.eatable;
	}
	
	/**
	 * 
	 * @param isEatable
	 * 			The current boolean value for the eatabillity of this object
	 * @post new.isEatable() == isEatable
	 */
	protected void setEatable(boolean isEatable) {
		this.eatable = isEatable;
	}
	
	/**
	 * Variable registering the current eatabillity of this object
	 */
	private boolean eatable;

	/**
	 * 
	 * @param accelerationY
	 * 			The initial vertical acceleration of this object
	 * @post new.getInitialAccelerationY() == accelerationY
	 */
	protected void setInitialAccelerationY(double accelerationY) {
	    this.initialAccelerationY = accelerationY;		
	}
	
	/**
	 * 
	 * Return the current world of this object
	 */
	@Basic @Model
	public World getWorld() {
	     return this.world ;		
		}
	
	/**
	 * 
	 * @return result ==
	 * 				this.getWorld().getGeologicalfeature(this.getPixelLeftX(),this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1)
	 */
	protected int getTopGeologicalfeature() {
		World world = this.getWorld();
		return world.getGeologicalfeature(this.getPixelLeftX() + 1,this.getPixelBottomY() + this.getCurrentSprite().getHeight() - 1);	
	}
	
	/**
	 * 
	 * @param velocity
	 * 			The velocity used to calculate the distance
	 * @param time
	 * 			The time used to calculate the distance
	 * @param acceleration
	 * 			The acceleration used to calculate the distance
	 * @pre !isNaN(velocity)
	 * @pre !isNaN(time)
	 * @pre !isNaN(acceleration)
	 * @return result ==
	 * 				(100.0*(velocity*time+0.5*(acceleration*Math.pow(time, 2))))
	 */
	@Model
	protected double getRealDistanceToMove(double velocity, double time,double acceleration) {
		return (double) (100.0*(velocity*time+0.5*(acceleration*Math.pow(time, 2))));
	}

	/**
	 * 
	 * @return result ==
	 * 				this.getWorld.getGeologicalfeature(this.getPixelLeftX()+1,this.getPixelBottomY()+1)
	 */
	@Model
	protected int getMainGeologicalfeature() {
		World world = this.getWorld();
		return world.getGeologicalfeature(this.getPixelLeftX()+1,this.getPixelBottomY()+1);
	}
	/**
	 * 
	 * @return result ==
	 * 				(this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX()))
	 */
	@Model
	protected boolean isImpassableLeft() {
		return (this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX()));
			}

	/**
	 * 
	 * @return result == 
	 * 				(this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1))
	 */
	@Model
	protected boolean isImpassableRight() {
		return (this.doesVerticalPixelOverlapImpassable(this.getPixelLeftX() + this.getCurrentSprite().getWidth() - 1));
			}

	/**
	 * 
	 * @param time
	 * 			The time for calculating the minimum
	 * @return result ==
	 * 				Math.min(time, 
	 * 					Math.min(0.01 / (Math.abs(this.getVelocityX()) + Math.abs(this.getAccelerationX())*this.getTime()),
	 * 					 timeY = 0.01 / (Math.abs(this.getVelocityY()) + Math.abs(this.getAccelerationY())*this.getTime())))
	 */
	@Model
	protected double getMinimumDt(double time) {
		double timeX = 0.01 / (Math.abs(this.getVelocityX()) + Math.abs(this.getAccelerationX())*this.getTime());
		double timeY = 0.01 / (Math.abs(this.getVelocityY()) + Math.abs(this.getAccelerationY())*this.getTime());
		double minimum = Math.min(timeX, timeY);
		return Math.min(minimum, time);
	}
	
	/**
	 * 
	 * @return result == 
	 * 				this.getWorld().getGeologicalfeature(this.getPixelLeftX()+1,this.getPixelBottomY())
	 */
	public int getBottomGeologicalfeature() {
			World world = this.getWorld();
			return world.getGeologicalfeature(this.getPixelLeftX()+1,this.getPixelBottomY());
		}
	
	/**
	 * @post !new.isJumping()
	 * @post new.getInitialVelocityY() == 0
	 * @post new.getInitialAccelerationY() == 0
	 * @post new.getVelocityY() == 0
	 * @post new.getAccelerationY() == O
	 */
	public void stopJumping() {
		this.setIsJumping(false);
		this.setInitialVelocityY(0);
		this.setInitialAccelerationY(-10);
		this.setVelocityY(0);
		this.setAccelerationY(0);
	}
	
	/**
	 * @post new.isFalling()
	 * @post new.getInitialAccelerationY() == -10
	 * @post new.getAccelerationY() == new.getInitialAccelerationY()
	 */
	protected void startFall() {
		this.setIsFalling(true);
		this.setIsJumping(false);
		this.setInitialAccelerationY(-10);					
		this.setAccelerationY(this.getInitialAccelerationY());
	}
	
	/**
	 * @post !new.isFalling()
	 * @post new.getVelocityY() == 0
	 * @post new.getAccelerationY() == 0
	 */
	protected void stopFall() {
		this.setIsFalling(false);
		this.setVelocityY(0);
		this.setAccelerationY(0);
	}
	
	/**
	 * Variable registering the current possibility of moving left of this object
	 */
	private boolean allowedLeft;
	
	/**
	 * 
	 * Return the current possibility of moving left of this object
	 */
	@Basic @Model
	protected boolean isAllowedLeft() {
		return allowedLeft;
	}
	
	/**
	 * 
	 * @param allowedLeft
	 * 			The current possibility of moving left of this object
	 * @post new.isAllowedLeft() == allowedLeft
	 */
	protected void setAllowedLeft(boolean allowedLeft) {
		this.allowedLeft = allowedLeft;
	}
	
	/**
	 * 
	 * Return the current possibility of moving right of this object
	 */
	@Basic @Model
	private boolean isAllowedRight() {
		return allowedRight;
	}
	
	/**
	 * 
	 * @param allowedRight
	 * 			The current possibility of moving right of this object
	 * @post new.isAllowedRight() == allowedRight
	 */
	protected void setAllowedRight(boolean allowedRight) {
		this.allowedRight = allowedRight;
	}
	/**
	 * 
	 * Return the current possibility of moving up of this object
	 */
	@Basic @Model
	private boolean isAllowedUp() {
		return allowedUp;
	}
	
	/**
	 * 
	 * @param allowedUp
	 * 			The current possibility of moving up of this object
	 * @post new.isAllowedUp() == allowedUp
	 */
	protected void setAllowedUp(boolean allowedUp) {
		this.allowedUp = allowedUp;
	}
	
	/**
	 * 
	 * Return the current possibility of moving down of this object
	 */
	@Basic @Model
	private boolean isAllowedDown() {
		return allowedDown;
	}
	
	/**
	 * 
	 * @param allowedDown
	 * 			The current possibility of moving down of this object
	 * @post new.isAllowedDown() == allowedDown
	 */
	protected void setAllowedDown(boolean allowedDown) {
		this.allowedDown = allowedDown;
	}
	
	/**
	 * Variable registering the current possibility of moving right of this object
	 */
	private boolean allowedRight;
	
	/**
	 * Variable registering the current possibility of moving up of this object
	 */
	private boolean allowedUp;
	
	/**
	 * Variable registering the current possibility of moving down of this object
	 */
	private boolean allowedDown;
	
	/**
	 * @post new.isAllowedLeft() == !this.isImpassableLeft()
	 * @post new.isAllowedRight() == !this.isImpassableRight()
	 * @post new.isAllowedUp() == !this.isImpassableTop()
	 * @post new.isAllowedDown() == !this.isImpassableBottom()
	 */
	@Model
	protected void setPassableDirections () {
		this.setAllowedLeft(!this.isImpassableLeft());
		this.setAllowedRight(!this.isImpassableRight());
		this.setAllowedUp(!this.isImpassableTop());
		this.setAllowedDown(!this.isImpassableBottom());
	}

	protected boolean collideObject(WorldObjects object) {
		boolean collide = false;
		if ( this.collideX(object) )  {
			if (this.getPixelLeftX() < object.getPixelLeftX()) {
				this.setAllowedRight(false);	
			}
			else {
				this.setAllowedLeft(false);				
			}
			collide = true;
		}
		else if ( this.collideY(object) )  {
			if (this.getPixelBottomY() > object.getPixelBottomY()) {
				this.setAllowedDown(false);					
			}
			else {
				this.setAllowedUp(false);			
			}
			collide = true;
		}
		return collide;
	}
	
	/**
	 * 
	 * @param object
	 * 			The object to check collision with
	 * @return result ==
	 * 				this.collideX(object) || this.collideY(object)
	 */
	protected boolean checkCollisionObject(WorldObjects object) {
		boolean collide = false;
		if ( this.collideX(object) )  {
			collide = true;
		}
		else if ( this.collideY(object) )  {
			collide = true;
		}
		return collide;
	}
	
	/**
	 * 
	 * @param object
	 * 			The object of collision
	 * @post if this.CollideX(object)
	 * 			then if this.getPixelLeftX() < object.getPixelLeftX()
	 * 					then new.isAllowedRight() == false
	 * 				else
	 * 					then new.isAllowedLeft() == false
	 * @post if this.collideY(object)
	 * 				then if this.getPixelBottomY() > object.getPixelBottomY()
	 * 						then new.isAllowedDown() == false
	 * 					else
	 * 						then new.isAllowedup() == false
	 */
	protected void blockDirectionOnCollision(WorldObjects object) {
		if ( this.collideX(object) )  {
			if (this.getPixelLeftX() < object.getPixelLeftX()) {
				this.setAllowedRight(false);	
			}
			else {
				this.setAllowedLeft(false);				
			}
		}
		if ( this.collideY(object) )  {
			if (this.getPixelBottomY() > object.getPixelBottomY()) {
				this.setAllowedDown(false);					
			}
			else {
				this.setAllowedUp(false);			
			}
		}
	}
	
	/**
	 * 
	 * @param object
	 * 			The object of collision
	 * @post new.getHitPoints() == this.getHitPoints() - 50
	 */
	protected void collisionChangeHitPoints(WorldObjects object) {
		this.setHitPoints(this.getHitPoints() - 50);
	}
	
	protected void collideObjectsActions() {
		ArrayList<WorldObjects> previousCollisionList = this.getCollisionList();
		ArrayList<WorldObjects> currentCollisionList = new ArrayList<WorldObjects>();
		for (WorldObjects object: this.getWorld().getCollectionEnemies()) {
			if (this.checkCollisionObject(object) && !object.isDeath() ) {
				this.blockDirectionOnCollision(object);
				if (this.getClass() != object.getClass()) {
					currentCollisionList.add(object);
					if (!previousCollisionList.contains(object)) {
						if (!this.isImmune() && !this.hasSameBottom(object)) {
							this.collisionChangeHitPoints(object);
							if (this.getHitPoints() == 0) {
								this.die();
							}
							else {
								this.setImmuneTime(0.0);
								this.setIsImmune(true);
							}		
						}
						if (!object.isImmune()) {
							object.collisionChangeHitPoints(this);
							if (object.getHitPoints() == 0) {
								object.die();
							}
							else {
								object.setImmuneTime(0.0);
								object.setIsImmune(true);
							}		
						}
						object.collisionList.add(this);
					}		
				}
			}
		}
		for (WorldObjects object: previousCollisionList) {
			if (!currentCollisionList.contains(object)) {
				object.collisionList.remove(this);
			}
		}
		this.setCollisionList(currentCollisionList);
	}
	
	/**
	 * 
	 * @param collision
	 * 			The new collision list for the object
	 * @post new.getCollisionList() == collision
	 */
	private void setCollisionList(ArrayList<WorldObjects> collision) {
		this.collisionList = collision;
	}
	
	/**
	 * 
	 * Return the current collision list for the object
	 */
	@Basic @Model
	protected ArrayList<WorldObjects> getCollisionList() {
		return (ArrayList<WorldObjects>) this.collisionList.clone();
	}
	
	/**
	 * Variable storing the collision list for the object
	 */
	private ArrayList<WorldObjects> collisionList = new ArrayList<WorldObjects>();


	
	/**
	 * 
	 * @param dt
	 * 		The time for the calculation of the distance
	 * @effect if this.getDirection()*this.getRealDistanceToMove(this.getVelocityX(),dt, this.getAccelerationX()) > 0
	 * 				then if !isAllowedRight()
	 * 					then setDoublePixelLeftX(this.getDoublePixelLeftX() + distanceToMove)
	 * 						setVelocityX(0)
	 * 					else
	 * 						then updateVelocityX(this.getAccelerationX(), dt)
	 * @effect if this.getDirection()*this.getRealDistanceToMove(this.getVelocityX(),dt, this.getAccelerationX()) > 0
	 * 				then if !isAllowedLeft()
	 * 					then setDoublePixelLeftX(this.getDoublePixelLeftX() + distanceToMove)
	 * 						setVelocityX(0)
	 * 					else
	 * 						then updateVelocityX(this.getAccelerationX(), dt)
	 * @effect if this.getRealDistanceToMove(this.getVelocityY(),dt, this.getAccelerationY()) > 0
	 * 				then if !isAllowedUp()
	 * 					then setDoublePixelBottomY(this.getDoublePixelBottomY() + distanceToMove)
	 * 						setVelocityY(0)
	 * 					else
	 * 						then updateVelocityY(this.getAccelerationY(), dt)
	 * @effect if this.getRealDistanceToMove(this.getVelocityY(),dt, this.getAccelerationY()) > 0
	 * 				then if !isAllowedDown()
	 * 					then setDoublePixelBottomY(this.getDoublePixelBottomY() + distanceToMove)
	 * 						setVelocityY(0)
	 * 					else
	 * 						then updateVelocityY(this.getAccelerationY(), dt)
	 */
	@Model
	protected void updateLocationAndVelocity(double dt) {
		if ((this.isMovingLeft() || this.isMovingRight()) && this.getVelocityX() == 0 && this.getAccelerationX() == 0) {
			this.setVelocityX(this.getInitialVelocityX());
			this.setAccelerationX(this.getInitialAccelerationX());
		}
		double velocityX = this.getVelocityX(); 
		double distanceToMove = this.getDirection()*this.getRealDistanceToMove(velocityX,dt, this.getAccelerationX());
		if (distanceToMove > 0) {
			if (!this.isAllowedRight()) {
				distanceToMove = 0;
				this.setVelocityX(0);
			}
			else {
				this.updateVelocityX(this.getAccelerationX(), dt);
			}
		}
		if (distanceToMove < 0) {
			if (!this.isAllowedLeft()) {
				distanceToMove = 0;
				this.setVelocityX(0);
			}
			else {
				this.updateVelocityX(this.getAccelerationX(), dt);
			}
		}
		this.setDoublePixelLeftX(this.getDoublePixelLeftX() + distanceToMove);
		if (velocityX == this.getVelocityX()) {
			this.setAccelerationX(0.0);
		}
		distanceToMove = this.getRealDistanceToMove(this.getVelocityY(),dt, this.getAccelerationY());
		if (distanceToMove > 0) {
			if (!this.isAllowedUp()) {
				distanceToMove = 0;
				this.setVelocityY(0);
			}
			else {
				this.updateVelocityY(this.getAccelerationY(), dt);
			}
		}
		if (distanceToMove < 0) {
			if (!this.isAllowedDown()) {
				distanceToMove = 0;
				this.setVelocityY(0);
			}
			else {
				this.updateVelocityY(this.getAccelerationY(), dt);
			}
		}
		this.setDoublePixelBottomY(this.getDoublePixelBottomY() + distanceToMove);
	}
	
	/**
	 * 
	 * @param dPixelLeftX
	 * 			The double value for the left x co
	 * @post new.getDoublePixelLeftX() == dPixelLeftX
	 */
	@Model
	private void setDoublePixelLeftX(double dPixelLeftX) {
		this.pixelLeftX = dPixelLeftX;
	}
	
	/**
	 * 
	 * Return the current double value for the left x co
	 */
	@Basic @Model
	private double getDoublePixelLeftX() {
		return this.pixelLeftX;
	}
	
	/**
	 * 
	 * @param dPixelBottomY
	 * 			The double value for the bottom y co
	 * @post new.getDoublePixelBottomY() == dPixelBottomY
	 */
	@Model
	private void setDoublePixelBottomY(double dPixelBottomY) {
		this.pixelBottomY = dPixelBottomY;
	}
	
	/**
	 * 
	 * Return the current double value for the bottom y co
	 */
	@Basic @Model
	private double getDoublePixelBottomY() {
		return this.pixelBottomY;
	}
	
	/**
	 * 
	 * @param world
	 * 			The world that need to be checked
	 * @return result ==
	 * 				(world != null)
	 */
	private boolean canHaveAsWorld(World world) {
		if (this.isTerminated())
			return world == null;
		return world != null;
	}
	
	/**
	 * 
	 * @return result ==
	 * 				this.canHaveAsWorld(this.getWorld()) && 
	 * 					(this.getWorld() == null || this.getWorld().getCollectionLiving().contains(this))
	 */
	private boolean isInWorld() {
		return this.canHaveAsWorld(this.getWorld()) && (this.getWorld() == null || this.getWorld().getCollectionLiving().contains(this));
	}

	public final static int AIR = 0;
	public final static int GROUND = 1;
	public final static int WATER = 2;
	public final static int MAGMA = 3;
	
	/**
	 * 
	 * @param program
	 * 			The new program to be set
	 * @post new.getProgram() == program
	 */
	protected void setProgram(Program program) {
		this.program = program;
	}
	
	/**
	 * 
	 * Return the current program
	 */
	@Model @Basic
	public Program getProgram() {
		return this.program;
	}
	
	/**
	 * Variable storing the current program
	 */
	private Program program;
	
	protected abstract void advanceTime(double time, World world);
	
	/**
	 * 
	 * @param time
	 * 			The time used in the calculations
	 * @param world
	 * 			The world of the object
	 * @effect while timePassed < time && !this.isTerminated()
	 * 				do this.getProgram().executeCycle()
	 * 					this.advanceTime(0.001, world)
	 * 					timePassed += 0.001
	 */
	protected void advanceTimeWithProgram(double time, World world) {
		double timePassed = 0;
		while (timePassed < time && !this.isTerminated()) {
			double timeCycle = 0.001;
			this.getProgram().executeCycle();
			this.advanceTime(timeCycle, world);
			timePassed += timeCycle;
		  }
	}
	
	/**
	 * 
	 * Return the current termination
	 */
	@Model @Basic
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * 
	 * @param terminated
	 * 			Boolean of the current termination
	 * @post new.isTerminated() == terminated
	 */
	private void setIsTerminated(boolean terminated) {
		this.isTerminated = terminated;
	}
	
	/**
	 * Variable storing the current termination
	 */
	private boolean isTerminated;
	
	/**
	 * @post if !this.isTerminated()
	 * 			then new.isTerminated() == true
	 */
	protected void terminate() {
		if (!this.isTerminated()) {
			this.setIsTerminated(true);
		}
	}
}


