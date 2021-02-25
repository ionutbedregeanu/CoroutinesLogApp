package com.coroutineslogapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.coroutineslogapp.R
import kotlinx.android.synthetic.main.layout_settings_item.view.*

class SettingsItem : ConstraintLayout {
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
        LayoutInflater.from(context).inflate(R.layout.layout_settings_item, this, true)
        setPadding(paddingLeft, paddingTopBottom, paddingRight, paddingTopBottom)
    }

    private fun setAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.SettingsItem, 0, 0)
            val nameValue = resources.getString(
                typedArray.getResourceId(
                    R.styleable.SettingsItem_name,
                    R.string.empty
                )
            )
            title.text = nameValue

            val descriptionValue = resources.getString(
                typedArray.getResourceId(
                    R.styleable.SettingsItem_description,
                    R.string.empty
                )
            )
            description.text = descriptionValue
            typedArray.recycle()
        }
    }
}
