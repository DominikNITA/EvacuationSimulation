package project.partie2.Population;

import pobj.simuagent.Strategy;
import pobj.simuagent.Terrain;
import pobj.tools.Vecteur2D;
import project.partie2.Personne;
import project.partie2.Salle;

import java.util.ArrayList;

public class Population {
    private ArrayList<Personne> personnes;
    private Salle s;
    private Vecteur2D lastSpot;
    private Vecteur2D initialSpot;
    private double margin = 1;
    private boolean isNotFull;

    public Population(Salle s){
        personnes = new ArrayList<>();
        this.s = s;
        initialSpot = new Vecteur2D(-1,-1);
        lastSpot = new Vecteur2D(-1,-1);
        isNotFull = true;
    }

    public void addGroupePersonne(int amount, Strategy str, double xLeft, double xRight, double yTop, double yBot){
        if(lastSpot.getX() == initialSpot.getX() && lastSpot.getY() == initialSpot.getY())
            lastSpot = new Vecteur2D(xLeft,yTop);
        int personneAdded = -1; //Initilized at -1 for logics reasons...
        for (int i = 0; i < amount && isNotFull; i++) {
            addPersonne(str, xLeft, xRight, yTop, yBot);
            personneAdded++;
        }
        System.out.println(personneAdded + " Personne " +  str.getClass().toString() + " added to population");
    }
    public void addGroupePersonne(int amount, Strategy str){
        addGroupePersonne(amount, str,0,s.getLarg()*s.getCote(),0,s.getHaut()*s.getCote());
    }

    private void addPersonne(Strategy str){
        addPersonne(str,0,s.getLarg()*s.getCote(),0,s.getHaut()*s.getCote());
    }
    private void addPersonne(Strategy str,double xLeft, double xRight, double yTop, double yBot){
        if (isNotFull) {
            Vecteur2D pos = findNextSpot(xLeft, xRight, yTop,yBot);
            if (pos != null) {
                Personne p = new Personne(pos.getX(),pos.getY(),str);
                personnes.add(p);

                System.out.println("Personne " + str.getClass().toString() + " added to population");
                lastSpot = pos;
            } else {
                isNotFull = false;
                System.out.println("Cannot add more Personne into specified Salle! No more space.");
            }
        }
    }

    private Vecteur2D findNextSpot(double xLeft, double xRight, double yTop, double yBot) {
        double x = 0,y = 0; // initialized for correct return
        boolean spotFound = false;
        while (!spotFound) {
            x = lastSpot.getX() + Personne.getRayonPers()*2 + margin;
            boolean changeRow = false;
            if(xLeft>x){
                //TODO: throw error
            }
            if(xRight <= x){
                x = xLeft;
                changeRow = true;
            }
            y = lastSpot.getY();
            if(yTop > y){
                //TODO: throw error
            }
            if(changeRow){
                y += Personne.getRayonPers()*2 + margin;
            }
            if(y >= yBot){
                //TODO: Change to exception maybe
                return null;
            }
            spotFound = CheckCorrectPos(x,y);
            lastSpot = new Vecteur2D(x,y);
        }
        return new Vecteur2D(x,y); //TODO: or return lastSpot or even change to boolean return type
    }

    private boolean CheckCorrectPos(double x, double y){
        // x,y = coordinates of top left corner of "circle's square"
        Vecteur2D topLimitCircle = new Vecteur2D(x /*+ Personne.getRayonPers()*/,y);
        //Vecteur2D rightLimitCircle = new Vecteur2D(x + 2*Personne.getRayonPers(), y + Personne.getRayonPers());
        //Vecteur2D leftLimitCircle = new Vecteur2D(x, y + Personne.getRayonPers());
        //Vecteur2D bottomLimitCircle = new Vecteur2D(x + 2*Personne.getRayonPers(), y + Personne.getRayonPers()*2);

        //TODO: to simplify
        if(s.get(topLimitCircle) == Terrain.Mur
               /* || s.get(rightLimitCircle) == Terrain.Mur
                || s.get(leftLimitCircle) == Terrain.Mur
                || s.get(bottomLimitCircle) == Terrain.Mur*/){
            return false;
        }
        return true;
    }

    public void move(){
        for (Personne p : personnes){
            p.move();
        }
    }

    public boolean isSafe(){
        for (Personne p: personnes) {
            if(!p.getIsSafe()){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Personne> getPersonnes() {
        return personnes;
    }
}
