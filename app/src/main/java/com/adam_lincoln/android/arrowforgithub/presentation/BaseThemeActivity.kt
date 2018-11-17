package com.adam_lincoln.android.arrowforgithub.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.view.*
import com.adam_lincoln.android.arrowforgithub.R
import com.adam_lincoln.android.arrowforgithub.presentation.theme.ThemeDialogFragment
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.app.DialogFragment
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

abstract class BaseThemeActivity : BaseActivity(), ThemeDialogFragment.ThemeDialogListener {

    companion object {
        val ACTIVITY_REQUEST_CODE_THEME: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val defaultValue = resources.getInteger(R.integer.user_pref_theme_key_default)
        val defaultTheme = if (defaultValue == -1) R.style.AppTheme_Color_Blue else defaultValue
        val theme = sharedPref.getInt(getString(R.string.user_pref_theme_key), defaultTheme)
        setTheme(theme)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_base_theme_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_theme -> {
                displayThemeDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun displayThemeDialog() {
        ThemeDialogFragment().show(supportFragmentManager, "ThemeDialogFragment")

//        startActivityForResult(Intent(baseContext, ThemeActivity::class.java), ACTIVITY_REQUEST_CODE_THEME)

//        val bitmap: Bitmap = captureViewBitmap(rootView)
//        val multi = MultiTransformation<Bitmap>(
//            jp.wasabeef.glide.transformations.GrayscaleTransformation(),
//            jp.wasabeef.glide.transformations.BlurTransformation(25, 3)
//        )
//        val target = object : SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
//            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                val drawable: Drawable = BitmapDrawable(resources, resource)
////                rootView.background = drawable  // Nope.
////                rootView.overlay.add(drawable)  // Does nothing.
////                window.setBackgroundDrawable(drawable)  // Nope.
//            }
//        }
//        Glide.with(applicationContext)
//            .asBitmap()
//            .load(bitmap)
//            .apply(RequestOptions.bitmapTransform(multi))
//            .into(target)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ACTIVITY_REQUEST_CODE_THEME) {
            if (resultCode == Activity.RESULT_OK) {
                recreate()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume(imageView: AppCompatImageView, dialogFragment: DialogFragment) {
        // https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Glide-Library
        // https://github.com/wasabeef/glide-transformations
        val context = imageView.context
        val bitmap: Bitmap = captureViewBitmap(rootView)
        val multi = MultiTransformation<Bitmap>(
            jp.wasabeef.glide.transformations.GrayscaleTransformation(),
            jp.wasabeef.glide.transformations.BlurTransformation(25, 3)
        )
        val target = object : SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val drawable: Drawable = BitmapDrawable(resources, resource)
//                val window = dialogFragment.dialog.window!!

//                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//                window.setBackgroundDrawable(drawable)
//                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
//                window.setGravity(Gravity.CENTER)

                // Best yet.  Needs to be backwards compat.  Needs to remove on dismiss.
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                val dimBackground = FrameLayout(this@BaseThemeActivity)
                dimBackground.background = drawable
                val params = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                window.addContentView(dimBackground, params)
            }
        }
        Glide.with(context)
            .asBitmap()
            .load(bitmap)
            .apply(RequestOptions.bitmapTransform(multi))
            .into(target)
    }

    private fun captureViewBitmap(view: View): Bitmap {
//        view.isDrawingCacheEnabled = true
//        view.buildDrawingCache()
//        val bitmap = view.drawingCache
//        view.isDrawingCacheEnabled = false
//        return bitmap

//        val background = view.background
//        view.background = null
        val width = view.width - view.paddingLeft - view.paddingRight
        val height = view.height - view.paddingTop - view.paddingBottom
//        if (width == 0 || height == 0) {
//            return null
//        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.translate((-view.paddingLeft).toFloat(), (-view.paddingTop).toFloat())
        view.draw(canvas)
//        view.background = background
        return bitmap
    }
}
