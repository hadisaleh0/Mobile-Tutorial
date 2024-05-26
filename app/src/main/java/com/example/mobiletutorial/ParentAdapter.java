package com.example.mobiletutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter {

    boolean isDeleting;
    Context context;
    private ArrayList<Children> childrenData;
    private View.OnClickListener onItemClickListener;

    public ParentAdapter(ArrayList <Children> contacts, Context context) {
        this.context = context;
        childrenData = contacts;
        onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)
                        v.getTag();
                int position = holder.getAdapterPosition();
                int childId = childrenData.get(position).getId();
                Intent intent = new Intent(context,
                        HomeActivity.class);
                intent.putExtra("childId", childId);
                context.startActivity(intent);
            }
        };
    }

    public void setDeleting(boolean deleting) {
        isDeleting = deleting;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.complex_list_item, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChildViewHolder childVH = (ChildViewHolder) holder;
        childVH.getContactTextView().
                setText(childrenData.get(position).getFirstName());
        childVH.getBloodGroupTextView().
                setText(childrenData.get(position).getBloodgroup());
        if (isDeleting) {
            childVH.getDeleteImageButton().setVisibility(View.VISIBLE);
            childVH.getDeleteImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(holder.getAdapterPosition());
                }
            });
        } else {
            childVH.getDeleteImageButton().setVisibility(View.INVISIBLE);
        }
    }

    private void deleteItem(int position) {
        ChildDataSource ds = new ChildDataSource(context);
        try {
            ds.open();
            int childId = childrenData.get(position).getId();
            boolean didDelete = ds.deleteChild(childId);
            ds.close();
            if (didDelete) {
                childrenData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, childrenData.size());
            } else {
                Toast.makeText(context, "Delete Failed", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return childrenData.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, BloodGroupTextView;
        ImageButton deleteImageButton;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewChildFirstName);
            BloodGroupTextView = itemView.findViewById(R.id.textViewChildBloodGroup);
            deleteImageButton = itemView.findViewById(R.id.imageButtonDelete);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        public TextView getContactTextView() {
            return nameTextView;
        }

        public TextView getBloodGroupTextView() {
            return BloodGroupTextView;
        }

        public ImageButton getDeleteImageButton() {
            return deleteImageButton;
        }
    }
}