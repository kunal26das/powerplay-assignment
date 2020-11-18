package `in`.getpowerplay.assignment.ui

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.databinding.ActivityDrawingBinding
import `in`.getpowerplay.assignment.mvvm.DrawingViewModel
import `in`.getpowerplay.assignment.source.model.Drawing
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.essentials.core.lifecycle.Activity
import androidx.essentials.events.Events

class DrawingActivity : Activity<ActivityDrawingBinding>() {

    override val layout = R.layout.activity_drawing
    private val addDrawingFragment = AddDrawingFragment()
    override val viewModel by viewModel<DrawingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDrawingFragment.arguments = Bundle()
        binding.addDrawingButton.setOnClickListener {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/*"
                startActivityForResult(this, REQUEST_CODE_PICK_DRAWING)
            }
        }
        binding.drawings.setOnDrawingClickListener {
            Intent(this, MarkerActivity::class.java).apply {
                putExtra(getString(R.string.drawing), it)
                startActivity(this)
            }
        }
    }

    override fun initObservers() {
        viewModel.idToken.observe {
            viewModel.getDrawings()
        }
        viewModel.drawings.observe {
            binding.drawings.submitList(it)
        }
        Events.subscribe(Drawing::class.java) {
            addDrawingFragment.dismiss()
            viewModel.getDrawings()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_DRAWING
            && resultCode == RESULT_OK && data != null
        ) {
            addDrawingFragment.arguments?.putParcelable(getString(R.string.drawing), data.data)
            addDrawingFragment.show(supportFragmentManager, null)
        }
    }

    companion object {
        private const val REQUEST_CODE_PICK_DRAWING = 101
    }
}