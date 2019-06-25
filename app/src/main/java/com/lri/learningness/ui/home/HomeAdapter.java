package com.lri.learningness.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.lri.learningness.R;
import com.lri.learningness.base.adapter.BaseAppAdapter;
import com.lri.learningness.base.adapter.BaseHolder;
import com.lri.learningness.model.tutorials.Tutorial;

public class HomeAdapter extends BaseAppAdapter<Tutorial> {

    private HomeItemListener listener;

    public HomeAdapter(HomeItemListener listener) {
        this.listener = listener;
    }

    @Override
    protected BaseHolder<Tutorial> viewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new HomeHolder(inflater.inflate(R.layout.tutorial_item, parent, false));
    }

    public class HomeHolder extends BaseHolder<Tutorial> {
        private AppCompatTextView tvTitle;
        private AppCompatTextView tvCertificated;
        private AppCompatTextView tvDuration;
        private AppCompatImageView ivCourse;


        public HomeHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(View view) {
            tvTitle = view.findViewById(R.id.tv_tutorial_name);
            tvCertificated = view.findViewById(R.id.tv_certificated);
            tvDuration = view.findViewById(R.id.tv_duration);
            ivCourse = view.findViewById(R.id.iv_course_image);
        }

        @Override
        protected void bindItem(Tutorial item) {
            tvTitle.setText(item.getName());
            tvDuration.setText(item.getDuration());
            Glide.with(itemView).load(item.getImgRes()).into(ivCourse);
            itemView.setOnClickListener(v -> listener.onItemClicked(item));
        }
    }

    public interface HomeItemListener {
        void onItemClicked(Tutorial tutorial);
    }
}
