package tj.colibri.avrang.utils

import android.content.Context
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.widget.RadioButton
import tj.colibri.avrang.R

class SubtitleRadioButton @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = R.attr.radioButtonStyle
) : androidx.appcompat.widget.AppCompatRadioButton(context, attributeSet, defStyleAttr) {
    private val titleSpan: TextAppearanceSpan
    var title: String = ""
        set(value) {
            field = value
            setUpText()
        }

    private val subtitleSpan: TextAppearanceSpan
    var subtitle: String = ""
        set(value) {
            field = value
            setUpText()
        }

    init {
        val typedArray = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.SubtitleRadioButton,
                defStyleAttr, 0
        )

        try {
            title = typedArray.getString(R.styleable.SubtitleRadioButton_titleText) ?: ""
            subtitle = typedArray.getString(R.styleable.SubtitleRadioButton_subtitleText) ?: ""

            val titleTextAppearance = typedArray.getResourceId(
                    R.styleable.SubtitleRadioButton_titleTextAppearance,
                    R.style.TextAppearance_AppCompat_Body1
            )
            titleSpan = TextAppearanceSpan(context, titleTextAppearance)

            val subtitleTextAppearance = typedArray.getResourceId(
                    R.styleable.SubtitleRadioButton_subtitleTextAppearance,
                    R.style.TextAppearance_AppCompat_Caption
            )
            subtitleSpan = TextAppearanceSpan(context, subtitleTextAppearance)
        } finally {
            typedArray.recycle()
        }

        setUpText()
    }

    private fun setUpText() {
        val spannableText = SpannableString("$title\n$subtitle")
        spannableText.setSpan(titleSpan, 0, title.length, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableText.setSpan(
                subtitleSpan,
                title.length, title.length + subtitle.length + 1,
                SpannableString.SPAN_EXCLUSIVE_INCLUSIVE
        )

        text = spannableText
    }
}

