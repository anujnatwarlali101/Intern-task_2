package com.example.studentscompanion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.os.Build.ID;

public class BackPaperRegisterActivity extends AppCompatActivity {

    private EditText txtname,txtadmin,txtcntct,txtbranch,txtsubjects;
    private TextView continueButton,change;
    private int count = 0,p=0;
    private Button saveButton;
    DatabaseReference reference;
    Register_bp_members members;
    String IDf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_paper_register);

        txtname = findViewById(R.id.bpr_name);
        txtadmin = findViewById(R.id.AdmnEditText);
        txtbranch = findViewById(R.id.BranchEdittext);
        txtcntct = findViewById(R.id.contactEditText);
        txtsubjects = findViewById(R.id.subEditText);
        continueButton = findViewById(R.id.continue_textView_bpregister);
        change = findViewById(R.id.edit_textView_bp_register);
        saveButton = findViewById(R.id.savebutton_bp_register);

        reference = FirebaseDatabase.getInstance().getReference("Back paper");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0)
                {
                    Intent intent = new Intent(BackPaperRegisterActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Toast.makeText(BackPaperRegisterActivity.this, "You shall be contacted by our Officials soon", Toast.LENGTH_SHORT).show();
                    finish();
                }

                else
                {
                    Toast.makeText(BackPaperRegisterActivity.this, "You need to enter the info first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                registerback();
                Toast.makeText(BackPaperRegisterActivity.this, "Viewing your saved Info", Toast.LENGTH_SHORT).show();
                reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        txtname.setText(dataSnapshot.child("mEname").getValue().toString());
                        txtadmin.setText(dataSnapshot.child("admiss").getValue().toString());
                        txtbranch.setText(dataSnapshot.child("branch").getValue().toString());
                        txtcntct.setText(dataSnapshot.child("contact").getValue().toString());
                        txtsubjects.setText(dataSnapshot.child("subs").getValue().toString());
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<0)
                {
                    Toast.makeText(BackPaperRegisterActivity.this, "Youneed to enter your info first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                Toast.makeText(BackPaperRegisterActivity.this, "You can make changes by editing again on the same page", Toast.LENGTH_SHORT).show();
                reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                    {
                        String n =  txtname.getText().toString().trim();
                        Long a = Long.parseLong(txtadmin.getText().toString().trim());
                        String b = txtbranch.getText().toString().trim();
                        Long c = Long.parseLong(txtcntct.getText().toString().trim());
                        String su = txtsubjects.getText().toString().trim();
                        members = new Register_bp_members(IDf,n,a,b,c,su);
                        reference.child(IDf).setValue(members);

                        txtname.setText(dataSnapshot.child("mEname").getValue().toString());
                        txtadmin.setText(dataSnapshot.child("admiss").getValue().toString());
                        txtbranch.setText(dataSnapshot.child("branch").getValue().toString());
                        txtcntct.setText(dataSnapshot.child("contact").getValue().toString());
                        txtsubjects.setText(dataSnapshot.child("subs").getValue().toString());


                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }}
        });

    }
    public void registerback()
    {
        String name =  txtname.getText().toString().trim();
        Long admin = Long.parseLong(txtadmin.getText().toString().trim());
        String branch = txtbranch.getText().toString().trim();
        Long contact = Long.parseLong(txtcntct.getText().toString().trim());
        String sub = txtsubjects.getText().toString().trim();

        if(name.isEmpty())
        {
            txtname.setError("This field is mandatory");
            txtname.requestFocus();
            return;
        }
        if(branch.isEmpty())
        {
            txtbranch.setError("Enter your Branch/Discipline");
            txtbranch.requestFocus();
            return;
        }
        if(sub.isEmpty())
        {
            txtsubjects.setError("Enter at least one subject");
            txtsubjects.requestFocus();
            return;
        }
        if(txtadmin.getText().toString().isEmpty())
        {
            txtadmin.setError("Enter your Admission/Registration No.");
            txtadmin.requestFocus();
            return;
        }
        if(txtcntct.getText().toString().isEmpty())
        {
            txtcntct.setError("Enter your Admission/Registration No.");
            txtcntct.requestFocus();
            return;
        }

        final String IDf = reference.push().getKey();
        members = new Register_bp_members(ID,name,admin,branch,contact,sub);
        reference.child(IDf).setValue(members);

    }


}
