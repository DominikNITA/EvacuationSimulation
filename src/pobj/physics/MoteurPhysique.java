package pobj.physics;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author vguigue
 *
 * Classe de gestion du moteur physique
 */
/**
 * @author   vguigue  Classe de gestion du moteur physique
 * @uml.dependency   supplier="pobj.physics.ObjectPhysique" stereotypes="Standard::Create"
 */
public class MoteurPhysique {
		
	/**
	 * liste des objets de l'univers 
	 */
	private ArrayList<ObjectPhysique> tab;
	/**
     * nb de collisions maximum à chaque itération, les autres sont ignorées 
     */
	private static int MAX_COLLISIONS = 500;
	/**
     * coefficient de frottement réduisant la vitesse des mobiles 
     */
	private static double COEF_FROTTEMENT = 0.995;
	/**
     * vitesse mise à 0 en dessous de ce seuil 
     */
	private static double SEUIL_VITESSE_NULLE = 0.005;
	/**
     * vitesse maximum dans l'univers 
     */
	private static double VITESSEMAXSIMULATION = 10.;
	/**
     * seuil de detection des collisions: pas de collision au-delà 
     */
    private static double DISTANCE_MIN_COLLISION = 500;
	
	/**
	 * Constructeur 
	 */
	public MoteurPhysique(){
		this.tab = new ArrayList<ObjectPhysique> ();
	}


    public static void setMAX_COLLISIONS(int mAX_COLLISIONS) {
        MAX_COLLISIONS = mAX_COLLISIONS;
    }

    public static double getDISTANCE_MIN_COLLISION() {
        return DISTANCE_MIN_COLLISION;
    }

    public static void setDISTANCE_MIN_COLLISION(double dISTANCE_MIN_COLLISION) {
        DISTANCE_MIN_COLLISION = dISTANCE_MIN_COLLISION;
    }



    /**
	 * Ajout d'éléments physique dans l'univers
	 * @param g: element a ajouter
	 */
	public void add(ObjectPhysique g){
		tab.add(g);
	}
	
	public void remove(ObjectPhysique g){
        tab.remove(g);
    }

	public void addAll(Collection<? extends ObjectPhysique> g){
		tab.addAll(g);
	}
	
	/**
	 * Passer a t+1 dans l'univers.
	 * Toutes les coordonnees des objets sont mises a jour en tenant compte
	 * de toutes les collisions
	 */
	public void updateMovablePosition(){
		int cpt = 0;
		while( findAndResolveOneCollision() ){
			cpt++;
			if(cpt > MAX_COLLISIONS){
				System.out.println("Pb de detection de choc... Sortie !");
				break;
			}

		}
		for(ObjectPhysique g:tab){ // un pas en avant
            if(g instanceof MovePhysique)
                ((MovePhysique) g).updatePositionAndVitesse(COEF_FROTTEMENT);
        }
	}

	private boolean findAndResolveOneCollision() {
		// Choc entre spheres

		for(int i=0; i<tab.size(); i++){
			for(int j=i+1; j<tab.size(); j++){
				ObjectPhysique o1 = tab.get(i);
				ObjectPhysique o2 = tab.get(j);
				
				// ils sont dans une région proche
				if(o1.getPos().distance(o2.getPos())>DISTANCE_MIN_COLLISION)
				    continue;
				// un des deux bouge
				if(!(o1 instanceof MovePhysique) && !(o2 instanceof MovePhysique))
				    continue;

				if(o1.collision(o2)){
//				    System.out.println("choc");
				    o1.choc();
				    o2.choc();
					o1.resolutionChoc(o2);
					return true;
				}

			}
		}

		return false; // pas de collision
	}


	/**
	 * Trouver la vitesse de l'objet le plus rapide afin de savoir stopper
	 * la simulation si plus rien ne bouge
	 * @return la vitesse de l'objet le plus rapide
	 */
	private double getVMax(){
		double vmax = 0.;
		for(ObjectPhysique g:tab) // un pas en avant
			if(g instanceof MovePhysique)
				vmax = Math.max(vmax, ((MovePhysique) g).getVit());

		if (vmax < SEUIL_VITESSE_NULLE){
			for(ObjectPhysique g:tab) // un pas en avant
				if(g instanceof MovePhysique)
					((MovePhysique) g).setVit(0.);
			vmax = 0.;
		}

		return vmax;
	}
	
	public boolean isMove(){
		return (getVMax() > 0.);
	}


	private double getVitesseMaxSimulation() {
		return VITESSEMAXSIMULATION;
	}


}

