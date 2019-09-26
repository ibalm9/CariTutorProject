package com.example.finalproject.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.Adapter.PagerAdapter;
import com.example.finalproject.R;

public class BeritaFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public Toolbar mToolbar;

    public static BeritaFragment newInstance() {
        BeritaFragment fragment = new BeritaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_berita, container, false);
        tabLayout=(TabLayout)view.findViewById(R.id.tab_layout);
        viewPager=(ViewPager)view.findViewById(R.id.pager);
        setupViewPager(viewPager);



        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter adapter =
                new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(new TabFragmentWajotv(), "Tab1");
        adapter.addFragment(new TabFragmentWajoterkini(), "Tab2");
        adapter.addFragment(new TabFragmentScapnews(), "Tab3");
        viewPager.setAdapter(adapter);
    }
    private void setupTabIcons() {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        TextView tabOne =(TextView)view.findViewById(R.id.text);
        tabOne.setText("WajoTV");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        TextView tabTwo =(TextView)view1.findViewById(R.id.text);
        tabTwo.setText("Wajo Terkini");
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        TextView tabThree =(TextView)view2.findViewById(R.id.text);
        tabThree.setText("SCAP");
        tabLayout.getTabAt(2).setCustomView(tabThree);

//
//        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
//        tabThree.setText("THREE");
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_settings_black_24dp, 0, 0);
//        tabLayout.getTabAt(2).setCustomView(tabThree);
    }
}
