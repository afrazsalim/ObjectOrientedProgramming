package jumpingalien.part3.facade;

import java.util.Collection;
import java.util.Optional;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Statements.Statement;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.program.programFactory;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.part3.programs.ProgramParser;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart3 {
	private int[] makeArray(int first, int second) {
		int[] array = new int[2];
		array[0] = first;
		array[1] = second;
		return array;
	}
	
	private double[] makeArray(double first, double second) {
		double[] array = new double[2];
		array[0] = first;
		array[1] = second;
		return array;
	}
	
	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		Mazub mazub = new Mazub(pixelLeftX, pixelBottomY, sprites);
		return mazub;
	}
	
	@Basic
	@Override
	public int[] getLocation(Mazub alien) {
		return makeArray(alien.getPixelLeftX(),alien.getPixelBottomY());
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		return makeArray(alien.getVelocityX(), alien.getVelocityY());
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		return makeArray(alien.getAccelerationX(), alien.getAccelerationY());
	}

	@Override
	public int[] getSize(Mazub alien) {
		return makeArray(alien.getCurrentSprite().getWidth(), alien.getCurrentSprite().getHeight());
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		alien.jump();
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMoveLeft();
		
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMoveLeft();
		
	}

	@Override
	public void startMoveRight(Mazub alien) throws ModelException{
		alien.startMoveRight();
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMoveRight();
		
	}

	@Override
	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	@Override
	public void endDuck(Mazub alien) {
		alien.endDuck();
	}
	
	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitPoints();
	}
	
	@Override
	public World createWorld(int tileSize, int nbTilesX,
			int nbTilesY, int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY) {
		World world = new World(tileSize, nbTilesX,nbTilesY,visibleWindowWidth,visibleWindowHeight, targetTileX, targetTileY);
		return world;
	}
	@Override
	public int[] getWorldSizeInPixels(World world) {
		return makeArray(world.getWidthInPixel(), world.getHeightInPixel());
	}
	@Override
	public int getTileLength(World world) {
		return world.getTileSize();
	}
	@Override
	public void startGame(World world) {
		world.startGame();
	}
	@Override
	public boolean isGameOver(World world) {
		return world.isGameOver();
	}
	@Override
	public boolean didPlayerWin(World world) {
		return world.isPlayerWon();
	}
	@Override
	public void advanceTime(World world, double dt) {
		world.advanceTime(dt);
		
	}
	@Override
	public int[] getVisibleWindow(World world) {
		return world.getVisibleWindowWindow();
	}
	@Override
	public int[] getBottomLeftPixelOfTile(World world,
			int tileX, int tileY) {
		return world.getBottomleftPixelOfTile(tileX,tileY);
	}
	@Override
	public int[][] getTilePositionsIn(World world,int pixelLeft, int pixelBottom, int pixelRight, int pixelTop) {
		return world.getTilePositionsIn(pixelLeft,pixelBottom,pixelRight,pixelTop);
	}
	@Override
	public int getGeologicalFeature(World world, int pixelX,
			int pixelY) throws ModelException {
		return world.getGeologicalfeature(pixelX,pixelY);
	}
	@Override
	public void setGeologicalFeature(World world, int tileX,
			int tileY, int tileType) {
		world.setGeologicalfeature(tileX,tileY,tileType);
		
	}
	@Override
	public void setMazub(World world, Mazub alien) {
		world.setMazub(alien);
		
	}
	@Override
	public boolean isImmune(Mazub alien) {
		return alien.isImmune();
	}
	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		Plant plant = new Plant(x,y,sprites);
		return plant;
	}
	@Override
	public void addPlant(World world,Plant plant) {
		if(plant instanceof Plant)
		world.addPlant(plant);
		
	}
	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getCollectionPlant();
	}
	@Override
	public int[] getLocation(Plant plant) {
		return this.makeArray(plant.getPixelLeftX(), plant.getPixelBottomY());
	}
	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}
	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		Shark shark = new Shark(x,y,sprites);
		return shark;
	}
	@Override
	public void addShark(World world,Shark shark) {
		world.addSharkToCollection(shark);
		
	}
	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getCollectionOfSharks();
	}
	@Override
	public int[] getLocation(Shark shark) {
		return this.makeArray(shark.getPixelLeftX(), shark.getPixelBottomY());
	}
	
	@Override
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}
	@Override
	public School createSchool() {
		School school = new School();
		return school;
	}
	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites,School school) {
	     Slime slime = new Slime(x,y,sprites,school);
		return slime;
	}
	@Override
	public void addSlime(World world,Slime slime) {
		world.addSlime(slime);
		
	}
	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getCollectionOfSlimes();
	}
	
	@Override
	public int[] getLocation(Slime slime) {
		return this.makeArray(slime.getPixelLeftX(), slime.getPixelBottomY());
	}
	
	@Override
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
		
	}
	@Override
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}

	@Override
	public Buzam createBuzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		Buzam buzam = new Buzam(pixelLeftX, pixelBottomY, sprites);
		return buzam;
	}

	@Override
	public Buzam createBuzamWithProgram(int pixelLeftX, int pixelBottomY,
			Sprite[] sprites, Program program) {
		Buzam buzam = new Buzam(pixelLeftX, pixelBottomY, sprites, program);
		return buzam;
	}

	@Override
	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites,
			Program program) {
		Plant plant = new Plant(x, y, sprites, program);
		return plant;
	}

	@Override
	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites,
			Program program) {
		Shark shark = new Shark(x, y, sprites, program);
		return shark;
	}

	@Override
	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites,
			School school, Program program) {
		Slime slime = new Slime(x, y, sprites, school, program);
		return slime;
	}

	@Override
	public ParseOutcome<?> parse(String text) {
		 IProgramFactory<Expression, Statement, Type, Program> factory = new programFactory();	
		  ProgramParser<Expression, Statement, Type, Program> parser = new ProgramParser<Expression,Statement,Type,Program>(factory);
		  Optional<Program> parseResult = parser.parseString(text);
		  Program program = parseResult.get();
		  if(!parseResult.isPresent()){
 			 return ParseOutcome.failure(parser.getErrors());
		 }
		  else 
		     return ParseOutcome.success(program);
	}

	@Override
	public boolean isWellFormed(Program program) {
		return program.isWellFormed();
	}

	@Override
	public void addBuzam(World world, Buzam buzam) {
		world.addBuzam(buzam);
	}

	@Override
	public int[] getLocation(Buzam alien) {
		return this.makeArray(alien.getPixelLeftX(), alien.getPixelBottomY());
	}

	@Override
	public double[] getVelocity(Buzam alien) {
		return this.makeArray(alien.getVelocityX(), alien.getVelocityY());
	}

	@Override
	public double[] getAcceleration(Buzam alien) {
		return makeArray(alien.getAccelerationX(), alien.getAccelerationY());
	}

	@Override
	public int[] getSize(Buzam alien) {
		return makeArray(alien.getCurrentSprite().getWidth(), alien.getCurrentSprite().getHeight());
	}

	@Override
	public Sprite getCurrentSprite(Buzam alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public int getNbHitPoints(Buzam alien) {
		return alien.getHitPoints();
	}

}
