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

        for (int i = 0; i < 30; i++) {
            listBase.add(new ImageObject(i, R.drawable.bead_small));
        }

//        listBase.add(new ImageObject(100, R.drawable.bead_small));
//        listBase.add(new ImageObject(101, R.drawable.bead_small));
//        listBase.add(new ImageObject(102, R.drawable.bead_small));
//        listBase.add(new ImageObject(103, R.drawable.bead_small));
//        listBase.add(new ImageObject(104, R.drawable.bead_small));
//        listBase.add(new ImageObject(105, R.drawable.bead_small));
//        listBase.add(new ImageObject(106, R.drawable.bead_small));
//        listBase.add(new ImageObject(107, R.drawable.bead_small));
//        listBase.add(new ImageObject(108, R.drawable.bead_small));
//        listBase.add(new ImageObject(109, R.drawable.bead_small));
//        listBase.add(new ImageObject(110, R.drawable.bead_small));
//        listBase.add(new ImageObject(111, R.drawable.bead_small));
//        listBase.add(new ImageObject(112, R.drawable.bead_small));
//        listBase.add(new ImageObject(113, R.drawable.bead_small));
//        listBase.add(new ImageObject(114, R.drawable.bead_small));

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
        view_pager.setCurrentItem(15);

        view_pager.setOffscreenPageLimit(5);
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
//                Log.d("Gambi", "onPageSelected: " + position);
//                if (position > mListImage.size() - 4) {
//                    mListImage.addAll(listBase);
//                    imageAdapter.setData(mListImage);
//                    imageAdapter.notifyItemRangeChanged(0, mListImage.size());
////                } else if (position == 0) {
////                    mListImage.add(0, new ImageObject(R.drawable.bead_small));
////                    mListImage.add(0, new ImageObject(R.drawable.bead_small));
////                    mListImage.add(0, new ImageObject(R.drawable.bead_small));
//                }
//                currentPosition = position;
//                delta = currentPosition - previousPosition;
//                Log.d("Gambi", "onPageSelected: delta = " + delta);

                if (imageAdapter.getFirstValue() >= imageAdapter.getValue(position) - 2)
                    imageAdapter.addPreviousItem();

                if (imageAdapter.getLastValue() <= imageAdapter.getValue(position) + 2)
                    imageAdapter.addLastItem();
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