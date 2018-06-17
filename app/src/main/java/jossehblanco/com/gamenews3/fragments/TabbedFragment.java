package jossehblanco.com.gamenews3.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jossehblanco.com.gamenews3.R;
import jossehblanco.com.gamenews3.adapters.TabbedFragmentViewPagerAdapter;

/**
 * Created by Salomon Castro on 16/6/2018.
 */

public class TabbedFragment extends Fragment {

    private String token;
    private int categoriaId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        token = getArguments().getString("token");
        categoriaId = getArguments().getInt("categoryId");
        return inflater.inflate(R.layout.fragment_tabbed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        TabbedFragmentViewPagerAdapter tfvpa = new TabbedFragmentViewPagerAdapter(getContext(), this.getChildFragmentManager(), token, categoriaId);
        viewPager.setAdapter(tfvpa);
        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}