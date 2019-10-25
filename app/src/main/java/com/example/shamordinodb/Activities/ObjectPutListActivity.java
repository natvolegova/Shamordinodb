package com.example.shamordinodb.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shamordinodb.Adapter.ObjectAdapter;
import com.example.shamordinodb.App;
import com.example.shamordinodb.Data.DatabaseHelper;
import com.example.shamordinodb.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ObjectPutListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv_list;
    private DatabaseHelper databaseHelper;
    private ObjectAdapter objectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_put_list);
        initView();
        databaseHelper = App.getInstance().getDatabaseInstance();
    }
    @Override
    protected void onResume() {
        super.onResume();
        objectAdapter = new ObjectAdapter(this, databaseHelper.ObjectPutDao().getAllObjects(),1);
        rv_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv_list.setAdapter(objectAdapter);
    }

    public void initView() {
        rv_list = findViewById(R.id.rv_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ObjectPutListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
