package `in`.getpowerplay.assignment.ui

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.mvvm.MarkerViewModel
import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.source.model.Marker
import android.graphics.Bitmap
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.ui.Activity
import androidx.essentials.events.Events
import com.davemorrissey.labs.subscaleview.ImageSource
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_marker.*

class MarkerActivity : Activity() {

    private lateinit var drawing: Drawing
    override val layout = R.layout.activity_marker
    override val viewModel by inject<MarkerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawing = intent?.getParcelableExtra(getString(R.string.drawing))!!
        markers.setOnMarkerClickListener {
            bottomSheet.hide()
        }
        loadDrawing()
    }

    override fun initObservers() {
        viewModel.idToken.observe {
            viewModel.getMarkers(drawing)
        }
        viewModel.markers.observe {
            drawingImage.markers = it?.map {
                PointF(it.x, it.y)
            }?.toMutableList() ?: mutableListOf()
            markers.submitList(it)
        }
        Events.subscribe(Marker::class.java) {
            viewModel.getMarkers(drawing)
            bottomSheet.expand()
        }
    }

    private fun loadDrawing() {
        val gestureDetector =
            GestureDetector(baseContext, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    bottomSheet.expand()
                    return true
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    drawingImage.viewToSourceCoord(e.x, e.y)?.let {
                        viewModel.postMarkers(
                            drawing, Marker(
                                null,
                                "Sample Marker",
                                "Description",
                                "Time Created",
                                it.x, it.y
                            )
                        )
                    }
                    return true
                }
            })
        Picasso.get().load(drawing.url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                drawingImage.setImage(ImageSource.bitmap(bitmap!!))
                drawingImage.setOnTouchListener { view, motionEvent ->
                    view.performClick()
                    gestureDetector.onTouchEvent(motionEvent)
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }
        })
    }

}