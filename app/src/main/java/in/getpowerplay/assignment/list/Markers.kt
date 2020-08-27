package `in`.getpowerplay.assignment.list

import `in`.getpowerplay.assignment.databinding.ItemMarkerBinding
import `in`.getpowerplay.assignment.source.model.Marker
import `in`.getpowerplay.assignment.view.MarkerView
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.essentials.list.List
import androidx.essentials.list.view.ListItemView

class Markers(
    context: Context,
    attrs: AttributeSet? = null
) : List<Marker, ItemMarkerBinding>(context, attrs) {

    override val emptyMessage = "No markers added yet"
    private var onMarkerClickListener: OnMarkerClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup): ListItemView.ViewHolder<Marker, ItemMarkerBinding> {
        val drawing = MarkerView(
            attachToRoot = false,
            context = parent.context,
            listOrientation = orientation
        )
        return drawing.viewHolder
    }

    override fun onBindViewHolder(holder: ListItemView.ViewHolder<Marker, ItemMarkerBinding>) {
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