package `in`.getpowerplay.assignment.ui

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.databinding.FragmentAddDrawingBinding
import `in`.getpowerplay.assignment.mvvm.DrawingViewModel
import `in`.getpowerplay.assignment.source.model.Drawing
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_drawing.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.io.ByteArrayOutputStream

class AddDrawingFragment : BottomSheetDialogFragment() {

    private lateinit var drawingUri: Uri
    private lateinit var binding: FragmentAddDrawingBinding
    private val viewModel by sharedViewModel<DrawingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        drawingUri = arguments?.getParcelable(getString(R.string.drawing))!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDrawingBinding.inflate(inflater, container, true)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.drawing = Drawing()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDrawingButton.setOnClickListener {
            if (drawingName.isValid) {
                ByteArrayOutputStream().apply {
                    val bitmap = drawingImage.drawable.toBitmap()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, this)
                    viewModel.postDrawing(binding.drawing!!, toByteArray())
                    addDrawingButton.visibility = View.GONE
                }
            }
        }
        loadDrawing()
    }

    private fun loadDrawing() {
        Picasso.get().load(drawingUri).into(drawingImage, object : Callback {
            override fun onSuccess() {
                addDrawingButton.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {}
        })
    }

}