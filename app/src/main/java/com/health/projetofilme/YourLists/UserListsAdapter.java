package com.health.projetofilme.YourLists;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.health.projetofilme.List_ItemActivity;
import com.health.projetofilme.R;

import java.util.List;

public class UserListsAdapter extends RecyclerView.Adapter<UserListsAdapter.UserListViewHolder>{
    private Context context;
    private List<Result> data;

    public UserListsAdapter(Context context, List<Result> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new UserListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final UserListViewHolder holder, int position) {
        final Result item = data.get(position);
        final String id = String.valueOf(item.getId());
        holder.name.setText(item.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                            Intent intent = new Intent(context, List_ItemActivity.class);
                            intent.putExtra("list_id", id);
                            context.startActivity(intent);


                    }
                }).start();

            }
        });

    }

    @Override
    public int getItemCount(){return data.size();}

    public class UserListViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public UserListViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);

        }
    }

}