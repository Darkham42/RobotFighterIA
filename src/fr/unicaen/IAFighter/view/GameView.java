package fr.unicaen.IAFighter.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;

import javax.swing.JButton;

import fr.unicaen.IAFighter.controller.ControllerFactory;
import fr.unicaen.IAFighter.controller.WatchGameFactory;
import fr.unicaen.IAFighter.model.GameBoard;

@SuppressWarnings("serial")
class GameView extends BaseView {
	private final static ControllerFactory controller = new WatchGameFactory();
	
	GameView(GameBoard model) {
		super(model);
		this.setLayout(new GridBagLayout());
		
		this.addChangeViewBtn(1, "Retour", new InitializeGameState());
		this.addChangeViewBtn(2, "Rejouer", new InitializeGridState());
		this.addChangeViewBtn(3, "Menu", new MainState());
		
		GridBagConstraints constraint1 = new GridBagConstraints();
		constraint1.gridx = 0;
		constraint1.gridy = 4;
		constraint1.insets = new Insets(10, 10, 10, 10);
		this.add(new BoardView(model), constraint1);
		
		GridBagConstraints constraint2 = new GridBagConstraints();
		constraint2.gridx = 0;
		constraint2.gridy = 5;
		constraint2.insets = new Insets(0, 10, 0, 10);
		
		JButton startGameBtn = new JButton("Commencer la partie !");
		startGameBtn.addActionListener(GameView.controller.getStartGameController(this.model));
		this.add(startGameBtn, constraint2);
		
		this.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
