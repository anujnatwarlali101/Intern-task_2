package com.example.studentscompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SyllabusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private EditText Syllabustext;
    private TextView backTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        Syllabustext = findViewById(R.id.Syllabus_text);
        Syllabustext.setText("");

        toolbar = findViewById(R.id.TOOLBAR_LAYOUT);
        setSupportActionBar(toolbar);

        Spinner spinner = findViewById(R.id.Syllabus_Spinner);
        spinner.setOnItemSelectedListener(this);

        backTextView = findViewById(R.id.Syllabus_back_textview);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SyllabusActivity.this,DashboardActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String s= parent.getSelectedItem().toString();
        if(s.equalsIgnoreCase("First Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.First));
        }
        else if(s.equalsIgnoreCase("Second Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.Second));
        }
        else if(s.equalsIgnoreCase("Third Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.Third));
        }
        else if(s.equalsIgnoreCase("Fourth Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.Fourth));
        }
        else if(s.equalsIgnoreCase("Fifth Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.Fifth));
        }
        else if(s.equalsIgnoreCase("Sixth Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.Sixth));
        }
        else if(s.equalsIgnoreCase("Seventh Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.Seventh));
        }
        else if(s.equalsIgnoreCase("Eighth Semester"))
        {
            Syllabustext.setText(getApplicationContext().getResources().getString(R.string.Eighth));
        }
        else
        {
            Syllabustext.setText("No Semester Selected");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Toast.makeText(this, "Please Select a Semester", Toast.LENGTH_SHORT).show();
    }
}
