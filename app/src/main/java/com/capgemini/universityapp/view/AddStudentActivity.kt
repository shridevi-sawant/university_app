package com.capgemini.universityapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capgemini.universityapp.R
import com.capgemini.universityapp.viewModel.StudentViewModel
import com.capgemini.universityapp.model.Student

class AddStudentActivity : AppCompatActivity() {

    private var isAddStudent: Boolean = false
    lateinit var vModel: StudentViewModel

    lateinit var firstNameEditText: EditText
    lateinit var lastNameEditText: EditText
    lateinit var marksEditText: EditText
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        firstNameEditText = findViewById(R.id.fNameE)
        lastNameEditText = findViewById(R.id.lNameE)
        marksEditText = findViewById(R.id.marksE)
        btn = findViewById(R.id.button)

        vModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(StudentViewModel::class.java)

       isAddStudent =  intent.getBooleanExtra("isAdd", true)
        if(!isAddStudent ){
            btn.text = "UPDATE"
        }

    }

    fun addClick(view: View) {
        val fName = firstNameEditText.text.toString()
        val lName = lastNameEditText.text.toString()
        val marks = marksEditText.text.toString()

        if( fName.isNotEmpty() && lName.isNotEmpty() && marks.isNotEmpty()) {
            if(isAddStudent)
                vModel.addStudent(Student(fName, lName, marks.toInt()))
            else
                vModel.updateStudent(Student(fName, lName, marks.toInt()))

            finish()
        }
        else
            Toast.makeText(this, "Pls enter all data", Toast.LENGTH_LONG).show()

    }
}