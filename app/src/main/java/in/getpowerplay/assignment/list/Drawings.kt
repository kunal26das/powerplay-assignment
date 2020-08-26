package `in`.getpowerplay.assignment.list

import `in`.getpowerplay.assignment.databinding.ItemDrawingBinding
import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.view.DrawingView
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.essentials.list.List
import androidx.essentials.list.view.ListItemView

class Drawings(
    context: Context,
    attrs: AttributeSet? = null
) : List<Drawing, ItemDrawingBinding>(context, attrs) {

    private var onDrawingClickListener: OnDrawingClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup): ListItemView.ViewHolder<Drawing, ItemDrawingBinding> {
        val drawing = DrawingView(
            attachToRoot = false,
            context = parent.context,
            listOrientation = orientation
        )
        return drawing.viewHolder
    }

    override fun onBindViewHolder(holder: ListItemView.ViewHolder<Drawing, ItemDrawingBinding>) {
        holder.listItemView.binding.root.setOnClickListener {
            holder.listItemView.item?.apply {
                onDrawingClickListener?.onClick(this)
            }
        }
    }

    fun setOnDrawingClickListener(action: (Drawing) -> Unit) {
        onDrawingClickListener = object : OnDrawingClickListener {
            override fun onClick(drawing: Drawing) {
                action(drawing)
            }
        }
    }

    interface OnDrawingClickListener {
        fun onClick(drawing: Drawing)
    }
}