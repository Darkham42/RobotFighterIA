package fr.unicaen.IAFighter.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;

import fr.unicaen.IAFighter.controller.ControllerFactory;
import fr.unicaen.IAFighter.controller.InitializeGameFactory;
import fr.unicaen.IAFighter.model.GameBoard;

@SuppressWarnings("serial")
public class InitializeGridView extends BaseView {
	private final static ControllerFactory controller = new InitializeGameFactory();
	
	public InitializeGridView(GameBoard model) {
		super(model);
		this.model.reinitialiser();
		this.setLayout(new GridBagLayout());
		
		this.addChangeViewBtn(1, "Initialisation des Joueurs", new InitializeGameState());
		this.addChangeViewBtn(2, "Menu", new MainState());
		
		GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.gridx = 0;
		constraints2.gridy = 3;
		constraints2.insets = new Insets(3, 10, 3, 10);
		this.add(new UpdateGridSize(this.model, InitializeGridView.controller), constraints2);
		
		GridBagConstraints constraints3 = new GridBagConstraints();
		constraints3.gridx = 0;
		constraints3.gridy = 4;
		constraints3.insets = new Insets(3, 10, 3, 10);
		this.add(new BoardView(this.model), constraints3);
		
		this.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}

}
