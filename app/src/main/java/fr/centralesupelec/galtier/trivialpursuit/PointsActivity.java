package fr.centralesupelec.galtier.trivialpursuit;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PointsActivity extends AppCompatActivity {
    final String CLE_NB_RECORD = "nbRecord";
    final String CLE_NOM_RECORD = "nomRecord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_points);

        SharedPreferences sharedPreferences1 = getSharedPreferences("record", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();

        TextView tv = findViewById(R.id.textView);
        TextView tvR = findViewById(R.id.textViewRecord);
        TextView tvC = findViewById(R.id.textView2);

        Intent appelant = getIntent();
        int points = appelant.getIntExtra("p",0);
        int totalPoints = appelant.getIntExtra("q", 0);
        int totalSearch = appelant.getIntExtra("r", 0);
        Button bouton = findViewById(R.id.buttonEnregistrer);
        EditText et = findViewById(R.id.editTextNom);
        et.setVisibility(VISIBLE);
        bouton.setVisibility(VISIBLE);
        float score = (float)points / totalPoints;

        String name = et.getText().toString();

        if (sharedPreferences1.contains(CLE_NB_RECORD)){
            float n = sharedPreferences1.getFloat(CLE_NB_RECORD, score);
            String m = sharedPreferences1.getString(CLE_NOM_RECORD, name);
            if (sharedPreferences1.getFloat(CLE_NB_RECORD, 0) < score) {
                tvR.setText("Le précédent record (" + n + ") était détenu par " + m + ". Vous établissez le nouveau record !");
                editor1.putFloat(CLE_NB_RECORD, score);
                editor1.apply();

            }
            else {
                et.setVisibility(INVISIBLE);
                bouton.setVisibility(INVISIBLE);
                tvR.setText("Ce n'est pas suffisant de prendre à " + m + " le titre de meilleur joueur avec un score de "+ n);
            }
        }
        else{
            editor1.putFloat(CLE_NB_RECORD, score);
            editor1.apply();
            tvR.setText("Vous etablissez le premier record !");
        }

        tv.setText("Vous avez marqué "+ points +" points sur " + totalPoints+"\nScore: " + score);
        tvC.setText("Vous avez utilisé Google " + totalSearch+ " fois.");
    }

    public void enregistrer(View view) {

        SharedPreferences sharedPreferences1 = getSharedPreferences("record", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        EditText et = findViewById(R.id.editTextNom);
        String name = et.getText().toString();
        editor1.putString(CLE_NOM_RECORD, name);
        editor1.apply();
    }
}