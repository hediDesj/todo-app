package dti.g25.maitredesbillets.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;

import dti.g25.maitredesbillets.présentation.ContratVuePrésenteurCréerBillet;
import dti.g25.maitredesbillets.présentation.modèle.Modèle;


public class PrésenteurCréerBillet implements ContratVuePrésenteurCréerBillet.IPrésenteurCréerBillet {

    private static final String EXTRA_POSITION = "dti.g25.maitredesbillets.position";
    private static final String EXTRA_DESCRIPTION_BILLET = "dti.g25.maitredesbillets.descriptionBillet";
    private static final String EXTRA_TITRE_BILLET = "dti.g25.maitredesbillets.titreBillet";

    Modèle modèle;
    Activity activité;
    ContratVuePrésenteurCréerBillet.IVueCréerBillet vue;

    private int position;

    /**
     * Constructeur du presentateur pour le nouveau billet
     * @param activité Activité dans laquelle le présenteur de billet sera placé
     * @param vue La vue qui est relié au présenteur du billet
     * @param modèle le modele du MVP
     */
    public PrésenteurCréerBillet(Activity activité, ContratVuePrésenteurCréerBillet.IVueCréerBillet vue, Modèle modèle) {
        this.activité=activité;
        this.vue=vue;
        this.modèle=modèle;
    }

    /**
     *Termine l'acitivté de création d'un nouveau billet
     */

    @Override
    public void terminerÉdition() {
        Intent donnéesRetour=new Intent();
        donnéesRetour.putExtra(EXTRA_TITRE_BILLET, vue.getTitreBillet());
        donnéesRetour.putExtra(EXTRA_DESCRIPTION_BILLET, vue.getDescriptionBillet());
        donnéesRetour.putExtra(EXTRA_POSITION, position);
        activité.setResult(activité.RESULT_OK, donnéesRetour);
        activité.finish();
    }

    /**
     *Commence l'acitivté de création d'un nouveau billet
     */
    @Override
    public void commencerCréeation(int position) {
        this.position = position;

    }

}
