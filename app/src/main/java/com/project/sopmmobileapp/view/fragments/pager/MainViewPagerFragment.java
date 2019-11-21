package com.project.sopmmobileapp.view.fragments.pager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.adapters.AdapterInAreaSurveysListItem;
import com.project.sopmmobileapp.view.adapters.AdapterTabsPager;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithLogOutDialog;
import com.project.sopmmobileapp.view.fragments.LoginFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainViewPagerFragment extends Fragment implements BackWithLogOutDialog {


    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;

    private View mainView;

    private final List<String> fragments = Arrays.asList("My Surveys", "Surveys in the area","All Surveys");

    private boolean isInitialized = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (!isInitialized) {

            mainView = inflater.inflate(R.layout.main_view_pager_fagment, container, false);

            ButterKnife.bind(this, mainView);
//
            CommonNavigator commonNavigator = new CommonNavigator(getContext());
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {

                @Override
                public int getCount() {
                    return fragments == null ? 0 : fragments.size();
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                    colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                    colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                    colorTransitionPagerTitleView.setText(fragments.get(index));
                    colorTransitionPagerTitleView.setOnClickListener(view -> viewPager
                            .setCurrentItem(index));
                    return colorTransitionPagerTitleView;
                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                    return indicator;
                }
            });
            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, viewPager);
            AdapterTabsPager adapterTabsPager = new AdapterTabsPager(getFragmentManager(), fragments);
            viewPager.setOffscreenPageLimit(-1);
            viewPager.setAdapter(adapterTabsPager);


            isInitialized = true;
        }
        return mainView;
    }

    @OnClick(R.id.log_out_button)
    public void logout() {
        ((MainActivity) Objects.requireNonNull(getActivity())).onBackPressed();
    }
}
