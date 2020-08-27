package `in`.getpowerplay.assignment.view

import `in`.getpowerplay.assignment.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

class PinView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : SubsamplingScaleImageView(context, attr) {

    private var marker: Bitmap
    private val paint = Paint()
    private val pointF = PointF()

    var markers = mutableListOf<PointF>()
        set(value) {
            field = value
            invalidate()
        }

    init {
        val density = resources.displayMetrics.densityDpi.toFloat()
        val bitmap = ContextCompat.getDrawable(context, R.drawable.ic_anchor_36dp)?.toBitmap()!!
        val w = density / 420f * bitmap.width
        val h = density / 420f * bitmap.height
        marker = Bitmap.createScaledBitmap(bitmap, w.toInt(), h.toInt(), true)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isReady) return
        paint.isAntiAlias = true
        markers.forEach {
            sourceToViewCoord(it, pointF)
            val vX = pointF.x - marker.width / 2
            val vY = pointF.y - marker.height
            canvas?.drawBitmap(marker, vX, vY, paint)
        }
    }

    fun mark(pointF: PointF) {
        markers.plusAssign(pointF)
        invalidate()
    }

}
