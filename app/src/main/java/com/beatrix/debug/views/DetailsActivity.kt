package com.beatrix.debug.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.beatrix.debug.R
import com.beatrix.debug.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*

/**
 * This class is responsible for getting the details activity
 * it is calling only intents so there was no need to put it in
 * a separate architecture layer.
 */
class DetailsActivity : AppCompatActivity() {

    // activity binding initialization
    private lateinit var binding: ActivityDetailsBinding

    // on activity call the details about some product are just displayed
    // this data is passed from the Recycler View to here.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        // get the amount spent for that item
        expenses_amount.text = getIntent().getStringExtra("AMOUNT")
        // get the category for that item
        category_name.text = getIntent().getStringExtra("CATEGORY")
        // get the description for the item
        expenses_description.text = getIntent().getStringExtra("DESCRIPTION")
        // get the image of the item's category
        getIntent().getStringExtra("CATEGLOGO")?.let { category_image.setImageResource(it.toInt()) }
    }
}