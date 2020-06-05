package com.example.studentscompanion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class todo_list_adapter extends ArrayAdapter<TodoItemsADDING>
{
    private Activity context;
    private List<TodoItemsADDING> itemsList;

    public todo_list_adapter(Activity context, List<TodoItemsADDING> itemsList) {
        super(context, R.layout.list_view_todo_list,itemsList);
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_todo_list,null,true);

        TextView textViewitemheading = (TextView)listViewItem.findViewById(R.id.textView_item_heading_todo_list);
        TextView textViewitem = (TextView)listViewItem.findViewById(R.id.textView_item_todo_list);

        TodoItemsADDING todoItemsADDING = itemsList.get(position);
        textViewitemheading.setText(todoItemsADDING.getItemHead());
        textViewitem.setText(todoItemsADDING.getItemName());
        return  listViewItem;
    }
}
