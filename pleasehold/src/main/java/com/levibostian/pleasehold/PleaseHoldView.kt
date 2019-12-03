package com.levibostian.pleasehold

import android.widget.LinearLayout
import android.content.Context
import android.util.AttributeSet
import android.annotation.TargetApi
import android.os.Build.VERSION_CODES
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class PleaseHoldView: LinearLayout {

    private lateinit var titleTextView: TextView
    private lateinit var messageTextView: TextView

    var title: String? = null
        set(value) {
            field = value

            titleTextView.apply {
                text = value
                visibility = if (field != null) View.VISIBLE else View.GONE
            }
        }

    var message: String? = null
        set(value) {
            field = value

            messageTextView.apply {
                text = value
                visibility = if (field != null) View.VISIBLE else View.GONE
            }
        }

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.pleaseholdview_style)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initialize(context, attrs, defStyleAttr)
    }
    @TargetApi(VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs, defStyleAttr)
    }

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
            setGravity(Gravity.CENTER)
        }
        setPadding(20, 20, 20, 20)

        val a = context.obtainStyledAttributes(attrs, R.styleable.PleaseHoldView, defStyleAttr, 0)
        val stylesArray = context.obtainStyledAttributes(attrs, R.styleable.PleaseHoldViewStyle, defStyleAttr, 0)
        try {
            val titleText: String? = a.getString(R.styleable.PleaseHoldView_pleasehold_title_text)
            val messageText: String? = a.getString(R.styleable.PleaseHoldView_pleasehold_message_text)

            val titleStyle = stylesArray.getResourceId(R.styleable.PleaseHoldViewStyle_pleaseholdview_titleTextView, R.style.PleaseHoldView_TitleTextView)
            val messageStyle = stylesArray.getResourceId(R.styleable.PleaseHoldViewStyle_pleaseholdview_messageTextView, R.style.PleaseHoldView_MessageTextView)
            val progressBarStyle = stylesArray.getResourceId(R.styleable.PleaseHoldViewStyle_pleaseholdview_progressBar, R.style.PleaseHoldView_ProgressBar)

            val textViewLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            titleTextView = TextView(ContextThemeWrapper(context, titleStyle), null, titleStyle)
            addView(titleTextView, textViewLayoutParams)

            messageTextView = TextView(ContextThemeWrapper(context, messageStyle), null, messageStyle)
            addView(messageTextView, textViewLayoutParams)

            val progressBar = ProgressBar(ContextThemeWrapper(context, progressBarStyle), null, progressBarStyle)
            addView(progressBar, textViewLayoutParams)

            title = titleText
            message = messageText
        } finally {
            a.recycle()
            stylesArray.recycle()
        }
    }

}