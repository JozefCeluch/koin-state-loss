package com.example.koinstateloss.navgraphvm

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
import com.example.koinstateloss.R
import com.example.koinstateloss.SecondFragmentViewModel
import com.example.koinstateloss.SharedViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SecondFragment : Fragment() {

    private val sharedViewModel by koinNavGraphViewModel<SharedViewModel>(R.id.navgraphvm_shared_state_flow)
    private val viewModel by viewModel<SecondFragmentViewModel> { parametersOf(sharedViewModel) }


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
                Text(text = "SECOND - koinNavGraphViewModel")
                Text(text = "This screen shows the state set by the previous screen")
                Text(text = "STATE: ${viewModel.sharedViewModel.state}")
                Button(
                    onClick = {
                        findNavController().popBackStack(R.id.navgraphvm_shared_state_flow, true)
                    }
                ) {
                    Text(text = "Close")
                }
            }
        }
    }
}

