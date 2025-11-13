package fr.centralesupelec.galtier.trivialpursuit;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class QuestionResponseError implements Response.ErrorListener {

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.i("question", volleyError.getMessage());
    }
}
