package com.gambi.testprayerbeads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ImageObject> mListImage, listBase;
    private ImageAdapter imageAdapter;
    private int currentPosition = 0, previousPosition = 0, delta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 view_pager = findViewById(R.id.view_pager);
//        RecyclerView recycler = findViewById(R.id.recycler);
        mListImage = new ArrayList<>();
        listBase = new ArrayList<>();

        listBase.add(new ImageObject(R.drawable.bead_small));
        listBase.add(new ImageObject(R.drawable.bead_small));
        listBase.add(new ImageObject(R.drawable.bead_small));
        listBase.add(new ImageObject(R.drawable.bead_small));
        listBase.add(new ImageObject(R.drawable.bead_small));
        listBase.add(new ImageObject(R.drawable.bead_small));
        listBase.add(new ImageObject(R.drawable.bead_small));

        mListImage.addAll(listBase);

        imageAdapter = new ImageAdapter(this);
        imageAdapter.setData(mListImage);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recycler.setLayoutManager(linearLayoutManager);
//        recycler.setAdapter(imageAdapter);


//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recycler);
//        recycler.scrollToPosition(2);
//        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
//        recycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

        view_pager.setAdapter(imageAdapter);
        view_pager.setCurrentItem(3);

        view_pager.setOffscreenPageLimit(6);
        view_pager.setClipChildren(false);
        view_pager.setClipToPadding(false);
        view_pager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(5));
        transformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleX(0.9f + r * 0.15f);
            page.setScaleY(0.9f + r * 0.15f);
        });
        view_pager.setPageTransformer(transformer);

        view_pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("Gambi", "onPageSelected: " + position);
                if (position > mListImage.size() - 4) {
                    mListImage.addAll(listBase);
                    imageAdapter.setData(mListImage);
                    imageAdapter.notifyItemRangeChanged(0, mListImage.size());
//                } else if (position == 0) {
//                    mListImage.add(0, new ImageObject(R.drawable.bead_small));
//                    mListImage.add(0, new ImageObject(R.drawable.bead_small));
//                    mListImage.add(0, new ImageObject(R.drawable.bead_small));
                }
                currentPosition = position;
                delta = currentPosition - previousPosition;
                Log.d("Gambi", "onPageSelected: delta = " + delta);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                Log.d("Gambi", "onPageScrollStateChanged: ");
                previousPosition = currentPosition;
            }
        });
    }
}