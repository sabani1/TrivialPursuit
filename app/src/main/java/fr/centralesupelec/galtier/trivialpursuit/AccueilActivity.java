package fr.centralesupelec.galtier.trivialpursuit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AccueilActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);

        Quiz quiz1 = new Quiz();
        //quiz1.creerQuizDefaut();

        requestQueue = Volley.newRequestQueue(getApplicationContext());;
        Response.Listener<String> responseListenerQ = new QuestionResponse(quiz1, this);
        Response.ErrorListener errorListenerQ = new QuestionResponseError();
        StringRequest request = new StringRequest(Request.Method.GET,
                "https://opentdb.com/api.php?amount=10",
                responseListenerQ ,
                errorListenerQ
        );
        requestQueue.add(request);

    }
}