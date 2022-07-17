package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ranking extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    TextView textViewUrl,textViewResult;
    Button buttonGo;
    ListView list;
    String [] listItems;
    JSONObject[] jsObjArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        textViewUrl =findViewById(R.id.textViewUrl);
        textViewResult =findViewById(R.id.textViewResult);
        buttonGo =findViewById(R.id.buttonGo);
        list =findViewById(R.id.listViewCode);
        buttonGo.setOnClickListener(this);
        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);
    }

    public void onClick(View view){//點擊顯示排行榜
        InputStream inputStream = null;
        String result = "";
        URL url = null;
        try{
            url = new URL(textViewUrl.getText().toString());//get xampp
            HttpURLConnection con =(HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.connect();

            inputStream = con.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while ((line=bufferedReader.readLine())!=null)
                result+=line;
            inputStream.close();

            //JSONObject json = new JSONObject(result);
            JSONArray jsonArray = new JSONArray(result);
            jsObjArray = new JSONObject[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                jsObjArray[i] = jsonArray.getJSONObject(i);
            }
            listItems = new String[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                //listItems[i] = jsObjArray[i].getString("Name");
                String rank = "Rank"+(i+1);
                String name = jsObjArray[i].getString("Name");
                String Duration = jsObjArray[i].getString("Duration")+"s";

                //for(int j=0;j<jsonArray.length(); j++){
                    //if(jsObjArray[i].getInt("Duration") < jsObjArray[j].getInt("Duration")){
                   // String [] listrank = new String[jsonArray.length()];
                        //listrank[i] =
                    //}
                //}
                listItems[i] = String.format("%-10s %-20s %-6s",rank,name,Duration);//set data to list
            }
            list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems));
            //set data to list to show



        }catch (Exception e){
            textViewResult.setText(e.getMessage());
        }

    }
    public void onItemClick(AdapterView<?> a,View v,int position,long id){
        textViewResult.setText(listItems[position]+"selected.");
    }



}

