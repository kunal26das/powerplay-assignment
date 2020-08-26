package `in`.getpowerplay.assignment.ui

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.mvvm.DrawingViewModel
import android.content.Intent
import android.os.Bundle
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.ui.Activity
import kotlinx.android.synthetic.main.activity_drawing.*

class DrawingActivity : Activity() {

    override val layout = R.layout.activity_drawing
    override val viewModel by inject<DrawingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.idToken.observe {
            viewModel.getDrawings(it)
        }
        viewModel.drawings.observe {
            drawings.submitList(it)
        }
        drawings.setOnDrawingClickListener {
            Intent(this, MarkerActivity::class.java).apply {
                putExtra(getString(R.string.drawing), it)
                startActivity(this)
            }
        }
    }

}