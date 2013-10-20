package com.fbhack.ourgroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.davidgassner.plainolnotes.R;
import com.fbhack.ourgroup.data.NoteItem;

public class NoteEditorActivity extends SherlockActivity {

	private NoteItem note;
	Button bStartDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_editor);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				
		Intent intent = this.getIntent();
		note = new NoteItem();
		note.setKey(intent.getStringExtra("key"));
		note.setText(intent.getStringExtra("text"));
		
		EditText et = (EditText) findViewById(R.id.noteText);
		et.setText(note.getText());
		et.setSelection(note.getText().length());
	}
	
  
	
	private void saveAndFinish() {
		EditText et = (EditText) findViewById(R.id.noteText);
		String noteText = et.getText().toString();
		Intent intent = new Intent();
		intent.putExtra("key", note.getKey());
		intent.putExtra("text", noteText);
		setResult(RESULT_OK, intent);
		finish();
		
	} 
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			saveAndFinish();
		}
		return false;
	}
	
	
	@Override
	public void onBackPressed() {
		saveAndFinish();
	}
	
	
	   
	

	
}
