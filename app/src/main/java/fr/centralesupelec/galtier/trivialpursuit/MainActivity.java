package fr.centralesupelec.galtier.trivialpursuit;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    TextView t;
    int questionId=1;
    Button vraieBouton;
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

        show();
    }

public void show(){
    LinearLayout layout = findViewById(R.id.main);//new LinearLayout(this);
    t=findViewById(R.id.textQuestion);
    Quiz Quiz1 = new Quiz();
    Vector<Carte>  get_cartes = Quiz1.getCartes();
    int nbAnswers = get_cartes.size();
    Carte carte = get_cartes.get(questionId-1);
    System.out.println(carte.getQuestion());
    t.setText(carte.getQuestion());

    if(layout.getChildCount()>2) {
        layout.removeViews(2, layout.getChildCount()-2 );
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
        toast.show();

        if(questionId < 3) {
            questionId += 1;
        }
        else{
            questionId=1;
        }
        show();
    }
    else{
        Toast toast = new Toast(getApplicationContext());
        toast.setText("Mauvaise réponse...");
        toast.show();
    }


}
}