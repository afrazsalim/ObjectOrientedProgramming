package jumpingalien.part1.tests;

import jumpingalien.model.Mazub;
import jumpingalien.part1.tests.TestUtils;
import jumpingalien.part1.facade.Facade;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

public class PartialFacadeTestt {
/*
	@Test
	public void startMoveRightCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] = 10.45 [cm], which falls into pixel (10, 0)

		assertArrayEquals(intArray(10, 0), facade.getLocation(alien));
	}
	
	@Test
	public void startMoveLeftCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(767, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 7.67 - 1 [m/s] * 0.1 [s] - 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 7.5655 [m] = 756 [cm], which falls into pixel (756, 0)

		assertArrayEquals(intArray(756, 0), facade.getLocation(alien));
	}
	
	@Test
	public void startMoveLeftMaxSpeedAtRightTime() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(767, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				0);
	}

	@Test
	public void startMoveRightMaxSpeedAtRightTime() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testEndMoveRightVelocity() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.endMoveRight(alien);
		
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getVelocity(alien),
				0);
	}
	
	@Test
	public void testEndMoveRightAcceleration() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.endMoveRight(alien);
		
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				0);
	}
	
	@Test
	public void testEndMoveLeftVelocity() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.endMoveLeft(alien);
		
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getVelocity(alien),
				0);
	}
	
	@Test
	public void testEndMoveLeftAcceleration() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.endMoveLeft(alien);
		
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				0);
	}

	@Test
	public void testAccellerationZeroWhenNotMoving() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testVelocityRunning() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);
		// v = 1 + 0.9*0.1 = 1.09
		assertArrayEquals(doubleArray(1.09, 0.0), facade.getVelocity(alien),
				0);
	}
	
	@Test
	public void testVelocityJumping() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startJump(alien);
		facade.advanceTime(alien, 0.1);
		// v = 8 - 10*0.1 = 7.0
		assertArrayEquals(doubleArray(0.0, 7.0), facade.getVelocity(alien),
				0);
	}
	
	@Test
	public void testAccellerationRunning() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		assertArrayEquals(doubleArray(0.9, 0.0), facade.getAcceleration(alien),
				0);
	}
	
	@Test
	public void testStartJumpCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startJump(alien);
		facade.advanceTime(alien, 0.1);

		// y_new [m] = 0 + 8 [m/s] * 0.1 [s] - 1/2 10.0 [m/s^2] * (0.1 [s])^2 =
		// 0.7500 [m] = 75.00 [cm], which falls into pixel (0, 75)

		assertArrayEquals(intArray(0, 75), facade.getLocation(alien));
	}
	
	@Test
	public void testAccellerationJumping() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startJump(alien);
		assertArrayEquals(doubleArray(0.0, -10.0), facade.getAcceleration(alien), 0);
	}
		
	@Test
	public void testWalkAnimationLastFrame() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);

		facade.startMoveRight(alien);

		facade.advanceTime(alien, 0.005);
		for (int i = 0; i < m; i++) {
			facade.advanceTime(alien, 0.075);
		}

		assertEquals(sprites[8+m], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testWalkAnimationLastFrameLeft() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(667, 0, sprites);

		facade.startMoveLeft(alien);

		facade.advanceTime(alien, 0.005);
		for (int i = 0; i < m; i++) {
			facade.advanceTime(alien, 0.075);
		}

		assertEquals(sprites[9+2*m], facade.getCurrentSprite(alien));
	}

	@Test
	public void testDuckingVelocityX() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(767, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.advanceTime(alien, 2.0);
		facade.startDuck(alien);
		// maximum speed reached after 0 seconds

		assertArrayEquals(doubleArray(1.0, 0.0), facade.getVelocity(alien),
				0);
	}
	
	
	@Test
	public void testEndDuckingVelocityX() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(767, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.startDuck(alien);
		facade.advanceTime(alien, 0.1);
		facade.endDuck(alien);
		facade.advanceTime(alien, 0.1);
		// v = 1.0 + 0.09 = 1.09

		assertArrayEquals(doubleArray(1.09, 0.0), facade.getVelocity(alien),
				0);
	}
	*/
}
