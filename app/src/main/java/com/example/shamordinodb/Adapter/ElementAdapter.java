package com.example.shamordinodb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shamordinodb.Activities.ElementItemActivity;
import com.example.shamordinodb.Objects.Element;
import com.example.shamordinodb.R;

import java.util.ArrayList;
import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VERTICAL_LAYOUT = 0;
    private static final int LIST_LAYOUT = 1;
    private List<Element> objectModels = new ArrayList<>();
    private Context context;
    private int layoutType;

    public ElementAdapter(Context context, List<Element> objectModels, int layoutType) {
        this.context = context;
        this.objectModels = objectModels;
        this.layoutType = layoutType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;

        if (layoutType == VERTICAL_LAYOUT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.object_item_v2, parent, false);
            viewHolder = new ElementAdapter.VerticalViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list, parent, false);
            viewHolder = new ElementAdapter.ListViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Resources res = context.getResources();
        if(layoutType== VERTICAL_LAYOUT)
        {
            ElementAdapter.VerticalViewHolder vActivityViewHolder = (ElementAdapter.VerticalViewHolder)holder;
            String name = objectModels.get(position).getName();
            String img_preview = objectModels.get(position).getImg_preview();
            String text_preview = objectModels.get(position).getText_preview();
            if (img_preview!=null) {
                int resID = res.getIdentifier(img_preview, "raw", context.getPackageName());
                vActivityViewHolder.tv_img_preview.setImageResource(resID);
                vActivityViewHolder.tv_img_preview.setVisibility(View.VISIBLE);
            }
            vActivityViewHolder.tv_name.setText(name);

            if (text_preview != null) {
                vActivityViewHolder.tv_text_preview.setText(text_preview);
                vActivityViewHolder.tv_text_preview.setVisibility(View.VISIBLE);
            }
        }
        else {
            ElementAdapter.ListViewHolder lActivityViewHolder = (ElementAdapter.ListViewHolder)holder;
            String name = objectModels.get(position).getName();
            lActivityViewHolder.tv_name.setText(name);
        }
    }
    class VerticalViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView tv_img_preview;
        private TextView tv_text_preview;

        public VerticalViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_img_preview = itemView.findViewById(R.id.img_preview);
            tv_text_preview = itemView.findViewById(R.id.tv_text_preview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    Element item = objectModels.get(id);
                    Intent intent = new Intent(context, ElementItemActivity.class);
                    intent.putExtra("id", item.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView tv_img_preview;

        public ListViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_img_preview = itemView.findViewById(R.id.img_preview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    Element item = objectModels.get(id);
                    Intent intent = new Intent(context, ElementItemActivity.class);
                    intent.putExtra("id", item.getId());
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
