package com.derrick.park.assignment3_contacts.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.models.Contact

class CAdapter(val delegate: OnCHolderClickListener, private val contactList: ArrayList<Contact>) :
        RecyclerView.Adapter<CAdapter.CViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_list_item, parent, false)
        return CViewHolder(itemView)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: CViewHolder, position: Int) {
        holder.bind(contactList[position])
        holder.itemView.setOnClickListener { v ->
            delegate.onClick(v, contactList[position])
        }
    }

    // ViewHolder
    inner class CViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val cellTextView: TextView = itemView.findViewById(R.id.cellTextView)

        fun bind(item: Contact) {
            nameTextView.text = item.name.toString()
            cellTextView.text = item.cell
        }
    }
}

interface OnCHolderClickListener {
    fun onClick(view: View, item: Contact)
}

