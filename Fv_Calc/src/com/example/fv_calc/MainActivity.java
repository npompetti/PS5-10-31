package com.example.fv_calc;

import java.text.NumberFormat;
import java.util.Locale;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import Future_value.Calc;

public class MainActivity extends ActionBarActivity {
	
	int years_met;
	Spinner years_spinner;
	EditText initial_invest;
	Double double_initial;
	EditText interest;
	Double double_interest;
	String answer;
	int counter = 0;
	String decimal = ".";
	int nbr_decimals = 0;
	EditText answer_block;
	Button Calc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		initial_invest = (EditText)findViewById(R.id.initial_edit);
		interest = (EditText)findViewById(R.id.interest_edit);
		answer_block= (EditText)findViewById(R.id.answerBlock);
		
		initial_invest.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				checkIfEmpty();
				String initial_input = s.toString();
				
				
				if(counter != 3){
					
					if(initial_input.contains(".")){
						counter+=1;
						Calc.setEnabled(true);
					}
								
				}
				else{
					answer_block.setText("Only two decimal places are valid");
					
				
			}
			}

		});
		
		interest.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				checkIfEmpty();
				String interest_input = s.toString();
				
				
				if(counter != 3){
					
					if(interest_input.contains(".")){
						counter+=1;
						Calc.setEnabled(true);
					}
								
				}
				else{
					answer_block.setText("Only two decimal places are valid");
					
					
					
				}
				
				answer_block.setText("" + counter);
			}
			
		});
		
		
		
		
		
		
		//Setting Up values for the spinner
		years_spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.num_years, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		years_spinner.setAdapter(adapter);
		
		
		
		
		
		
		
		
		//Calculate Button with method included
		Calc = (Button)findViewById(R.id.btnCalculate);
		
		Calc.setOnClickListener(new View.OnClickListener() {
			//Method for when calculate button is clicked
			@Override
			public void onClick(View v) {
				//Initial Investment
				
				double_initial = Double.parseDouble(initial_invest.getText().toString());
				
				
				//Years Spinner Value
				years_met = Integer.parseInt((years_spinner.getSelectedItem().toString()));
				
				//Interest edit value
				
				
				
				double_interest = Double.parseDouble(interest.getText().toString());
				
				
				answer = calc_fv(double_initial, years_met, double_interest);
				
				
				answer_block.setText(" "+answer);
				
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void checkIfEmpty(){
		
		String s1 = initial_invest.getText().toString();
		String s3 = interest.getText().toString();
		
		if (s1.equals("") || s3.equals("")){
			Calc.setEnabled(false);
		}else{
			Calc.setEnabled(true);
		}
		
	}
	
	public static String calc_fv(double i_investment, int years, double interest_rate){
		double future_value;
		double int_rate= interest_rate / 100;
		future_value = i_investment * Math.pow((1 + int_rate), years);
		
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		
		String ans = n.format(future_value);
		return ans;
	}
	
}
