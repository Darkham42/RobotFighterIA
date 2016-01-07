package fr.unicaen.IAFighter.model.strategy;

import fr.unicaen.IAFighter.model.Robot;
/**
 * Classe abstraite representant un noeud dans notre arbre de strategie
 *
 */
public abstract class NoeudAbstrait {
	/**
	 * Constructeur par defaut
	 */
	public NoeudAbstrait(){
	}
	/**
	 * Methode abstraite action.
	 * @param r : robot
	 * @return une action a effectuer
	 */
	public abstract Action executer(Robot r);
	
}
