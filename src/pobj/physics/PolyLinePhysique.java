package pobj.physics;

import java.util.ArrayList;

import pobj.tools.Vecteur2D;

/**
 * @author  vguigue  classe de rectangle physique immobile : le type de base des murs...
 */
public class PolyLinePhysique extends ObjectPhysique {

	/**
	 * Attribut de largeur
	 * @uml.property  name="larg"
	 */
	private double larg;
	
	/**
	 * Attribut de hauteur
	 * @uml.property  name="haut"
	 */
	private double haut;
	
	// sauvegarde des données de collision
	/**
	 * @uml.property  name="buffer_collision_A"
	 * @uml.associationEnd  
	 */
	private static Vecteur2D buffer_collision_A ;
	/**
	 * @uml.property  name="buffer_collision_B"
	 * @uml.associationEnd  
	 */
	private static Vecteur2D buffer_collision_B ;
	/**
	 * @uml.property  name="buffer_collision_P"
	 * @uml.associationEnd  
	 */
	private static Vecteur2D buffer_collision_P ;

	private ArrayList<Vecteur2D> tabPts;

	/**
	 * Constructeur de base d'un rectangle
	 * @param x
	 * @param y
	 */
	public PolyLinePhysique(double x, double y, ArrayList<Vecteur2D> tabPts){
		super(x,y);
		//this.larg = larg;
		//this.haut = haut;
		
		this.tabPts = tabPts;
	}

	protected PolyLinePhysique(double x, double y){
		super(x,y);
	}

	protected void setTabPts(ArrayList<Vecteur2D> tabPts){
		this.tabPts = tabPts;
	}
	
	/**
	 * accesseur
	 * @return
	 * @uml.property  name="larg"
	 */
	public double getLarg(){return larg;}
	
	/**
	 * accesseur
	 * @return
	 * @uml.property  name="haut"
	 */
	public double getHaut(){return haut;}


	/**
	 * recuperation des coordonnees des quatres points du rectangle
	 * @return
	 */
	private ArrayList<Vecteur2D> getAllPts(){
		
		return tabPts;
	}
	
	public int size(){
		return tabPts.size();
	}
	
	private int[] xCoordAsInt = null; //buff
	private int[] yCoordAsInt = null;
	
	private int[] getXCoordAsInt(double offsetX, double offsetY, double facteur){
		if(xCoordAsInt == null){
			xCoordAsInt = new int[size()];
			for(int i=0; i<size(); i++){
				xCoordAsInt[i] = (int)(tabPts.get(i).getX()*facteur + offsetY);
			}
		}
		
		return xCoordAsInt;
	}
	
	private int[] getYCoordAsInt(double offsetX, double offsetY, double facteur){
		if(yCoordAsInt == null){
			yCoordAsInt = new int[size()];
			for(int i=0; i<size(); i++){
				yCoordAsInt[i] = (int)(tabPts.get(i).getY()*facteur + offsetY);
			}
		}
		
		return yCoordAsInt;
	}

	protected boolean collision(ObjectPhysique o) {
		if(o instanceof CerclePhysique){
			CerclePhysique oc = (CerclePhysique) o;
			ArrayList<Vecteur2D> allpts = getAllPts();

			for (int i=0; i<allpts.size() - 1; i ++){
				Vecteur2D A = allpts.get(i);
				Vecteur2D B = allpts.get(i+1);
				Vecteur2D C = o.getPos();

				Vecteur2D AC = new Vecteur2D(A,C);
				Vecteur2D AB = new Vecteur2D(A,B);

				double theta = Math.acos(AB.scalaire(AC)/(AB.norm()*AC.norm()) );
				double dist = Math.sin(theta)*AC.norm();

				if(dist>=oc.getRayon()) // distance trop grande
					continue;

				if(theta > Math.PI/2.) // pas sur le segment
					continue;

				if(AB.normSq() + (oc.getRayon()*oc.getRayon()) < AC.normSq()) // trop loin du segment
					continue;

				Vecteur2D P = new Vecteur2D(A);
				double f = AC.norm() * Math.cos(theta) / AB.norm();
				P.autoadd(AB.fact(f));
				Vecteur2D CP = new Vecteur2D(C,P);

				if (oc.getVit() == 0. ||  oc.getDir().scalaire(CP)<=0) // pas dans la bonne direction
					continue;

				// sauvegarde des donn�es de collision
				buffer_collision_A = A;
				buffer_collision_B = B;
				buffer_collision_P = P;

				return true;
			}

			return false;
		}
		else if(o instanceof PolyLinePhysique)
			return false; 
		else
			return o.collision(this);

	}


	protected void resolutionChoc(ObjectPhysique o) {
		if(buffer_collision_A == null || buffer_collision_B == null || buffer_collision_P == null)
			return;
		
		if(o instanceof CerclePhysique){
			CerclePhysique oc = (CerclePhysique) o;
			Vecteur2D A = buffer_collision_A;
			Vecteur2D B = buffer_collision_B;
			Vecteur2D P = buffer_collision_P;
			Vecteur2D C = oc.getPos();

			//Vecteur2D AC = new Vecteur2D(A,C);
			Vecteur2D AB = new Vecteur2D(A,B);

			double theta = Math.acos(AB.scalaire(oc.getDir())/AB.norm() );

			Vecteur2D Perp = new Vecteur2D(P,C);
			Perp.normalize(); 

			double alpha = 2*oc.getVit()*Math.sin(theta);
			Vecteur2D V1_tp1 = oc.getDir().fact(oc.getVit()); // vecteur vitesse original
			V1_tp1.autoadd(Perp.fact(alpha));
			double v1 = V1_tp1.norm();
			V1_tp1.normalize(); // extraire la direction

			// mise a jour
			oc.setDir(V1_tp1);
			oc.setVit(v1);
		}
		else if(!(o instanceof PolyLinePhysique))
			o.resolutionChoc(this);

	}

}
