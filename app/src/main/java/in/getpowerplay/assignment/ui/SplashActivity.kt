package `in`.getpowerplay.assignment.ui

import `in`.getpowerplay.assignment.R
import `in`.getpowerplay.assignment.mvvm.SplashViewModel
import android.content.Intent
import androidx.essentials.core.ui.Activity
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseUser

class SplashActivity : Activity() {

    override val layout = R.layout.activity_splash
    override val viewModel by viewModel<SplashViewModel>()

    override fun initObservers() {
        viewModel.currentUser.observe(this, object : Observer<FirebaseUser?> {
            override fun onChanged(it: FirebaseUser?) {
                if (it != null) {
                    viewModel.currentUser.removeObserver(this)
                    Intent(baseContext, DrawingActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }
            }
        })
    }

}