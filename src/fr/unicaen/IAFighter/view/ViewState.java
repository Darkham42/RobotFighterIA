package fr.unicaen.IAFighter.view;

import fr.unicaen.IAFighter.model.GameBoard;

class ViewState {
	private State state;
	private Gui gui;
	
	ViewState(Gui gui) {
		this.gui = gui;
	}
	protected BaseView view(GameBoard model){
		return this.state.view(model);
	}
	
	String titleView(){
		return this.state.titleView();
	}
	
	protected void setState(State state){
		this.state = state;
		this.gui.constructView();
	}
}
