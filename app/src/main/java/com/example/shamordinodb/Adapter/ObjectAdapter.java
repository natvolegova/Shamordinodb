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

import java.util.ArrayList;
import java.util.List;

import com.example.shamordinodb.Objects.ObjectPut;
import com.example.shamordinodb.Activities.ObjectPutItemActivity;
import com.example.shamordinodb.R;

public class ObjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HORISONTAL_LAYOUT = 0;
    private static final int VERTICAL_LAYOUT = 1;

    private List<ObjectPut> objectModels = new ArrayList<>();
    private Context context;
    private int layoutType;

    public ObjectAdapter(Context context, List<ObjectPut> objectModels, int layoutType) {
        this.context = context;
        this.objectModels = objectModels;
        this.layoutType = layoutType;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;

        if (layoutType == HORISONTAL_LAYOUT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.object_item_h, parent, false);
            viewHolder = new HorisontalViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.object_item_v, parent, false);
            viewHolder = new VerticalViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Resources res = context.getResources();

        if(layoutType== HORISONTAL_LAYOUT)
        {
            HorisontalViewHolder hActivityViewHolder = (HorisontalViewHolder)holder;
            String name = objectModels.get(position).getName();
            String img_preview = objectModels.get(position).getImg_preview();
            if (img_preview!=null) {
                int resID = res.getIdentifier(img_preview, "raw", context.getPackageName());
                hActivityViewHolder.tv_img_preview.setImageResource(resID);
                hActivityViewHolder.tv_img_preview.setVisibility(View.VISIBLE);
            }
            hActivityViewHolder.tv_name.setText(name);
        }
        else {
            VerticalViewHolder vActivityViewHolder = (VerticalViewHolder)holder;
            String name = objectModels.get(position).getName();
            String img_preview = objectModels.get(position).getImg_preview();
            if (img_preview!=null) {
                int resID = res.getIdentifier(img_preview, "raw", context.getPackageName());
                vActivityViewHolder.tv_img_preview.setImageResource(resID);
                vActivityViewHolder.tv_img_preview.setVisibility(View.VISIBLE);
            }

            vActivityViewHolder.tv_name.setText(name);
        }
    }

    class HorisontalViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView tv_img_preview;

        public HorisontalViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_img_preview = itemView.findViewById(R.id.img_preview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    ObjectPut item = objectModels.get(id);
                    Intent intent = new Intent(context, ObjectPutItemActivity.class);
                    intent.putExtra("id", item.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    class VerticalViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView tv_img_preview;

        public VerticalViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_img_preview = itemView.findViewById(R.id.img_preview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    ObjectPut item = objectModels.get(id);
                    Intent intent = new Intent(context, ObjectPutItemActivity.class);
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
