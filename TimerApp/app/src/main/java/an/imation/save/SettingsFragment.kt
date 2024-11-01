package an.imation.save

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragment


class SettingsFragment : PreferenceFragment(),
    SharedPreferences.OnSharedPreferenceChangeListener,
    Preference.OnPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.timer_preference, rootKey)

        val sharedPreferences = preferenceScreen.sharedPreferences
        val preferenceScreen = preferenceScreen
        val count = preferenceScreen.preferenceCount

        for (i in 0 until count) {
            val preference = preferenceScreen.getPreference(i)

            if (preference !is CheckBoxPreference) {
                val value = sharedPreferences?.getString(preference.key, "")
                setPreferenceLabel(preference, value ?: "")
            }
        }

        val preference = findPreference<Preference>("default_interval")
        preference?.setOnPreferenceChangeListener { _, _ ->
            true
        }
    }

    private fun setPreferenceLabel(preference: Preference, value: String) {
        when (preference) {
            is ListPreference -> {
                val index = preference.findIndexOfValue(value)
                if (index >= 0) {
                    preference.summary = preference.entries[index]
                }
            }

            is EditTextPreference -> preference.summary = value
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val preference = key?.let { findPreference<Preference>(it) }
        if (preference !is CheckBoxPreference) {
            val value = sharedPreferences?.getString(key, "")
            if (preference != null) {
                setPreferenceLabel(preference, value ?: "")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }
    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        var toast: Toast? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toast = Toast.makeText(context, "Please enter an integer number", Toast.LENGTH_LONG)
        }

        if (preference.key == "default_interval") {
            val defaultIntervalString = newValue as String

            return try {
                val defaultInterval = defaultIntervalString.toInt()
                true
            } catch (e: NumberFormatException) {
                toast?.show()
                false // Возвращаем false, чтобы не сохранять значение
            }
        }

        return true
    }
}