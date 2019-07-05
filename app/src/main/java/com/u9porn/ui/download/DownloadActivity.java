package com.u9porn.ui.download;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.orhanobut.logger.Logger;
import com.u9porn.R;
import com.u9porn.adapter.DownloadFragmentAdapter;
import com.u9porn.ui.BaseAppCompatActivity;

import java.util.List;

import javax.inject.Inject;


/**
 * @author flymegoc
 */
public class DownloadActivity extends BaseAppCompatActivity {

    private static final String TAG = DownloadActivity.class.getSimpleName();

    ViewPager downloadViewpager;
    TabLayout downloadTab;
    Toolbar toolbar;

    @Inject
    protected DownloadingFragment downloadingFragment;

    @Inject
    protected FinishedFragment finishedFragment;

    @Inject
    List<Fragment> fragmentList;

    @Inject
    DownloadFragmentAdapter downloadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        downloadViewpager = findViewById(R.id.download_viewpager);
        downloadTab = findViewById(R.id.download_tab);
        toolbar = findViewById(R.id.toolbar);
        initToolBar(toolbar);


        fragmentList.add(downloadingFragment);
        fragmentList.add(finishedFragment);

        downloadAdapter.setData(fragmentList);

        downloadViewpager.setAdapter(downloadAdapter);
        downloadTab.setupWithViewPager(downloadViewpager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.t(TAG).d("onDestroy()");
    }
}
