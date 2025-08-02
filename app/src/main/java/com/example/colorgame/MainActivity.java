package com.example.colorgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtColumn,edtRow;
    Button btnSumbitt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtRow = findViewById(R.id.row);
        edtColumn = findViewById(R.id.col);
        btnSumbitt  = findViewById(R.id.btnSubmite);

        btnSumbitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int row = Integer.parseInt(edtRow.getText().toString());
                int col = Integer.parseInt(edtColumn.getText().toString());

                Intent intent = new Intent(getApplicationContext(),ColorTableView.class);
                intent.putExtra("row",row);
                intent.putExtra("col",col);
                startActivity(intent);
            }
        });


    }
}