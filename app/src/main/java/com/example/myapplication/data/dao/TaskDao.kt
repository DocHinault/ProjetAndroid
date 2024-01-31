package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.Task
import java.util.*

// L'interface DAO pour définir les opérations de base de données pour les tâches.
@Dao
interface TaskDao {
    // Insérer une nouvelle tâche dans la base de données.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    // Mettre à jour une tâche existante.
    @Update
    suspend fun updateTask(task: Task)

    // Supprimer une tâche spécifique.
    @Delete
    suspend fun deleteTask(task: Task)

    // Obtenir toutes les tâches avec un LiveData pour observer les changements.
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    // Obtenir les tâches filtrées par priorité.
    @Query("SELECT * FROM tasks ORDER BY priority DESC")
    fun getTasksSortedByPriority(): LiveData<List<Task>>

    // Obtenir les tâches filtrées par date butoire.
    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    fun getTasksSortedByDueDate(): LiveData<List<Task>>

    // Obtenir une tâche par son titre.
    @Query("SELECT * FROM tasks WHERE title = :taskTitle LIMIT 1")
    fun getTaskByTitle(taskTitle: String): LiveData<Task?>

}
