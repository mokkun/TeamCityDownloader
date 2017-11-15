package io.petter.teamcity.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.petter.teamcity.fragments.QuickStartFragment;
import io.petter.teamcity.fragments.WhatsNewFragment;
import io.petter.teamcity.global.Common;

public class WelcomeTabsPagerAdapter extends FragmentPagerAdapter {

    public WelcomeTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case Common.WELCOME_TAB_WHATS_NEW:
                return new WhatsNewFragment();
            case Common.WELCOME_TAB_QUICK_START:
                return new QuickStartFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
