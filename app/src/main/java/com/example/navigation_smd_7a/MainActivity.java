package com.example.navigation_smd_7a;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 vp2;
    ViewPagerAdapter adapter;
    int count=0;
    boolean flag = false;
    FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        adapter = new ViewPagerAdapter(this);
        vp2 = findViewById(R.id.viewpager2);
        vp2.setAdapter(adapter);
        tabLayout = findViewById(R.id.tabLayout);
        fab_add = findViewById(R.id.fab_add);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Add Product");
                View v = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.add_new_product_dialog_design, null, false);
                dialog.setView(v);
                EditText etTitle = v.findViewById(R.id.etTitle);
                EditText etDate = v.findViewById(R.id.etDate);
                EditText etPrice = v.findViewById(R.id.etPrice);
                etDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                MainActivity.this,
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
                            Toast.makeText(MainActivity.this, "Product Add Failed", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ProductDB productDB = new ProductDB(MainActivity.this);
                        productDB.open();
                        productDB.insert(title, date, Integer.parseInt(price));
                        NewOrderFragment.products.clear();
                        NewOrderFragment.products.addAll(productDB.fetchProducts("new"));
                        NewOrderFragment.updateProductList();
                        productDB.close();
                        Toast.makeText(MainActivity.this, "Product Added", Toast.LENGTH_SHORT).show();

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


        TabLayoutMediator tabLayoutMediator =
                new TabLayoutMediator(tabLayout, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position)
                        {
                            case 0:
                                tab.setText("Scheduled");
                                tab.setIcon(R.drawable.schedule_icon);
                                break;
                            case 1:
                                tab.setText("Delivered");
                                tab.setIcon(R.drawable.delivered_icon);
                                break;
                            default:
                                    tab.setText("New Orders");
                                    tab.setIcon(R.drawable.new_orders_icon);
                        }
                    }
                });
        tabLayoutMediator.attach();

    }
}