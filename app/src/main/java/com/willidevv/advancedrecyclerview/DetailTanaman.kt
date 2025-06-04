package com.willidevv.advancedrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailTanaman.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailTanaman : Fragment() {
    private val tanamanviewmodel : TanamanViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_tumbuhan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("id_tanaman") ?: return

        tanamanviewmodel.setDatabase(TanamanDatabase.getInstance(requireContext()))

        tanamanviewmodel.getTanamanById(id).observe(viewLifecycleOwner) { tanaman ->
            tanaman?.let {
                view.findViewById<ImageView>(R.id.gambarTumbuhan).setImageResource(R.drawable.tanaman)
                view.findViewById<TextView>(R.id.namaTanaman).text = it.namaTanaman
                view.findViewById<TextView>(R.id.deskripsiTanaman).text = it.deskripsiTanaman
                view.findViewById<TextView>(R.id.kategoriTanaman).text = it.category
            }
        }

    }
    companion object {
        fun newInstance(id : Int):DetailTanaman {
            val fragment = DetailTanaman()
            val args = Bundle()
            args.putInt("id_tanaman", id)
            fragment.arguments = args
            return fragment
        }
    }

}