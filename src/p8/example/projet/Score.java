package p8.example.projet;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


@SuppressLint("NewApi")
public class Score extends Activity {
//
	private SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	
	Button Rejoue,sauvegarder;
	private Grille_sudoku  grille_sudoku;
	static int second=0;
	static int minute=0;
	static int heure=0;
	int counter,compte;
	static int nb=0;
	static int	nbName=0;
	String value;
	String ScoreStr,ScoreStr1,ScoreStr2,ScoreStr3,NomStr;
	public static int enregister=0;
	AlertDialog.Builder boite3; 
	View score,score1,score2,score3,Nom;
	int cpt1=0;
	int cpt2=0;
	int cpt3=0;
	int cpt=0;
	public Score()
	{

	}
	 
	public void initDialog(){
		boite3 = new AlertDialog.Builder(this);
		 boite3.setTitle("Felicitation "); 
	      boite3.setIcon(R.drawable.ic_launcher);
	      boite3.setMessage("score obtenu : "+getScore(getCounter()));
	      
  
	}
public void enregisrerNom(){

	initDialog();
	  final EditText input = new EditText(this);
	  
	  boite3.setView(input);
   
      boite3.setPositiveButton("Sauvegarder", new DialogInterface.OnClickListener() {
      	
      	public void onClick(DialogInterface dialog, int which) {
 		
      		value = input.getText().toString();      		   
		    ScoreStr=getScore(getCounter());		    
		    NomStr=getName();
		    addScore(ScoreStr,NomStr);
	       		 	
      	}
      	}
      ); 
  boite3.setNeutralButton("Rejouer", new DialogInterface.OnClickListener() {
      	
      	public void onClick(DialogInterface dialog, int which) {      	
      		finish();		   	            
      	}}); 
   boite3.setNegativeButton("quitter", new DialogInterface.OnClickListener() {
      	
      	public void onClick(DialogInterface dialog, int which) {
   		finish();	        			
      	    }
      	}
   ); 
  
      boite3.show();
    }
	public String getName()
	{	
	return value;
	}

	public int getCounter()
	{
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		 counter=preferences.getInt("cpt",grille_sudoku.compte);
	
	 
	return counter;
	}
	public String getScore(int counter)
	{
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		 counter=preferences.getInt("cpt",grille_sudoku.compte);
		second=(int) (counter/1000);
		minute=(int) (counter/60000);
		heure=(int) (counter/3600000);
		 // Dessine le temps écoulé sous forme de h:mm:ss  
		 
	 String Score=heure +":"+(minute/10)%6+(minute%10)+":"+(second/10)%6+second%10;
	 
	return Score;
	}


	public void addScore(String score,String name)
	{
		 editor = preferences.edit();
 		
 		 ScoreStr= name+"      "+score;
 		
 		 compte=getCounter(); 		
	 
 	 	 String compteStr=String.valueOf(compte);
 	 	 
		 editor.putString(compteStr,ScoreStr);
		 
		 editor.apply();	 
		 
		 MajScore();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
	
	if (enregister==1)	enregisrerNom();	 
		enregister=0;
	  preferences = PreferenceManager.getDefaultSharedPreferences(this);
		 
	  initScore();

	  
	}
public void initScore(){
	
	
	 cpt1=preferences.getInt("cpt1", cpt1);
	 cpt2=preferences.getInt("cpt2", cpt2);
	 cpt3=preferences.getInt("cpt3", cpt3);
	 
	 String CPT1=String.valueOf(cpt1);
	 String CPT2=String.valueOf(cpt2);
	 String CPT3=String.valueOf(cpt3);
	 
	 
	 
	  ScoreStr1=preferences.getString(CPT1,ScoreStr1 );
	  ScoreStr2=preferences.getString(CPT2,ScoreStr2 );
	  ScoreStr3=preferences.getString(CPT3,ScoreStr3 );
	 
	score1 = findViewById(R.id.txtScore1);			
   ((TextView) score1).setText(ScoreStr1);
   
   score2 = findViewById(R.id.txtScore2);			
   ((TextView) score2).setText(ScoreStr2);
   
   score3 = findViewById(R.id.txtScore3);			
   ((TextView) score3).setText(ScoreStr3);
		
	 
	 	
}
	private void MajScore(){
		
		if(ScoreStr!=null ){
		editor = preferences.edit();
			String CPT1=String.valueOf(cpt1);
			String CPT2=String.valueOf(cpt2);
			String CPT3=String.valueOf(cpt3);//valeur compteur
			 int  intent = grille_sudoku.choix_grille;
		 if	 (intent==1){
			if(compte<cpt1){ 

		 		 editor.putString(CPT1,ScoreStr );		 		  
		 		 score = findViewById(R.id.txtScore1);	 						 		 		 
			     editor.putInt("cpt1",compte); 
			     
			 }
			}
		 else if(intent==2){
		 	  if(compte<cpt2){
		 		 
		 		 editor.putString(CPT2,ScoreStr );				 
			 	 score = findViewById(R.id.txtScore2);
			 	 editor.putInt("cpt2",compte);
			      }}
		 else if(intent==3){
		 	 		 if(compte<cpt3){
		 	 			
		 	 			   editor.putString(CPT3,ScoreStr );
		 	 			   score = findViewById(R.id.txtScore3);	
		 	 			   editor.putInt("cpt3",compte); 								 
		 	 			}	
		 }
		 	((TextView) score).setText(ScoreStr);
		 	editor.apply();     	
		}		 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.score, menu);
		return true;	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}