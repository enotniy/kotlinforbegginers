package ru.ekitselyuk.kotlinforbegginers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.ekitselyuk.kotlinforbegginers.databinding.DetailFragmentBinding
import ru.ekitselyuk.kotlinforbegginers.viewmodel.MainViewModel
import ru.ekitselyuk.kotlinforbegginers.databinding.MainFragmentBinding
import ru.ekitselyuk.kotlinforbegginers.model.Weather
import ru.ekitselyuk.kotlinforbegginers.viewmodel.AppState
import ru.ekitselyuk.kotlinforbegginers.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?) : DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Weather>("WEATHER_EXTRA")?.let { weather ->
            binding.cityName.text = weather.city.name
            binding.temperature.text = weather.temperature.toString()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}