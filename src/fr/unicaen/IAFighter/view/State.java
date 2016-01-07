package fr.unicaen.IAFighter.view;

import fr.unicaen.IAFighter.model.GameBoard;

interface State {
	BaseView view(GameBoard model);
	String titleView();
}
