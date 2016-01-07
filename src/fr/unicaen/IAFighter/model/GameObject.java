package fr.unicaen.IAFighter.model;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 *	Classe abstraite des objets qui peuvent agir en jeux (robots et missiles)
 *	Les deux objets en question possedent un sens et peuvent avancer;
 * @see Missile, Robot
 */
public abstract class GameObject {
	
	private boolean dead;
	private Color color;
	protected GameBoard model;
	private TypeObject typeObject;
	protected BufferedImage image;
	
	/**
	 * int col, row : représente la position du GameObject dans le tableau de Cell
	 */
	protected int col, row;
	/**
	 * enum Direction: représente la direction de l'objet en question
	 */
	protected Direction direction;
	
	/**
	 * Constructeur de GameObject.java
	 * @param model
	 */
	public GameObject(GameBoard model) {
		// TODO Auto-generated constructor stub
		this.model = model;
		int red = new Random().nextInt(256);
		int green = new Random().nextInt(256);
		int blue = new Random().nextInt(256);
		this.color = new Color(red,green,blue);
		this.direction = Direction.top;
		this.dead = false;
	}
	
	/**
	 * Indique si le GameObject est mort
	 * @return vrai si ce GameObject est mort
	 */
	public boolean isDead(){
		return this.dead;
	}
	
	/**
	 * Tue le GameObject
	 */
	public void die(){
		this.dead = true;
	}
	
	/**
	 * Renvoie le {@link GameBoard model} accessible par le GameObject
	 * @return
	 */
	public GameBoard getModel() {
		return model;
	}
	
	/**
	 * Met a jour la couleur du GameObject
	 * @param color la nouvelle couleur
	 */
	public void setColor(Color color){
		this.color = color;
		this.model.changed();
		this.model.notifyObservers();
	}
	
	/**
	 * Retourne la couleur du GameObject
	 * @return 
	 */
	public Color getColor(){
		return this.color;
	}
	
	/**
	 * Modificateur de la diretion du GameObject
	 * @param direction
	 */
	public void setDirection(Direction direction){
		this.direction = direction;
		this.model.changed();
		this.model.notifyObservers();
	}
	
	/**
	 * La ligne ou ce GameObject se situe sur le plateau
	 * @return
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * La Colonne ou ce GameObject se situe sur le plateau
	 * @return
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * tous les objects agissent pendant leur tours
	 * cette action est effectue par cette methode 
	 */
	public abstract void action();
	
	/**
	 * Permet de mettre a jour la position du robot (uniquement la mise a jour des valeurs entiere row et col
	 * @param row la nouvelle ligne
	 * @param col la nouvelle colonne
	 */
	public void updatePosition(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Permet aux GameObject d'avancer
	 */
	public abstract void forward();

	/**
	 * retourne la direction du GameObject
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * 
	 * @return le type de cet objet
	 */
	public TypeObject getTypeObject(){
		return this.typeObject;
	}
	
	/**
	 * Met a jour le type de cet objet
	 * @param typeObject
	 */
	public void setTypeObject(TypeObject typeObject){
		this.typeObject = typeObject;
	}
	
	
	public String toString(){
		return "[" + this.row + ", " + this.col + "]";
	}
	
	/**
	 * Renvoie une image representant l'objet
	 * @return l'image de type Image
	 * @throws IOException si le fichier a eté erenomme ou supprimmer
	 */
	public abstract Image print() throws IOException;

	/**
	 * 
	 * @return le nom de la strategie utiliser par le GameObject
	 * (Pour bien faire il aurait fallu la definir dans la classe Robot)
	 */
	public String getStrategyName() {
		return "";
	}
}
