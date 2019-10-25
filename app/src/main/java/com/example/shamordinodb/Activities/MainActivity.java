package com.example.shamordinodb.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shamordinodb.Adapter.CategoryAdapter;
import com.example.shamordinodb.Adapter.ObjectAdapter;
import com.example.shamordinodb.App;
import com.example.shamordinodb.Objects.Highload;
import com.example.shamordinodb.Data.DatabaseHelper;
import com.example.shamordinodb.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_highload_text, tv_highload_name;
    private RecyclerView rv_list_object_put, rv_list_molitv;
    private DatabaseHelper databaseHelper;
    private ObjectAdapter objectAdapter;
    private CategoryAdapter categoryAdapter;

    private CardView cvAbout, cvForPalmers, cvForNovos, cvRaspisanie, cvPominoveniya, cvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = App.getInstance().getDatabaseInstance();
        initView();
        initCardList();
        initHighloadView();
    }

    public void initView() {
        rv_list_object_put = findViewById(R.id.rv_list_object_put);
        rv_list_molitv = findViewById(R.id.rv_list_molitv);
    }

    public void initHighloadView() {
        tv_highload_text = findViewById(R.id.tv_highload_text);
        tv_highload_name = findViewById(R.id.tv_highload_name);
        Highload highloadItem = databaseHelper.HighloadDao().getRandomItem();
        tv_highload_text.setText(highloadItem.getText_detail());
        tv_highload_name.setText(highloadItem.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //путеводитель по монастырю
        objectAdapter = new ObjectAdapter(this, databaseHelper.ObjectPutDao().getAllObjects(), 0);
        rv_list_object_put.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rv_list_object_put.setAdapter(objectAdapter);

        //молитвослов
        Integer[] idList = {Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11)};
        categoryAdapter = new CategoryAdapter(this, databaseHelper.CategoryDao().getCategories(idList), 0, "list");
        rv_list_molitv.setLayoutManager(new GridLayoutManager(this, 3));
        rv_list_molitv.setAdapter(categoryAdapter);
    }

    private void initCardList() {
        cvAbout = findViewById(R.id.about);
        cvAbout.setOnClickListener(this);

        cvRaspisanie = findViewById(R.id.raspisanie);
        cvRaspisanie.setOnClickListener(this);

        cvForPalmers = findViewById(R.id.forPalmers);
        cvForPalmers.setOnClickListener(this);

        cvForNovos = findViewById(R.id.forNovos);
        cvForNovos.setOnClickListener(this);

        cvPominoveniya = findViewById(R.id.pominoveniya);
        cvPominoveniya.setOnClickListener(this);

        cvContacts = findViewById(R.id.contacts);
        cvContacts.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.about:
                intent = new Intent(this, AboutActivity.class);
                break;
            case R.id.raspisanie:
                intent = new Intent(this, RaspisanieActivity.class);
                break;
            case R.id.forPalmers:
                intent = new Intent(this, CategoryItemActivity.class);
                intent.putExtra("id", 5);
                intent.putExtra("template", "list");
                break;
            case R.id.forNovos:
                intent = new Intent(this, CategoryItemActivity.class);
                intent.putExtra("id", 6);
                intent.putExtra("template", "list");
                break;
            case R.id.pominoveniya:
                intent = new Intent(this, PominovenieActivity.class);
                break;
            case R.id.contacts:
                intent = new Intent(this, MapsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
