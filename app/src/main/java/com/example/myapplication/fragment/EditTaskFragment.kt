package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentEditTaskBinding
import com.example.myapplication.ui.viewmodel.TaskViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.model.Task

class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel

    // Liste pour stocker les tâches
    private var tasksList: List<Task> = listOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
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
                binding.radioGroupEditTasks.addView(radioButton)
            }
        })

        // Gérer le clic sur le bouton d'édition
        binding.buttonEditTask.setOnClickListener {
            val selectedTaskId = binding.radioGroupEditTasks.checkedRadioButtonId
            val taskToEdit = tasksList.firstOrNull { it.id == selectedTaskId }

            taskToEdit?.let {
                // Ici, vous pouvez ajouter la logique pour éditer la tâche.
                // Par exemple, passer l'ID de la tâche à un autre fragment pour l'édition.
                // findNavController().navigate(R.id.action_editTaskFragment_to_taskDetailsFragment, Bundle().apply { putInt("taskId", it.id) })
            }

            // Rediriger vers le HomeFragment après la modification
            findNavController().navigate(R.id.action_editTaskFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
