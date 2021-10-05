package com.capgemini.universityapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.universityapp.R
import com.capgemini.universityapp.model.Student
import com.capgemini.universityapp.model.StudentRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var repo: StudentRepo
    lateinit var rView : RecyclerView
    var stdAdapter : StudentAdapter? = null
    val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = findViewById(R.id.studentL)
        rView.layoutManager = LinearLayoutManager(this)

        repo = StudentRepo(this)

    }

    override fun onResume() {
        super.onResume()
        populateStudents()
    }

    private fun populateStudents() {
        studentList.clear()

        CoroutineScope(Dispatchers.Default).launch {
           studentList.addAll(repo.getAllStudents())
            Log.d("MainActivity", "Students: $studentList")

            runOnUiThread {
                if(stdAdapter == null){
                    stdAdapter = StudentAdapter(studentList)
                    rView.adapter = stdAdapter
                }
                else
                    stdAdapter?.notifyDataSetChanged()
            }


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add Student")
        menu?.add("Delete All")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Add Student" -> {
                // launch activity to Add student
                val i = Intent(this, AddStudentActivity::class.java)
                startActivity(i)

            }
            "Delete All" -> {
                repo.deleteAllStudents()

                populateStudents()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}