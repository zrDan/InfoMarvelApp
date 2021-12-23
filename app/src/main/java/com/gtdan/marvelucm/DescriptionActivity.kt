package com.gtdan.marvelucm

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.ScrollView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.gtdan.marvelucm.adapter.GalleryAdapter
import com.gtdan.marvelucm.adapter.bindImage
import com.gtdan.marvelucm.databinding.ActivityDescriptionBinding
import com.gtdan.marvelucm.network.MarvelApi
import com.gtdan.marvelucm.network.model.ItemModel
import kotlinx.coroutines.launch

class DescriptionActivity : AppCompatActivity() {

    private val args: DescriptionActivityArgs by navArgs()
    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            try {
                val result = MarvelApi.retrofitService.getItem(args.itemType, args.itemId)
                fillContent(result.item)
            }catch (e: Exception){
                Log.e("API",  "Error " + e.toString())
            }
        }

    }

    private fun fillContent(item: ItemModel){
        val date = item.info.date.day + " " + item.info.date.month + " " + item.info.date.year

        bindImage(binding.itemCover, item.resources.cover)
        binding.itemTitle.text = item.name
        binding.itemTime.text = item.info.time
        binding.itemScore.text = item.info.score
        binding.itemDate.text = date

        if (item.info.chapters != null && item.info.seasons != null){
            binding.textSeason.isVisible = true
            binding.textChapters.isVisible = true
            binding.itemSeasons.isVisible = true
            binding.itemChapters.isVisible = true

            binding.itemSeasons.text = item.info.seasons
            binding.itemChapters.text = item.info.chapters
        }

        binding.itemResume.text = item.info.resume

        binding.recyclerViewGallery.adapter = GalleryAdapter(this, item.resources.gallery)
    }

}
