package `in`.getpowerplay.assignment.ui

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.mvvm.MarkerViewModel
import `in`.getpowerplay.assignment.source.model.Drawing
import android.os.Bundle
import android.view.WindowManager
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.ui.Activity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_marker.*

class MarkerActivity : Activity() {

    private val mScaleFactor = 1.0f
    private lateinit var drawing: Drawing
    override val layout = R.layout.activity_marker
    override val viewModel by inject<MarkerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawing = intent?.getParcelableExtra(getString(R.string.drawing))!!
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        Picasso.get().load(drawing.url).into(drawingImage)
    }

}