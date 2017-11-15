package io.petter.teamcity.fragments.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import io.petter.teamcity.R;
import io.petter.teamcity.activities.settings.SettingsAppearanceActivity;
import io.petter.teamcity.global.ThemeUtility;

/**
 * Created by posborn on 11/24/14.
 */
public class SettingsFragmentAppearance extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String tag = "SettingsAppearanceFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings_appearance);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        // set theme on the fly
        if (key.equals("theme")) {
            ((SettingsAppearanceActivity) getActivity()).restart();
        }
    }

    @Override
    public void onResume() {
        ThemeUtility.setAppTheme(getActivity());
        super.onResume();

        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
