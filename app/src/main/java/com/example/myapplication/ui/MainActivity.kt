package com.example.myapplication
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.adapter.TaskAdapter
import com.example.myapplication.ui.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        setupRecyclerView()
        observeTasks()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewTasks.adapter = adapter
        // Ici, vous pouvez configurer d'autres aspects de RecyclerView si nécessaire.
    }

    private fun observeTasks() {
        viewModel.allTasks.observe(this, { tasks ->
            // Mettez à jour l'adapter avec la liste des tâches.
            adapter.setTasks(tasks)
        })
    }
    // Ici, vous pouvez ajouter des méthodes pour gérer les interactions de l'utilisateur.
}

