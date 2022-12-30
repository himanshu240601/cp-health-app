package com.example.healthapp_collegeproject.home.utility

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.widget.Toast
import com.example.healthapp_collegeproject.databinding.FragmentHomeBinding
import com.example.healthapp_collegeproject.home.fragments.homefragment.HomeFragment
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.TasksSection
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.tasks_database.TaskDataBaseClass

class CheckInternetConnection(fragmentHomeBinding: FragmentHomeBinding, tasksSection: TasksSection, taskDataBaseClass: TaskDataBaseClass) {
    lateinit var context: Context
    private val networkCallback: ConnectivityManager.NetworkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            //call data fetching method
            taskDataBaseClass.fetchTasksDataFromDB(
                context,
                fragmentHomeBinding,
                tasksSection
            )
            HomeFragment().fetchFromDBSleepMood(context, fragmentHomeBinding)
        }
        override fun onLost(network: Network) {
            Toast.makeText(context,"No internet connection!",Toast.LENGTH_SHORT).show()
        }
    }

    fun listenForInternetConnectivity(context: Context) {
        this.context = context;
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    private fun unregisterListenerForInternetConnectivity() {
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}