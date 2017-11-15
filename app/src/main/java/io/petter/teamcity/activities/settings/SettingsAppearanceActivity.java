package io.petter.teamcity.activities.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.petter.teamcity.R;
import io.petter.teamcity.fragments.settings.SettingsFragmentAppearance;
import io.petter.teamcity.global.ThemeUtility;

/**
 * Created by raidzero on 1/11/15.
 */
public class SettingsAppearanceActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ThemeUtility.setAppTheme(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        getFragmentManager().beginTransaction().replace(R.id.frameLayout_settings,
                new SettingsFragmentAppearance()).commit();
    }

    public void restart() {
        Intent i = getIntent();
        finish();
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
