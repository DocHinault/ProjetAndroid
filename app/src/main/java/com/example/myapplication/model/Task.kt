package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Définition de l'entité Task pour la base de données Room.
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // L'ID unique pour chaque tâche, généré automatiquement.
    var title: String, // Le titre de la tâche.
    var description: String, // Une description détaillée de la tâche
    var priority: Int, // La priorité de la tâche, avec 1 étant la plus basse et 5 la plus haute.
    var dueDate: Long, // La date butoir pour la tâche.
    var status: String, // Le statut de la tâche, par exemple "En cours" ou "Terminée".
) {
}
