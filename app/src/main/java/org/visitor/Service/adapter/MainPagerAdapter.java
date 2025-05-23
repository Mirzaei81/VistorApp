package org.visitor.Service.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.visitor.Service.fragments.BaseEntryFragment;
import org.visitor.Service.fragments.SettingsFragment;
import org.visitor.Service.fragments.SubmitFactorFragment;

import java.util.Locale;

public class MainPagerAdapter extends FragmentStateAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int).
        Log.d(MainPagerAdapter.class.getName(),String.format(Locale.getDefault(),"position clicked %d",position));
        return switch (position) {
            case 0 -> new BaseEntryFragment();
            case 1 -> new SubmitFactorFragment();
            case 2 -> new SettingsFragment();
            default -> throw new IllegalStateException("Unexpected value: " + position);
        };
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}
