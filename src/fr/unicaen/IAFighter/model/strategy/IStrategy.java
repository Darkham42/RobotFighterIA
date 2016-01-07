package fr.unicaen.IAFighter.model.strategy;

import fr.unicaen.IAFighter.model.Robot;
/**
 * Interface representant une strategie = interfarce du design pattern strategy
 *
 */
public interface IStrategy {
		/**
		 * Methode qui dit quoi faire au robot
		 * @param r
		 */
		public void execute(Robot r);
		/**
		 * Renvoit le nom de la strategie
		 * @return le nom de la strategie courante
		 */
		public String getName();
}
