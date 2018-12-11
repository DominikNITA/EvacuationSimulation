package pobj.physics;

import java.util.ArrayList;

import pobj.tools.Vecteur2D;

public class RectanglePhysique extends PolyLinePhysique {

	private double larg;
	private double haut;
	
	public RectanglePhysique(double x, double y, double larg, double haut) {
		super(x, y);
		this.larg = larg;
		this.haut = haut;
		ArrayList<Vecteur2D> ret = new ArrayList<Vecteur2D>();
		ret.add(getPos());
		ret.add(getPos().add(larg, 0.));
		ret.add(getPos().add(larg, haut));
		ret.add(getPos().add(0., haut));
		ret.add(getPos());
		
		setTabPts(ret);
	}

	public double getLarg() {
		return larg;
	}

	public double getHaut() {
		return haut;
	}

	
	
}
