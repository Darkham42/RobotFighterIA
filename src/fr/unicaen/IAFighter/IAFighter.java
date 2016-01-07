package fr.unicaen.IAFighter;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.view.Gui;
/**
 * Client
 * @author charpentier-herve-lebert-picard
 *
 */
public class IAFighter{

	/**
	 * Point d'entree de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		GameBoard model = new GameBoard();
		new Gui(model);
	}
}
