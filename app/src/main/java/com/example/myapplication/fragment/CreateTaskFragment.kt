package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCreateTaskBinding
import com.example.myapplication.ui.viewmodel.TaskViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import java.util.*

class CreateTaskFragment : Fragment() {

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSaveTask.setOnClickListener {
            val title = binding.editTextTaskName.text.toString()
            val description = binding.editTextTaskDescription.text.toString()
            val priority = binding.editTextTaskPriority.text.toString().toInt()
            val status = if (binding.radioButtonToDo.isChecked) "À faire" else "Fini"
            val dueDate = Calendar.getInstance().apply {
                set(binding.datePickerDueDate.year, binding.datePickerDueDate.month, binding.datePickerDueDate.dayOfMonth)
            }.time

            viewModel.addTask(title, description, priority, dueDate, status)
            findNavController().navigate(R.id.action_createTaskFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
