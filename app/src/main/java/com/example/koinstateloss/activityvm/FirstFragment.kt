package com.example.koinstateloss.activityvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.koinstateloss.FirstFragmentViewModel
import com.example.koinstateloss.R
import com.example.koinstateloss.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FirstFragment : Fragment() {

    private val args by navArgs<FirstFragmentArgs>()
    private val sharedViewModel by activityViewModel<SharedViewModel>()
    private val viewModel by viewModel<FirstFragmentViewModel> {
        parametersOf(sharedViewModel, args.initialState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "FIRST - activityViewModel")
                Text(text = "This screen shows the state set by nav args")
                Text(text = "STATE: ${viewModel.sharedViewModel.state}")
                Button(onClick = { findNavController().navigate(R.id.second_fragment) }) {
                    Text(text = "Next")
                }
            }
        }
    }
}