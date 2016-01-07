package fr.unicaen.IAFighter.model;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * represente un Missile
 */
public class Missile extends GameObject{

/*
 *
 * Constructeur de Missile.java
 * Un Missile est lancee par un joueur et possede une direction fixe
 * @param model
 * @param d la direction
 * @param player le joueur
 */
	public Missile(GameBoard model, Direction d, Robot player) {
		super(model);
		this.setColor(Color.BLACK);
		this.setTypeObject(TypeObject.missile);
		this.direction = d;
		this.updatePosition(player.getRow() + this.direction.getRow(), player.getCol() + this.direction.getCol());
	}

	/**
	 * Un missile ne peut que avancer durant son tour.
	 */
	public void action(){
		if(this.isDead())
			return;
		
		this.forward();
		this.model.changed();
	}
	
	@Override
	public void forward(){
		int row = this.getRow() + this.getDirection().getRow();
		int col = this.getCol() + this.getDirection().getCol();
		
		if(this.getModel().inGrid(row, col)){
			this.getModel().getBoard()[row][col].setContent(this);
			this.getModel().getBoard()[this.getRow()][this.getCol()].removeContent(this);
			this.updatePosition(row, col);
		}
		else{
			this.die();
		}
	}

	@Override
	public Image print() throws IOException {
		if(image == null){
			File imageFile = new File("assets/missile.png");
			image = ImageIO.read(imageFile);
		}
		return this.getDirection().rotateImage(image);
	}
}
