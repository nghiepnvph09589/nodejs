package com.example.nodejs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpConnect httpConnect = new HttpConnect();
                httpConnect.execute();
            }
        });
    }
     public class  HttpConnect extends AsyncTask<Void, Void, String>{
        public static final String Server ="http://192.168.5.108:9994/getJsonUser";
            String result = "";
            String readLine = "";
         @Override
         protected String doInBackground(Void... voids) {
             try {
                 URL url = new URL(Server);
                 HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                 InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                 StringBuilder stringBuilder = new StringBuilder();
                 while ((readLine=bufferedReader.readLine())!=null)
                 {
                     stringBuilder.append(readLine);
                 }
                 result = stringBuilder.toString();
             } catch (MalformedURLException e) {
                 e.printStackTrace();
                 Log.e("Loi", e.getMessage());
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return result;
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             textView.setText(result);
         }
     }
}