package fr.unicaen.IAFighter.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;

import fr.unicaen.IAFighter.model.*;

@SuppressWarnings("serial")
public abstract class BaseView extends JComponent implements Observer {
	protected GameBoard model;
	
	public BaseView(GameBoard model) {
		this.model = model;
		this.model.addObserver(this);
	}
	
	protected void addChangeViewBtn(int row, String btnText, final State state){
		GridBagConstraints constraint1 = new GridBagConstraints();
		constraint1.gridx = 0;
		constraint1.gridy = row;
		constraint1.insets = new Insets(3, 10, 3, 10);
		JButton doGame = new JButton(btnText);
		doGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gui.viewState.setState(state);
			}
		});
		this.add(doGame, constraint1);
	}
}
