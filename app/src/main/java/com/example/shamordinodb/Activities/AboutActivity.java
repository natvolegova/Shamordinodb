package com.example.shamordinodb.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shamordinodb.Adapter.CategoryAdapter;
import com.example.shamordinodb.App;
import com.example.shamordinodb.Data.DatabaseHelper;
import com.example.shamordinodb.R;

public class AboutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv_list;
    private DatabaseHelper databaseHelper;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        databaseHelper = App.getInstance().getDatabaseInstance();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Integer[] idList = {Integer.valueOf(1),Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(4)};

        categoryAdapter = new CategoryAdapter(this, databaseHelper.CategoryDao().getCategories(idList),1,"detail");
        rv_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_list.setAdapter(categoryAdapter);
    }
    private void initView() {
        rv_list = findViewById(R.id.rv_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
