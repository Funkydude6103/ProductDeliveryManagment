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

public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;
    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
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

        TextView tvTitle = v.findViewById(R.id.tvProductTitle);
        ImageView ivEdit = v.findViewById(R.id.ivEdit);
        ImageView ivDelete = v.findViewById(R.id.ivDelete);
        ImageView ivSchedule=v.findViewById(R.id.ivSchedule);

        Product p = getItem(position);
        tvTitle.setText("Tittle: "+p.getTitle()+"\n"+"Price: "+p.getPrice()+"\n"+"Date: "+p.getDate());

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Edit Product");
                View v = LayoutInflater.from(context)
                        .inflate(R.layout.add_new_product_dialog_design, null, false);
                dialog.setView(v);
                EditText etTitle = v.findViewById(R.id.etTitle);
                EditText etDate = v.findViewById(R.id.etDate);
                EditText etPrice = v.findViewById(R.id.etPrice);
                etTitle.setText(p.getTitle());
                etPrice.setText(p.getPrice()+"");
                etDate.setText(p.getDate());

                etDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                context,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        etDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                    }
                                }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = etTitle.getText().toString().trim();
                        String date = etDate.getText().toString().trim();
                        String price = etPrice.getText().toString();
                        if(title.isEmpty() || date.isEmpty() || price.isEmpty())
                        {
                            Toast.makeText(context, "Product Edit Failed", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // Update product object
                        int price2 = Integer.parseInt(price);
                        p.setTitle(title);
                        p.setDate(date);
                        p.setPrice(price2);

                        // Update in the database (use the existing ID)
                        ProductDB productDB = new ProductDB(context);
                        productDB.open();
                        productDB.update(p.getId(), title, date, price2,p.getStatus());
                        NewOrderFragment.products.clear();
                        NewOrderFragment.products.addAll(productDB.fetchProducts("new"));
                        productDB.close();

                        // Notify adapter about data change
                        notifyDataSetChanged();  // This refreshes the ListView

                        Toast.makeText(context, "Product Edited", Toast.LENGTH_SHORT).show();

                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.show();

            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDB db = new ProductDB(context);
                db.open();
                db.remove(p.getId());
                NewOrderFragment.products.clear();
                NewOrderFragment.products.addAll(db.fetchProducts("new"));
                db.close();
                notifyDataSetChanged();
            }
        });

        ivSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDB db = new ProductDB(context);
                db.open();
                db.update(p.getId(),p.getTitle(),p.getDate(),p.getPrice(),"schedule");
                NewOrderFragment.products.clear();
                NewOrderFragment.products.addAll(db.fetchProducts("new"));
                db.close();
                notifyDataSetChanged();
            }
        });


        return v;



    }
}
