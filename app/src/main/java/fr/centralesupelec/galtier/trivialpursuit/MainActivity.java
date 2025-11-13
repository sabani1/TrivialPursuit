package fr.centralesupelec.galtier.trivialpursuit;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import java.io.Serializable;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.button.MaterialButton;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    TextView t;
    int questionId=1;
    Button vraieBouton;
    int points = 0 ;
    int essaie = 0;
    int totalPoints;

    int totalSearch = 0;

    Quiz Quiz1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent mIntent = getIntent();
        Quiz1 =  mIntent.getSerializableExtra("s", Quiz.class);
        assert Quiz1 != null;

        show();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("Cycle", "La langue était changée");
    }

    public void show(){
        essaie = 0;
        LinearLayout layout = findViewById(R.id.main);//new LinearLayout(this);
        t=findViewById(R.id.textQuestion);
        Vector<Carte>  get_cartes = Quiz1.getCartes();
        int nbAnswers = get_cartes.size();
        int nbQuestions = Quiz1.getNbCartes();
        Carte carte = get_cartes.get(questionId-1);
        System.out.println(carte.getQuestion());
        t.setText(carte.getQuestion());

        if(layout.getChildCount()>3) {
            layout.removeViews(3, layout.getChildCount()-3);
        }

        Vector <String> propositions = carte.getMauvaisesReponses();
        propositions.add(carte.getBonneReponse());
        Collections.shuffle(propositions);

        for (int i=0; i<propositions.toArray().length; i++){
            MaterialButton bouton = new MaterialButton(this);
            bouton.setText(propositions.get(i));
            bouton.setId(i);
            layout.addView(bouton);
            if (propositions.get(i).equals(carte.getBonneReponse())) {
                vraieBouton = bouton;
            }
            bouton.setOnClickListener(this::tirer);
        }

}

public void tirer(View view) {


    if(view.getId()==vraieBouton.getId()){
        Toast toast = new Toast(getApplicationContext());
        toast.setText("Bravo");
        Vector<Carte>  get_cartes = Quiz1.getCartes();
        toast.show();
        int nbQuestions = Quiz1.getNbCartes();

        if(essaie == 0){
            points += 2;
        }
        else if(essaie == 1){
            points += 1;
        }

        if(questionId < nbQuestions) {
            questionId += 1;
        }
        else{
            Intent intentPoints = new Intent(this, PointsActivity.class);
            intentPoints.putExtra("p",points);
            totalPoints = nbQuestions * 2;
            intentPoints.putExtra("q",totalPoints);
            intentPoints.putExtra("r", totalSearch);
            startActivity(intentPoints);
        }
        show();
    }
    else{
        essaie += 1;
        Toast toast = new Toast(getApplicationContext());
        toast.setText("Mauvaise réponse...");
        toast.show();
    }


}

    public void chercher(View view) {
        totalSearch += 1;
        Button bGoogle = findViewById(R.id.buttonAide);
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        Vector<Carte>  get_cartes = Quiz1.getCartes();
        Carte carte = get_cartes.get(questionId-1);
        String question_search = carte.getQuestion();
        intent.putExtra(SearchManager.QUERY, question_search);
        startActivity(intent);
    }
}