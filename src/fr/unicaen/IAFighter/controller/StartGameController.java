package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.unicaen.IAFighter.model.GameBoard;

/**
 * Demarre le jeu si la partie peut commencer
 *
 */
class StartGameController extends BaseController implements ActionListener {
	public StartGameController(GameBoard model) {
		super(model);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!this.model.terminal())
			this.model.startGame();
	}

}
