package fr.centralesupelec.galtier.trivialpursuit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    TextView t;
    Button b1, b2, b3, b4;
    int questionId=1;
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
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        t=findViewById(R.id.textQuestion);
        Quiz Quiz1 = new Quiz();
        Vector<Carte>  get_cartes = Quiz1.getCartes();
        Carte carte = get_cartes.get(questionId-1);
        System.out.println(carte.getQuestion());
        t.setText(carte.getQuestion());

        b1.setText(carte.getBonneReponse());
        b2.setText(carte.getMauvaisesReponses().get(0));
        b3.setText(carte.getMauvaisesReponses().get(1));
        b4.setText(carte.getMauvaisesReponses().get(2));
    }

public void tirer(View view) {

    if(view.getId()==R.id.button1){
        Toast toast = new Toast(getApplicationContext());
        toast.setText("Bravo");
        toast.show();

        if(questionId < 3) {
            questionId += 1;
        }
        else{
            questionId=1;
        }
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        t=findViewById(R.id.textQuestion);
        Quiz Quiz1 = new Quiz();
        Vector<Carte>  get_cartes = Quiz1.getCartes();
        Carte carte = get_cartes.get(questionId-1);
        System.out.println(carte.getQuestion());
        t.setText(carte.getQuestion());

        b1.setText(carte.getBonneReponse());
        b2.setText(carte.getMauvaisesReponses().get(0));
        b3.setText(carte.getMauvaisesReponses().get(1));
        b4.setText(carte.getMauvaisesReponses().get(2));
    }
    else{
        Toast toast = new Toast(getApplicationContext());
        toast.setText("Mauvaise réponse...");
        toast.show();
    }


}
}