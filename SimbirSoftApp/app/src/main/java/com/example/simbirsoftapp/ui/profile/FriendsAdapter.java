package com.example.simbirsoftapp.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.UserModel;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {

    private List<UserModel> friends;
    private Context context;

    FriendsAdapter(List<UserModel> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false);

        return new FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        UserModel currentPerson = friends.get(position);

        holder.name.setText(currentPerson.getFullName());
        holder.logo.setImageDrawable(context.getResources().getDrawable(currentPerson.getLogo()));
    }

    @Override
    public int getItemCount() {
        return friends == null ? 0 : friends.size();
    }

    class FriendsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView logo;

        FriendsViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.friend_name);
            logo = v.findViewById(R.id.friend_logo);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), name.getText(), Toast.LENGTH_SHORT).show();
        }

    }
}
