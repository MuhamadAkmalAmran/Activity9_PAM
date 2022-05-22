package com.example.activity7_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditTeman extends AppCompatActivity {
    TextView idText;
    EditText edNama, edTelpon;
    Button editBtn;
    String nma, tlp, id, namaEd, telponEd;
    int sukses;

    private static String url_select = "http://10.0.2.2/umyTI/updatetm.php";
    public static final String TAG = EditTeman.class.getSimpleName();
    public static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_edit_teman);

        idText = findViewById(R.id.textId);
        edNama = findViewById(R.id.edNm);
        edTelpon = findViewById(R.id.edTlp);
        editBtn = findViewById(R.id.buttonEdit);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("kunci1");
        nm = bundle.getString("kunci2");
        tlp = bundle.getString("kunci3");

        idText.setText("Id : " + id);
        edNama.setText(nm);
        edTelpon.setText(tlp);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditData();
                }
        });
    }

    public void  EditData()
    {
        namaEd = edNama.getText().toString();
        telponEd = edTelpon.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon : " + response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    sukses = jsonObject = jsonObject.getInt(TAG_SUCCES);
                    if (sukses == 1) {
                        Toast.makeText(EditTeman.this, "Sukses mengedit data", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(EditTeman.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Error : " +error.getMessage());
                Toast.makeText(EditTeman.this, "Gagal Edit data", Toast.LENGTH_SHORT).show();
            }
        })
    }
}