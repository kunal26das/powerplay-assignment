package `in`.getpowerplay.assignment.ui

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.mvvm.DrawingViewModel
import `in`.getpowerplay.assignment.source.model.Drawing
import android.content.Intent
import android.os.Bundle
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.ui.Activity
import androidx.essentials.events.Events
import kotlinx.android.synthetic.main.activity_drawing.*

class DrawingActivity : Activity() {

    override val layout = R.layout.activity_drawing
    override val viewModel by inject<DrawingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postDrawingFab.setOnClickListener {
            viewModel.postDrawing(
                Drawing(
                    null,
                    "Sample Drawing",
                    "Time Added",
                    "$URL"
                )
            )
        }
        drawings.setOnDrawingClickListener {
            Intent(this, MarkerActivity::class.java).apply {
                putExtra(getString(R.string.drawing), it)
                startActivity(this)
            }
        }
    }

    override fun initObservers() {
        viewModel.idToken.observe {
            viewModel.getDrawings()
        }
        viewModel.drawings.observe {
            drawings.submitList(it)
        }
        Events.subscribe(Drawing::class.java) {
            viewModel.getDrawings()
        }
    }

    companion object {
        private const val URL =
            "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F51be4238-9686-46e7-bd24-e5ac79a9bd51%2Fbasement_image.jpg?table=block&id=9d6d10e6-73a8-439f-8f68-39ea2524c372&width=960&userId=&cache=v2"
    }

}