package com.capgemini.universityapp.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StudentRepo(val ctx: Context) {

    private val db = StudentDatabase.getInstance(ctx)
    private val dao = db.getStudentDao()

    fun addStudent(std: Student){
        CoroutineScope(Dispatchers.Default).launch {
            dao.addStudent(std)
        }

    }

    fun deleteStd(std: Student){
        CoroutineScope(Dispatchers.Default).launch {
            dao.deleteStudent(std)
        }

    }

    fun deleteAllStudents(){
        CoroutineScope(Dispatchers.Default).launch {
            dao.deleteAll()
        }

    }

    fun updateStd(std: Student){
        CoroutineScope(Dispatchers.Default).launch {
            dao.updateStudent(std)
        }

    }

    suspend fun getAllStudents(): List<Student> {
        // launch - fire and forget
        // asynch - wait for result

        val deferredResult = CoroutineScope(Dispatchers.Default).async {
            dao.getStudents()
        }

        return deferredResult.await()
    }

}