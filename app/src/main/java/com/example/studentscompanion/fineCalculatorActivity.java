package com.example.studentscompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class fineCalculatorActivity extends AppCompatActivity {

    EditText numbook,numday;
    int day,fine;
    Button cal;
    TextView res,dash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_calculator);

        numbook = findViewById(R.id.fine_calculator_books);
        numday = findViewById(R.id.fine_calculator_days);
        cal = findViewById(R.id.fine_calculator_calculate);
        res = findViewById(R.id.fine_calculator_text_msg);
        dash = findViewById(R.id.fine_calculator_dashboard);

        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fineCalculatorActivity.this,DashboardActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numbook.getText().toString().isEmpty())
                {
                    numbook.setError("The no. of books required");
                    numbook.requestFocus();
                    return;
                }
                if(numday.getText().toString().isEmpty())
                {
                    numday.setError("The no. of days is required");
                    numday.requestFocus();
                    return;
                }
                int books = Integer.parseInt(numbook.getText().toString());
                int days = Integer.parseInt(numday.getText().toString());
                if(days<=15)
                {
                    res.setText("No Fine has been incurred till now. The standard fine rates start after 15 days of issue.");
                }
                else
                {
                    day = days-15;
                    fine = day*books;
                    res.setText("You have been charged with a fine of Rs."+fine+".You need to pay the fine in the form of challan.");
                }

            }
        });
    }
}
