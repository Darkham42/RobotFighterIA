package fr.unicaen.IAFighter.view;

import fr.unicaen.IAFighter.model.GameBoard;

class InitializeGameState implements State {

	@Override
	public BaseView view(GameBoard model) {
		return new InitializeGameView(model);
	}

	@Override
	public String titleView() {
		return "IA Fighter - Initialisation des Joueurs";
	}

}
