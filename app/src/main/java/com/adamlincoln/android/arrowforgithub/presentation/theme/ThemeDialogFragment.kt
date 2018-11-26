package com.adamlincoln.android.arrowforgithub.presentation.theme

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.WindowManager
import com.adamlincoln.android.arrowforgithub.R
import com.adamlincoln.android.arrowforgithub.presentation.common.BaseBlurDialogFragment
import timber.log.Timber
import kotlin.math.roundToInt

class ThemeDialogFragment : BaseBlurDialogFragment() {

    private val redThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Red,
        R.string.md_color_name_red,
        R.color.md_red_500,
        R.color.md_red_800,
        R.color.md_red_A400
    )
    private val pinkThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Pink,
        R.string.md_color_name_pink,
        R.color.md_pink_500,
        R.color.md_pink_800,
        R.color.md_pink_A400
    )
    private val purpleThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Purple,
        R.string.md_color_name_purple,
        R.color.md_purple_500,
        R.color.md_purple_800,
        R.color.md_purple_A400
    )
    private val deepPurpleThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_DeepPurple,
        R.string.md_color_name_deep_purple,
        R.color.md_deep_purple_500,
        R.color.md_deep_purple_800,
        R.color.md_deep_purple_A400
    )
    private val indigoThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Indigo,
        R.string.md_color_name_indigo,
        R.color.md_indigo_500,
        R.color.md_indigo_800,
        R.color.md_indigo_A400
    )
    private val blueThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Blue,
        R.string.md_color_name_blue,
        R.color.md_blue_500,
        R.color.md_blue_800,
        R.color.md_blue_A400
    )
    private val lightBlueThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_LightBlue,
        R.string.md_color_name_light_blue,
        R.color.md_light_blue_500,
        R.color.md_light_blue_800,
        R.color.md_light_blue_A400
    )
    private val cyanThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Cyan,
        R.string.md_color_name_cyan,
        R.color.md_cyan_500,
        R.color.md_cyan_800,
        R.color.md_cyan_A400
    )
    private val tealThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Teal,
        R.string.md_color_name_teal,
        R.color.md_teal_500,
        R.color.md_teal_800,
        R.color.md_teal_A400
    )
    private val greenThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Green,
        R.string.md_color_name_green,
        R.color.md_green_500,
        R.color.md_green_800,
        R.color.md_green_A400
    )
    private val lightGreenThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_LightGreen,
        R.string.md_color_name_light_green,
        R.color.md_light_green_500,
        R.color.md_light_green_800,
        R.color.md_light_green_A400
    )
    private val limeThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Lime,
        R.string.md_color_name_lime,
        R.color.md_lime_500,
        R.color.md_lime_800,
        R.color.md_lime_A400
    )
    private val yellowThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Yellow,
        R.string.md_color_name_yellow,
        R.color.md_yellow_500,
        R.color.md_yellow_800,
        R.color.md_yellow_A400
    )
    private val amberThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Amber,
        R.string.md_color_name_amber,
        R.color.md_amber_500,
        R.color.md_amber_800,
        R.color.md_amber_A400
    )
    private val orangeThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Orange,
        R.string.md_color_name_orange,
        R.color.md_orange_500,
        R.color.md_orange_800,
        R.color.md_orange_A400
    )
    private val deepOrangeThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_DeepOrange,
        R.string.md_color_name_deep_orange,
        R.color.md_deep_orange_500,
        R.color.md_deep_orange_800,
        R.color.md_deep_orange_A400
    )
    private val brownThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Brown,
        R.string.md_color_name_brown,
        R.color.md_brown_500,
        R.color.md_brown_800,
        R.color.md_brown_200
    )
    private val greyThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_Grey,
        R.string.md_color_name_grey,
        R.color.md_grey_500,
        R.color.md_grey_800,
        R.color.md_grey_200
    )
    private val blueGreyThemeModel = ThemeViewModel(
        R.style.AppTheme_Colour_BlueGrey,
        R.string.md_color_name_blue_grey,
        R.color.md_blue_grey_500,
        R.color.md_blue_grey_800,
        R.color.md_blue_grey_200
    )

    private val themeModelList = listOf(
        redThemeModel,
        pinkThemeModel,
        purpleThemeModel,
        deepPurpleThemeModel,
        indigoThemeModel,
        blueThemeModel,
        lightBlueThemeModel,
        cyanThemeModel,
        tealThemeModel,
        greenThemeModel,
        lightGreenThemeModel,
        limeThemeModel,
        yellowThemeModel,
        amberThemeModel,
        orangeThemeModel,
        deepOrangeThemeModel,
        brownThemeModel,
        greyThemeModel,
        blueGreyThemeModel
    )

    private val themeAdapterListener = object : ThemeAdapter.OnThemeSelectedListener {
        override fun onThemeSelectedListener(theme: ThemeViewModel) {
            // TODO: Select theme.
            Timber.d("theme: $theme")
        }
    }

    private val clickListener = DialogInterface.OnClickListener { dialog, which ->
        // TODO: Set preference.
//        val theme = themeModelList[which]
        Timber.d("which: $which")
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val adapter = ThemeAdapter(context!!, themeModelList, themeAdapterListener)
        val customView = LayoutInflater.from(context).inflate(R.layout.dialog_theme, null, false)
        recyclerView = customView.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        return AlertDialog.Builder(context!!)
            .setIcon(R.drawable.ic_color_lens_black_24dp)
            .setTitle(R.string.dialog_title_theme)
            .setMessage(R.string.dialog_message_theme)
            .setView(customView)
            .setPositiveButton(android.R.string.ok, clickListener)
            .create()
    }

    override fun onResume() {
        super.onResume()

        val typedValue = TypedValue()
        (context as Activity).theme.resolveAttribute(android.R.attr.listPreferredItemHeightSmall, typedValue, true)
        val metrics = android.util.DisplayMetrics()
        val wm = activity!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        val listPreferredItemHeightSmall = typedValue.getDimension(metrics)
        val heightInt = listPreferredItemHeightSmall.roundToInt() * 4
        val layoutParams = recyclerView.layoutParams
        layoutParams.height = heightInt
        recyclerView.layoutParams = layoutParams
    }

    // getAttributeSize(theme, android.R.attr.textAppearanceLarge, android.R.attr.textSize) -> return android:textSize
    private fun getAttributeSize(themeId: Int, attrId: Int, attrNameId: Int): Int {
        val typedValue = TypedValue()
        val ctx = ContextThemeWrapper(context, themeId)
        ctx.theme.resolveAttribute(attrId, typedValue, true)
        val attributes = intArrayOf(attrNameId)
        val index = 0
        val array = ctx.obtainStyledAttributes(typedValue.data, attributes)
        val res = array.getDimensionPixelSize(index, 0)
        array.recycle()
        return res
    }
}
