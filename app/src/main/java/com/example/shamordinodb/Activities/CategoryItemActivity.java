package com.example.shamordinodb.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shamordinodb.Adapter.ElementAdapter;
import com.example.shamordinodb.App;
import com.example.shamordinodb.Data.DatabaseHelper;
import com.example.shamordinodb.Objects.Category;
import com.example.shamordinodb.R;

public class CategoryItemActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv_list;
    private DatabaseHelper databaseHelper;
    private Category category;
    private ElementAdapter elementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);
        databaseHelper = App.getInstance().getDatabaseInstance();
        initView();
    }

    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String template = intent.getStringExtra("template");
        category = databaseHelper.CategoryDao().getById(id);
        initCategoryView();
        initElementListView(template);
    }

    private void initCategoryView() {
        TextView title = findViewById(R.id.title);
        TextView text_detail = findViewById(R.id.text_detail);
        ImageView mainBackground = findViewById(R.id.mainBackground);

        String cTitle = category.getName();
        title.setText(cTitle);

        String cDesc = category.getText_detail();
        if (cDesc != null) {
            text_detail.setText(cDesc);
            text_detail.setVisibility(View.VISIBLE);
        }

        String img = "";
        if (category.getImg_detail() != null) {
            img = category.getImg_detail();
        } else if (category.getImg_preview() != null) {
            img = category.getImg_preview();
        }
        if (!img.equals("")) {
            int resID = getResources().getIdentifier(img, "raw", getPackageName());
            mainBackground.setImageResource(resID);
        }
    }

    private void initElementListView(String template) {
        int layoutType = 1; //шаблон список
        switch (template) {
            case "list":
                layoutType = 1;
                break;
            case "detail":
                layoutType = 0;
                break;
            default:
                layoutType = 1;
        }


        elementAdapter = new ElementAdapter(this, databaseHelper.ElementDao().getAllElement(category.getId()), layoutType);
        rv_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_list.setAdapter(elementAdapter);
    }

    private void initView() {
        rv_list = findViewById(R.id.rv_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
