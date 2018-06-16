package jossehblanco.com.gamenews3.adapters;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jossehblanco.com.gamenews3.fragments.TabbedChildFragment;

/**
 * Created by UCA on 16/6/2018.
 */

public class TabbedFragmentViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String token;
    private int categoryId;
    public TabbedFragmentViewPagerAdapter(Context context, FragmentManager fm, String token, int categoryId) {
        super(fm);
        mContext = context;
        this.token = token;
        this.categoryId = categoryId;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            TabbedChildFragment tcf = new TabbedChildFragment();
            Bundle bundle = new Bundle();
            switch (categoryId){
                case 0:
                    bundle.putString("category", "lol");
                    break;
                case 1:
                    bundle.putString("category", "overwawtch");
                    break;
                case 2:
                    bundle.putString("category", "csgo");
                    break;
            }
            bundle.putString("token", token);
            tcf.setArguments(bundle);
            return tcf;
        }else if(position == 1){
            TabbedChildFragment tcf1 = new TabbedChildFragment();
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            switch (categoryId){
                case 0:
                    bundle.putString("category", "lol");
                    break;
                case 1:
                    bundle.putString("category", "overwawtch");
                    break;
                case 2:
                    bundle.putString("category", "csgo");
                    break;
            }
            tcf1.setArguments(bundle);
            return tcf1;
        }else if(position == 2){
            TabbedChildFragment tcf2 = new TabbedChildFragment();
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            switch (categoryId){
                case 0:
                    bundle.putString("category", "lol");
                    break;
                case 1:
                    bundle.putString("category", "overwawtch");
                    break;
                case 2:
                    bundle.putString("category", "csgo");
                    break;
            }
            tcf2.setArguments(bundle);
            return tcf2;
        }
        else{
            return null;
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "News";
            case 1:
                return "Top Players";
            case 2:
                return "Images";
            default:
                return null;
        }
    }

}