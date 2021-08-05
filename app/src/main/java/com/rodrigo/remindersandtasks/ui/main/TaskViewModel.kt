package com.rodrigo.remindersandtasks.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rodrigo.remindersandtasks.data.room.TaskDatabase
import com.rodrigo.remindersandtasks.data.model.Task
import com.rodrigo.remindersandtasks.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}