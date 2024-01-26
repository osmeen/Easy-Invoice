package com.osmeen.easyinvoice.ui.theme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.osmeen.easyinvoice.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }
}
//BaseActivity Holds Common things from the two activities
// I used setOnApplyWindowInsetsListener so that I can scroll my content up when the keyboard is visible