package com.codangcoding.arcx.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codangcoding.arcx.R
import com.codangcoding.arcx.presentation.model.LeagueModel
import kotlinx.android.synthetic.main.list_item_league.view.*

class LeagueListAdapter(
    private val clickListener: (LeagueModel) -> Unit
) : ListAdapter<LeagueModel, LeagueListAdapter.ViewHolder>(DiffItemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_league, parent, false)

        return ViewHolder(rootView).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(getItem(position))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

        private val tvName = itemView.tv_name

        fun bind(leagueModel: LeagueModel) {
            tvName.text = leagueModel.name
        }
    }

    companion object DiffItemCallback : DiffUtil.ItemCallback<LeagueModel>() {

        override fun areItemsTheSame(oldItem: LeagueModel, newItem: LeagueModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LeagueModel, newItem: LeagueModel): Boolean {
            return oldItem == newItem
        }

    }
}