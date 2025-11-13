package fr.centralesupelec.galtier.trivialpursuit;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuestionResponse implements Response.Listener<String> {

    Carte carte1;
    Quiz quiz1;
    public QuestionResponse(){
    }

    @Override
    public void onResponse(String response) {
        Log.i("carte", response);
        try {
            JSONObject jsonResponse = new JSONObject(response);
            String question = jsonResponse.getString("question");
            String bonneReponse = jsonResponse.getString("correct_answer");
            JSONArray mauvaisesReponses = jsonResponse.getJSONArray("incorrect_answers");
            carte1.setQuestion(question);
            carte1.setBonneReponse(bonneReponse);
            int ItemsThisPage = mauvaisesReponses.length();
            System.out.println("abc Items" + ItemsThisPage);

            for(int i=0; i<ItemsThisPage; i++){
                carte1.addMauvaiseReponse(mauvaisesReponses.getString(i));
                System.out.println("abc ajoute reponse" + i);
            }


            quiz1.ajouterCarte(carte1);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
