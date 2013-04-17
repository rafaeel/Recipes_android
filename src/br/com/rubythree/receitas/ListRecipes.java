package br.com.rubythree.receitas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListRecipes extends Activity {
	
	String response;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_recipes);
		
		ListView list = (ListView) findViewById(R.id.list_recipes);
        
        final ArrayList<String> myList = new ArrayList<String>();
        
        ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        //String response = makeRequest("http://api.crunchbase.com/v/1/search.js?query=sony&api_key=xkbgxdv3ayxggp2m54ba65u7");
        response = makeRequest("http://192.168.0.25:3000/prescriptions.json");
        //response = makeRequest("http://192.168.0.102:3000/prescriptions.json");
        
        try{
        //JSONObject json = new JSONObject(response);
        JSONArray jsonA = new JSONArray(response);
        
        for(int i = 0; i < jsonA.length(); i++){
        	JSONObject n = jsonA.getJSONObject(i);
        	String name_of_recipe = n.getJSONObject("prescription").getString("name");
         	myList.add(name_of_recipe);
        }   
        
        }catch (JSONException e){
        	e.printStackTrace();
        
        }
        ArrayAdapter<String> AA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        list.setAdapter(AA);
        
       list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				String ind = ((TextView) arg1).getText().toString();
				
				try{
			        //JSONObject json = new JSONObject(response);
			        JSONArray jsonA = new JSONArray(response);
			        
			       
			        	JSONObject aux = jsonA.getJSONObject(position);
			        	String varAux = jsonA.getJSONObject(position).toString();
			        	String nome = aux.getJSONObject("prescription").getString("name");
			        	String ingredientes = aux.getJSONObject("prescription").getString("ingredients");
			         	Log.d("NAME", nome);
						Log.d("INGREDIENTS", ingredientes);
						Intent in = new Intent(ListRecipes.this, RecipeDetail.class);
						in.putExtra("data", varAux);
						//in.putExtra("name", nome);
						//in.putExtra("ingredients", ingredientes);
						startActivity(in);
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
			       
				
				//Toast.makeText(ListRecipes.this, "Selecionado", Toast.LENGTH_LONG).show();
				Log.d("SELECIONADO", "SELECIONADO " + ind);
				//Toast.makeText(ListRecipes.this, "Adapter " + arg0, Toast.LENGTH_LONG).show();
				//Toast.makeText(ListRecipes.this, "posicao " + position, Toast.LENGTH_LONG).show();
				//Toast.makeText(ListRecipes.this, "Long " + arg3, Toast.LENGTH_LONG).show();
				}
        	
        }); 

	}
	

	private String makeRequest(String urlAddress){
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		try {
			url = new URL(urlAddress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		
		return response;
	}
	
	private String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null){
				try{
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}
}