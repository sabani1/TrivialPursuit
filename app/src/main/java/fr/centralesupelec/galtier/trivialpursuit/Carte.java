package fr.centralesupelec.galtier.trivialpursuit;

import java.util.Vector;
import java.io.Serializable;

public class Carte implements  Serializable{
    private String question;
    private String bonneReponse;
    private Vector<String> mauvaisesReponses;

    public String getQuestion() {
        return question;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    public Vector<String> getMauvaisesReponses() {
        return mauvaisesReponses;
    }

    public Carte() {
        mauvaisesReponses = new Vector<String>();
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setBonneReponse(String bonneReponse) {
        this.bonneReponse = bonneReponse;
    }
    public void addMauvaiseReponse(String mauvaiseReponse) {
        mauvaisesReponses.add(mauvaiseReponse);
    }
}