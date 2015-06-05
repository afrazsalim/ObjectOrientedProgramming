package jumpingalien.part3.Expressions;

public class ObjectsTests {

	/*@Test
	public void test() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
        Expression e = new objectTypesTests(alien);
        getheight h = new getheight(e, new SourceLocation(1,1));
        System.out.println(h.getValueOfConstant(new Program(null,null)));

	}
	
	@Test
	public void test_isJumping() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
        Expression e = new objectTypesTests(alien);
        alien.setIsJumping(false);
         isJumping h = new isJumping(e, new SourceLocation(1,1));
        System.out.println(h.getValueOfConstant(new Program(null,null)));

	}
	@Test
	public void test_isMazub() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
        Expression e = new objectTypesTests(alien);
        alien.setIsJumping(false);
        isMazub h = new isMazub(e, new SourceLocation(1,1));
        System.out.println(h.getValueOfConstant(new Program(null,null)));

	}
	@Test
	public void test_getX() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
        Expression e = new objectTypesTests(alien);
        alien.setIsJumping(false);
        GetX h = new GetX(e, new SourceLocation(1,1));
        Program program = new Program(null,null);
        System.out.println(h.getValue(program));

	}

	
	@Test
	public void test_getY() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
        Expression e = new objectTypesTests(alien);
        alien.setIsJumping(false);
        GetY h = new GetY(e, new SourceLocation(1,1));
        Program program = new Program(null,null);
        System.out.println(h.getValue(program));

	}
	
	@Test
	public void test_getWidth() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
        Expression e = new objectTypesTests(alien);
        alien.setIsJumping(false);
        getWidth h = new getWidth(e, new SourceLocation(1,1));
        Program program = new Program(null,null);
        System.out.println(h.getValue(program));

	}
	
	
	@Test
	public void test_getHeight() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 8));
        Expression e = new objectTypesTests(alien);
        alien.setIsJumping(false);
        getheight h = new getheight(e, new SourceLocation(1,1));
        Program program = new Program(null,null);
        System.out.println(h.getValue(program));

	}
	
	
	@Test
	public void test_getTile() {
		Expression e = new DoubleLiteral(300, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(300, new SourceLocation(2,3));
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 8));
		World world = facade.createWorld(50, 3, 3, 1, 1, 1, 1);
		Program program = new Program(null,null);
        program.setObject(alien);
		int[][] actualTiles = facade
				.getTilePositionsIn(world, 20, 20, 105, 105);
		int[][] expectedTiles = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 },
				{ 1, 1 }, { 2, 1 }, { 0, 2 }, { 1, 2 }, { 2, 2 } };
		facade.setMazub(world, alien);
        alien.setWorld(world);
		assertArrayEquals(expectedTiles, actualTiles);        
        getTile h = new getTile(e,e2, new SourceLocation(1,1));
        
        System.out.println(h.getValue(program));

	}

	@Test
	public void test_isMoving() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
        Expression e = new selfDefined(alien,new SourceLocation(1,1));
        DirectionType t = new DirectionType(Direction.RIGHT);
        Program program = new Program(null,null);
        Expression a = new createDirection(t.getValueOfConstant(program),new SourceLocation(1,1));
        alien.setIsMovingRight(true);
        alien.setIsJumping(false);
        isMoving h = new isMoving(e,a, new SourceLocation(1,1));
        System.out.println(h.getValue(program));

	}
	@Test
	public void test_isDucking() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
        Expression e = new selfDefined(alien,new SourceLocation(1,1));
        DirectionType t = new DirectionType(Direction.RIGHT);
        Program program = new Program(null,null);
        Expression a = new createDirection(t.getValueOfConstant(program),new SourceLocation(1,1));
        alien.setIsDucking(true);
        alien.setIsJumping(false);
        isDucking h = new isDucking(e,new SourceLocation(1,1));
        System.out.println(h.getValue(program));

	}

	@Test
	public void test_Assignment() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
        gameObjectType t = new gameObjectType(alien);
        Program program = new Program(null,new HashMap<String,Type>());
        Expression e = new ObjectLiteral(t,new SourceLocation(1,1));
        alien.setIsJumping(true);
        assignment a = new assignment("v",t,e,new SourceLocation(1,1));
        a.execute(program);
        Expression k = new ReadVariable("v",t,new SourceLocation(2,2));
        System.out.println(k.evaluate(program));

	}
/*
	@Test
	public void test_isJumping() {
        Expression d = new DoubleLiteral(10,new SourceLocation(1,1));
        Statement body = new Print(d,new SourceLocation(1,1));
        Expression cond= new TrueLiteral(new SourceLocation(1,1));
        Statement main = new whileStatement(cond,body,new SourceLocation(1,1));
        Program program = new Program(null,null);
        main.execute(program);

	}
	
	
	@Test
	public void test_getHeight() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
		gameObjectType type = new gameObjectType(alien);
        Expression e = new ObjectLiteral(type,new SourceLocation(1,1));
        getheight h = new getheight(e, new SourceLocation(1,1));
        Program program = new Program(null,null);
        System.out.println(h.getValue(program));

	}
	
	@Test
	public void test_getHitPoints() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(30, 499, spriteArrayForSize(3, 3));
		gameObjectType type = new gameObjectType(alien);
        Expression e = new ObjectLiteral(type,new SourceLocation(1,1));
        getWidth h = new getWidth(e, new SourceLocation(1,1));
        Program program = new Program(null,null);
        System.out.println(h.getValue(program));

	}
	
	*/
	
	
}
