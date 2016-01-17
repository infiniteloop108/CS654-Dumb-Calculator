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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private int state;
    private String a,b;
    private String op;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private Button b_del, b_calc, b_dot;
    private Button b_plus, b_minus, b_mult, b_div;
    private TextView input, result;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_calc = (Button) findViewById(R.id.button_calc);
        b_del= (Button) findViewById(R.id.button_D);
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
        result.setText("Result");
        state = -1;
        queue = Volley.newRequestQueue(this);
        b_del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longpressdel(v);
                return true;
            }
        });
        float sz = b1.getPaint().measureText("1");
        sz = sz-3;
        b0.setTextSize(sz);
        b1.setTextSize(sz);
        b2.setTextSize(sz);
        b3.setTextSize(sz);
        b4.setTextSize(sz);
        b5.setTextSize(sz);
        b6.setTextSize(sz);
        b7.setTextSize(sz);
        b8.setTextSize(sz);
        b9.setTextSize(sz);
        b_dot.setTextSize(sz);
        b_plus.setTextSize(sz);
        b_minus.setTextSize(sz);
        b_div.setTextSize(sz);
        b_mult.setTextSize(sz);
        sz = sz* (float) 0.66;
        b_del.setTextSize(sz);
        input.setTextSize(sz*(float)1.2);
        result.setTextSize(sz * (float)1.2);
        b_calc.setTextSize(sz * (float)0.80);
        setState(0);
    }

    private void setState(int s) {
        if(s==0 && state!=0) {
            //Initial state, No input given or just cleared
            state=0;
            a = "";
            b = "";
            op = "";
            b_calc.setEnabled(false);
            b_minus.setEnabled(true);
            b_plus.setEnabled(false);
            b_mult.setEnabled(false);
            b_div.setEnabled(false);
            b_del.setEnabled(false);
            b_dot.setEnabled(true);
            setInput();
        }
        else if(s==1 && state!=1) {
            //First number has starting minus
            state=1;
            b_minus.setEnabled(false);
            b_plus.setEnabled(false);
            b_mult.setEnabled(false);
            b_div.setEnabled(false);
            b_del.setEnabled(true);
            b_calc.setEnabled(false);
        }
        else if(s==2 && state!=2) {
            //First number has a dot in last position
            state=2;
            b_dot.setEnabled(false);
            b_del.setEnabled(true);
            if (a.equals(".") || a.equals("-.")) {
                b_minus.setEnabled(false);
                b_plus.setEnabled(false);
                b_mult.setEnabled(false);
                b_div.setEnabled(false);
                b_calc.setEnabled(false);
            }
        }
        else if(s==3 && state !=3) {
            //First number has a digit entered
            state=3;
            b_plus.setEnabled(true);
            b_minus.setEnabled(true);
            b_mult.setEnabled(true);
            b_div.setEnabled(true);
            b_del.setEnabled(true);
        }
        else if(s==4 && state!=4) {
            //Operator pressed between two numbers
            state=4;
            b_minus.setEnabled(true);
            b_plus.setEnabled(false);
            b_mult.setEnabled(false);
            b_div.setEnabled(false);
            b_dot.setEnabled(true);
            b_del.setEnabled(true);
            b_calc.setEnabled(false);
        }
        else if(s==5 && state!=5) {
            //Second number begins with minus
            state = 5;
            b_minus.setEnabled(false);
            b_plus.setEnabled(false);
            b_mult.setEnabled(false);
            b_div.setEnabled(false);
            b_calc.setEnabled(false);
        }
        else if(s==6 && state!=6) {
            //Second number has dot in last position
            state = 6;
            b_dot.setEnabled(false);
            b_minus.setEnabled(false);
            b_plus.setEnabled(false);
            b_mult.setEnabled(false);
            b_div.setEnabled(false);
            if(b.equals(".") || b.equals("-.")) {
                b_calc.setEnabled(false);
            }
        }
        else if(s==7 && state !=7) {
            //Second number has atleast one digit
            state=7;
            b_calc.setEnabled(true);
            b_minus.setEnabled(false);
            b_plus.setEnabled(false);
            b_mult.setEnabled(false);
            b_div.setEnabled(false);
        }
    }

    private void setInput()
    {
        if (a.length() == 0) {
            input.setText("Press numbers to input");
            return;
        }
        String current = a;
        if (op != "") {
            current = current + " " + op;
        }
        if(b != "") {
            current = current + " " + b;
        }
        input.setText(current);
    }

    public void press0(View view) {
        if(state < 4) {
            a += "0";
            setState(3);
        } else {
            b += "0";
            setState(7);
        }
        setInput();
    }

    public void press1(View view) {
        if(state < 4) {
            a += "1";
            setState(3);
        } else {
            b += "1";
            setState(7);
        }
        setInput();
    }

    public void press2(View view) {
        if(state < 4) {
            a += "2";
            setState(3);
        } else {
            b += "2";
            setState(7);
        }
        setInput();
    }

    public void press3(View view) {
        if(state < 4) {
            a += "3";
            setState(3);
        } else {
            b += "3";
            setState(7);
        }
        setInput();
    }

    public void press4(View view) {
        if(state < 4) {
            a += "4";
            setState(3);
        } else {
            b += "4";
            setState(7);
        }
        setInput();
    }

    public void press5(View view) {
        if(state < 4) {
            a += "5";
            setState(3);
        } else {
            b += "5";
            setState(7);
        }
        setInput();
    }

    public void press6(View view) {
        if(state < 4) {
            a += "6";
            setState(3);
        } else {
            b += "6";
            setState(7);
        }
        setInput();
    }

    public void press7(View view) {
        if(state < 4) {
            a += "7";
            setState(3);
        } else {
            b += "7";
            setState(7);
        }
        setInput();
    }

    public void press8(View view) {
        if(state < 4) {
            a += "8";
            setState(3);
        } else {
            b += "8";
            setState(7);
        }
        setInput();
    }

    public void press9(View view) {
        if(state < 4) {
            a += "9";
            setState(3);
        } else {
            b += "9";
            setState(7);
        }
        setInput();
    }

    public void pressplus(View view) {
        op = "+";
        setState(4);
        setInput();
    }

    public void pressminus(View view) {
        if(state == 0) {
            a += "-";
            setState(1);
        } else if (state == 4) {
            b+= "-";
            setState(5);
        } else {
            op = "-";
            setState(4);
        }
        setInput();
    }

    public void pressmult(View view) {
        op = "*";
        setState(4);
        setInput();
    }

    public void pressdiv(View view) {
        op = "/";
        setState(4);
        setInput();
    }

    public void pressdot(View view) {
        if(state < 4) {
            a += ".";
            setState(2);
        } else {
            b += ".";
            setState(6);
        }
        setInput();
    }

    public void pressdel(View view) {
        if(b != "") {
            int l = b.length();
            if(l == 1) {
                b = "";
                setState(4);
            } else if (b.charAt(l-1) == '.') {
                b = b.substring(0, l-1);
                b_dot.setEnabled(true);
            } else {
                b = b.substring(0, l-1);
                if(l==2) {
                    if(b.charAt(0) == '.')setState(6);
                    else if(b.charAt(0) == '-')setState(5);
                } else if(l==3) {
                    if(b.charAt(0) == '-' && b.charAt(1) == '.') setState(6);
                }
            }
        } else if (op != "") {
            op = "";
            setState(3);
        } else {
            int l = a.length();
            if(l == 1) {
                a = "";
                setState(0);
            } else if (a.charAt(l-1) == '.') {
                a = a.substring(0, l-1);
                b_dot.setEnabled(true);
            } else {
                a = a.substring(0, l-1);
                if(l==2) {
                    if(a.charAt(0) == '.')setState(2);
                    else if(a.charAt(0) == '-')setState(1);
                } else if(l==3) {
                    if(a.charAt(0) == '-' && a.charAt(1) == '.') setState(2);
                }
            }
        }
        setInput();
    }

    public void longpressdel(View view) {
        setState(0);
        setInput();
    }

    public void presscalc(View view)
    {
        String url = "http://" + getResources().getString(R.string.web_server) + "/api?";
        url += "a=" + a + "&op=";
        if (op.charAt(0) == '+') url += "1";
        else if (op.charAt(0) == '-') url += "2";
        else if (op.charAt(0) == '*') url += "3";
        else url += "4";
        url += "&b=" + b ;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("result");
                            result.setText(res);
                        } catch (JSONException e) {
                            result.setText("Something went wrong");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.setText("Something went wrong");
                    }
                }
        );
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
