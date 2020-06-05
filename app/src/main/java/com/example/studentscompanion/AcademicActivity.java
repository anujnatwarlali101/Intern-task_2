package com.example.studentscompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AcademicActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView dashtextView;
    private TextView backTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);

        toolbar = findViewById(R.id.toolbar_layout_students);
        setSupportActionBar(toolbar);

        dashtextView = findViewById(R.id.Academic_section_dashboard_textview);
        dashtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcademicActivity.this,DashboardActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        backTextView = findViewById(R.id.Academic_section_back_textview);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AcademicActivity.this,BackPaperRegisterActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });
    }}


