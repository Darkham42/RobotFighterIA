package fr.unicaen.IAFighter.model;

import java.util.ArrayList;

/**
 *	Représente une cellule du tableau
 *  Une cellule peut être composée de plusieurs elements (missiles robots....)
 *  C'est les regles du jeu qui définissent si il est possible de superposer les robots et missiles
 *  @see fr.unicaen.IAFighter.model.GameRule
 */
public class Cell {
	private ArrayList<GameObject> content;
	private boolean laserFire;
	/**
	 * Constructeur de la cellule
	 */
	public Cell() {
		this.content = new ArrayList<GameObject>();
		this.laserFire = false;
	}
	
	/**
	 * Cette methode effectue un travail double:
	 * Ajout d'un GameObject sur la cellule
	 * Verifie si l'ajout de cette object modifie
	 * @param o le GameObject à empiler sur cette case
	 */
	public void setContent(GameObject o){
		if(this.laserFire && o.getTypeObject() == TypeObject.robot){
			o.die();
		}
		
		if(this.laserFire && o.getTypeObject() == TypeObject.missile && GameRule.MissileDestroyableByLaser){
			o.die();
		}
		
		if(o.isDead()){
			this.content.add(o);
			return;
		}

		if(this.numberOf(TypeObject.missile) >= 1 || o.getTypeObject() == TypeObject.missile){
			
			if(o.getTypeObject() == TypeObject.missile && this.numberOf(TypeObject.robot) >= 1){
				o.die();
			}
			
			if(GameRule.MissileDestroyableTogether && this.numberOf(TypeObject.missile) >= 1){
				o.die();
			}
			
			for(GameObject object : this.getContent()){
				if(object.getTypeObject() == TypeObject.missile && GameRule.MissileDestroyableTogether){
					object.die();
				}
				
				if(object.getTypeObject() == TypeObject.robot){
					object.die();			
				}
			}
		}
		
		this.content.add(o);
	}
	
	/**
	 * Affiche le contenu de la cellule
	 * @see GameObject#die()
	 * @see GameObject#isDead()
	 * @return une liste des GameObject vivant sur cette cellule
	 */
	public GameObject[] getContent(){
		ArrayList<GameObject> dynContent = new ArrayList<GameObject>();
		
		for (int i = 0; i < this.content.size(); i++) {
			if(!this.content.get(i).isDead())
				dynContent.add(this.content.get(i));
		}
		
		GameObject[] content = new GameObject[dynContent.size()];
		
		return dynContent.toArray(content);
	}
	
	public GameObject[] getAllContent(){
		
		GameObject[] content = new GameObject[this.content.size()];
		
		return this.content.toArray(content);
	}
	
	/**
	 * active le laser de cette cellule
	 */
	public void setLaserOn(){
		this.laserFire = true;
	}
	
	/**
	 * desactive le laser de cette cellule
	 */
	public void setLaserOff(){
		this.laserFire = false;
	}
	
	/**
	 * 
	 * @return vrai si il y a un tir de laser sur cette cellule
	 */
	public boolean getLaserOccured(){
		return this.laserFire;
	}
	
	/**
	 * 
	 * @param type le type de GameObject rechercher
	 * @return le nombre de GameObject vivant du type que l'on recherche
	 */
	public int numberOf(TypeObject type){
		int integer = 0;
		for (GameObject gameObject : this.getContent()) {
			if(gameObject.getTypeObject() == type && !gameObject.isDead())
				integer++;
		}
		
		return integer;
	}
	
	/**
	 * Enleve un GameObject de cette cellule
	 * @see Missile#forward()
	 * @see Missile#backward()
	 * @see Robot#forward()
	 * @see Robot#backward()
	 * @see GameBoard#setStartPoint(GameObject)
	 * @see GameBoard#removeGameObject(GameObject)
	 * @param o le GameObject à supprimé
	 */
	public void removeContent(GameObject o){
		this.content.remove(o);
	}

}
