package fr.unicaen.IAFighter.view;

import fr.unicaen.IAFighter.model.GameBoard;

class InitializeGridState implements State {

	@Override
	public BaseView view(GameBoard model) {
		// TODO Auto-generated method stub
		return new InitializeGridView(model);
	}

	@Override
	public String titleView() {
		// TODO Auto-generated method stub
		return "IA Fighter - Initialisation de la grille de jeu";
	}

}
