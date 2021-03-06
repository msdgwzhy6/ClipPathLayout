package com.yxf.clippathlayout.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.yxf.clippathlayout.PathInfo;
import com.yxf.clippathlayout.transition.TransitionAdapter;
import com.yxf.clippathlayout.transition.TransitionFrameLayout;
import com.yxf.clippathlayout.transition.generator.CircleTransitionPathGenerator;
import com.yxf.clippathlayout.transition.generator.OvalTransitionPathGenerator;
import com.yxf.clippathlayout.transition.generator.RandomTransitionPathGenerator;
import com.yxf.clippathlayout.transition.generator.RhombusTransitionPathGenerator;

public class ViewTransitionFragment extends Fragment {

    TransitionFrameLayout mLayout;
    View mBlueView, mGreenView;

    private int mLastX, mLastY;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayout = (TransitionFrameLayout) inflater.inflate(R.layout.fragment_view_transition, null);
        RandomTransitionPathGenerator generator =
                new RandomTransitionPathGenerator(new CircleTransitionPathGenerator());
        generator.add(new OvalTransitionPathGenerator());
        generator.add(new RhombusTransitionPathGenerator());
        mLayout.setAdapter(new TransitionAdapter(generator));
        mBlueView = mLayout.findViewById(R.id.blue_view);
        mGreenView = mLayout.findViewById(R.id.green_view);
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                return false;
            }
        };
        mBlueView.setOnTouchListener(listener);
        mGreenView.setOnTouchListener(listener);
        mBlueView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionAdapter adapter = mLayout.switchView(mGreenView);
                adapter.setPathCenter(mLastX, mLastY);
                adapter.animate();
            }
        });
        mGreenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionAdapter adapter = mLayout.switchView(mBlueView);
                adapter.setPathCenter(mLastX, mLastY);
                adapter.animate();
            }
        });
        return mLayout;
    }
}
