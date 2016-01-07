package fr.unicaen.IAFighter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComponent;

import fr.unicaen.IAFighter.model.Cell;
import fr.unicaen.IAFighter.model.GameObject;
import fr.unicaen.IAFighter.model.Robot;
import fr.unicaen.IAFighter.model.TypeObject;

@SuppressWarnings("serial")
class CellView extends JComponent {
	private Cell cell;
	CellView(Cell cell, int cellSize) {
		this.cell = cell;
		this.setSize(cellSize, cellSize);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for(GameObject gameObject : this.cell.getAllContent()){
			if(gameObject.isDead()){
				this.paintDead(g);
				continue;
			}
			
			try {
				if(gameObject.getTypeObject() == TypeObject.robot){
					g.setColor(((Robot)gameObject).getColor());
					g.fillRect(0, 0, this.getWidth(), this.getHeight());
				}
				g.drawImage(gameObject.print(), 0, 0, null);
				
			} catch (IOException e) {
			}
		}
		
		if(this.cell.getLaserOccured()){
			this.paintLaser(g);
		}
	}
	
	private void paintDead(Graphics g){
		g.setColor(Color.RED);
		g.drawLine(this.getWidth() / 2, this.getWidth() / 2, this.getHeight() / 2, this.getHeight() / 2);
		g.drawLine(this.getWidth() / 2, this.getWidth() / 2, this.getHeight() / 2, this.getHeight() / 2);
	}
	
	private void paintLaser(Graphics g){
		g.setColor(Color.RED);
		g.drawLine(this.getWidth() / 2, 0, this.getHeight() / 2, this.getHeight());
		g.drawLine(0, this.getWidth() / 2, this.getHeight(), this.getHeight() / 2);
	}
}
