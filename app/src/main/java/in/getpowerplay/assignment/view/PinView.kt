package `in`.getpowerplay.assignment.view

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView


class PinView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : SubsamplingScaleImageView(context, attr) {

    fun dropPin(pin: PointF) {
        if (!isReady) return
        val vPin = PointF()
        sourceToViewCoord(pin, vPin)
    }

}
