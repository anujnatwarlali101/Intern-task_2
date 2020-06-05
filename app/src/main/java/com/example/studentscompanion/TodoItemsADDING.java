package com.example.studentscompanion;

public class TodoItemsADDING
{
    String itemID;
    String itemName;
    String itemHead;

    public TodoItemsADDING()
    {

    }

    public TodoItemsADDING(String itemID, String itemName, String itemHead) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemHead = itemHead;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemHead() {
        return itemHead;
    }
}
