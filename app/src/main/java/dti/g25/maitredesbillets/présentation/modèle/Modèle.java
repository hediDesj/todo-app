package dti.g25.maitredesbillets.présentation.modèle;


import android.content.Context;
import dti.g25.maitredesbillets.domaine.entité.Billet;
import dti.g25.maitredesbillets.domaine.interacteur.CréationBillet;
import dti.g25.maitredesbillets.domaine.entité.Projet;
import dti.g25.maitredesbillets.présentation.modèle.dao.DAO;
import dti.g25.maitredesbillets.présentation.modèle.dao.DAOFactory;

import java.util.List;

public class Modèle {
    List<DAO<Projet>> projets;
    List<DAO<Billet>> billets;
    Context context;
    DAOFactory daoFactory;

    /**
     * Ajouter le billet en paramètre au projet en paramètre à l'aide du DAO
     * @param projetPosition le projet auquel sera ajouté le billet
     * @param billet le billet qui sera ajouté au projet
     */
    public void ajouterDAOBillet(int projetPosition, Billet billet){
        daoFactory.ajouterBilletAuProjet(context,getProjetDAOParPos(projetPosition), billet);
    }

    /**
     * Modifie les informations du billet (Insere un nouveau objet billet a la place exacte de son prédécesseur)
     * @param positionProjet la position du projet contenant le billet à modifier
     * @param positionBillet la position du billet à modifier
     * @param titre le (nouveau) tire du billet
     * @param description la (nouvelle) description du billet
     */
    public void modifierBillets(int positionProjet, int positionBillet, String titre, String description){
        Billet billet =  CréationBillet.créerBillet(titre, description);
        if(positionBillet > -1){
            getProjetParPos(positionProjet).getBillets().set(positionBillet, billet);
        }
    }


    public void modifierDAOBillets(int positionProjet, int positionBillet, String titre, String description){
        Billet billet =  CréationBillet.créerBillet(titre, description);
        if(positionBillet > -1){
            getDAOBillets(positionProjet).get(positionBillet).modifier(billet);
        }
    }

    /**
     * Permet d'effacer un Billet pour un projet donné
     * @param positionProjet la position du projet contenant le billet à retirer
     * @param positionBillet la position du billet à retirer
     */
    public void supprimerBillet(int positionProjet, int positionBillet){
        getProjetParPos(positionProjet).getBillets().remove(getProjetParPos(positionProjet).getBillets().get(positionBillet));
    }

    /**
     * Liste des billets pour un projet donné
     * @param positionProjet l'emplacement d'un projet
     * @return La lsite des Billets en format "List"
     */
    public List<Billet> getBillets(int positionProjet){
        return getProjetParPos(positionProjet).getBillets();
    }
    public List<DAO<Billet>> getDAOBillets(int positionProjet){
        return daoFactory.creerListeDAOBilletParProjet(context, getProjetDAOParPos(positionProjet));
    }


    public void setBillets(int positionProjet, List<Billet> billets){
         getProjetParPos(positionProjet).setBillets(billets);
    }
    public void setDaoBillets(int positionProjet){
        billets= daoFactory.creerListeDAOBilletParProjet(context, getProjetDAOParPos(positionProjet));
    }


    /**
     * Constructeur du modele
     */
    public Modèle(Context context, DAOFactory daoFactory){
        this.context=context;
        this.daoFactory=daoFactory;
        projets=daoFactory.creerListeDAOProjet(context);
    }

    /**
     * @return les projets dans le modele
     */
    public List<DAO<Projet>> getDAOProjets() {
        return projets;
    }

    /**
     * Ajoute un projet a la liste de projet
     * @param projet le projet a ajouter au modele
     */
    public void ajouterUnProjet(Projet projet){
        DAO<Projet> projetDAO= daoFactory.creerDAOProjet(context);
        projetDAO.creer(projet);
    }

    /**
     * @param position la position du projet dans la liste projets
     * @return le projet à la position passée en param
     */
    public Projet getProjetParPos(int position){
        return projets.get(position).lire();
    }

    public DAO<Projet> getProjetDAOParPos(int position){return projets.get(position);}

    /**
     * Retire un projet de la liste projets selon sa position
     * @param postion la position du projet à retirer
     */
    public boolean retirerProjetParPos(int postion){ return projets.get(postion).supprimer();}

    public void rafraichir(){
        projets=daoFactory.creerListeDAOProjet(context);
    }

}
