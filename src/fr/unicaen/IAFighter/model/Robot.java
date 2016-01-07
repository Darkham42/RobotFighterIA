package fr.unicaen.IAFighter.model;

import fr.unicaen.IAFighter.model.strategy.RandomStrategy;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import fr.unicaen.IAFighter.model.strategy.IStrategy;

/**
 * Represente un robot
 */
public class Robot extends GameObject{
	private IStrategy strategy;
	private int energie, laserSize;
	private boolean hasShield, hasLaserFire;
	private int energieMax;
	
	/**
	 * 
	 * Constructeur de Robot.java
	 * @param model
	 */
	public Robot(GameBoard model) {
		super(model);
		this.setTypeObject(TypeObject.robot);
		this.strategy = new RandomStrategy();
		this.energie = 10;
		this.energieMax = 10;
		this.laserSize = 3;
		this.hasShield = false;
		this.hasLaserFire = false;
	}
	
	/**
	 * Execute la strategie utiliser par ce Robot
	 */
	public void action(){
		if(GameRule.infiniteEnergy)
			this.energie = this.energieMax;
		
		if(this.isDead())
			return;
		
		this.hasShield = false;
		
		this.strategy.execute(this);
		
		this.energie--;
	}
	
	/**
	 * Met a jour l'energie max du Robot
	 * @param energie
	 */
	public void setEnergie(int energie){
		this.energie = energie;
		this.energieMax = energie;
	}
	
	/**
	 * Met a jour la portee du laser
	 * @param laserSize la nouvelle portee du laser
	 */
	public void setLaserSize(int laserSize){
		this.laserSize = laserSize;
	}
	
	/**
	 * Lit la porter du laser
	 * @return
	 */
	public int getLaserSize(){
		return this.laserSize;
	}
	
	/**
	 * Lit l'energie du Robot d
	 * @return
	 */
	public int getEnergie(){
		return this.energie;
	}
	
	/**
	 * Met a jour la strategie du Robot
	 * @param strategy Une strategie Abstraite
	 */
	public void setStrategy(IStrategy strategy){
		this.strategy = strategy;
	}
	
	/**
	 * Tire un laser
	 */
	public void laserFire(){
		if(this.energie <= 0)
			return;
		
		this.hasLaserFire = true;
		
		for(int i = 1 ; i <= this.laserSize ; i++){
			int laserRow = this.getRow() + (i * this.direction.getRow());
			int laserCol = this.getCol() + (i * this.direction.getCol());
			if(this.getModel().inGrid(laserRow, laserCol))
				this.getModel().getBoard()[laserRow][laserCol].setLaserOn();
			else
				return;
		}
	}
	
	/**
	 * Efface le laser tirer
	 */
	public void stopLaserFire(){
		this.hasLaserFire = false;
		
		for(int i = 1 ; i <= this.laserSize ; i++){
			int laserRow = this.getRow() + (i * this.direction.getRow());
			int laserCol = this.getCol() + (i * this.direction.getCol());
			if(this.getModel().inGrid(laserRow, laserCol)){
				this.getModel().getBoard()[laserRow][laserCol].setLaserOff();
			}
			else return;
		}
	}
	
	/**
	 * Tire un missile
	 */
	public void missileFire(){
		if(this.energie <= 0)
			return;
		
		Missile missile = new Missile(this.getModel(), this.direction, this);
		this.getModel().addGameObject(missile);
	}
	
	@Override
	public String getStrategyName() {
		return this.strategy.getName();
	}
	
	@Override
	public void forward(){
		if(this.energie <= 0)
			return;
		
		int row = this.getRow() + this.getDirection().getRow();
		int col = this.getCol() + this.getDirection().getCol();
		
		if(this.getModel().inGrid(row, col)){
			if(this.getModel().getBoard()[row][col].numberOf(TypeObject.robot) != 0)
				return;
			
			this.getModel().getBoard()[row][col].setContent(this);
			this.getModel().getBoard()[this.getRow()][this.getCol()].removeContent(this);
			this.updatePosition(row, col);
		}
	}
	
	/**
	 * Recule le robot
	 */
	public void backward(){
		if(this.energie <= 0)
			return;
		
		Direction d = Direction.values()[(this.getDirection().ordinal() + 2) % 4];
		
		int row = this.getRow() + d.getRow();
		int col = this.getCol() + d.getCol();
		
		if(this.getModel().inGrid(row, col)){
			if(this.getModel().getBoard()[row][col].numberOf(TypeObject.robot) != 0)
				return;
			
			this.getModel().getBoard()[row][col].setContent(this);
			this.getModel().getBoard()[this.getRow()][this.getCol()].removeContent(this);
			this.updatePosition(row, col);
		}
	}
	
	/**
	 * Fais tourner le robot vers la gauche
	 */
	public void turnLeft(){
		if(this.energie <= 0)
			return;
		
		this.direction = Direction.values()[(this.direction.ordinal() + 3) % 4];
	}
	
	/**
	 * Fais tourner le robot vers la droite
	 */
	public void turnRight(){
		if(this.energie <= 0)
			return;
		
		this.direction = Direction.values()[(this.direction.ordinal() + 1) % 4];
	}
	
	/**
	 * Active un bouclier qui dure Un tour
	 */
	public void shield(){
		if(this.energie <= 0){
			return;
		}
		
		this.hasShield = true;
	}
	
	/**
	 * Se repose, ne fait rien
	 */
	public void doNothing(){
		if(this.energie < this.energieMax)
			this.energie += 2;
	}
	
	/**
	 * 
	 * @return vrai si le robot a un bouclier
	 */
	public boolean hasShield(){
		return this.hasShield;
	}
	
	/**
	 * 
	 * @return vrai si le robot a tirer
	 */
	public boolean hasLaserFire(){
		return this.hasLaserFire;
	}
	
	@Override
	public Image print() throws IOException{
		if(image == null){
			File imageFile = new File("assets/robot.png");
			image = ImageIO.read(imageFile);
		}
		return this.getDirection().rotateImage(image);
	}

	/**
	 * 
	 * @return l'energie max du robot
	 */
	public int getMaxEnergie() {
		return this.energieMax;
	}
}
