package fr.unicaen.IAFighter.model.strategy;

import fr.unicaen.IAFighter.model.Robot;
/**
 * Classe qui implémente l'interface IStrategy
 * Elle represente l'arbre d'une stratégie extraite à partir du fichier xml.
 *
 */
public class Strategy implements IStrategy {
	private String name;
	private StrategyReader reader;
	private NoeudAbstrait strategy;
	/**
	 * Constructeur logique
	 * @param name
	 */
	public Strategy(String name) {
		this.name = name;
		reader = new StrategyReader();
		reader.construireStrategie(name);
		strategy = reader.lireStrategie();
		this.reader = null;
	}
	/**
	 * accesseur sur le nom de la strategie.
	 * @return this.name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * definit l'action que le robot doit executer.
	 * Cette action est ensuite transmise au robot.
	 */
	@Override
	public void execute(Robot r){
		Action action = strategy.executer(r);
		action.execute(r);
	}
}
