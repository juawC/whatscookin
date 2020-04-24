package com.app.juawcevada.whatscookin.common.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import coil.api.load
import com.app.juawcevada.whatscookin.R


@BindingAdapter("setVisible")
fun setVisible(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) return
    imageView.load(url) {
        crossfade(true)
    }
}

@BindingAdapter("htmlText")
fun bindHtpText(textView: TextView, text: String?) {
    if (text == null) return
    textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
}


@BindingAdapter("placeHolderText")
fun bindPlaceHolderText(textView: TextView, text: String?) {
    if (text.isNullOrEmpty()) {
        textView.setBackgroundColor(textView.context.getColorByAttr(R.attr.textColorLight))
    } else {
        textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        textView.background = null
    }
}

