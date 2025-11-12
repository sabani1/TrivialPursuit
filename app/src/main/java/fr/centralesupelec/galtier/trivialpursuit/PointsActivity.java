package fr.centralesupelec.galtier.trivialpursuit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PointsActivity extends AppCompatActivity {
    final String CLE_NB_RECORD = "nbRecord";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_points);

        Intent appelant = getIntent();
        int points = appelant.getIntExtra("p",0);
        int totalPoints = appelant.getIntExtra("q", 0);
        TextView tv = findViewById(R.id.textView);
        float score = (float)points / totalPoints;

        TextView textViewRecord = findViewById(R.id.textViewRecord);
        EditText editTextNom = findViewById(R.id.editTextNom);
        String name = editTextNom.getText().toString();;

        SharedPreferences sharedPreferences1 = getSharedPreferences("record", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        if (sharedPreferences1.contains(CLE_NB_RECORD)){
            float n = sharedPreferences1.getFloat(CLE_NB_RECORD, score);
            textViewRecord.setText("Le record est : " +n);
            editor1.putFloat(CLE_NB_RECORD, n);
            editor1.apply();
        }
        else{
            editor1.putFloat(CLE_NB_RECORD, score);
            editor1.commit();
            textViewRecord.setText("Vous etablissez le premier record !");
        }

        tv.setText("Vous avez marqué "+ points +" points sur " + totalPoints+"\nScore: " + score);
    }
}