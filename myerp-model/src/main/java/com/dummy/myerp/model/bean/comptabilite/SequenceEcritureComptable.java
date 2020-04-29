package com.dummy.myerp.model.bean.comptabilite;


/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
public class SequenceEcritureComptable {

    // ==================== Attributs ====================
    /**
     * L'année
     */
    private Integer annee;
    /**
     * La dernière valeur utilisée
     */
    private Integer derniereValeur;

    private String journalCode;

    // ==================== Constructeurs ====================

    /**
     * Constructeur
     */
    public SequenceEcritureComptable() {
    }

    /**
     * Constructeur
     *
     * @param pAnnee          -
     * @param pDerniereValeur -
     * @param journalCode
     */
    public SequenceEcritureComptable(Integer pAnnee, Integer pDerniereValeur, String journalCode) {
        annee = pAnnee;
        derniereValeur = pDerniereValeur;
        this.journalCode = journalCode;
    }


    // ==================== Getters/Setters ====================
    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer pAnnee) {
        annee = pAnnee;
    }

    public Integer getDerniereValeur() {
        return derniereValeur;
    }

    public void setDerniereValeur(Integer pDerniereValeur) {
        derniereValeur = pDerniereValeur;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String        vSEP = ", ";
        vStB.append("{")
                .append("annee=").append(annee)
                .append(vSEP).append("derniereValeur=").append(derniereValeur)
                .append("}");
        return vStB.toString();
    }

    public String getJournalCode() {
        return journalCode;
    }

    public void setJournalCode(String journalCode) {
        this.journalCode = journalCode;
    }
}