package `in`.getpowerplay.assignment.list

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.databinding.ItemDrawingBinding
import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.view.DrawingView
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.setMargins
import androidx.essentials.list.List
import androidx.essentials.list.view.ListItemView

class Drawings @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.recyclerViewStyle
) : List<Drawing, ItemDrawingBinding>(context, attrs, defStyleAttr) {

    private val globalMargin = context.resources.getDimension(R.dimen.margin_global).toInt()
    private var onDrawingClickListener: OnDrawingClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = DrawingView(
        attachToRoot = false,
        context = parent.context,
        listOrientation = orientation
    ).apply {
        layoutParams = MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(globalMargin)
        }
    }.viewHolder

    override fun onBindViewHolder(
        holder: ListItemView.ViewHolder<Drawing, ItemDrawingBinding>,
        position: Int, item: Drawing
    ) {
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