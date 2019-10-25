package com.example.shamordinodb.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;

import com.example.shamordinodb.App;
import com.example.shamordinodb.Data.DatabaseHelper;
import com.example.shamordinodb.Objects.Category;
import com.example.shamordinodb.Objects.Element;
import com.example.shamordinodb.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ElementItemActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DatabaseHelper databaseHelper;
    private Element element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_item);
        databaseHelper = App.getInstance().getDatabaseInstance();
        initView();
    }

    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        element = databaseHelper.ElementDao().getById(id);
        initElementView();
    }

    private void initElementView() {
        TextView title = findViewById(R.id.title);
        TextView text_detail = findViewById(R.id.text_detail);
        ImageView mainBackground = findViewById(R.id.mainBackground);
        ImageView img_detail = findViewById(R.id.img_detail);
        View bg_detail = findViewById(R.id.bg_detail);

        String cTitle = element.getName();
        title.setText(cTitle);

        String htmlText = element.getText_detail();
        if (htmlText != null) {
            text_detail.setText(HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT));
        }

        Category parent_category = databaseHelper.CategoryDao().getById(element.getCategory_id());
        String img = "";
        if (parent_category.getImg_detail() != null) {
            img = parent_category.getImg_detail();
        } else if (parent_category.getImg_preview() != null) {
            img = parent_category.getImg_preview();
        }
        if (!img.equals("")) {
            int resID = getRawResIdByName(img);
            mainBackground.setImageResource(resID);
        }

        String imgElement = "";
        if (element.getImg_detail() != null) {
            imgElement = element.getImg_detail();
        } else if (element.getImg_preview() != null) {
            imgElement = element.getImg_preview();
        }
        if (!imgElement.equals("")) {
            int resID = getRawResIdByName(imgElement);
            img_detail.setImageResource(resID);
            img_detail.setVisibility(View.VISIBLE);
            bg_detail.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
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
    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }
}
