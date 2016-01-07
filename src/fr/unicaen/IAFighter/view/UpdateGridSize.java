package fr.unicaen.IAFighter.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

import fr.unicaen.IAFighter.controller.ControllerFactory;
import fr.unicaen.IAFighter.model.GameBoard;

@SuppressWarnings("serial")
public class UpdateGridSize extends BaseView {
	private JSpinner rowSpin, colSpin; 
	public UpdateGridSize(GameBoard model, ControllerFactory controller) {
		super(model);
		this.setLayout(new GridBagLayout());
		
		ArrayList<Integer> boardSize = new ArrayList<Integer>();
		
		for(int i = this.model.minSize ; i <= this.model.maxSize ; i++){
			boardSize.add(i);
		}
		
		GridBagConstraints constraints1 = new GridBagConstraints();
		constraints1.gridx = 1;
		constraints1.gridy = 0;
		constraints1.insets = new Insets(3, 10, 3, 10);
		
		this.rowSpin = new JSpinner(new SpinnerListModel(boardSize));
		this.rowSpin.setPreferredSize(new Dimension(40, 20));
		this.rowSpin.addChangeListener(controller.getRowSizeController(this.model));
		this.add(this.rowSpin, constraints1);
		
		GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.gridx = 2;
		constraints2.gridy = 0;
		constraints2.insets = new Insets(3, 10, 3, 10);
		
		this.colSpin = new JSpinner(new SpinnerListModel(boardSize));
		this.colSpin.setPreferredSize(new Dimension(40, 20));
		this.colSpin.addChangeListener(controller.getColSizeController(this.model));
		this.add(this.colSpin, constraints2);
		
		this.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg) {
	}

}
