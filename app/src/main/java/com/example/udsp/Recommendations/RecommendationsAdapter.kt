package com.example.udsp.Recommendations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udsp.databinding.RecommendationItemLayoutBinding

class RecommendationsAdapter(private val context: Context,
                             private val recommendationItemList: MutableList<RecommendationItem>)
    : RecyclerView.Adapter<RecommendationsAdapter.RecommendationsItemViewHolder>()  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationsItemViewHolder {
        val binding = RecommendationItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecommendationsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationsItemViewHolder, position: Int) {
        val recommendationItem = recommendationItemList[position]
        holder.bind(recommendationItem)
    }

    override fun getItemCount(): Int {
        return recommendationItemList.size
    }

    class RecommendationsItemViewHolder(recommendationItemLayoutBinding: RecommendationItemLayoutBinding)
        : RecyclerView.ViewHolder(recommendationItemLayoutBinding.root) {
        private val binding = recommendationItemLayoutBinding
        fun bind(recommendationItem: RecommendationItem) {
            binding.textView.text = recommendationItem.recommendation
        }
    }
}