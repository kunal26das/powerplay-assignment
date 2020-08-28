package `in`.getpowerplay.assignment.view

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.databinding.ItemDrawingBinding
import `in`.getpowerplay.assignment.source.model.Drawing
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.core.graphics.drawable.toBitmap
import androidx.essentials.list.AbstractList.Companion.DEFAULT_ORIENTATION
import androidx.essentials.list.view.ListItemView
import androidx.palette.graphics.Palette
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialCardViewStyle,
    attachToRoot: Boolean = DEFAULT_ATTACH_TO_ROOT,
    listOrientation: Int = DEFAULT_ORIENTATION
) : ListItemView<Drawing, ItemDrawingBinding>(
    context, attrs, defStyleAttr, attachToRoot, listOrientation
) {

    private val transparentColor = Color.parseColor("#00000000")

    init {
        radius = context.resources.getDimension(R.dimen.radius_global)
        elevation = context.resources.getDimension(R.dimen.elevation_global)
    }

    override val binding = ItemDrawingBinding.inflate(
        LayoutInflater.from(context), this, attachToRoot
    )

    public override fun bind(item: Drawing) {
        binding.apply {
            drawing = item
            executePendingBindings()
            drawingTimeAdded.text = item.timeAdded
            Log.d("Url", item.url)
            Picasso.get().load(Uri.parse(item.url))
                .into(drawingImage, object : Callback {
                    override fun onSuccess() {
                        val dominantColor =
                            Palette.Builder(drawingImage.drawable.toBitmap())
                                .generate().getDominantColor(transparentColor)
                        textLayout.background = GradientDrawable(
                            GradientDrawable.Orientation.BOTTOM_TOP,
                            intArrayOf(dominantColor, transparentColor)
                        )
                        val antiDominantColor = -16777215 - dominantColor
                        drawingTimeAdded.setTextColor(antiDominantColor)
                        drawingName.setTextColor(antiDominantColor)
                    }

                    override fun onError(e: Exception?) {}
                })
        }
    }

}