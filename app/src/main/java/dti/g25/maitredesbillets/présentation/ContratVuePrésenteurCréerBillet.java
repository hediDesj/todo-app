package dti.g25.maitredesbillets.présentation;

import dti.g25.maitredesbillets.domaine.entité.Billet;

/**
 *le contrat entre la vue et le présenteur
 * pour s'assurer des rôles de chacuns
 */

public interface ContratVuePrésenteurCréerBillet {

    /**
     * contrate de la vue créer billet
     */
    public interface  IVueCréerBillet{
        public String getTitreBillet();
        public String getDescriptionBillet();

    }

    /**
     * contrat du présenteur créer billet
     */
    public interface  IPrésenteurCréerBillet{
        public void terminerÉdition();
        public void commencerCréeation(int position);
    }

}
