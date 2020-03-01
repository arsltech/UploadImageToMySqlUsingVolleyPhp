package com.developer.arsltech.uploadimagetomysqlusingvolleyphp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowImages extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<ModelImage> imageList;
    ModelImage modelImage;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        imageList = new ArrayList<>();
        myAdapter = new MyAdapter(this,imageList);
        recyclerView.setAdapter(myAdapter);

        fetchImages();

    }

    public void fetchImages(){

        StringRequest request = new StringRequest(Request.Method.POST, "https://arsltechmysql.000webhostapp.com/fetchImages.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(succes.equals("1")){

                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String imageurl = object.getString("image");

                                    String url = "https://arsltechmysql.000webhostapp.com/Images/"+imageurl;

                                    modelImage = new ModelImage(id,url);
                                    imageList.add(modelImage);
                                    myAdapter.notifyDataSetChanged();

                                }





                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowImages.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}
