package com.example.navigation_smd_7a;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class ProductDeliverAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;
    public ProductDeliverAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null)
        {
            v = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView tvTitle = v.findViewById(R.id.tvdeliverProductTitle);


        Product p = getItem(position);
        tvTitle.setText("Tittle: "+p.getTitle()+"\n"+"Price: "+p.getPrice()+"\n"+"Date: "+p.getDate());


        return v;



    }
}
