package com.lri.learningness.ui.categoryresult;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BaseFragment;
import com.lri.learningness.model.tutorials.TutorialCategory;
import com.lri.learningness.tools.TutorialHelper;
import com.lri.learningness.ui.home.HomeAdapter;
import com.lri.learningness.ui.tutorial.TutorialFragment;

import static com.lri.learningness.ui.tutorial.TutorialFragment.TUTORIAL_ITEM;

public class CategoryResultFragment extends BaseFragment {

    public static final String CATEGORY_NAME = "CATEGORY_NAME";
    private String categoryName;

    private HomeAdapter adapter;
    private RecyclerView rvCategories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter(itemListener);
        readExtra();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.category_result_fragment, container, false);
        bindView(fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void bindView(View view) {
        rvCategories = view.findViewById(R.id.rv_category_result);
    }

    private void initAdapter() {
        rvCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvCategories.setAdapter(adapter);
        filterCategory();
    }

    private void readExtra() {
        Bundle args = getArguments();
        if (args != null && args.containsKey(CATEGORY_NAME)) {
            categoryName = args.getString(CATEGORY_NAME);
        }
    }

    private void filterCategory() {
        if (categoryName != null) {
            for (TutorialCategory category : TutorialHelper.get().getTutorialCategory()) {
                if (category.getCategory().equalsIgnoreCase(categoryName)) {
                    adapter.updateData(category.getTutorials());
                    return;
                }
            }
        }
    }

    private HomeAdapter.HomeItemListener itemListener = tutorial -> {
        Bundle args = new Bundle();
        args.putSerializable(TUTORIAL_ITEM, tutorial);
        fragNav().navigate(new TutorialFragment(), args);
    };
}
