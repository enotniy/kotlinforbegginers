package ru.ekitselyuk.kotlinforbegginers.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.ekitselyuk.kotlinforbegginers.databinding.DetailFragmentBinding
import ru.ekitselyuk.kotlinforbegginers.viewmodel.MainViewModel
import ru.ekitselyuk.kotlinforbegginers.databinding.MainFragmentBinding
import ru.ekitselyuk.kotlinforbegginers.model.*
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

    private val listener = Repository.OnLoadListener {
        RepositoryImpl.getWeatherFromServer()?.let { weather ->
            binding.weatherCondition.text = weather.condition
            binding.temperatureValue.text = weather.temperature.toString()
            binding.feelsLikeValue.text = weather.feelsLike.toString()
        } ?: Toast.makeText(context, "ОШИБКА", Toast.LENGTH_LONG).show()
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


        RepositoryImpl.addLoadedListener(listener)

        arguments?.getParcelable<Weather>("WEATHER_EXTRA")?.let { weather ->

            binding.cityName.text = weather.city.name
            binding.cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"

            requireActivity().startService(Intent(requireContext(), MainIntentService::class.java).apply {
                putExtra("WEATHER_EXTRA", weather)
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        RepositoryImpl.removeLoadedListener(listener)
        _binding = null
    }
}