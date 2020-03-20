package com.bankmtk.pictureoftheday

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bankmtk.pictureoftheday.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
class EquilateralImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyle: Int = 0
): AppCompatImageView(context,attrs,defStyle){
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}

