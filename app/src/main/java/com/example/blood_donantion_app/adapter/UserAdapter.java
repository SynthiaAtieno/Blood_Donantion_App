package com.example.blood_donantion_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blood_donantion_app.R;
import com.example.blood_donantion_app.model.Users;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<Users> usersList;

    public UserAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Users users = usersList.get(position);
        holder.usertype.setText(users.getType());
        if (users.getType().equals("Donor"))
        {
            holder.emailBtn.setVisibility(View.GONE);
        }
        holder.useremail.setText(users.getType());
        holder.username.setText(users.getType());
        holder.userbloodgroup.setText(users.getType());
        holder.userphone.setText(users.getType());

        Glide.with(context).load(users.getProfilePictureurl()).into(holder.profileimage);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView profileimage;
        public TextView username, userphone, userbloodgroup, usertype, useremail;
        AppCompatButton emailBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileimage = itemView.findViewById(R.id.profileImage);
            useremail = itemView.findViewById(R.id.useremail);
            userphone = itemView.findViewById(R.id.userphonenumber);
            username = itemView.findViewById(R.id.username);
            userbloodgroup = itemView.findViewById(R.id.userbloodgroup);
            usertype = itemView.findViewById(R.id.usertype);
            emailBtn = itemView.findViewById(R.id.emailNow);
        }
    }
}
