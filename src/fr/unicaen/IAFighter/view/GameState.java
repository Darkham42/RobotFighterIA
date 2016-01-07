package fr.unicaen.IAFighter.view;

import fr.unicaen.IAFighter.model.GameBoard;

class GameState implements State {

	@Override
	public BaseView view(GameBoard model) {
		return new GameView(model);
	}

	@Override
	public String titleView() {
		return "IA Fighter - Jeu Set Et Match !";
	}

}
