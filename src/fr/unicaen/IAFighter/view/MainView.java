package fr.unicaen.IAFighter.view;

import java.awt.GridBagLayout;
import java.util.Observable;

import fr.unicaen.IAFighter.model.GameBoard;

@SuppressWarnings("serial")
class MainView extends BaseView {
	public MainView(GameBoard model){
		super(model);
		this.setLayout(new GridBagLayout());
		
		this.addChangeViewBtn(1, "Nouvelle Partie", new InitializeGridState());
		
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
