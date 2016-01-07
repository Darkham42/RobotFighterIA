package fr.unicaen.IAFighter.model;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Représente une direction, la direction est compose des 4 points cardinaux
 */
public enum Direction {
	top(0, -1) {
		@Override
		public BufferedImage rotateImage(BufferedImage image) {
			return image;
		}
	},
	right(1, 0) {
		@Override
		public BufferedImage rotateImage(BufferedImage image) {
			double rotationRequired = Math.toRadians (90);
			double locationX = image.getWidth() / 2;
			double locationY = image.getHeight() / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			return op.filter(image, null);
		}
	},
	bottom(0, 1) {
		@Override
		public BufferedImage rotateImage(BufferedImage image) {
			double rotationRequired = Math.toRadians (180);
			double locationX = image.getWidth() / 2;
			double locationY = image.getHeight() / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			return op.filter(image, null);
		}
	},
	left(-1, 0) {
		@Override
		public BufferedImage rotateImage(BufferedImage image) {
			double rotationRequired = Math.toRadians (-90);
			double locationX = image.getWidth() / 2;
			double locationY = image.getHeight() / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			return op.filter(image, null);
		}
	};
	
	/**
	 * 
	 * @return la composante col du vecteur de direction
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @return la composante row du vecteur de direction
	 */
	public int getRow() {
		return row;
	}

	private int col, row;
	
	/**
	 * Ce type enumere permet de representer un vecteur 2D
	 * Constructeur de Direction.java
	 * @param row la composante row du vecteur
	 * @param col la composante col du vecteur
	 */
	private Direction(int row, int col){
		this.col = row;
		this.row = col;
	}
	/**
	 * Permet de tourner une image en fonction de la direction
	 * @param image l'image à tourner
	 * @return l'image tourner
	 */
	public abstract BufferedImage rotateImage(BufferedImage image);
}
