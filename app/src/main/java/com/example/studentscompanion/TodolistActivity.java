package com.example.studentscompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TodolistActivity extends AppCompatActivity {

    EditText list;
    TextView dashboad;
    Button add;
    Spinner spin;
    DatabaseReference refer;
    ListView todolistview;
    List<TodoItemsADDING> itemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        list= findViewById(R.id.todo_list_item_edittext);
        spin = findViewById(R.id.todo_heading_spinner);
        add = findViewById(R.id.todo_list_add_button);
        dashboad = findViewById(R.id.todo_list_dashboard);
        todolistview = findViewById(R.id.todolistitemsview);
        itemsList = new ArrayList<>();
        refer = FirebaseDatabase.getInstance().getReference("To-Do lIst");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additems();
                Toast.makeText(TodolistActivity.this, "Item added", Toast.LENGTH_SHORT).show();
            }
        });

        dashboad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodolistActivity.this,DashboardActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemsList.clear();
                for(DataSnapshot itemSnapshot:dataSnapshot.getChildren())
                {
                    TodoItemsADDING todoItemsADDING = itemSnapshot.getValue(TodoItemsADDING.class);
                    itemsList.add(todoItemsADDING);
                }
                todo_list_adapter adapter= new todo_list_adapter(TodolistActivity.this,itemsList);
                todolistview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void additems()
    {
        String item = list.getText().toString();
        String heading = spin.getSelectedItem().toString();

        if(!TextUtils.isEmpty(item))
        {
            String Id = refer.push().getKey();
            TodoItemsADDING todoItemsADDING = new TodoItemsADDING(Id,item,heading);
            refer.child(Id).setValue(todoItemsADDING);
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "ADD an Item", Toast.LENGTH_SHORT).show();
        }
    }
}