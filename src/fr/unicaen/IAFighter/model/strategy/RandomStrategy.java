package fr.unicaen.IAFighter.model.strategy;

import java.util.ArrayList;
import java.util.Random;

import fr.unicaen.IAFighter.model.Robot;

/**
 * Classe correspondant à la strategie aleatoire
 *
 */
public class RandomStrategy implements IStrategy{
	
	private ArrayList<String> nom_strategie;
	/**
	 * Constructeur logique
	 */
	public RandomStrategy() {
		 nom_strategie = new ArrayList<String>();
		 nom_strategie.add("Stratégie aléatoire");
	}
	/**
	 * prend un nombre aleatoire entre 0 et 7 (nombre d'actions implementees)
	 * et ordonne au robot d'executer l'action correspondante.
	 * @param Robot r
	 */
	@Override
	public void execute(Robot r) {
		int nombreAleatoire = new Random().nextInt(7);
		switch(nombreAleatoire){
		case 0 : // ne rien faire TESTER
			r.doNothing();
			break;
		case 1 : // Avancer TESTER
			r.forward();
			break;
		case 2 : // Reculer TESTER
			r.backward();
			break;
		case 3 : //aller a gauche TESTER
			r.turnLeft();
			break;
		case 4 : //aller a droite TESTER
			r.turnRight();
			break;
		case 5 : // tirer un missile TESTER
			r.missileFire();
			break;
		case 6 : // tirer un laser TESTER
			r.laserFire();
			break;
		case 7 : //Bouclier (se proteger) TESTER
			r.shield();
			break;
		}
		
	}
	/**
	 * Accesseur sur le nom de la strategie
	 * @return le nom de la strategie
	 */
	@Override
	public String getName() {
		return "Aléatoire";
	}
}
