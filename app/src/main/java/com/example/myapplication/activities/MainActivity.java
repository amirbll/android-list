package com.example.myapplication.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.response.RequestDetailResponse;
import com.example.myapplication.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //Creating a retrofit object
     Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build();


    //creating the api interface
    Api api = retrofit.create(Api.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewUniversities);

        getUniversities();

    }


    private void getUniversities() {



        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<RequestDetailResponse>> call = api.getUniversities();

        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<List<RequestDetailResponse>>() {
            @Override
            public void onResponse(Call<List<RequestDetailResponse>> call, Response<List<RequestDetailResponse>> response) {

                //In this point we got our hero list
                //thats damn easy right ;)
                List<RequestDetailResponse> universitiesList = response.body();

                //Creating an String array for the ListView
                String[] universities = new String[universitiesList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < universitiesList.size(); i++) {
                    universities[i] = universitiesList.get(i).getName();
                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, universities));

                Toast.makeText(getApplicationContext(), "Data Received", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<RequestDetailResponse>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
