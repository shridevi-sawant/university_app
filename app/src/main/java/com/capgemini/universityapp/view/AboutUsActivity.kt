package com.capgemini.universityapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.capgemini.universityapp.R
import com.capgemini.universityapp.viewModel.UniversityViewModel
import com.capgemini.universityapp.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {


    var univModel : UniversityViewModel? = null
    lateinit var binding : ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_about_us)

       binding = DataBindingUtil.setContentView(this,
           R.layout.activity_about_us)

        // VM created or retrieved from View Model store of this activity
        univModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(UniversityViewModel::class.java)


        binding.uModel = univModel
        binding.lifecycleOwner = this

    }

    override fun onResume() {
        super.onResume()
        univModel?.updateCount()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Show")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        univModel?.updateCount()

        return super.onOptionsItemSelected(item)
    }
}