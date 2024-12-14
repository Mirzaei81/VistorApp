package org.visitor.Service.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.visitor.Service.fragments.BaseEntryFragment;

public class MainPagerAdapter extends FragmentStateAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int).
        switch (position){
            case 0:
                return new BaseEntryFragment();
            default:
                return new BaseEntryFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

