package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionListener;

import fr.unicaen.IAFighter.model.GameBoard;

/**
 * Delegue a la vue les controllers mais rend les modifications du modele impossible
 * (acces au modele en lecture seulement)
 *
 */
public class WatchGameFactory extends ControllerFactory {

	@Override
	public ActionListener getStartGameController(GameBoard model) {
		return new StartGameController(model);
	}
}
