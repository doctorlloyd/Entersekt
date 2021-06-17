package com.lloyd.entersekt.ui.cityDetails

import android.os.Bundle
import androidx.activity.viewModels
import com.lloyd.entersekt.ITEM_KEY
import com.lloyd.entersekt.R
import com.lloyd.entersekt.data.models.cities.City
import com.lloyd.entersekt.databinding.CityDetailsLayoutBinding
import com.lloyd.entersekt.ui.base.BaseActivity
import com.lloyd.entersekt.utils.observe
import com.lloyd.entersekt.utils.toGone
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: CityDetailsLayoutBinding

    override fun initViewBinding() {
        binding = CityDetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initIntentData(intent.getParcelableExtra(ITEM_KEY) ?: City())
    }

    override fun observeViewModel() {
        observe(viewModel.cityData, ::initializeView)
    }

    private fun initializeView(city: City) {
        binding.tvName.text = city.name
        binding.tvHeadline.text = city.name
        binding.tvDescription.text = city.name
//        Picasso.get().load(card.imageUrl).placeholder(R.drawable.ic_launcher_foreground)
//            .into(binding.ivCityImage)
        binding.pbLoading.toGone()
    }
}