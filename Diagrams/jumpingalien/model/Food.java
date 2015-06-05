package jumpingalien.model;

import jumpingalien.util.Sprite;

    /**
     * 
     * @author Afraz Salim & Niels van Belle
     * version 2.0
     *
     */
public abstract class Food extends WorldObjects {
    /**
     * A constructor initializing the super class with given parameters.
     * @param x
     *        The x co-ordinate of food.
     * @param y
     *        The y co-ordinate of the food.
     * @param sprites
     *        A array consisting the sprites of the food.
     * @post. Super class will be initialized with the given 
     *        parameters.
     *        |super(x, y, sprites);
     */
	protected Food(int x, int y, Sprite[] sprites) {
		super(x, y, sprites);
	}
}
