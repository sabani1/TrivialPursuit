package fr.centralesupelec.galtier.trivialpursuit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import android.os.Bundle;
import android.util.Log;

public class QuestionResponse implements Response.Listener<String> {

    private Quiz quiz;
    private Context context;

    public QuestionResponse(Quiz quiz, Context context) {
        this.quiz = quiz;
        this.context = context;

    }

    @Override
    public void onResponse(String response) {
        Log.i("carte", response);
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray results = jsonResponse.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject questionObject = results.getJSONObject(i);
                String question = questionObject.getString("question");
                String correctAnswer = questionObject.getString("correct_answer");
                JSONArray incorrectAnswers = questionObject.getJSONArray("incorrect_answers");

                Carte carte = new Carte();
                carte.setQuestion(question);
                carte.setBonneReponse(correctAnswer);

                for (int j = 0; j < incorrectAnswers.length(); j++) {
                    carte.addMauvaiseReponse(incorrectAnswers.getString(j));
                }

                quiz.ajouterCarte(carte);
                Intent intentQuiz = new Intent(context, MainActivity.class);
                intentQuiz.putExtra("s", quiz);
                context.startActivity(intentQuiz);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}