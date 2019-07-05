package com.tijo.personalchatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val context: Context, val user:String) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {

    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        
    }
}