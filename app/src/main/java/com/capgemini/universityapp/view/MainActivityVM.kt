package com.capgemini.universityapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.universityapp.R
import com.capgemini.universityapp.viewModel.StudentViewModel

class MainActivityVM : AppCompatActivity() {


    lateinit var rView : RecyclerView
    var stdAdapter : StudentAdapter? = null
    lateinit var vModel: StudentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = findViewById(R.id.studentL)
        rView.layoutManager = LinearLayoutManager(this)

        // DO NOT do this
        //vModel = StudentViewModel(application)

        vModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(StudentViewModel::class.java)

        // setup observer on the mutablelive data
        vModel.studentList.observe(this, Observer {
            Log.d("MainActivity", "List Updated: $it")
            stdAdapter = StudentAdapter(it)
            rView.adapter = stdAdapter
        })

       // loadList()
    }

    override fun onResume() {
        super.onResume()
        vModel.updateList()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add Student")
        menu?.add("Delete All")
        menu?.add("About Us")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "About Us" -> {
                val i = Intent(this, AboutUsActivity::class.java)
                startActivity(i)
            }
            "Add Student" -> {
                // launch activity to Add student
                val i = Intent(this, AddStudentActivity::class.java)
                i.putExtra("isAdd", true)
                startActivity(i)

            }
            "Delete All" -> {
                vModel.deleteAll()

            }
        }
        return super.onOptionsItemSelected(item)
    }


}