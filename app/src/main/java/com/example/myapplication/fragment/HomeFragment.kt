package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.adapter.TaskAdapter
import com.example.myapplication.ui.viewmodel.TaskViewModel
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskViewModel
    private val adapter = TaskAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeTasks()

        binding.buttonAddTask.setOnClickListener {
            // Naviguer vers CreateTaskFragment
            findNavController().navigate(R.id.action_homeFragment_to_createTaskFragment)
        }

        binding.buttonEditTask.setOnClickListener {
            // Naviguer vers EditTaskFragment
            findNavController().navigate(R.id.action_homeFragment_to_editTaskFragment)
        }

        binding.buttonDeleteTask.setOnClickListener {
            // Naviguer vers DeleteTaskFragment
            findNavController().navigate(R.id.action_homeFragment_to_deleteTaskFragment)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTasks.adapter = adapter
    }

    private fun observeTasks() {
        viewModel.allTasks.observe(viewLifecycleOwner, { tasks ->
            adapter.setTasks(tasks)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
