package com.example.colorgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorTableView extends AppCompatActivity {

    GridView gridView;
    int totalCell, redCount = 0;
    List<GridModel> cellList = new ArrayList<>();
    boolean allRed = false;
    MyAdpter myAdpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        gridView = findViewById(R.id.gridView);
        int row = getIntent().getIntExtra("row",2);
        int col = getIntent().getIntExtra("col",2);
        totalCell = row * col ;

        for( int i=0; i< totalCell;i++){
            cellList.add(new GridModel(Color.LTGRAY,false));
        }

        int initialIndex = new Random().nextInt(cellList.size());
        cellList.get(initialIndex).color = Color.RED;
        cellList.get(initialIndex).enable = true;
        redCount = 1;

        myAdpter = new MyAdpter(this,cellList);
        gridView.setNumColumns(col);
        gridView.setAdapter(myAdpter);

        gridView.setOnItemClickListener((parent, view, position,id) -> handleCellClick(position));

    }

    public void handleCellClick(int position){
        GridModel clickedcell =cellList.get(position);

        if(clickedcell.color == Color.RED && clickedcell.enable == true){
            clickedcell.enable = false;

            List<Integer> unColoredIndices = new ArrayList<>();
            for(int i =0; i<cellList.size();i++){
                if(cellList.get(i).color==Color.LTGRAY){
                    unColoredIndices.add(i);
                }
            }

            if(!unColoredIndices.isEmpty()){
                int randomIndex  = unColoredIndices.get(new Random().nextInt(unColoredIndices.size()));
                GridModel newRedCell = cellList.get(randomIndex);
                newRedCell.color = Color.RED;
                newRedCell.enable = true;
                redCount++;

                if(redCount == totalCell && !allRed){
                    allRed = true;
                    Toast.makeText(this, "All boxes are red. Green phase started!", Toast.LENGTH_SHORT).show();

                    List<Integer> redIndices = new ArrayList<>();
                    for(int i =0 ; i< cellList.size(); i++){
                        if(cellList.get(i).color == Color.RED){
                            redIndices.add(i);
                        }
                    }
                    if(!redIndices.isEmpty()){
                        int greenIndex = redIndices.get(new Random().nextInt(redIndices.size()));
                        GridModel greenCell = cellList.get(greenIndex);
                        greenCell.color = Color.GREEN;
                        greenCell.enable = true;

                        myAdpter.notifyDataSetChanged();

                    }
                }
            }
            myAdpter.notifyDataSetChanged();
        } else if (clickedcell.color == Color.GREEN && clickedcell.enable) {
                clickedcell.enable = false;
                List<Integer> redIndices = new ArrayList<>();
                for(int i =0; i<cellList.size(); i++){
                    if(cellList.get(i).color == Color.RED){
                        redIndices.add(i);
                    }
                }

                if(!redIndices.isEmpty()){
                    int randomIndex = redIndices.get(new Random().nextInt(redIndices.size()));
                    GridModel greenCell = cellList.get(randomIndex);
                    greenCell.color = Color.GREEN;
                    greenCell.enable = true;
                } else{
                    Toast.makeText(this, "All red boxes turned green! Task complete!", Toast.LENGTH_SHORT).show();
                }
                myAdpter.notifyDataSetChanged();
        }

    } 
}