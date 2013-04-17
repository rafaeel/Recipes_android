package br.com.rubythree.receitas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button addRecipe = (Button) findViewById(R.id.activity1);
		addRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent addRecipeIntent = new Intent(MainActivity.this, AddRecipe.class);
				startActivity(addRecipeIntent);
			}
		});
		
		Button listRecipes = (Button) findViewById(R.id.activity2);
		listRecipes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent listRecipesIntent = new Intent(MainActivity.this, ListRecipes.class);
				startActivity(listRecipesIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
