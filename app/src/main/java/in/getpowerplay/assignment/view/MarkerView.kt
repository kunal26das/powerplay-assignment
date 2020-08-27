package `in`.getpowerplay.assignment.view

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.databinding.ItemMarkerBinding
import `in`.getpowerplay.assignment.source.model.Marker
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.essentials.list.AbstractList
import androidx.essentials.list.view.ListItemView

class MarkerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialCardViewStyle,
    attachToRoot: Boolean = DEFAULT_ATTACH_TO_ROOT,
    listOrientation: Int = AbstractList.DEFAULT_ORIENTATION
) : ListItemView<Marker, ItemMarkerBinding>(
    context, attrs, defStyleAttr, attachToRoot, listOrientation
) {

    init {
        radius = 0f
        elevation = 0f
    }

    override val binding = ItemMarkerBinding.inflate(
        LayoutInflater.from(context), this, attachToRoot
    )

    override fun bind(item: Marker) {
        binding.apply {
            marker = item
            executePendingBindings()
        }
    }

}