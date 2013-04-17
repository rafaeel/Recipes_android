package br.com.rubythree.receitas;

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
		
		String name_detail = extras.getString("name");
		String ingredients_detail = extras.getString("ingredients");
		
		detail_recipe_name.setText(name_detail);
		detail_recipe_ingredients.setText(ingredients_detail);
		
	}

}
