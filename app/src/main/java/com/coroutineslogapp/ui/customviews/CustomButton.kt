package com.coroutineslogapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.coroutineslogapp.R
import kotlinx.android.synthetic.main.layout_custom_button.view.tv_text as buttonText
import kotlinx.android.synthetic.main.layout_custom_button.view.iv_image as buttonIcon

class CustomButton : ConstraintLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setAttributes(attrs)

    }

    init {
        val paddingTopBottom = context.resources.getDimension(R.dimen.profile_button_margin).toInt()
        LayoutInflater.from(context).inflate(R.layout.layout_custom_button, this, true)
        setPadding(paddingLeft, paddingTopBottom, paddingRight, paddingTopBottom)
        setBackgroundResource(R.drawable.grey_button_selector)
    }

    private fun setAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomButton, 0, 0)
            val textValue = resources.getString(typedArray.getResourceId(R.styleable.CustomButton_text, R.string.empty))
            buttonText.text = textValue
            buttonIcon.setImageResource(typedArray.getResourceId(R.styleable.CustomButton_icon, R.string.empty))
            typedArray.recycle()
        }
    }
}
