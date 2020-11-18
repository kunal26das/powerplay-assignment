package `in`.getpowerplay.assignment.list

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.databinding.ItemMarkerBinding
import `in`.getpowerplay.assignment.source.model.Marker
import `in`.getpowerplay.assignment.view.MarkerView
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.setMargins
import androidx.essentials.list.List
import androidx.essentials.list.view.ListItemView

class Markers @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.recyclerViewStyle
) : List<Marker, ItemMarkerBinding>(context, attrs, defStyleAttr) {

    private val globalMargin = context.resources.getDimension(R.dimen.margin_global).toInt()
    private var onMarkerClickListener: OnMarkerClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MarkerView(
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
        holder: ListItemView.ViewHolder<Marker, ItemMarkerBinding>,
        position: Int,
        item: Marker
    ) {
        holder.listItemView.binding.root.setOnClickListener {
            holder.listItemView.item?.apply {
                onMarkerClickListener?.onClick(this)
            }
        }
    }

    fun setOnMarkerClickListener(action: (Marker) -> Unit) {
        onMarkerClickListener = object : OnMarkerClickListener {
            override fun onClick(marker: Marker) {
                action(marker)
            }
        }
    }

    interface OnMarkerClickListener {
        fun onClick(marker: Marker)
    }

}