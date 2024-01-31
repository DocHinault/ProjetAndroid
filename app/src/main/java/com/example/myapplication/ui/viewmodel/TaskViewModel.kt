package com.example.myapplication.ui.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.model.Task
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.repository.TaskRepository
import kotlinx.coroutines.launch
import java.util.Date

// Le ViewModel fournit les données à l'interface utilisateur et survit aux changements de configuration.
class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(taskDao)
    val allTasks: LiveData<List<Task>> = repository.allTasks


    // Wrapper pour l'insertion qui appelle la méthode insert du repository dans une coroutine.
    fun addTask(title: String, description: String, priority: Int, dueDate: Date, status: String) {
        val newTask = Task(0, title, description, priority, dueDate.time, status)
        viewModelScope.launch {
            repository.insert(newTask)
        }
    }


    // Wrapper pour la mise à jour.
    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    // Wrapper pour la suppression.
    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    // Wrapper pour obtenir une tâche par titre.
    fun getTaskByTitle(title: String): LiveData<Task?> {
        return repository.getTaskByTitle(title)
    }
}
