package com.adam_lincoln.android.arrowforgithub.presentation.theme

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.adam_lincoln.android.arrowforgithub.R
import com.adam_lincoln.android.arrowforgithub.presentation.BaseThemeActivity
import kotlinx.android.synthetic.main.activity_theme.*

class ThemeActivity : BaseThemeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_theme)

        val redThemeModel = ThemeViewModel(R.style.AppTheme_Color_Red, R.string.md_color_name_red, R.color.md_red_500, R.color.md_red_800, R.color.md_red_A400)
        val pinkThemeModel = ThemeViewModel(R.style.AppTheme_Color_Pink, R.string.md_color_name_pink, R.color.md_pink_500, R.color.md_pink_800, R.color.md_pink_A400)
        val purpleThemeModel = ThemeViewModel(R.style.AppTheme_Color_Purple, R.string.md_color_name_purple, R.color.md_purple_500, R.color.md_purple_800, R.color.md_purple_A400)
        val deepPurpleThemeModel = ThemeViewModel(R.style.AppTheme_Color_DeepPurple, R.string.md_color_name_deep_purple, R.color.md_deep_purple_500, R.color.md_deep_purple_800, R.color.md_deep_purple_A400)
        val indigoThemeModel = ThemeViewModel(R.style.AppTheme_Color_Indigo, R.string.md_color_name_indigo, R.color.md_indigo_500, R.color.md_indigo_800, R.color.md_indigo_A400)
        val blueThemeModel = ThemeViewModel(R.style.AppTheme_Color_Blue, R.string.md_color_name_blue, R.color.md_blue_500, R.color.md_blue_800, R.color.md_blue_A400)
        val lightBlueThemeModel = ThemeViewModel(R.style.AppTheme_Color_LightBlue, R.string.md_color_name_light_blue, R.color.md_light_blue_500, R.color.md_light_blue_800, R.color.md_light_blue_A400)
        val cyanThemeModel = ThemeViewModel(R.style.AppTheme_Color_Cyan, R.string.md_color_name_cyan, R.color.md_cyan_500, R.color.md_cyan_800, R.color.md_cyan_A400)
        val tealThemeModel = ThemeViewModel(R.style.AppTheme_Color_Teal, R.string.md_color_name_teal, R.color.md_teal_500, R.color.md_teal_800, R.color.md_teal_A400)
        val greenThemeModel = ThemeViewModel(R.style.AppTheme_Color_Green, R.string.md_color_name_green, R.color.md_green_500, R.color.md_green_800, R.color.md_green_A400)
        val lightGreenThemeModel = ThemeViewModel(R.style.AppTheme_Color_LightGreen, R.string.md_color_name_light_green, R.color.md_light_green_500, R.color.md_light_green_800, R.color.md_light_green_A400)
        val limeThemeModel = ThemeViewModel(R.style.AppTheme_Color_Lime, R.string.md_color_name_lime, R.color.md_lime_500, R.color.md_lime_800, R.color.md_lime_A400)
        val yellowThemeModel = ThemeViewModel(R.style.AppTheme_Color_Yellow, R.string.md_color_name_yellow, R.color.md_yellow_500, R.color.md_yellow_800, R.color.md_yellow_A400)
        val amberThemeModel = ThemeViewModel(R.style.AppTheme_Color_Amber, R.string.md_color_name_amber, R.color.md_amber_500, R.color.md_amber_800, R.color.md_amber_A400)
        val orangeThemeModel = ThemeViewModel(R.style.AppTheme_Color_Orange, R.string.md_color_name_orange, R.color.md_orange_500, R.color.md_orange_800, R.color.md_orange_A400)
        val deepOrangeThemeModel = ThemeViewModel(R.style.AppTheme_Color_DeepOrange, R.string.md_color_name_deep_orange, R.color.md_deep_orange_500, R.color.md_deep_orange_800, R.color.md_deep_orange_A400)
        val brownThemeModel = ThemeViewModel(R.style.AppTheme_Color_Brown, R.string.md_color_name_brown, R.color.md_brown_500, R.color.md_brown_800, R.color.md_brown_200)
        val greyThemeModel = ThemeViewModel(R.style.AppTheme_Color_Grey, R.string.md_color_name_grey, R.color.md_grey_500, R.color.md_grey_800, R.color.md_grey_200)
        val blueGreyThemeModel = ThemeViewModel(R.style.AppTheme_Color_BlueGrey, R.string.md_color_name_blue_grey, R.color.md_blue_grey_500, R.color.md_blue_grey_800, R.color.md_blue_grey_200)

        val list = listOf(
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
                getPreferences(Context.MODE_PRIVATE)
                    .edit()
                    .putInt(getString(R.string.user_pref_theme_key), theme.themeResId)
                    .apply()

                recreate()
            }
        }

        val adapter = ThemeAdapter(this, list, listener)

        recyclerView.adapter = adapter

        buttonOk.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

}