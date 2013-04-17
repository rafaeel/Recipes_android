package br.com.rubythree.receitas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeDetail extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_detail);
		
		//TextView detail_recipe_name = (TextView) findViewById(R.id.recipe_name_detail);
		//detail_recipe_name.setText("TESTE TESTANDO 1 2 3");
		
		TextView detail_recipe_name = (TextView) findViewById(R.id.recipe_name_detail);
		TextView detail_recipe_ingredients = (TextView) findViewById(R.id.recipe_ingredients_detail);
		
		Bundle extras = getIntent().getExtras();
		
		String jsonAr = extras.getString("data");
		
		try {
			JSONObject jsonO = new JSONObject(jsonAr);
			String name_detail = jsonO.getJSONObject("prescription").getString("name");
			String ingredients_detail = jsonO.getJSONObject("prescription").getString("ingredients");
			
			detail_recipe_name.setText(name_detail);
			detail_recipe_ingredients.setText(ingredients_detail);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String name_detail = extras.getString("name");
		//String ingredients_detail = extras.getString("ingredients");
		
		//detail_recipe_name.setText(name_detail);
		//detail_recipe_ingredients.setText(ingredients_detail);
		
	}

}
