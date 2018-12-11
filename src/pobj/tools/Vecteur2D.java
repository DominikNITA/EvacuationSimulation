package pobj.tools;

import java.awt.Point;
import java.util.Random;

/**
 * Classe pour repr�senter des points ou des vecteurs en 2D Definition des op�rateurs pour les calculs de g�om�trie
 * @author  vguigue
 */
public class Vecteur2D implements Cloneable {
	/**
	 * @uml.property  name="x"
	 */
	private double x;
	/**
	 * @uml.property  name="y"
	 */
	private double y;
	
	public Vecteur2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Vecteur2D(Point p) {
		super();
		this.x = (double) p.getX();
		this.y = (double) p.getY();
	}
	
	public Vecteur2D(Vecteur2D a, Vecteur2D b) {
		super();
		this.x = b.x - a.x;
		this.y = b.y - a.y;
	}
	
	public Vecteur2D(Vecteur2D a) {
		super();
		this.x =  a.x;
		this.y =  a.y;
	}
	
	/**
	 * @return
	 * @uml.property  name="x"
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 * @uml.property  name="x"
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return
	 * @uml.property  name="y"
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 * @uml.property  name="y"
	 */
	public void setY(double y) {
		this.y = y;
	}

	public double distance(Vecteur2D v){
		return Math.sqrt( (v.x-this.x)*(v.x-this.x) + (v.y-this.y)*(v.y-this.y));
	}
	
	public double distanceSq(Vecteur2D v){
		return  (v.x-this.x)*(v.x-this.x) + (v.y-this.y)*(v.y-this.y);
	}
	
	public double norm(){
		return Math.sqrt (x*x+y*y);
	}
	
	public double normSq(){
		return (x*x+y*y);
	}
	
	public Vecteur2D normalize(){
		double n = norm();
		if (n==0)
			return this;
		x /= n;
		y /= n;
		return this;
	}
	
	public Vecteur2D add(Vecteur2D v){
		return new Vecteur2D( v.x+this.x, v.y+this.y);
	}
	public Vecteur2D add(double x, double y){
		return new Vecteur2D( x+this.x, y+this.y);
	}
	
	public void autoadd(Vecteur2D v){
		this.x = v.x+this.x;
		this.y = v.y+this.y;
	}
	public Vecteur2D fact(double f){
		return new Vecteur2D( f*this.x, f*this.y);
	}
	public void autofact(double f){
		this.x = f*this.x;
		this.y = f*this.y;
	}
	
	public double scalaire(Vecteur2D v){
		return  v.x*this.x + (v.y*this.y);
	}
	
	public double vect(Vecteur2D v){ // retourne la composante en z !!! C'est � dire un scalaire
		return  this.x * v.y - (this.y*v.x);
	}

	@Override
	public String toString() {
		return "(" + Double.toString(this.getX()) + ", " + Double.toString(this.getY())+")";
//		return super.toString();
	}

	public static Vecteur2D buildRandomVecteur() {
		Random r = new Random();
		return new Vecteur2D(r.nextGaussian(), r.nextGaussian());
	}

    public Vecteur2D minus(Vecteur2D v) {
        return new Vecteur2D( this.x - v.x, this.y-v.y);
    }
	
	
}
