package com.adam_lincoln.android.arrowforgithub.presentation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import jp.wasabeef.glide.transformations.SupportRSBlurTransformation
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation

open class BaseBlurDialogFragment : DialogFragment() {

    private lateinit var dialogBackgroundView: AppCompatImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addDialogBackgroundView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        removeDialogBackgroundView()
        super.onDestroyView()
    }

    private fun addDialogBackgroundView() {
        // https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Glide-Library
        // https://github.com/wasabeef/glide-transformations
        val activityContext = activity!!.baseContext
        val activityWindow = activity!!.window

        // Add background view.
        dialogBackgroundView = AppCompatImageView(activityContext)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        activityWindow.addContentView(dialogBackgroundView, layoutParams)

        // Capture bitmap, transform and load.
        val viewContent = activityWindow.decorView.findViewById<View>(android.R.id.content)
        val viewBitmap = captureViewBitmap(viewContent)
        val multiTransformation = MultiTransformation<Bitmap>(
            BrightnessFilterTransformation(0.25f),
            GrayscaleTransformation(),
            SupportRSBlurTransformation(15)
        )
        Glide.with(activityContext)
            .asBitmap()
            .load(viewBitmap)
            .apply(RequestOptions.bitmapTransform(multiTransformation))
            .into(dialogBackgroundView)
    }

    private fun removeDialogBackgroundView() {
        // Remove background view.
        (dialogBackgroundView.parent as ViewGroup).removeView(dialogBackgroundView)
    }

    private fun captureViewBitmap(view: View): Bitmap {
        val width = view.width - view.paddingLeft - view.paddingRight
        val height = view.height - view.paddingTop - view.paddingBottom
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.translate((-view.paddingLeft).toFloat(), (-view.paddingTop).toFloat())
        view.draw(canvas)
        return bitmap
    }
}
