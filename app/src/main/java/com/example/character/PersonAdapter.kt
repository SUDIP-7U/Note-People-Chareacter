package com.example.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(private var list: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = list[position]
        holder.tvName.text = person.name
        holder.tvRating.text = person.rating
    }

    override fun getItemCount() = list.size

    fun updateList(newList: List<Person>) {
        list = newList
        notifyDataSetChanged()
    }
}
