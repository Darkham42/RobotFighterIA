package fr.unicaen.IAFighter.view;

import fr.unicaen.IAFighter.model.GameBoard;

class MainState implements State {

	@Override
	public BaseView view(GameBoard model) {
		return new MainView(model);
	}

	@Override
	public String titleView() {
		return "IA Fighter - Vue Principale";
	}

}
