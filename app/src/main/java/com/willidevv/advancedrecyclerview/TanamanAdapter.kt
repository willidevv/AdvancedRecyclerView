package com.willidevv.advancedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TanamanAdapter(
    private val onItemClick: (Tanaman) -> Unit
        ) : ListAdapter<Tanaman, TanamanAdapter.TanamanViewHolder>(TanamanDiffCallback()) {

            class TanamanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
                val tvtumbuhan: TextView = itemView.findViewById(R.id.tvTumbuhan)
                val btnbaca: TextView = itemView.findViewById(R.id.btnBaca)
                val ivtumbuhan: ImageView = itemView.findViewById(R.id.ivTumbuhan)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TanamanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tanaman_view, parent, false)
        return TanamanViewHolder(view)
    }

    override fun onBindViewHolder(holder: TanamanViewHolder, position: Int) {
        val tanaman = getItem(position)
        holder.tvtumbuhan.text = tanaman.namaTanaman
        holder.btnbaca.setOnClickListener{
            onItemClick(tanaman)
        }
        holder.ivtumbuhan.setImageResource(R.drawable.tanaman)
    }

        class TanamanDiffCallback : DiffUtil.ItemCallback<Tanaman>() {
            override fun areItemsTheSame(oldItem: Tanaman, newItem: Tanaman): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tanaman, newItem: Tanaman): Boolean {
                return oldItem == newItem
            }
        }
        }

