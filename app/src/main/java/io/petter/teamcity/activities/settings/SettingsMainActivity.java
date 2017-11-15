package io.petter.teamcity.activities.settings;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.petter.teamcity.R;
import io.petter.teamcity.activities.MainActivity;
import io.petter.teamcity.fragments.settings.SettingsFragmentMain;
import io.petter.teamcity.global.Common;
import io.petter.teamcity.global.DialogUtility;
import io.petter.teamcity.global.ThemeUtility;

/**
 * Created by posborn on 6/24/14.
 */
public class SettingsMainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ThemeUtility.setAppTheme(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        getFragmentManager().beginTransaction().replace(R.id.frameLayout_settings,
                new SettingsFragmentMain()).commit();
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(i, 0);
        super.onBackPressed();
    }

    public void launchPlayStore() {
        try {
            String marketLink = getString(R.string.market_uri);
            String pkgName = getString(R.string.market_pkg_name);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(marketLink + pkgName)));
        } catch (ActivityNotFoundException e) {
            String noMarketTitle = getString(R.string.market_no_app_title);
            String noMarketMsg = getString(R.string.market_no_app_msg);
            DialogUtility.showAlert(this, noMarketTitle, noMarketMsg);
        }
    }

    public void launchGithub() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Common.gitHubUrl)));
    }

    public void launchContact() {
        String emailSubject = getString(R.string.contact_email_subject);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        emailIntent.setData(Uri.parse("mailto:" + Common.emailAddress));
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(emailIntent);
    }

    public String getVersionName() {
        PackageManager manager = getPackageManager();

        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // ignored
        }

        return Common.appName + " v" + versionName;
    }

    /* settings screen launchers */
    public void launchGeneral() {
        Intent i = new Intent(this, SettingsGeneralActivity.class);
        startActivity(i);
    }

    public void launchServer() {
        Intent i = new Intent(this, SettingsServerActivity.class);
        startActivity(i);
    }

    public void launchAppearance() {
        Intent i = new Intent(this, SettingsAppearanceActivity.class);
        startActivity(i);
    }
}
