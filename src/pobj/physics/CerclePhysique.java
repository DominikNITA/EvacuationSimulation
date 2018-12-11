package pobj.physics;

import pobj.tools.Vecteur2D;

/**
 * @author  vguigue  Implementation d'une particule physique ronde en 2D
 */
public class CerclePhysique extends MovePhysique  {


	/**
	 * rayon du cercle physique
	 * @uml.property  name="rayon"
	 */
	protected double rayon;
	/**
	 * masse du cercle physique (pour les collisions)
	 * @uml.property  name="masse"
	 */
	protected double masse;

	/**
	 * Constructeur complet
	 * 
	 * @param x : position x
	 * @param y : position y
	 * @param rayon : rayon
	 * @param masse : masse
	 */
	public CerclePhysique(double x, double y, double rayon, double masse){
		super(x,y);
		this.rayon = rayon;
		this.masse = masse;
	}

	/** 
	 * Constructeur simplifie:
	 * la masse vaut 1., le rayon 10.
	 * @param x
	 * @param y
	 */
	public CerclePhysique(double x, double y){
		super(x,y);
		this.rayon = 10.;
		this.masse = 1.;
	}


	/**
	 * accesseur
	 * @return  le rayon du cercle
	 * @uml.property  name="rayon"
	 */
	public double getRayon(){return rayon;}
	/**
	 * accesseur
	 * @return  la masse du cercle
	 * @uml.property  name="masse"
	 */
	public double getMasse(){return masse;}

	protected boolean collision(ObjectPhysique o) {
		if(o instanceof CerclePhysique){
			CerclePhysique oc = (CerclePhysique) o;
			double d2 = getPos().distanceSq(o.getPos());
			double r2  = (rayon+((CerclePhysique) o).getRayon()) * (rayon+((CerclePhysique) o).getRayon());
			
			double d2_next = getPos().add(getDir().fact(getVit())).distanceSq(o.getPos().add(oc.getDir().fact(oc.getVit())));
			if( d2_next < d2)
				return d2 < r2;
			else	
				return false;
		}
		else 
			return o.collision(this);

	}

	protected void resolutionChoc(ObjectPhysique o) {
		if(o instanceof CerclePhysique){
			CerclePhysique oc = (CerclePhysique) o;
			Vecteur2D C1C2 = new Vecteur2D(this.getPos(), oc.getPos());
			Vecteur2D C2C1 = new Vecteur2D(oc.getPos(), this.getPos());

			Vecteur2D Perp = new Vecteur2D(C2C1);
			Perp.normalize(); // vecteur unitaire

			// traiter le cas des vecteurs nuls !!! 

			double thetaR = Math.acos(C1C2.scalaire(this.getDir())/C1C2.norm());
			double theta  = Math.PI/2. - thetaR;

			double phiR = Math.acos(C2C1.scalaire(oc.getDir())/C2C1.norm());
			double phi  = Math.PI/2. - phiR;

			double alpha1 = 2*oc.getMasse()   /(oc.getMasse()+this.getMasse())*(this.getVit()*Math.sin(theta)+ oc.getVit()*Math.sin(phi));
			double alpha2 = 2*this.getMasse()/(oc.getMasse()+this.getMasse())*(this.getVit()*Math.sin(theta)+ oc.getVit()*Math.sin(phi));

			Vecteur2D V1_tp1 = this.getDir().fact(this.getVit());
			V1_tp1.autoadd(Perp.fact(alpha1));

			Vecteur2D V2_tp1 = oc.getDir().fact(oc.getVit());
			V2_tp1.autoadd(Perp.fact(-1.*alpha2));

			double v1 = V1_tp1.norm();
			double v2 = V2_tp1.norm();
			V1_tp1.normalize(); // extraire la direction
			V2_tp1.normalize();

			// mise a jour
			oc.setDir(V2_tp1);
			oc.setVit(v2);
			this.setDir(V1_tp1);
			this.setVit(v1);
		}
		else 
			o.resolutionChoc(this);

	}







}
