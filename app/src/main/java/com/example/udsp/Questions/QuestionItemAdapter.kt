package com.example.udsp.Questions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udsp.databinding.QuestionItemLayoutBinding

class QuestionItemAdapter(private val context: Context,
                          private val questionItemList: MutableList<QuestionItem>,
                          private var answer: IntArray)
    : RecyclerView.Adapter<QuestionItemAdapter.QuestionItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemViewHolder {
        val binding = QuestionItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return QuestionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        val questionItem = questionItemList[position]
        holder.bind(questionItem)
        holder.radioGroup.setOnCheckedChangeListener { _, i ->
            if (i == holder.radioYes.id) answer[position] = 1
            else answer[position] = 0
        }
    }

    fun getAnswers(): IntArray {
        return answer
    }

    override fun getItemCount(): Int {
        return questionItemList.size
    }

    class QuestionItemViewHolder(questionItemLayoutBinding: QuestionItemLayoutBinding)
        : RecyclerView.ViewHolder(questionItemLayoutBinding.root) {
        private val binding = questionItemLayoutBinding
        fun bind(questionItem: QuestionItem) {
            binding.textView.text = questionItem.question
        }
        val radioGroup = binding.radioGroup
        val radioYes = binding.radioYes
    }
}