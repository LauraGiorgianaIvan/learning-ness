package com.lri.learningness.ui.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.lri.learningness.R;
import com.lri.learningness.base.adapter.BaseAppAdapter;
import com.lri.learningness.base.adapter.BaseHolder;
import com.lri.learningness.model.tutorials.TutorialCategory;

public class CategoryAdapter extends BaseAppAdapter<TutorialCategory> {

    private CategoryListener listener;

    public CategoryAdapter(CategoryListener listener) {
        this.listener = listener;
    }

    @Override
    protected BaseHolder<TutorialCategory> viewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new CategoryHolder(inflater.inflate(R.layout.category_item, parent, false));
    }

    public class CategoryHolder extends BaseHolder<TutorialCategory> {

        private AppCompatTextView tvCategoryTitle;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(View view) {
            tvCategoryTitle = view.findViewById(R.id.tv_category_item_title);
        }

        @Override
        protected void bindItem(TutorialCategory item) {
            tvCategoryTitle.setText(item.getCategory());
            itemView.setOnClickListener(v -> listener.onItemClicked(item));
        }
    }

    public interface CategoryListener {
        void onItemClicked(TutorialCategory item);
    }
}
