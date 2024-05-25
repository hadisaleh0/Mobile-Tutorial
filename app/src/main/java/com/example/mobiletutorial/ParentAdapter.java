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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter {

    boolean isDeleting;
    Context context;
    private ArrayList<Children> children;
    private View.OnClickListener onItemClickListener;

    public ParentAdapter(ArrayList <Children> children, Context context) {
        this.context = context;
        this.children = children;
        onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)
                        v.getTag();
                int position = holder.getAdapterPosition();
                int child = children.get(position).getParentId();
                Intent intent = new Intent(context,
                        MainActivity.class);
                intent.putExtra("parentId", child);
                context.startActivity(intent);
            }
        };
    }

    public void setDeleting(boolean deleting) {
        isDeleting = deleting;
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
        ChildViewHolder contactVH = (ChildViewHolder) holder;
        contactVH.getContactTextView().
                setText(children.get(position).getFirstName());
//        contactVH.getPhoneTextView().
//                setText(children.get(position).getPhoneNumber());
//        if (isDeleting) {
//            contactVH.getDeleteImageButton().setVisibility(View.VISIBLE);
//            contactVH.getDeleteImageButton().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    deleteItem(holder.getAdapterPosition());
//                }
//            });
//        } else {
//            contactVH.getDeleteImageButton().setVisibility(View.INVISIBLE);
//        }
    }

//    private void deleteItem(int position) {
//        ChildDataSource ds = new ChildDataSource(context);
//        try {
//            ds.open();
//            int contactId = children.get(position).getId();
//            boolean didDelete = ds.deleteContact(contactId);
//            ds.close();
//            if (didDelete) {
//                children.remove(position);
//                notifyDataSetChanged();
//            } else {
//                Toast.makeText(context, "Delete Failed", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(context, "Delete Failed", Toast.LENGTH_LONG).show();
//        }
//    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView nameTextView;
        ImageButton deleteImageButton;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardLabTest);
            nameTextView = itemView.findViewById(R.id.textChildName);
            //deleteImageButton = itemView.findViewById(R.id.imageButtonDelete);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        public TextView getContactTextView() {
            return nameTextView;
        }

//        public TextView getPhoneTextView() {
//            return phoneTextView;
//        }

//        public ImageButton getDeleteImageButton() {
//            return deleteImageButton;
//        }
    }
}