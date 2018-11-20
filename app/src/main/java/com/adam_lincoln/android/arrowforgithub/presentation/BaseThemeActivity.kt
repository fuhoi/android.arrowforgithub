package com.adam_lincoln.android.arrowforgithub.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import com.adam_lincoln.android.arrowforgithub.R
import com.adam_lincoln.android.arrowforgithub.presentation.theme.ThemeDialogFragment

abstract class BaseThemeActivity : BaseActivity() {

    companion object {
        const val ACTIVITY_REQUEST_CODE_THEME: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val defaultValue = resources.getInteger(R.integer.user_pref_theme_key_default)
        val defaultTheme = if (defaultValue == -1) R.style.AppTheme_Colour_Blue else defaultValue
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
                ThemeDialogFragment().show(supportFragmentManager, "ThemeDialogFragment")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ACTIVITY_REQUEST_CODE_THEME) {
            if (resultCode == Activity.RESULT_OK) {
                recreate()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
