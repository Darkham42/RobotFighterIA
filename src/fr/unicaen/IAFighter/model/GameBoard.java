package fr.unicaen.IAFighter.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
/**
 *	Il s'agit du plateau de jeux, il représente le modele de l'application.
 *	Il initialise le tableau de cellule
 *	et contient une liste des objets en jeux (robots et missiles)
 *	c'est lui qui va faire jouer les objets chacun leurs tour.
 */
public class GameBoard extends Observable implements Runnable{
	private int col, row;
	public final int minSize, maxSize;
	private Cell[][] board;
	private LinkedList<GameObject> gameObjects;
	private int currentPlayerIdx;
	private boolean exit;
	
	/**
	 * 
	 * Constructeur de GameBoard.java
	 */
	public GameBoard() {
		this.exit = false;
		this.gameObjects = new LinkedList<GameObject>();
		this.currentPlayerIdx = -1;
		
		this.minSize = 5;
		this.maxSize = 15;
		
		//width = col
		//height = row
		this.col = 5;
		this.row = 5;
		generateBoard();
	}
	
	/**
	 * demarre un thread charger de jouer la partie
	 */
	public void startGame(){
		Thread th = new Thread(this);
		th.start();
	}
	
	/**
	 * methode privee qui regenere le tableau de jeu
	 */
	private void generateBoard(){
		this.gameObjects.removeAll(this.gameObjects);
		this.board = new Cell[this.row][this.col];
		
		for(int row = 0 ; row < this.board.length ; row++){
			for(int col = 0 ; col < this.board[0].length ; col++){
				
				this.board[row][col] = new Cell();
			}
		}
	}
	
	/**
	 * retourne le curseur qui represente le GameObject courant
	 * @return
	 */
	public int getCurrentPlayerIdx(){
		return this.currentPlayerIdx;
	}
	
	/**
	 * verifie si un couple representant une position passer en parametre se situe dans le plateau
	 * @param row la ligne a tester
	 * @param col la colonne a tester
	 * @return vrai si le couple appartient au plateau de jeu sinon faux
	 */
	public boolean inGrid(int row, int col){
		if(row < 0 || col < 0)
			return false;
		
		if(row >= this.board.length || col >= this.board[0].length)
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * @return le nombre le ligne du plateau de jeu
	 */
	public int getRow(){
		return this.row;
	}
	
	/**
	 * 
	 * @return le nombre de colonne du plateau de jeu
	 */
	public int getCol(){
		return this.col;
	}
	
	/**
	 * Accesseur du tableau de Cell representant le plateau de jeu
	 * @return
	 */
	public Cell[][] getBoard(){
		return this.board;
	}
	
	/**
	 * met a jour le nombre de colonnes du plateau
	 * @param col
	 */
	public void setCol(int col){
		this.board = null;
		this.col = col;
		this.setChanged();
		this.notifyObservers(TypeEvent.sizeEvent);
		this.generateBoard();
	}
	
	/**
	 * 
	 * @param met a jour le nombre de lignes du plateau
	 */
	public void setRow(int row){
		this.board = null;
		this.row = row;
		this.setChanged();
		this.notifyObservers(TypeEvent.sizeEvent);
		this.generateBoard();
	}
	
	/**
	 * ajoute un GameObject a la liste des joueurs actuels
	 * @param o le GameObject a ajouter
	 */
	public void addGameObject(GameObject o){
		
		try{
			this.setStartPoint(o);
		}
		catch(IndexOutOfBoundsException e){
			o = null;
			return;
		}
		this.gameObjects.add(o);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * supprime un GameObject de la liste des joueurs actuels
	 * @param o le GameObject a supprimer
	 */
	public void removeGameObject(GameObject o){
		if(!this.inGrid(o.getRow(), o.getRow())){
			System.out.println("Object not in grid");
		}else{
			this.board[o.getRow()][o.getCol()].removeContent(o);
		}
		this.gameObjects.remove(o);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * callback public de la methode private setChanged de l'interface Observable
	 * @see Observable#setChanged()
	 */
	public void changed(){
		this.setChanged();
	}
	
	/**
	 * Accesseur a la liste des GameObject present sur le plateau de jeu
	 * @return 
	 */
	public LinkedList<GameObject> listGameObjects(){
		return this.gameObjects;
	}
	
	/**
	 * Positionne un GameObject pour la premiere fois
	 * @param o le GameObject a positionner
	 * @throws ArrayIndexOutOfBoundsException si o se trouve en dehors du plateau
	 */
	public void setStartPoint(GameObject o) throws ArrayIndexOutOfBoundsException {
		if(!inGrid(o.getRow(), o.getCol()))
			throw new ArrayIndexOutOfBoundsException();
		
		this.board[o.getRow()][o.getCol()].setContent(o);
	}
	
	/**
	 * Iterateur qui retourne un GameObject
	 * @return le prochain GameObject qui n'est pas mort
	 */
	public GameObject nextPlayer(){
		if(this.listGameObjects().size() == 0)
			return null;
		
		this.currentPlayerIdx++;
		
		if(this.currentPlayerIdx >= this.gameObjects.size())
			this.currentPlayerIdx = 0;
		
		if(this.listGameObjects().get(this.currentPlayerIdx).isDead()){
			this.removeGameObject(this.listGameObjects().get(this.currentPlayerIdx--));
			return this.nextPlayer();
		}
		
		return this.listGameObjects().get(this.currentPlayerIdx);
	}
	
	/**
	 * Condition pour qu'une partie soit considerer finie
	 * @return vrai si il n'y a qu'un seul Robot sur le plateau de jeu
	 * @see Robot
	 */
	public boolean terminal(){
		int robotCount = 0;
		for (Iterator iterator = gameObjects.iterator(); iterator.hasNext();) {
			GameObject gameObject = (GameObject) iterator.next();
			if(gameObject.getTypeObject() == TypeObject.robot)
				robotCount++;
		}
		
		if(robotCount > 1)
			return false;
		
		return true;
	}
	
	/**
	 * reinitialise le modele pour une nouvelle partie
	 */
	public void reinitialiser(){
		this.board = null;
		this.listGameObjects().removeAll(this.listGameObjects());
		this.setRow(5);
		this.setCol(5);
	}

	@Override
	public void run() {
		Robot robot = null;
		
		while(!this.terminal() && !this.exit){
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}

			GameObject gameObject = null;
			
			gameObject = this.nextPlayer();
			if (gameObject == null){
				System.err.println("null player");
				return;
			}
			gameObject.action();

			this.setChanged();
			
			if(gameObject.getTypeObject() == TypeObject.robot){
				this.notifyObservers(TypeEvent.gameMove);
			}
			
			GameRule.apply(this);
			
			if(robot != null)
				if(robot.hasLaserFire())
					robot.stopLaserFire();
			
			if (gameObject.getTypeObject() == TypeObject.robot) {
				Robot r = (Robot) gameObject;
				if (r.hasLaserFire()){
					robot = r;
				}
			}
		}
		
		System.out.println("nombre de joueur: " + this.listGameObjects());
		this.notifyObservers(TypeEvent.gameMove);
		
		if(!this.exit)
			System.out.println("Partie Finie !!!");
	}
	
	/**
	 * permet de quitter proprement le thread qui represente le jeu en cours
	 */
	public void exit(){
		this.exit = true;
	}
}
