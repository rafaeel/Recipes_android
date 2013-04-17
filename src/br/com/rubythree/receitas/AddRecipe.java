package br.com.rubythree.receitas;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static us.monoid.web.Resty.*;
import us.monoid.web.Resty;
import us.monoid.web.Resty.*;

public class AddRecipe extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_recipe);
		
		setThreadPolicy();
		
		Button saveRecipe = (Button) findViewById(R.id.add_recipe_button);
		saveRecipe.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					postRecipe();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setThreadPolicy() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);		
	}

	public void postRecipe() throws IOException {
		EditText nameEdit = (EditText) findViewById(R.id.recipe_name_edit);
		EditText ingredientsEdit = (EditText) findViewById(R.id.recipe_ingredients_edit);
		String name = nameEdit.getText().toString();
		String ingredients = ingredientsEdit.getText().toString();
		// "name"=>"Arroz", "ingredients"=>"Pacote de arroz, agua"}
		try{
			new Resty().json("http://192.168.0.102:3000/prescriptions.json", //json("http://192.168.0.25:3000/prescriptions", 
					  	form(data("prescription[name]", name),
					  		 data("prescription[ingredients]", ingredients)));
			Toast.makeText(AddRecipe.this, "Your recipe has been saved", Toast.LENGTH_LONG).show();
			nameEdit.setText("");
			ingredientsEdit.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
