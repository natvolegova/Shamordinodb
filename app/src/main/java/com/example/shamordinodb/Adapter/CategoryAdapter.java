package com.example.shamordinodb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shamordinodb.Activities.CategoryItemActivity;
import com.example.shamordinodb.Activities.MainActivity;
import com.example.shamordinodb.Objects.Category;
import com.example.shamordinodb.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HORISONTAL_LAYOUT = 0;
    private static final int LIST_LAYOUT = 1;
    private List<Category> objectModels = new ArrayList<>();
    private Context context;
    private int layoutType;
    private String template;

    public CategoryAdapter(Context context, List<Category> objectModels, int layoutType, String template) {
        this.context = context;
        this.objectModels = objectModels;
        this.layoutType = layoutType;
        this.template = template;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;

        if (layoutType == HORISONTAL_LAYOUT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.object_item_book, parent, false);
            viewHolder = new CategoryAdapter.HorisontalViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list, parent, false);
            viewHolder = new CategoryAdapter.ListViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Resources res = context.getResources();

        if(layoutType== HORISONTAL_LAYOUT)
        {
            CategoryAdapter.HorisontalViewHolder hActivityViewHolder = (CategoryAdapter.HorisontalViewHolder)holder;
            String name = objectModels.get(position).getName();
            String img_preview = objectModels.get(position).getImg_preview();
            if (img_preview!=null) {
                int resID = res.getIdentifier(img_preview, "raw", context.getPackageName());
                hActivityViewHolder.tv_img_preview.setImageResource(resID);
                hActivityViewHolder.tv_img_preview.setVisibility(View.VISIBLE);
            }
           // hActivityViewHolder.tv_name.setText(name);
        }
        else {
            CategoryAdapter.ListViewHolder lActivityViewHolder = (CategoryAdapter.ListViewHolder)holder;
            String name = objectModels.get(position).getName();
            lActivityViewHolder.tv_name.setText(name);
        }
    }
    class HorisontalViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView tv_img_preview;

        public HorisontalViewHolder(View itemView) {
            super(itemView);
         //   tv_name = itemView.findViewById(R.id.tv_name);
            tv_img_preview = itemView.findViewById(R.id.img_preview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    Category item = objectModels.get(id);
                    Intent intent = new Intent(context, CategoryItemActivity.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("template", template);
                    context.startActivity(intent);
                }
            });
        }
    }
    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;

        public ListViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    Category item = objectModels.get(id);
                    Intent intent = new Intent(context, CategoryItemActivity.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("template", template);
                    context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return objectModels.size();
    }
}
