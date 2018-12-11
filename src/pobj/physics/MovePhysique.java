package pobj.physics;

import pobj.tools.Vecteur2D;

/**
 * @author  vguigue  Classe abstraite de gestion des objets mobiles dans le moteur physique
 */
public abstract class MovePhysique extends ObjectPhysique {

	/**
	 * @uml.property  name="vit"
	 */
	private double vit;
	/**
	 * @uml.property  name="dir"
	 * @uml.associationEnd  
	 */
	private Vecteur2D dir;
	
	protected MovePhysique(double x, double y, double vx, double vy) {
		super(x, y);
		
		dir = new Vecteur2D(vx,vy);
		vit = dir.norm();
		
		dir.normalize();
	}

	protected MovePhysique(double x, double y) {
		super(x, y);
		//this.pos = new Vecteur2D(x,y);
		vit = 0;
		dir = new Vecteur2D(0.,0.);
	}
	
	/**
	 * Setter protected pour la direction
	 * @param d  : direction
	 * @uml.property  name="dir"
	 */
	protected void setDir(Vecteur2D d) {
		dir = d;
		
	}
	
	/**
	 * Setter protected pour la vitesse
	 * @param v  : vitesse
	 * @uml.property  name="vit"
	 */
	protected void setVit(double v) {
		vit = v;
		
	}
	
//	public void setDirAndVitesse(Vecteur2D d, double v){
//		setDir(d);
//		setVit(v);
//	}
	
	/**
	 *  Initialisation al�atoire de la direction et de la vitesse d'une particule
	 */
	public void setRandomDirectionAndVitesse(){
		Vecteur2D newDir = Vecteur2D.buildRandomVecteur();
		newDir.normalize();
		
		setDir(newDir);
		setVit((Math.random()+0.5)*2.);
	}
	
	
	/**
	 * accesseur public
	 * @return  direction
	 * @uml.property  name="dir"
	 */
	public Vecteur2D getDir(){return dir;};
	
	/**
	 * accesseur
	 * @return  vitesse
	 * @uml.property  name="vit"
	 */
	public double getVit(){return vit;}
	
	
	/**
	 * accesseur a la coordonnee en X de la vitesse
	 * @return
	 */
	public double getPosVX() {
		
		return dir.getX();
	}

	/**
	 * accesseur a la coordonnee en Y de la vitesse
	 * @return
	 */
	public double getPosVY() {
		
		return dir.getY();
	}

	/**
	 * MAJ de la position et de la vitesse des particules
	 * sans frottements
	 */
	protected void updatePositionAndVitesse(){ // sans frottements, pour l'instant
		updatePositionAndVitesse(1.);
	}

	/**
	 * MAJ de la position et de la vitesse des particules
	 * AVEC frottements (coefficient <= 1)
	 * 
	 * @param coef_frottement: coefficient de frottement
	 */
	protected void updatePositionAndVitesse(double coef_frottement) {
		setVit(getVit()*coef_frottement);
		getPos().autoadd(dir.fact(vit)); // une horreur de programmation JAVA... Mais ou est le const???
	}
	
}
