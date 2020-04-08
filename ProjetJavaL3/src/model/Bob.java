package model;

import java.util.List;

public class Bob extends Personnage {

    private int Munitons;
    private boolean[] Inventaire = new boolean[7];
    private boolean Assome;


    private boolean RistalHasSpoken = false;
    private boolean RuyeHasSpoken = false;

    public Bob(String nom, int pdv, int mun) {
        super(nom, pdv);
        Munitons = mun;
        InstanciateInventaire();
    }

    private void InstanciateInventaire() {
        //Sac de fer = 0, Carburium = 1;, Recipient = 2, Cataliseur = 3,
        // recette = 4, Carburant Complet = 5, Guide démarage = 6
        for (int i = 0; i<Inventaire.length; i++) {
            Inventaire[i] = false;
        }
    }
    // TO DO
    public void Inventaire() {
        // ouvre l'inventaire
    }

    public void gainObect(int Id){
        Inventaire[Id] = true;
    }

    public void TirBlaster(Personnage Ennemi) {
        // s'il n'est pas assomé
        if (!isAssome()  && !isHyponotise()) {
            // s'il lui reste des munitions
            if (getMunitons() > 0) {
                setMunitons(getMunitons() - 1);
                // Inflige petits dégats et brule pendant 2 tours
                Ennemi.PerdrePdv(2);
                Ennemi.setBrule(2);
            }
        } else if(isHyponotise()){
            PerdrePdv(1);
            setHyponotise(false);
            // Info bulle "il se blesse dans sa confusion"
        } else {
            setAssome(false);
            // info bulle Bob est asomé
        }

    }

    public void CoupDePoing(Personnage Ennemi) {
        // s'il n'est pas assomé
        if (!isAssome()  && !isHyponotise()) {
            // Inflige de bons dégats
            Ennemi.PerdrePdv(2);
        }
        else if(isHyponotise()){
            PerdrePdv(1);
            setHyponotise(false);
            // Info bulle "il se blesse dans sa confusion"
        }
        else {
            setAssome(false);
            // info bulle Bob est asomé
        }
    }

    public void Soins() {
        // s'il n'est pas assomé
        if (!isAssome() && !isHyponotise()) {
            // soigne Bob
            GagnerPdv(3);
            if (this.getPointsDeVie() > 20)
                this.setPointsDeVie(20);
        }
        else if(isHyponotise()){
            PerdrePdv(1);
            // Info bulle "il se blesse dans sa confusion"
        }
        else {
        setAssome(false);
        // info bulle Bob est asomé
    }
    }

    public boolean HasObject(int obj) {
        if (Inventaire[obj])
            return true;
        else
            return false;
    }

    public int getMunitons() {
        return Munitons;
    }

    public void setMunitons(int munitons) {
        Munitons = munitons;
    }

    public boolean isAssome() {
        return Assome;
    }

    public void setAssome(boolean assome) {
        Assome = assome;
    }
    public boolean isRistalHasSpoken() {
        return RistalHasSpoken;
    }

    public void setRistalHasSpoken(boolean ristalHasSpoken) {
        RistalHasSpoken = ristalHasSpoken;
    }

    public boolean isRuyeHasSpoken() {
        return RuyeHasSpoken;
    }

    public void setRuyeHasSpoken(boolean ruyeHasSpoken) {
        RuyeHasSpoken = ruyeHasSpoken;
    }
}
