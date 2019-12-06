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

public class TuCuaBan extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    List<YourWord> yourWords;
    YourWordAdapter adapter;
    ImageView search, tv;

    String urlGetData = "http://192.168.43.33:81/androidwebserver/GetDataTCB.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_cua_ban);

        searchView = findViewById(R.id.search);
        listView = findViewById(R.id.lvTCB);
        search = findViewById(R.id.imgTK);
        tv = findViewById(R.id.imgTV);

        yourWords = new ArrayList<YourWord>();
        adapter = new YourWordAdapter(this, yourWords, R.layout.tucuaban);
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                YourWord yourWord = (YourWord) adapter.getItem(position);
                Intent intent = new Intent(TuCuaBan.this, TraTu.class);

                intent.putExtra("Word", yourWord.getWord1());
                intent.putExtra("Type", yourWord.getType1());
                intent.putExtra("Pronounce", yourWord.getPronounce1());
                intent.putExtra("Meaning", yourWord.getMeaning1());
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuCuaBan.this, Search.class);
                startActivity(intent);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TuCuaBan.this, MainActivity.class);
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
//                        Toast.makeText(TuDaTra.this, response.toString(), Toast.LENGTH_LONG).show();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                yourWords.add(new YourWord(
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
                        Toast.makeText(TuCuaBan.this, error.toString(), Toast.LENGTH_LONG).show();
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
