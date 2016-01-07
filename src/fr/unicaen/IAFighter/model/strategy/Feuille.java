package fr.unicaen.IAFighter.model.strategy;

import fr.unicaen.IAFighter.model.Robot;
/**
 * Classe representant une feuille de notre arbre de strategies
 *
 */
public class Feuille extends NoeudAbstrait {
	/**
	 * Represente l'action a effectuer
	 */
	private Action action;
	/**
	 * Constructeur logique
	 * @param action
	 */
	public Feuille(String action) {
		this.action = Action.valueOf(action);
	}
	/**
	 * Redefinition de la methode executer
	 * Cette methode indique au robot qu'elle est l'action a effectuer.
	 */
	@Override
	public Action executer(Robot r) {
		return this.action;
	}

}
