package com.example.pogado_product_database;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Activity extends AppCompatActivity {

    EditText name_input, desc_input, price_input, quantity_input;
    Button update_button, delete_button;
    String id, name, desc, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.UpproductName);
        desc_input = findViewById(R.id.UpproductDesc);
        price_input = findViewById(R.id.UpproductPrice);
        quantity_input = findViewById(R.id.UpproductQuantity);
        update_button = findViewById(R.id.UpProd_button);
        delete_button = findViewById(R.id.DelProd_button);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update_Activity.this);
                name = name_input.getText().toString().trim();
                desc = desc_input.getText().toString().trim();
                price = price_input.getText().toString().trim();
                quantity = quantity_input.getText().toString().trim();
                myDB.updateData(id, name, desc, price, quantity);
                Intent intent = new Intent(Update_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("desc") && getIntent().hasExtra("price")){
            //Getting Data from Intent in CustomAdapter
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            desc = getIntent().getStringExtra("desc");
            price = getIntent().getStringExtra("price");
            quantity = getIntent().getStringExtra("quantity");

            //Setting Data to the Update Activity
            name_input.setText(name);
            desc_input.setText(desc);
            price_input.setText(price);
            quantity_input.setText(quantity);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update_Activity.this);
                myDB.deleteRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}