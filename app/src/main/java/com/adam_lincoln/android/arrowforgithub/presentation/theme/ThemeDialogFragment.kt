package com.adam_lincoln.android.arrowforgithub.presentation.theme

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.adam_lincoln.android.arrowforgithub.R

class ThemeDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

    private val redThemeModel = ThemeViewModel(R.style.AppTheme_Color_Red, R.string.md_color_name_red, R.color.md_red_500, R.color.md_red_800, R.color.md_red_A400)
    private val pinkThemeModel = ThemeViewModel(R.style.AppTheme_Color_Pink, R.string.md_color_name_pink, R.color.md_pink_500, R.color.md_pink_800, R.color.md_pink_A400)
    private val purpleThemeModel = ThemeViewModel(R.style.AppTheme_Color_Purple, R.string.md_color_name_purple, R.color.md_purple_500, R.color.md_purple_800, R.color.md_purple_A400)
    private val deepPurpleThemeModel = ThemeViewModel(R.style.AppTheme_Color_DeepPurple, R.string.md_color_name_deep_purple, R.color.md_deep_purple_500, R.color.md_deep_purple_800, R.color.md_deep_purple_A400)
    private val indigoThemeModel = ThemeViewModel(R.style.AppTheme_Color_Indigo, R.string.md_color_name_indigo, R.color.md_indigo_500, R.color.md_indigo_800, R.color.md_indigo_A400)
    private val blueThemeModel = ThemeViewModel(R.style.AppTheme_Color_Blue, R.string.md_color_name_blue, R.color.md_blue_500, R.color.md_blue_800, R.color.md_blue_A400)
    private val lightBlueThemeModel = ThemeViewModel(R.style.AppTheme_Color_LightBlue, R.string.md_color_name_light_blue, R.color.md_light_blue_500, R.color.md_light_blue_800, R.color.md_light_blue_A400)
    private val cyanThemeModel = ThemeViewModel(R.style.AppTheme_Color_Cyan, R.string.md_color_name_cyan, R.color.md_cyan_500, R.color.md_cyan_800, R.color.md_cyan_A400)
    private val tealThemeModel = ThemeViewModel(R.style.AppTheme_Color_Teal, R.string.md_color_name_teal, R.color.md_teal_500, R.color.md_teal_800, R.color.md_teal_A400)
    private val greenThemeModel = ThemeViewModel(R.style.AppTheme_Color_Green, R.string.md_color_name_green, R.color.md_green_500, R.color.md_green_800, R.color.md_green_A400)
    private val lightGreenThemeModel = ThemeViewModel(R.style.AppTheme_Color_LightGreen, R.string.md_color_name_light_green, R.color.md_light_green_500, R.color.md_light_green_800, R.color.md_light_green_A400)
    private val limeThemeModel = ThemeViewModel(R.style.AppTheme_Color_Lime, R.string.md_color_name_lime, R.color.md_lime_500, R.color.md_lime_800, R.color.md_lime_A400)
    private val yellowThemeModel = ThemeViewModel(R.style.AppTheme_Color_Yellow, R.string.md_color_name_yellow, R.color.md_yellow_500, R.color.md_yellow_800, R.color.md_yellow_A400)
    private val amberThemeModel = ThemeViewModel(R.style.AppTheme_Color_Amber, R.string.md_color_name_amber, R.color.md_amber_500, R.color.md_amber_800, R.color.md_amber_A400)
    private val orangeThemeModel = ThemeViewModel(R.style.AppTheme_Color_Orange, R.string.md_color_name_orange, R.color.md_orange_500, R.color.md_orange_800, R.color.md_orange_A400)
    private val deepOrangeThemeModel = ThemeViewModel(R.style.AppTheme_Color_DeepOrange, R.string.md_color_name_deep_orange, R.color.md_deep_orange_500, R.color.md_deep_orange_800, R.color.md_deep_orange_A400)
    private val brownThemeModel = ThemeViewModel(R.style.AppTheme_Color_Brown, R.string.md_color_name_brown, R.color.md_brown_500, R.color.md_brown_800, R.color.md_brown_200)
    private val greyThemeModel = ThemeViewModel(R.style.AppTheme_Color_Grey, R.string.md_color_name_grey, R.color.md_grey_500, R.color.md_grey_800, R.color.md_grey_200)
    private val blueGreyThemeModel = ThemeViewModel(R.style.AppTheme_Color_BlueGrey, R.string.md_color_name_blue_grey, R.color.md_blue_grey_500, R.color.md_blue_grey_800, R.color.md_blue_grey_200)

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

    val listener: ThemeAdapter.OnThemeSelectedListener = object : ThemeAdapter.OnThemeSelectedListener {
        override fun onThemeSelectedListener(theme: ThemeViewModel) {
            // no-op.
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

    }

    lateinit var customView: View
    lateinit var imageView: AppCompatImageView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val adapter = ThemeAdapter(context!!, themeModelList, listener)
        customView = LayoutInflater.from(context).inflate(R.layout.dialog_theme, null, false)
        val recyclerView: RecyclerView = customView.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        imageView = customView.findViewById(R.id.imageView)
        return AlertDialog.Builder(context!!)
            .setIcon(R.drawable.ic_color_lens_black_24dp)
            .setTitle(R.string.dialog_title_theme)
            .setMessage(R.string.dialog_message_theme)
            .setView(customView)
            .setPositiveButton(android.R.string.ok,this)
            .create()
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view: View = inflater.inflate(R.layout.dialog_theme, container)
//
//        val message: AppCompatTextView = view.findViewById(R.id.message)
//        message.text = getString(R.string.dialog_message_theme)
//
//        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
//        recyclerView.adapter = adapter
//
//        return view
//    }

    private var parentListener: ThemeDialogListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ThemeDialogListener) {
            parentListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement " + ThemeDialogListener::class)
        }
    }

    interface ThemeDialogListener {
        fun onResume(imageView: AppCompatImageView, dialogFragment: DialogFragment)
    }

    override fun onResume() {
        super.onResume()
        activity!!.runOnUiThread { Handler().postDelayed({
            parentListener?.onResume(imageView, this)
        }, 3000) }
    }

}
