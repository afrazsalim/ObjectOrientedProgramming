package jumpingalien.model;

import jumpingalien.util.Sprite;

/**
 * 
 * @author Afraz Salim && Niels van Belle
 * @version 2.0
 * @invar |isInWorld()
 * @invar |isValidPixelLeftX(getPixelLeftX())
 * @invar |isValidPixelBottomY(getPixelBottomY())
 * @invar |isValidVelocityX(getVelocityX())
 * @invar |isValidSprites(getSprites())
 * @invar |isValidTime(getTime())
 */
public class Buzam extends Mazub{



	/**
	 * A constructor to create the object.
	 * @param pixelLeftX
	 *        The given left pixel of the object.
	 * @param pixelBottomY
	 *        The given bottom Y pixel of the object.
	 * @param sprites
	 *         The given array constiting the sprites os the buzam.
	 */
	public Buzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX, pixelBottomY, sprites);
	}

	/**
	 * 
	 * A constructor to create the object with program.
	 * @param pixelLeftX
	 *        The given left pixel of the object.
	 * @param pixelBottomY
	 *        The given bottom Y pixel of the object.
	 * @param sprites
	 * @param program
	 *        The given program of the object which controles the obejct.
	 * @Pre.. The given pixel of the object should be a valid object in order to set.
	 *        |(isValidePixelLeftX(pixelLeftX)
	 * @post. The left pixel of the object is equal to the given pixel.
	 *        |new.pixelLeftX = pixelLeftX
	 * @post.. The bottom y pixel of the object should be a valid object.
	 *        |new.pixelBottomY = pixelBottomY
	 * @effect The new program of the object is equal to the program.
	 *        |this.setProgram(program);
	 * @effect. The new hitpoints of the alien will be equal to the given hitPoints.
	 *        |new.HitPoints = 500.
	 */
	public Buzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites,
			Program program) {
		super(pixelLeftX, pixelBottomY, sprites);
		this.initialize();
		if (!program.isWellFormed()) {
			this.setProgram(null);
		}
		else {
		this.setProgram(program);
		program.setObject(this);
		}
	}
	
	@Override
	protected void initialize() {
		setHitPoints(500);
	}
	
	
//	/**
//	 * A method to advance the time of Buzam.
//	 * @param dt
//	 *        The given time with which the time of the buzam will be updated.
//	 * @param world
//	 *        The given world of buzam.
//	 * @throws Throws illegalArgumentException if the given world is null.
//	 *         |if(!(isValidWorld(world)))
//	 *         |throw new IllegalArgumentException("world cannot be null");
//	 * @effect The new world of the object will be equal to the given world.
//	 *         |new.World = world
//	 * @post.  If the given program is not null and corrupted then it returns 
//	 *         and executes the next program of next object.
//	 *         |if(this.getProgram() != null)
//	 *         |if(this.hasCorruptedProgram())
//	 *         |return;
//	 * @effect If the given program is not null then the object executing the 
//	 *         program will be equal to this object.
//	 *         |this.getProgram().setObject(this);
//	 * @post.  The object starts executing the program.
//	 *         |this.getProgram().executeCycle();
//	 * @post.  The object advances the time after executing program in order to 
//	 *         update locaiton and velocity.
//	 *         |super.advanceTimeMazub(dt, world);
//	 */
//	public void advanceTimeBuzam(double dt, World world) {
//		if(!(isValidWorld(world)))
//			throw new IllegalArgumentException("world cannot be null");
//		this.setWorld(world);
//		this.setTimeDeath(dt);
//		if(this.getProgram() != null){
//			if(this.hasCorruptedProgram())
//				return;
//			else
//			{
//			this.getProgram().setObject(this);
//			this.getProgram().executeCycle();
//			super.advanceTimeMazub(dt, world);
//			}
//		}
//	}
	@Override
	public void advanceTimeWithProgram(double time, World world) {
			double timePassed = 0;
		  while (timePassed < time && !this.isTerminated()) {
			double timeCycle = 0.001;
			this.getProgram().executeCycle();
			super.advanceTime(timeCycle, world);
			timePassed += timeCycle;
		  }
	}

	
	/**
	 * A checker to check whether the given world is an effective world.
	 * @param world
	 *        The given world of the object.
	 * @return
	 *        Returns true if and only if the given world is not null.
	 *        |return world != null
	 */
	private boolean isValidWorld(World world) {
		return world != null;
	}
	
	
	

}
