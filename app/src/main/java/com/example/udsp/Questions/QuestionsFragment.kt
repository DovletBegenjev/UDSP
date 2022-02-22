package com.example.udsp.Questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.example.udsp.R
import com.example.udsp.databinding.FragmentQuestionsBinding

class QuestionsFragment : Fragment() {
    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: QuestionItemAdapter
    private var questionItemList: MutableList<QuestionItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        val view = binding.root

        var answer: IntArray = intArrayOf(0, 0, 0, 0, 0, 0, 0)
        adapter = QuestionItemAdapter(requireContext(), questionItemList, answer)
        binding.RVQuestions.adapter = adapter
        binding.RVQuestions.layoutManager = LinearLayoutManager(requireContext())

        binding.resultButton.setOnClickListener {
            answer = adapter.getAnswers()
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToRecommendationsFragment(answer)
            it.findNavController().navigate(action)
        }

        return view
    }

    private fun initData() {
        val questions: Array<String> = arrayOf(
            "День болезни c 1го по 5й  (макс. - до 7го)?",
            "Возраст пациента 65 или старше;   или   наличие хр. заболеваний бронхолегочной, сердечно-сосудистой, эндокринной систем; заболеваний соед. ткани; хр. болезнью почек; онкозаболеваниями; иммунодефицитом; циррозом; ВЗК; болезнями двигательного нейрона?",
            "Держится ли гипертермия более 5 (3-х) сут;  или   ЧДД > 22/мин;   или   Снижается кислород (сатурация) менее 95%;   или   Вероятное или определенное поражение легких (клинически, РОГК, СКТ);  или  СРБ >50(30)?",
            "Есть признаки бактериальной суперинфекции: лейкоцитоз > 10 тыс,/мкл, палочкоядерный сдвиг >6%, гнойная мокрота, повышение прокальцитонина >0.5 нг/мл?",
            "Есть увеличенный риск ТГВ / ТЭЛА (возраст старше 70 лет, сердечная/дыхательная недостаточность, ожирение, системное заболевания соединительной ткани, гормональная заместительная терапия/приём КОК)?",
            "Пациент принимает антиагрегант (АСК, дипиридамол - Аспирин, Курантил и т.п.)?",
            "Пациентка беременна?"
        )

        for (i in 0..questions.size - 1) {
            val question = questions[i]
            val questionItem = QuestionItem(question)
            questionItemList.add(questionItem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}