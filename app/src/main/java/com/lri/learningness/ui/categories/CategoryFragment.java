package com.lri.learningness.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BaseFragment;
import com.lri.learningness.tools.TutorialHelper;
import com.lri.learningness.ui.categoryresult.CategoryResultFragment;

import static com.lri.learningness.ui.categoryresult.CategoryResultFragment.CATEGORY_NAME;

public class CategoryFragment extends BaseFragment {

    private RecyclerView rvCategories;
    private CategoryAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CategoryAdapter(categoryListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.category_fragment, container, false);
        bindView(fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void bindView(View view) {
        rvCategories = view.findViewById(R.id.rv_categories);
    }

    private void initAdapter() {
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategories.setAdapter(adapter);

        adapter.updateData(TutorialHelper.get().getTutorialCategory());
    }

    private CategoryAdapter.CategoryListener categoryListener = item -> {
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_NAME, item.getCategory());
        fragNav().navigate(new CategoryResultFragment(), bundle);
    };
}
