package jumpingalien.model;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
/**
 * A class of world , involving alien, plant,Buzam and slimes.
 * @author Afraz Salim & Niels van Belle
 * version 2.0
 * 
 * @invar The given object should be an effective object.
 *        | canHaveAsObject(WorldObjects object)
 * @invar Total number of objects in the game world must be less than or equal to 100.
 *        |(this.getNumberOfObjects() < 100)
 * @invar The given Tiles should be valid tiles in order to set.
 *        |isValidNumberOfTile(int nbTiles)
 * @invar The given tileSize of the tiles should be a valid TileSize.
 *        |isvalidTileSize()
 * @invar The given buzam instance should not be null.
 *        |canHaveAsObject(buzam)
 * @invar The given Alien should not be null.
 *        |canHaveAsAlien(alien)
 */
public class World implements Cloneable {
	private int visibleWindowWidth;
	private int visibleWindowHeight;
	private int nbTilesx;
	private int tileSize;
	private int nbTilesY;
	private int[][] tilesType;
	private ArrayList<Plant> plantCollection;
	private ArrayList<Shark> sharkCollection;
	private ArrayList<Slime> slimeCollection;

	/**
	 * Initializes this new World class with given, tileSize,nbTiles on x co-ordinate ,number of tiles on y co-ordinate,
	 * visibleWindow width, visibleWindowheight and final tiles , targetTileX and targetTileY.
	 * @param tileSize
	 *        The given tile size of the world.
	 * @param nbTilesX
	 *        Number of tiles on X-co-ordinate.
	 * @param nbTilesY
	 *        Number of tiles on y-co-ordinate.
	 * @param visibleWindowWidth
	 *        Given Width of the visible window.
	 * @param visibleWindowHeight
	 *        Given height of the visible window.
	 * @param targetTileX
	 *        The given final x tile as target tile.
	 * @param targetTileY
	 *        The given final y tile as target tile Y.
	 * @post. The tile size is equal to the given tile size.
	 *        |new.getTileSoze() == tileSize.
	 * @post. Number of tiles on x-co are equal to the given, new number of tiles on xCo.
	 *        |new.getNumberTileXco() = nbTilesX
	 * @post. Visible window width is equal to the given visible window width.
	 *        |new.getVisibleWindowWidth() = visibleWindowWidth
	 * @post. Visible window height is equal to the new given visible window height.
	 *        |new.getVisibleWindowHeight() = visibleWindowHeight()
	 * @post. X co-ordinate of the target Tile will be equal to the given x co-ordinate of the
	 *        target tile.
	 *        |this.setTargetTileX(targetTileX)
	 * @post. Y co-ordinate of the target tile will be equal to the given value of target tile.
	 *        |this.setTargetTileY(targetTileY);
	 * @post. Initializes a new arrayList for plants.
	 *        |plantCollection = new ArrayList<Plant>()
	 * @post. Initializes a new ArrayList for Sharks.
	 *        |sharkCollection = new ArrayList<Shark>()
	 * @post. Initializes a new ArrayList for slimes.
	 *        |slimeCollection = new ArrayList<Slime>()  
	 */
	@Raw
	public World(int tileSize, int nbTilesX, int nbTilesY,int visibleWindowWidth, int visibleWindowHeight, int targetTileX, int targetTileY) {
		assert(isValidTileSize(tileSize));
		this.setTileSize(tileSize);
		assert(isValidNumberOfTile(nbTilesX));
		this.setNbTilesX(nbTilesX);
		assert(isValidNumberOfTile(nbTilesY));
		this.setNbTilesY(nbTilesY);
		this.setVisibleWindowWidth(visibleWindowWidth);
		this.setVisibleWindowHeight(visibleWindowHeight);
		tilesType = new int[this.getNbTilesX()][this.getNbTilesY()];
		this.setTargetTileX(targetTileX);
		this.setTargetTileY(targetTileY);
		plantCollection = new ArrayList<Plant>();
		sharkCollection = new ArrayList<Shark>();
		slimeCollection = new ArrayList<Slime>();

	}

	

	/**
	 * A getter to get the X co-ordinate of the target tile.
	 * @return
	 *       Return the X co-ordinate of the target tile.
	 *       |result = (this.targetTileX)
	 */
	@Basic
	public int getTargetTilX(){
		return this.targetTileX;
	}
	
	/**
	 * A getter to get the y co-ordinate of the target tile.
	 * @return
	 *       Returns the y co-ordinate of the target tile.
	 *       |result = (this.targetTileY)
	 */
	@Basic
	public int getTargetTileY(){
		return this.targetTileY;
	}
	/**
	 * A private variable to store the value of targetTileY
	 */
	private int targetTileY;
	
	/**
	 * A setter to set the value of targetTileY
	 * @param targetTileY
	 *        The given y co-ordinate of the targetTile.
	 * @post. The new value of targetTileY will be equal to the given value.
	 *        |new.targetTileY = targetTileY
	 */
	private void setTargetTileY(int targetTileY) {
       this.targetTileY = targetTileY;		
	}

	/**
	 * A private variable to store the value of targetTileX
	 */
	private int targetTileX;
	/**
	 * A setter to set the targetTileX.
	 * @param targetTileX
	 *        The give x co-ordinate of targetTile.
	 * @post. The value of targetTileX will be equal to the given value.
	 *        |new.targetTileX = targetTileX
	 */
	private void setTargetTileX(int targetTileX) {
		this.targetTileX = targetTileX;
	}

	/**
	 * A getter to get number of tiles on yCo.
	 * @return
	 *      Returns number of tiles on y co-ordinate.
	 *      |result = (nbTilesY)
	 */
	@Basic
	public int getNbTilesY() {
		return nbTilesY;
	}

	/**
	 * Sets the number of tiles on yCo equal to the new given number of tiles.
	 * @param nbTilesY
	 *        The given number of tiles to initialize the number of tiles on yCo.
	 * @post. New number of tiles on yCo are equal to the given number of tiles on yCo.
	 *        |new.nbTilesY = nbTilesY
	 */
	private void setNbTilesY(int nbTilesY) {
		this.nbTilesY = nbTilesY;
	}

	/**
	 * A getter to get the tile size of the world.
	 * @return
	 *        Return the given tile size of the world.
	 *        |result = (tileSize)
	 */ 
	@Basic @Immutable
	public int getTileSize() {
		return tileSize;
	}

	/**
	 * Set the tile size of the world equal to the given tileSize.
	 * @param tileSize
	 *        The new tileSize to initialize the old variable.
	 *        |new.tileSize = tileSize.
	 */
	@Immutable
	private void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	/**
	 * A getter to get the number of tiles on X Co-ordinate.
	 * @return
	 *        Returns the number of tiles on x coordinate.
	 *        |result = (nbTilesX)
	 */
	@Basic @Immutable
	public int getNbTilesX() {
		return nbTilesx;
	}

	/**
	 * Sets the number of tiles on X co-ordiante equal to the given number of tiles on XCo.
	 * @param nbTilesx
	 *        A given parameter holding the value of number of tiles on X Co-ordiante.
	 * @post. The new number of tiles on x co-ordiante are equal to the given number of tiles 
	 *        on xCo.
	 *        |new.nbTilesx = nbTilesx.
	 */
	@Immutable
	public void setNbTilesX(int nbTilesx) {
		this.nbTilesx = nbTilesx;
	}
    
	/**
	 * A getter to get the height of the visible window.
	 * @return
	 *     Returns the visible height of the window. 
	 *     |result = (visibleWindowheight)  
	 */
	@Basic
	public int getVisibleWindowHeight() {
		return visibleWindowHeight;
	}

	/**
	 * Set the visible window height equal to the given height of the visible window.
	 * @param visibleWindowHeight
	 *        The new value for visible height of the window.
	 * @post. The new value of visible window height is equal to the given value.
	 *        |new.visibleWindowheight = visibleWindowheight
	 */
	private void setVisibleWindowHeight(int visibleWindowHeight) {
		this.visibleWindowHeight = visibleWindowHeight;
	}

	/**
	 * A getter to get the width of the visible widow.
	 * @return
	 *       Returns the width of the visible window.
	 *       |result = (visibleWindowWidth)
	 */
	@Basic
	public int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}

	/**
	 * Sets the visible width of the window equal to the given value.
	 * @param  visibleWindowWidth
	 *         A variable holding the new value for the width of the visible window.
	 * @post.  The new value of the visible Window width is equal to the given value.
	 *         |new.visibleWindowWidth = visibleWindowWidth
	 */
	private void setVisibleWindowWidth(int visibleWindowWidth) {
		this.visibleWindowWidth = visibleWindowWidth;
	}
	
	/**
	 * A checker to check whether the tile size is valid or not.
	 * @param tileSize
	 *        The given tileSize to be checked.
	 * @return
	 *        Returns true if and only if the given tileSize is greater than 0.
	 *        |result = (tileSize >= 1)
	 */
	private boolean isValidTileSize(int tileSize) {
        return tileSize >= 1 && tileSize < Double.POSITIVE_INFINITY;
	}
	
	/**
	 * A getter to get the width in pixels of all the tiles on x co-ordiante.
	 * @return
	 *       Returns the Number of Pixels of all tiles on X Co-ordinate.
	 *       |result = (this.getNbTilesX()*this.getTileSize())
	 */
	@Basic @Immutable
	public int getWidthInPixel() {
		return this.getNbTilesX()*this.getTileSize();
	}
	
	
    /**
     * A checker to check whether the given tile is a valid tile or not.
     * @param nbTiles
     *        The given number of tiles.
     * @return
     *        Returns true if and only if the given number of the tiles are valid tiles.
     *        |result = (nbTiles > 1)
     */
	private boolean isValidNumberOfTile(int nbTiles) {
		return nbTiles > 1 && nbTiles < Double.POSITIVE_INFINITY;
	}

	/**
	 * A getter to get the height in pixels of all the tiles on y co-ordiante.
	 * @return
	 *       Returns the Number of Pixels of all tiles on Y Co-ordinate.
	 *       |result = (this.getNbTilesY()*this.getTileSize())
	 */
	@Basic @Immutable
	public int getHeightInPixel() {
		return this.getNbTilesY()*this.getTileSize();
	}

	
	/**
	 * A priavte variable to initialize the mazub.
	 */
	private  Mazub mazub;
	
//	/**
//	 * A method which advances time iteratively.
//	 * @param   dt
//	 *          The given time to update the time.
//	 * @effect  Advances the time of the alien.
//	 *          |this.getMazub().advanceTime(dt,this)
//	 * @effect  A loop to advance the time of all the plants.
//	 *          |for (Plant plant: this.getCollectionPlant());
//	 *          | plant.advanceTimePlant(dt, this)
//	 * @effect  A loop to advance the time of all the slimes.
//	 *          |for (Slime slime: this.getCollectionOfSlimes())
//	 *          |slime.advanceTimeSlime(dt, this);
//	 * @effect  A loop to advance the time of all the sharks.
//	 *          |for (Shark shark: this.getCollectionOfSharks())
//	 *          |shark.advanceTimeShark(dt, this);
//	 *         
//	 */
//	public void advanceTime2(double dt) {
//		if((this.isPlayerWon())) {
//	    	this.setIsGameOver(true);
//	    	this.terminate();
//		}
//	    this.getMazub().advanceTimeMazub(dt,this);
//	    for (Plant plant: this.getCollectionPlant())
//	    	plant.advanceTimeWithProgram(dt, this);
//	    for (Slime slime: this.getCollectionOfSlimes())
//	    	slime.advanceTimeWithProgram(dt, this);
//	    for (Shark shark: this.getCollectionOfSharks())
//	    	shark.advanceTimeWithProgram(dt, this);
//	    Buzam buzam = this.getBuzam();
//	    if (!buzam.isTerminated())
//	    	buzam.advanceTimeWithProgram(dt, this);
//	   }
	
	/**
	 * A method which advances time iteratively.
	 * @param   dt
	 *          The given time to update the time.
	 * @effect  Advances the time of the alien.
	 *          |this.getMazub().advanceTime(dt,this)
	 * @effect  A loop to advance the time of all the plants.
	 *          |for (Plant plant: this.getCollectionPlant());
	 *          | plant.advanceTimePlant(dt, this)
	 * @effect  A loop to advance the time of all the slimes.
	 *          |for (Slime slime: this.getCollectionOfSlimes())
	 *          |slime.advanceTimeSlime(dt, this);
	 * @effect  A loop to advance the time of all the sharks.
	 *          |for (Shark shark: this.getCollectionOfSharks())
	 *          |shark.advanceTimeShark(dt, this);
	 *         
	 */
	public void advanceTime(double dt) {
		if((this.isPlayerWon())) {
	    	this.setIsGameOver(true);
	    	this.terminate();
		}
		for (WorldObjects object: this.getCollectionLiving()) {
			object.advanceTimeWithProgram(dt, this);
		}
	}

	/**
	 * A getter to get the alien.
	 * @return
	 *       Returns the alien.
	 *       |result = (this.mazub)
	 */
	public Mazub getMazub(){
		return this.mazub;
	}

	/**
	 * Initializes the mazub with given value of the alien.
	 * @param  alien
	 *         The new variable holding the reference to the mazub.
	 * @post.  The new mazub will be equal to the given mazub.
	 *         |new.mazub = alien.
	 */
	public void setMazub(Mazub alien) {
		if(!(canHaveAsAlien(alien)))
			throw new NullPointerException("alien cannot be null");
		if (!this.isGameStarted() &&
				alien.isValidePixelLeftX(alien.getPixelLeftX()) && alien.isValidePixelBottomY(alien.getPixelBottomY()))
			this.mazub = alien;
	}

	/**
	 * A checker to check whether the objects is not null.
	 * @param alien
	 *        The given obejct,s reference to be checked.
	 * @return
	 *        Returns true if and only if the object is not null.
	 *        |result = (alien != null)
	 */
	private boolean canHaveAsAlien(Mazub alien) {
		if (this.isTerminated)
			return alien == null;
		return alien != null;
	}

	private boolean hasProperAlien() {
		return this.canHaveAsAlien(this.getMazub()) && (this.getMazub() == null || this.getMazub().getWorld() == this);
	}

	/**
	 * A checker to check whether the corresponding pixels belong to the impassable tile.
	 * @param pixelLeftX
	 *        Left pixel X of the object to check whether the tiles are Impassable.
	 * @param pixelBottomY
	 *        Left pixel Y of the object to check whether the tiles are impassable.
	 * @return
	 *        Returns true if and only if the corresponding pixels belong to the impassable tiles.
	 *        |int result = this.getGeologicalfeature(pixelLeftX, pixelBottomY)
	 *        |if(result == 1)
	 *        |return true;
	 */
	@Immutable
	public boolean isImpassablePassAble(int pixelLeftX, int pixelBottomY) {
		int result = this.getGeologicalfeature(pixelLeftX, pixelBottomY);
		if(result == 1)
			return true;
		else 
			return false;
	}

 
	/**
	 * A getter to get the left pixel on the x co-ordinate.
	 * @return
	 *        Returns the left pixel X of the alien.
	 *        |result = (this.getMazub().getPixelLeftX())
	 */
	@Basic
	public int getLeftPixelX()  {
		return this.getMazub().getPixelLeftX();
	}
	
	/**
	 * A getter to get the left pixel of the object on y co-ordinate.
	 * @return
	 *       Returns the left pixel of the y co-ordinate of the object.
	 *       |result = (this.getMazub().getPixelBottomY())
	 */
	@Basic
	public int getLeftPixelY(){
		return this.getMazub().getPixelBottomY();
	}

	
	/**
	 * A getter to the visible window which moves with the alien.
	 * @post. Initializes a new array for four elements.
	 *        |int [] array = new int [4]
	 * @post. Computes the center of the given visible window width.
	 *        |int halfVisibleWindowWidth = this.getVisibleWindowWidth()/2
	 * @post. Computes the center of the given visible window height.
	 *        |int halfVisibleWindowHeight = this.getVisibleWindowHeight()/2
	 * @post. if the pixelX of the alien is less than the half of the given visibleWindowwidth
	 *        then 0 will be stored as left pixel.
	 *        |if (pixelX < halfVisibleWindowWidth)
	 *        |array[0] = 0
	 * @post. if the difference between the left pixel and visible window width is smaller than the half width
	 *        of the window than window will be moved twice as half of the size of the window otherwise window will move
	 *        equal to the difference between pixel left X and half size of the window.
	 *        |else if (this.getWidthInPixel() - pixelX < halfVisibleWindowWidth)
	 *        |array[0] = this.getWidthInPixel() - 2*halfVisibleWindowWidth
	 *        |else
	 *        |array[0] = pixelX - halfVisibleWindowWidth
	 * @post. As long as the pixelBottomY is less than the half size of the window height zero will be stored 
	 *        and returned.
	 *        | if (pixelY < halfVisibleWindowHeight)
	 *        |array[1] = 0
	 * @post. if the difference between the bottom pixel and visible window height is smaller than the half height
	 *        of the window than window will be moved twice as half of the size of the window height otherwise window will move
	 *        equal to the difference between pixelbottomY and half size of the window height.
	 *        |else if (this.getHeightInPixel() - pixelY < halfVisibleWindowHeight)
	 *        |array[1] = this.getHeightInPixel() - 2*halfVisibleWindowHeight;
	 *        |else
	 *        |array[1] = pixelY - halfVisibleWindowHeight;
	 * @post. Window,s visible width will be equal to the pixelLeftX + twice as half of the window width.
	 *        |array[2] = array[0] + 2*halfVisibleWindowWidth
	 * @post. Window,s visible height will be equal to the pixelBottomY+ twice as half of the window height.
	 *        |array[3] = array[1] + 2*halfVisibleWindowHeight
	 * @return
	 *        Returns an array consisting the bottomLeftPixelX, bottomPixelY,rightPixel and the topPixel respectively.
	 *        |result = (array)
	 */
	 @Basic
	public int[] getVisibleWindowWindow() {
        int [] array = new int [4];
        int halfVisibleWindowWidth = this.getVisibleWindowWidth()/2;
        int halfVisibleWindowHeight = this.getVisibleWindowHeight()/2;
        int pixelX = this.getMazub().getPixelLeftX() + this.getMazub().getCurrentSprite().getWidth()/2;
        int pixelY = this.getMazub().getPixelBottomY() + this.getMazub().getCurrentSprite().getHeight()/2;
        if (pixelX < halfVisibleWindowWidth)
    		array[0] = 0;
        else if (this.getWidthInPixel() - pixelX < halfVisibleWindowWidth)
        	array[0] = this.getWidthInPixel() - 2*halfVisibleWindowWidth;
        else 
        	array[0] = pixelX - halfVisibleWindowWidth;
        if (pixelY < halfVisibleWindowHeight)
    		array[1] = 0;
        else if (this.getHeightInPixel() - pixelY < halfVisibleWindowHeight)
        	array[1] = this.getHeightInPixel() - 2*halfVisibleWindowHeight;
        else 
        	array[1] = pixelY - halfVisibleWindowHeight;
		array[2] = array[0] + 2*halfVisibleWindowWidth;
		array[3] = array[1] + 2*halfVisibleWindowHeight;
		return array;
	}

	/**
	 * A getter to get the bottom left pixel of the given tile.
	 * @param tileX
	 *        The x co-ordinate of the given tile.
	 * @param tileY
	 *        The y co-ordinate of the given tile.
	 * @return
	 *        Returns an array consisting number of pixels on xCo and number of pixels
	 *        on the y co-ordinate of the given tile respectively.
	 *        |array[0] = NumberOfPixelOnXCo
	 *        |array[1] = NumberOfPixelOnYco
	 *        |result = (array)
	 */
	public int[] getBottomleftPixelOfTile(int tileX, int tileY) {
		int [] array = new int[2];
		int NumberOfPixelOnXCo = tileX*this.getTileSize();
		int NumberOfPixelOnYco = tileY*this.getTileSize();
		array[0] = NumberOfPixelOnXCo;
		array[1] = NumberOfPixelOnYco;
		return array;
	}

	/**
	 * Returns the positions of all the tiles between the given pixels in the given world.
	 * @param pixelLeft
	 *        Bottom left pixel of the visible window.
	 * @param pixelBottom
	 *        Bottom pixel y of the visible window.
	 * @param pixelRight
	 *        Right pixel of the visible window.
	 * @param pixelTop
	 *        Top pixel of the given visible window.
	 * @return
	 *        Returns the tiles positions of all tiles of the pixels between the given range from
	 *        the bottom to top and from right to left respectively.
	 *        |array[index][0] = vertical
	 *        |array[index][1] = horizontal
	 *        |result = (array)
	 */
	public int[][] getTilePositionsIn(int pixelLeft, int pixelBottom,
			int pixelRight, int pixelTop) {
			int leftTile = pixelLeft/this.getTileSize();
			int rightTile = pixelRight/this.getTileSize();
			int bottomTile = pixelBottom/this.getTileSize();
			int topTile = pixelTop/this.getTileSize();
			int index = 0;
			int array [][] = new int[Math.abs((rightTile-leftTile+1)*(topTile-bottomTile+1))][2];
			for(int horizontal = bottomTile; horizontal <= topTile;horizontal++){
		    	for(int vertical = leftTile;vertical <= rightTile;vertical++){
		    		array[index][0] = vertical;
		    		array[index][1] = horizontal;
				    index++;
		    	}
		    }
		   return array;
	}

	/**
	 * Sets the geological feature of the given tiles.
	 * @param tileX
	 *        The x co-ordinate of the given tile.
	 * @param tileY
	 *        The y co-ordinate of the given tile.
	 * @param tileType
	 *        A variable containing the type of the tile.
	 * @pre.  The game must be started in order to set the geologicalfeatures.
	 *        |(this.isGameStarted())
	 * @post. Set the value of the tileTyp to the given tilType.
	 *        |new.tileType[tileX][tilesY] = tileType
	 */
	public void setGeologicalfeature(int tileX, int tileY, int tileType) {
	  if(!(this.isGameStarted()))
	   this.tilesType[tileX][tileY] = tileType;
	}
    
	/**
	 * A getter to get the geological feature of the given pixels.
	 * @param pixelX
	 *        The x co-ordinate of the given pixel.
	 * @param pixelY
	 *        The y co-ordinate of the given pixel.
	 * @post. Converts the given pixelX to the x co-ordiante of the tile.
	 *        |int tileX = pixelX/this.getTileSize()
	 * @post. Converts the given pixelY to the given y co-ordiante of the tile.
	 *        |int tileY = pixelY/this.getTileSize()
	 * @return
	 *        Returns the type of the tile.
	 *        |result = (this.tilesType[tileX][tileY])
	 */
	@Basic
	public int getGeologicalfeature(int pixelX, int pixelY) {
		int tileX = pixelX/this.getTileSize();
		int tileY = pixelY/this.getTileSize();
		return this.tilesType[tileX][tileY];
	}



	/**
	 * Adds the plants to the collection of the plants.
	 * @param plant
	 *        The new plant to be add in collection.
	 *        |this.addPlantToList(plant)
	 * @pre.  In order to add the plant to the plant list , the game must not be started.
	 *        |if(!(this.isGameStarted()))
	 */
	public void addPlant(Plant plant) {
		if(this.hasValidNumberOfObjects() && !(this.isGameStarted()) &&
				plant.isValidePixelLeftX(plant.getPixelLeftX()) && plant.isValidePixelBottomY(plant.getPixelBottomY())){
			this.plantCollection.add(plant);
			this.setNumberOfObjects(this.getNumberOfObjects() + 1);
			plant.setWorld(this);
		}
	}

	/**
	 * A getter to get the clone of the collection of the plants.
	 * @return
	 *       Returns the clone of the collection of the plants.
	 *       |result = ((Collection<Plant>) this.plantCollection.clone())
	 */
	@Basic
	public Collection<Plant> getCollectionPlant() {
		return (Collection<Plant>) this.plantCollection.clone();
	}

	public Collection<WorldObjects> getCollectionEnemies() {
		Collection<WorldObjects> enemies = new ArrayList<WorldObjects>(); 
		enemies.add(this.getMazub());
		if (!this.getBuzam().isTerminated())
			enemies.add(this.getBuzam());
		enemies.addAll(this.getCollectionOfSharks()) ;
		enemies.addAll(this.getCollectionOfSlimes()) ;
		return enemies;
	}
	
	public Collection<WorldObjects> getCollectionLiving() {
		Collection<WorldObjects> living = new ArrayList<WorldObjects>(); 
		living.add(this.getMazub());
		if (!this.getBuzam().isTerminated())
			living.add(this.getBuzam());
		living.addAll(this.getCollectionPlant()) ;
		living.addAll(this.getCollectionOfSharks()) ;
		living.addAll(this.getCollectionOfSlimes()) ;
		return living;
	}


	
	/**
	 * Return a clone of this World.
	 * 
	 * @return The resulting World is the same as this World
	 *         if and only if this World is immutable.
	 *       | (result == this) == (! this.isMutable())
	 */
	@Override
	public World clone() {
		try {
				return (World) super.clone();
		} catch (CloneNotSupportedException exc) {
			return null;
		}
	}
	
	
    /**
     * An adder to add the shark to the collection of the sharks.
     * @param shark
     *        The new sharks to be added to the collection of the sharks.
     * @pre . The total number of objects in the game world must always be less than 100.
     *        |this.hasValidNumberOfObjects()
     * @pre.  The given object should be shark object.
     *        |if(shark instanceof Shark)
     * @pre   The game must not be started in order to add sharks to the game world.
     *        |!(this.isGameStarted())
     * @post. Adds the sharks to the given list of the sharks.
     *        |this.sharkCollection.add(shark)
     * @post new.getNumberOfObjects() == this.getNumberOfObects() + 1
     */
	public void addSharkToCollection(Shark shark) {
		if(this.hasValidNumberOfObjects() && !(this.isGameStarted()) &&
				shark.isValidePixelLeftX(shark.getPixelLeftX()) && shark.isValidePixelBottomY(shark.getPixelBottomY())){
			this.sharkCollection.add(shark);
			this.setNumberOfObjects(this.getNumberOfObjects() + 1);
			shark.setWorld(this);
		}
	}

	/**
	 * A getter to get the collection of the sharks as clone.
	 * @return
	 *       Returns the clone of the collections of the sharks.
	 *       |result = ((Collection<Shark>) this.sharkCollection.clone())
	 */
	@Basic
	public Collection<Shark> getCollectionOfSharks() {
		return (Collection<Shark>) this.sharkCollection.clone();
	}

	/**
	 * An adder to add the slime to the collection of the slimes.
	 * @param slime
	 *        The new slime to be added to the given collection.
	 * @pre   The total number of the objects in  the game world must be less than 100.
	 *        |this.hasValidNumberOfObjects()
	 * @pre.  The game must not be started in order to add new objects to the game world.
	 *        |!(this.isGameStarted())
	 * @post. Adds the given slime to the list of the slimes.
	 *        |this.slimeCollection.add( slime)
	 * @post new.getNumberOfObjects() == this.getNumberOfObects() + 1
	 */
	public void addSlime(Slime slime) {
		if(this.hasValidNumberOfObjects() && !(this.isGameStarted()) &&
				slime.isValidePixelLeftX(slime.getPixelLeftX()) && slime.isValidePixelBottomY(slime.getPixelBottomY())){
			this.slimeCollection.add( slime);
			this.setNumberOfObjects(this.getNumberOfObjects() + 1);
			slime.setWorld(this);
		}
	}
    
	/**
	 * A checker to check whether the total number of objects in game world are less than 100.
	 * @return
	 *       Returns true if and only if the total number of objects in game world are less than 100.
	 *       |result = (this.getNumberOfObjects()+1 < 100)
	 */
	private boolean hasValidNumberOfObjects() {
		return this.getNumberOfObjects() <= 99;
	}



	/**
	 * A setter to set and update the number of object in the game world.
	 * @param numberOfObjects
	 * 			The new number of objects in the world
	 * @post new.getNumberOfObjects() == numberOfObjects
	 */
	private void setNumberOfObjects(int numberOfObjects) {
		this.numberOfObjects = numberOfObjects;
	}

	/**
	 * A getter to get he total number of the objects in the world.
	 * @return
	 *       Returns the total number of the objects in the given world.
	 *       |result = (this.numberOfObjects)
	 */
	private int getNumberOfObjects() {
		return this.numberOfObjects;
	}

	/**
	 * Variable registering the current number of objects in the world
	 */
	private int numberOfObjects;
	
	/**
	 * A checker to check whether total number of objects are valid objectS.
	 * @return
	 *       Return true if and only if the total number of objects are less than or equal to
	 *       100.
	 *       |result = (this.getNumberOfObjects() < 100)
	 */
	private boolean isValidNumberOfObjects() {
		return this.getNumberOfObjects() < 100;
	}
	
	/**
	 * A checker to check whether an object is a valid object.
	 * @param object
	 *        The given object to be checked.
	 * @return
	 *        Returns true if and only if the total number of the objects are valid 
	 *        and the given object does not hold a null reference.
	 *        |result = (this.isValidNumberOfObjects() && object != null)
	 */
	public boolean canHaveAsObject(WorldObjects object) {
		if (this.isTerminated())
			return object == null;
		return this.isValidNumberOfObjects() && object != null;
	}
	
	/**
	 * 
	 * @return for each object in this.getCollectionLiving()
	 * 				do if (!(this.canHaveAsObject(object) && (object == null || object.getWorld() == this)))
	 * 					then result == false
	 * 			result == true
	 */
	private boolean hasProperObject() {
		for (WorldObjects object: this.getCollectionLiving()) {
			if (!(this.canHaveAsObject(object) && (object == null || object.getWorld() == this)))
				return false;
		}
		return true;
	}

	/**
	 * A getter to get the collection of the slimes.
	 * @return
	 *       Returns the collection of the slimes as clone.
	 *       |result = ((Collection<Slime>) this.slimeCollection.clone())
	 */
	@Basic
	public Collection<Slime> getCollectionOfSlimes() {
		return (Collection<Slime>) this.slimeCollection.clone();
	}

	/**
	 * A setter to set the given object as invisible.
	 * @param  object
	 *         The new given object to be removed from the world.
	 * @effect. if the given object is instanceof the plant then it will be removed from
	 *          the collection of the plant.
	 *          |if(object instanceof Plant)
	 *          |this.plantCollection.remove(object)
	 * @effect. If the given object is instanceof the slime then it will be 
	 *          removed from the given world.
	 *          |if(object instanceof Slime)
	 *          |this.slimeCollection.remove(object)
	 * @effect. If the given object is instanceof the shark then it will be 
	 *          removed from the given world.
	 *          |if(object instanceof Shark)
	 *          |this.sharkCollection.remove(object)
	 * @post.   Set the reference to null
	 *          |object = null
	 * @effect  After removing the object from the collection , the total number of the objects will
	 *          decreased with factor 1.
	 *          |new.getNumberOfObjects() == this.getNumberOfObects() - 1
	 */
	public void setInvisibleWorldObject(WorldObjects object) {
		if(object instanceof Plant){
			this.plantCollection.remove(object);
			object = null;
		}
		else if(object instanceof Slime){
			this.slimeCollection.remove(object);
			object = null;
		}
		else if (object instanceof Shark){
			this.sharkCollection.remove(object);
			object = null;
		}
		else if (object instanceof Buzam){
		}
		else if (object instanceof Mazub) {
			this.setIsGameOver(true);
			this.terminate();
			object = null;
		}
	}

	/**
	 * A checker to check whether the game is over.
	 * @return
	 *       Returns true if and only if the game is over.
	 *       |result = (this.GameOver())
	 */
	public boolean isGameOver() {
		return this.GameOver();
	}
	
	/**
	 * A setter to set if the game is over.
	 * @param gameOver
	 *        The new value of the game to be checked if the game is over.
	 * @post. The value of the isGameOver will be set equal to the given value
	 *        |this.isGameOver= gameOver
	 */
	private void setIsGameOver(boolean gameOver){
		this.isGameOver = gameOver;
	}

	/**
	 * A boolean variable to store the value of game over.
	 */
	private boolean isGameOver;
	
	/**
	 * A boolean checker to check whether the game is over or not.
	 * @return
	 *      Returns true if and only if the game is over.
	 *      |result = (this.isGameOver)
	 */
	private boolean GameOver() {
		return this.isGameOver;
	}

	/**
	 * A checker to check whether the tiles corresponding to the given pixels, represent water.
	 * @param pixelLeftX
	 *        The given left pixel of the object.
	 * @param pixelBottomY
	 *        The given bottom y pixel of the object.
	 * @return
	 *       Returns true if and only if the the corresponding pixels represent a tile containing water.
	 *       |int result = this.getGeologicalfeature(pixelLeftX, pixelBottomY)
	 *       | if(result == 2 )
	 *       | result = (true)
	 */
	public boolean isWater(int pixelLeftX, int pixelBottomY) {
		int result = this.getGeologicalfeature(pixelLeftX, pixelBottomY);
       if(result == 2 )
    	   return true;
		return false;
	}

	/**
	 * A checker to check whether the given pixels represent a tile containing the magma.
	 * @param pixelLeftX
	 *        The pixel left X of the object.
	 * @param pixelBottomY
	 *        The pixel bottom y of the object.
	 * @return
	 *        Returns true if and only if the tile corresponding the pixels represent magma.
	 *        |int result = this.getGeologicalfeature(pixelLeftX, pixelBottomY)
	 *        | if(result == 3 )
	 *        |result = (true)
	 */
	public boolean isMagma(int pixelLeftX, int pixelBottomY) {
		int result = this.getGeologicalfeature(pixelLeftX, pixelBottomY);
       if(result == 3 )
    	   return true;
		return false;
	}
 
	/**
	 * A checker to check whether the player won or not.
	 * @return
	 *        Returns true if and only if the player,s x and y co-ordinates are equal to the 
	 *        alien,s co-ordinates.
	 *        |if(this.getTargetTilX() == tileX && this.getTargetTileY() == tileY)
	 *        |return true;
	 */
	public boolean isPlayerWon() {
		int tileX = this.getMazub().getPixelLeftX()/this.getTileSize();
		int tileY = this.getMazub().getPixelBottomY()/this.getTileSize();
		if(this.getTargetTilX() == tileX && this.getTargetTileY() == tileY)
			return true;
		return false;
	}
	
	/**
	 * After invoking this method game will be started.
	 * @post. The new state isGameStarted will be equal to the given value.
	 *        |this.setGameStarted(true);
	 */
	public void startGame() {
		this.setGameStarted(true);
	}


	/**
	 * A private boolean variable to store the value of isGameStarted.
	 */
	private boolean isGameStarted;


   
	/**
	 * A checker to check whether game is started or not.
	 * @return
	 *       Returns true if and only if the game has been started.
	 *       |result = (this.isGameStarted)
	 */
	public boolean isGameStarted() {
		return this.isGameStarted;
	}

    /**
     * A setter to set the set the game started.
     * @param isGameStarted
     *        The given parameter to update the current state of the game.
     * @post. The new value of the isGameStarted will be equal to the given
     *        value.
     *        |isGameStarted() = isGameStarted
     */
	public void setGameStarted(boolean isGameStarted) {
		this.isGameStarted = isGameStarted;
	}



	/**
	 * An adder to add the buzam to this world.
	 * @param buzam
	 *        The given instance of the buzam.
	 * @throws Throws NullpOinterException if the buzam is null.
	 *         |if(!(canHaveAsObject(buzam)))
	 *         |throw new NullPointerException("Buzam cannot be null");
	 * @post.  The new Buzam will be equal to the old.
	 *         new.buzam = buzam.
	 */
	public void addBuzam(Buzam buzam) {
		if(!(canHaveAsObject(buzam)))
			throw new NullPointerException("Buzam cannot be null");
		this.buzam = buzam;
	}

	/**
	 * A getter to get the buzam.
	 * @return
	 *      Returns the buzam.
	 *      |result = (this.buzam)
	 */
	public Buzam getBuzam(){
		return this.buzam;
	}
	
	/**
	 * A private variable to register the value of buzam.
	 */
	private Buzam buzam;
	
	/**
	 * 
	 * Return the termination of the world
	 */
	@Basic
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * 
	 * @param terminated
	 * 			The new termination of the world
	 * @post new.isTerminated() == terminated
	 * 
	 */
	private void setIsTerminated(boolean terminated) {
		this.isTerminated = terminated;
	}
	
	/**
	 * Variable storing the termination of the world
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
