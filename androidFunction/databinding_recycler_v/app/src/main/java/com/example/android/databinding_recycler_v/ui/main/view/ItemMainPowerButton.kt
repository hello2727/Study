package com.example.android.databinding_recycler_v.ui.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android.databinding_recycler_v.databinding.ItemMainPowerButtonBinding

class ItemMainPowerButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: ItemMainPowerButtonBinding by lazy {
        ItemMainPowerButtonBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var isChecked: Boolean = false
        set(value) {
            binding.ivPowerButton.isEnabled = value
            field = value
        }
}
