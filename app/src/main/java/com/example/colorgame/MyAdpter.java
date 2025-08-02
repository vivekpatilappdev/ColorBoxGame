package com.example.colorgame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MyAdpter extends BaseAdapter {

    private Context context;
    private List<GridModel> cellList;

    public MyAdpter(Context context, List<GridModel> list){
        this.context = context;
        this.cellList = list;
    }
    @Override
    public int getCount() {
        return cellList.size();
    }

    @Override
    public Object getItem(int position) {
        return cellList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;

        if (convertView == null) {
            btn = new Button(context);
            btn.setLayoutParams(new GridView.LayoutParams(200, 200));
        } else {
            btn = (Button) convertView;
        }

        GridModel cell = cellList.get(position);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(cell.color);

        // Change stroke color based on enabled state
        if (cell.enable && (cell.color == Color.RED || cell.color == Color.GREEN)) {
            drawable.setStroke(8, Color.YELLOW); // Enabled cells
        } else {
            drawable.setStroke(8, Color.WHITE);  // Disabled cells
        }

        btn.setBackground(drawable);
        btn.setEnabled(cell.enable);

        btn.setOnClickListener(v -> {
            ((ColorTableView) context).handleCellClick(position);
        });

        return btn;
    }

}

