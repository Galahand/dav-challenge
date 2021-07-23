package com.example.platzi_challenge.ui.utils

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.core.text.bold
import androidx.core.text.inSpans
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.platzi_challenge.R

object BaseBinding {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun handleUrlImage(
        image: ImageView,
        url: String,
    ) {
        Glide.with(image.context)
            .load(url)
            .into(image)
    }

    @JvmStatic
    @BindingAdapter("beer_stat_quantity")
    fun formatBeerInfo(text: TextView, statQuantity: Double?) = text.apply {
        val title = this.text.toString()
        val quantity = statQuantity?.toString() ?: context.getString(R.string.not_applicable)
        val span = SpannableStringBuilder()
            .fontSize(context.getDimenPixelSize(R.dimen.beer_stat_quantity_size)) {
                bold { append(quantity) }
                appendLine()
            }.append(title)
        setText(span)
    }

    private fun Context.getDimenPixelSize(@DimenRes res: Int) = resources.getDimensionPixelSize(res)

     private inline fun SpannableStringBuilder.fontSize(
         pixelSize: Int,
         action: SpannableStringBuilder.() -> Unit,
     ) = inSpans(AbsoluteSizeSpan(pixelSize), action)
}