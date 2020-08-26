package `in`.getpowerplay.assignment.ui.main

import `in`.getpowerplay.assignment.R
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.ui.Activity

class MainActivity : Activity() {

    override val layout = R.layout.activity_main
    override val viewModel by inject<MainViewModel>()

}