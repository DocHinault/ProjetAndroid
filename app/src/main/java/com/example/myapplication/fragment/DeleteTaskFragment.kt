package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentDeleteTaskBinding
import com.example.myapplication.ui.viewmodel.TaskViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.model.Task

class DeleteTaskFragment : Fragment() {

    private var _binding: FragmentDeleteTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel

    // Liste pour stocker les tâches
    private var tasksList: List<Task> = listOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDeleteTaskBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observer les tâches et les ajouter à la liste et au RadioGroup
        viewModel.allTasks.observe(viewLifecycleOwner, { tasks ->
            tasksList = tasks
            tasks.forEach { task ->
                val radioButton = RadioButton(context).apply {
                    text = task.title
                    id = task.id
                }
                binding.radioGroupTasks.addView(radioButton)
            }
        })

        // Gérer le clic sur le bouton de suppression
        binding.buttonConfirmDelete.setOnClickListener {
            val selectedTaskId = binding.radioGroupTasks.checkedRadioButtonId
            val taskToDelete = tasksList.firstOrNull { it.id == selectedTaskId }
            taskToDelete?.let { viewModel.delete(it) }
            findNavController().navigate(R.id.action_deleteTaskFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
