package com.hariapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SmsAdaptor(val userList: ArrayList<Message>) : RecyclerView.Adapter<SmsAdaptor.ViewHolder>() {
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsAdaptor.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contactlist_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: SmsAdaptor.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Message) {
            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
            val textViewDate = itemView.findViewById(R.id.textViewDate) as TextView
            textViewDate.visibility = View.VISIBLE;
            textViewName.text = user.name
            textViewAddress.text = user.address
            textViewDate.text = user.dTime
        }
    }
}