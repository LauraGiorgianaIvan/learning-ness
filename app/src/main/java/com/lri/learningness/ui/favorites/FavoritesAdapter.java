package com.lri.learningness.ui.favorites;

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
import com.lri.learningness.model.tutorials.TutorialSection;

public class FavoritesAdapter extends BaseAppAdapter<Tutorial> {
    private static final int TYPE_SECTION = 0;
    private static final int TYPE_ITEM = 1;

    private FavoritesItemListener listener;

    public FavoritesAdapter(FavoritesItemListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItems().get(position).getSection() != null) {
            return TYPE_SECTION;
        }
        return TYPE_ITEM;
    }

    @Override
    protected BaseHolder<Tutorial> viewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new FavoritesHolder(inflater.inflate(R.layout.favorites_item, parent, false));
        }
        return new SectionFavoriteHolder(inflater.inflate(R.layout.favorite_section_item, parent, false));
    }

    public class FavoritesHolder extends BaseHolder<Tutorial> {

        private AppCompatImageView ivFavorite;
        private AppCompatTextView tvTitle;
        private AppCompatTextView tvDuration;

        public FavoritesHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(View view) {
            ivFavorite = view.findViewById(R.id.iv_favorite);
            tvTitle = view.findViewById(R.id.tv_favorite_tutorial_title);
            tvDuration = view.findViewById(R.id.tv_favorite_duration);
        }

        @Override
        protected void bindItem(Tutorial item) {
            itemView.setOnClickListener(v -> listener.onItemClicked(item));
            tvTitle.setText(item.getName());
            tvDuration.setText(item.getDuration());
            Glide.with(itemView.getContext()).load(item.getImgRes()).into(ivFavorite);
        }
    }

    public class SectionFavoriteHolder extends BaseHolder<Tutorial> {
        private AppCompatTextView tvTitle;
        private AppCompatImageView ivSectionType;

        public SectionFavoriteHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(View view) {
            tvTitle = view.findViewById(R.id.tv_certificate_section_title);
            ivSectionType = view.findViewById(R.id.iv_certificate_section);
        }

        @Override
        protected void bindItem(Tutorial item) {
            TutorialSection section = item.getSection();
            tvTitle.setText(section.getTitle());
            Glide.with(itemView.getContext()).load(
                    section.isCertificated() ? R.drawable.ic_document : R.drawable.ic_favs).into(ivSectionType);

        }
    }

    public interface FavoritesItemListener {
        void onItemClicked(Tutorial tutorial);
    }
}
