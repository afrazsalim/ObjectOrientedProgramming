package jumpingalien.model;

import jumpingalien.util.Sprite;

import java.util.Random;
/**
 * 
 * @author Afraz Salim & Niels van Belle
 * version 2.0
 *
 */
public abstract class DefaultWithoutProgram extends WorldObjects {

	 /**
     * A constructor initializing the super class with given parameters.
     * @param x
     *        The x co-ordinate of enemy.
     * @param y
     *        The y co-ordinate of the enemy.
     * @param sprites
     *        A array consisting the sprites of the enemy.
     * @post. Super class will be initialized with the given 
     *        parameters.
     *        |super(x, y, sprites);
     */
	protected DefaultWithoutProgram(int x, int y, Sprite[] sprites) {
		super(x, y, sprites);
	}
	
	protected abstract void updateAction();
	
	protected void advanceTimeWithProgram(double time, World world) {
		if (this.getProgram() == null) {
			this.updateAction();
			this.advanceTime(time, world);
		}
		else {
		super.advanceTimeWithProgram(time, world);
		}
	 }
}
