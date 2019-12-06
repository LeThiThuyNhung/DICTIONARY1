package com.example.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TuDaTra extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    List<TDT> tdt;
    TDTAdapter adapter;
    ImageView search, tv;

    String urlGetData = "http://192.168.43.33:81/androidwebserver/GetDataTDT.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_da_tra);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.lvTDT);
        search = findViewById(R.id.imgsearch);
        tv = findViewById(R.id.imgCCel);

        tdt = new ArrayList<TDT>();
        adapter = new TDTAdapter(this, tdt, R.layout.tudatra);
        listView.setAdapter(adapter);

        GetData(urlGetData);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuDaTra.this, Search.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TDT tdt = (TDT) adapter.getItem(position);
                Intent intent = new Intent(TuDaTra.this, TraTu.class);

                intent.putExtra("Word", tdt.getWord2());
                intent.putExtra("Type", tdt.getType2());
                intent.putExtra("Pronounce", tdt.getPronounce2());
                intent.putExtra("Meaning", tdt.getMeaning2());
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuDaTra.this, Search.class);
                startActivity(intent);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuDaTra.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void GetData(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                tdt.add(new TDT(
                                        object.getString("Word"),
                                        object.getString("Type"),
                                        object.getString("Pronounce"),
                                        object.getString("Meaning")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TuDaTra.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

        jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

    }

}
