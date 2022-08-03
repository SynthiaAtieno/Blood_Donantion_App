package com.example.blood_donantion_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView profileimage;
        public TextView username, userphone, useridno, usertype, useremail;
        AppCompatButton emailBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileimage = itemView.findViewById(R.id.profileImage);
            useremail = itemView.findViewById(R.id.useremail);
            userphone = itemView.findViewById(R.id.userphonenumber);
            username = itemView.findViewById(R.id.username);
            useridno = itemView.findViewById(R.id.useridnumber);
            usertype = itemView.findViewById(R.id.usertype);
            emailBtn = itemView.findViewById(R.id.emailNow);
        }
    }
}
