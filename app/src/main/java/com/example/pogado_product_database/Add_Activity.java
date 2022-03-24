package com.example.pogado_product_database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Activity extends AppCompatActivity {

    EditText name_input, desc_input, price_input, quantity_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.productName);
        desc_input = findViewById(R.id.productDesc);
        price_input = findViewById(R.id.productPrice);
        quantity_input = findViewById(R.id.productQuantity);
        add_button = findViewById(R.id.addProd_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Add_Activity.this);
                myDB.addProduct(name_input.getText().toString().trim(),
                        desc_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()),
                        Integer.valueOf(quantity_input.getText().toString().trim()));
            }

        });
    }
}
