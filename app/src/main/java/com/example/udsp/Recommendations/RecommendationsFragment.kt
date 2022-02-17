package com.example.udsp.Recommendations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udsp.databinding.FragmentRecommendationsBinding

class RecommendationsFragment : Fragment() {
    private var _binding: FragmentRecommendationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecommendationsAdapter
    private var recommendationItemList: MutableList<RecommendationItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendationsBinding.inflate(inflater, container, false)
        val view = binding.root

        val allRecommendations: Array<String> = arrayOf(
            "=> Статус соответствует \"легкому течению\" в ранней фазе (до 7дн). Амбулаторное лечение. В обследовании рассмотреть: ПЦР, +/- ОАК, СРБ, фибриноген, ферритин. Антимикробная терапия не показана! В первые 5 дн. рассмотреть назначение ИФН-а назально; + Умифеновир, или фавипиравир, молнупиравир; +/- НПВС (симптоматически); +/- будесонид. После 5го дня - симптоматическая терапия.",
            "=> Статус соответствует \"легкому течению\", 2-я и более неделя заболевания. Амбулаторное лечение. В обследовании рассмотреть: ПЦР, +/- ОАК, СРБ, фибриноген, ферритин, д-димер. Антимикробная терапия не показана! При подозрении на персистирование вируса - противовир.терапия (Умифеновир, или фавипиравир, молнупиравир). Симптоматическая терапия +/-будесонид.",
            "=> ! Исключить назначение запрещенных при беременности ЛС (фавипиравир, НОАК и др.)",
            "=> ! С учетом доп. рисков (возраст, хр.патология, % поражения) рекомендована госпитализация специализированной выездной бригадой СМП",
            "=> !!! Состояние средней тяжести (возможно, тяжелое) - показана госпитализация! При отказе - оформить информированный отказ. Обязательны антикоагулянты (НМГ п/к, или НОАК: апиксабан, дабигатран, ривароксабан - Эликвис 2,5*2р, или  Прадакса 75-110мг х 2р, или Ксарелто 10 мг х 1р); +/- п/вир. ЛС (ИФН-а, фавипиравир или молнупиравир); +/- системные ГКС (при более тяжелом течении, поражении 30-50% легких и более; дексаметазон в/м 6 мг/сут (до 12-16мг на 3 дня со снижением), или метилпреднизолон 36 мг/сут на 5-10 дн (до 250мг на 3 дн. со снижением); либо оральные формы при отсутствии парентеральных форм.). АМТ в большинстве случаев не нужны! Обследование: ПЦР, ОАК, СРБ, коагулограмма, ферритин, д-димер +/-ПКТ - в динамике. При снижении сатурации менее 92% - показана кислородная поддержка (концентратор, госпитализация).",
            "=> Назначение антибактериального препарата в среднетерапевтических дозах на 5-7 дней (АМО, АМО/КК, Азитро-, Кларитромицин, Лево- или Моксифлоксацин - один из ЛС) - только при наличии явных признаков бактериальной инфекции (гнойная мокрота, лейкоцитоз, сдвиг лейкоформулы, повышение прокальцитонина)!",
            "=> Назначение антикоагулянта в полной терапевтической дозировке (НМГ по весу; апиксабан, дабигатран, ривароксабан - Эликвис 5мг*2р, или  Прадакса 150мг*2р, или Ксарелто 15-20мг*1р)",
            "=> Отменить антиагрегант (АСК), при показаниях к назначению антикоагулянта, и назначить антикоагулянт (НМГ, апиксабан, дабигатран, ривароксабан - Эликвис, Ксарелто, Прадакса)"
        )

        val answers = RecommendationsFragmentArgs.fromBundle(requireArguments()).answers

        val finalRecommendations: ArrayList<String> = arrayListOf()

        if (answers[0] == 1 && answers[2] == 0 && answers[3] == 0) finalRecommendations += allRecommendations[0]

        if (answers[1] == 1) finalRecommendations += allRecommendations[3]

        if (answers[2] == 1) finalRecommendations += allRecommendations[4]

        if (answers[3] == 1) finalRecommendations += allRecommendations[5]

        if (answers[4] == 1 && answers[6] == 0) finalRecommendations += allRecommendations[6]

        if (answers[5] == 1) finalRecommendations += allRecommendations[7]

        if (answers[6] == 1 && answers[0] == 0) {
            finalRecommendations += allRecommendations[1]
            finalRecommendations += allRecommendations[2]
        }
        if (answers[6] == 1 && answers[4] == 0) {
            finalRecommendations += allRecommendations[2]
        }

        if (answers[6] == 1 && answers[4] == 1 && answers[0] == 0) {
            finalRecommendations += allRecommendations[1]
            finalRecommendations += allRecommendations[2]
            finalRecommendations += allRecommendations[6]
        }
        if (answers[6] == 1 && answers[4] == 1) {
            finalRecommendations += allRecommendations[2]
            finalRecommendations += allRecommendations[6]
        }

        if (finalRecommendations.isEmpty()) finalRecommendations += allRecommendations[1]

        for (i in 0..finalRecommendations.size-1) {
            val finalRecommendation = finalRecommendations[i]
            val recommendationItem = RecommendationItem(finalRecommendation)
            recommendationItemList.add(recommendationItem)
        }

        adapter = RecommendationsAdapter(requireContext(), recommendationItemList)
        binding.RVRecommendations.adapter = adapter
        binding.RVRecommendations.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}