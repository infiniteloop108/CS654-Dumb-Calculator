package com.nonexistent.akshayaggarwal.akshaydac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    private int state;
    private String a,b;
    private String op;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private Button bD, b_calc, b_dot;
    private Button b_plus, b_minus, b_mult, b_div;
    private TextView input, result;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_calc = (Button) findViewById(R.id.button_calc);
        bD= (Button) findViewById(R.id.button_D);
        b_dot = (Button) findViewById(R.id.button_dot);
        b0 = (Button) findViewById(R.id.button_0);
        b1 = (Button) findViewById(R.id.button_1);
        b2 = (Button) findViewById(R.id.button_2);
        b3 = (Button) findViewById(R.id.button_3);
        b4 = (Button) findViewById(R.id.button_4);
        b5 = (Button) findViewById(R.id.button_5);
        b6 = (Button) findViewById(R.id.button_6);
        b7 = (Button) findViewById(R.id.button_7);
        b8 = (Button) findViewById(R.id.button_8);
        b9 = (Button) findViewById(R.id.button_9);
        b_plus = (Button) findViewById(R.id.button_plus);
        b_minus = (Button) findViewById(R.id.button_minus);
        b_mult = (Button) findViewById(R.id.button_mult);
        b_div = (Button) findViewById(R.id.button_div);
        input = (TextView) findViewById(R.id.text_input);
        result = (TextView) findViewById(R.id.text_result);
        state = -1;
        queue = Volley.newRequestQueue(this);
        setState(0);
    }

    private void setState(int s) {
        if(s==0 && state!=0) {
            //Initial state, No input given or just cleared
            state=0;
            b_calc.setEnabled(false);
            b_plus.setEnabled(false);
            b_minus.setEnabled(false);
            b_mult.setEnabled(false);
            b_div.setEnabled(false);
            input.setText("Press numbers to input");
            result.setText("Result");
        }
        else if(s==1 && state!=1) {
            state=1;
            b_plus.setEnabled(true);
            b_minus.setEnabled(true);
            b_mult.setEnabled(true);
            b_div.setEnabled(true);
        }
    }

    public void press1(View view) {


        setState(1);
    }

    public void press2(View view) {

        setState(0);
    }

    public void press3(View view) {

        String url = getResources().getString(R.string.web_server);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        result.setText("Response is: "+ response.substring(0,10));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("That didn't work!");
                    }
                }
        );
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        setState(0);
    }

}
