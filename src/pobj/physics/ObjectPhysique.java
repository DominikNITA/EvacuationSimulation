package pobj.physics;

import pobj.tools.Vecteur2D;

/**
 * @author  vguigue  Objet de base du moteur physique qui utilise la classe Vecteur2D  Les objets par defaut sont positionnable et collisionnable
 */
public abstract class ObjectPhysique {
	
	/**
	 * Attribut de position 2D
	 * @uml.property  name="pos"
	 * @uml.associationEnd  
	 */
	private Vecteur2D pos;
	private int cptChocs;
	
	/**
	 * Constructeur protege d'objet positionnable
	 * @param x
	 * @param y
	 */
	protected ObjectPhysique(double x, double y){
		pos = new Vecteur2D(x,y);
		cptChocs = 0;
	}
	
	/**
	 * @return  le Vecteur2D de position de l'objet
	 * @uml.property  name="pos"
	 */
	public Vecteur2D getPos() {
		return pos;
	}
	
	protected void setPos(Vecteur2D v) {
		 pos = v;
	}
	/**
     * comptage des chocs sur l'objet
     */
    public void choc() {
        cptChocs ++;
    }
    /**
     * RAZ du comptage des chocs sur l'objet
     */
    public void resetChoc() {
        cptChocs =0;
    }
    /**
     * recupéraiton du comptage des chocs sur l'objet
     */
    public int getCptChocs() {
        return cptChocs;
    }
    
	
	/**
	 * @return la coordonnee en X
	 */
	public double getPosX() {
		
		return pos.getX();
	}

	/**
	 * @return la coordonnee en Y
	 */
	public double getPosY() {
		
		return pos.getY();
	}

	
	/** 
	 * Fonction de test de collision entre deux objets physiques
	 * @param o: deuxieme objet a tester (pour la collision)
	 * @return true s'il y a collision false sinon
	 */
	protected abstract boolean collision(ObjectPhysique o);
	
	
	/**
	 * Fonction de mise a jour d'un objet suite a une collision
	 * NB: la fonction met a jour les deux objets concernes
	 * NB2: cette fonction suppose qu'il y a effectivement collision (la fonction collision doit
	 * etre utilisee au prealable)
	 * @param o: second objet concerne par la collision
	 */
	protected abstract void resolutionChoc(ObjectPhysique o);
	
}

