package com.example.myapplication.repository

import com.example.myapplication.data.dao.TaskDao
import com.example.myapplication.model.Task
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

// Le repository pour les tâches qui sert d'intermédiaire entre le DAO et la couche présentation.
class TaskRepository(private val taskDao: TaskDao) {
    // Obtenez toutes les tâches. Cela renvoie un LiveData, donc toute mise à jour de la DB sera observée.
    val allTasks = taskDao.getAllTasks()

    // Insérer une tâche. La méthode doit être suspendue car elle doit être appelée depuis une coroutine.
    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    // Mettre à jour une tâche. Encore une fois, suspendue pour une exécution asynchrone.
    suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }

    // Supprimer une tâche.
    suspend fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    // Fonction pour insérer une tâche avec gestion d'erreurs.
    fun insertTask(task: Task) = liveData(Dispatchers.IO) {
        try {
            if (task.title.isBlank()) {
                emit(Result.failure(Exception("Le titre de la tâche ne peut pas être vide")))
            } else {
                taskDao.insertTask(task)
                emit(Result.success(Unit))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    // Obtenir une tâche par titre.
    fun getTaskByTitle(title: String) = taskDao.getTaskByTitle(title)
}
