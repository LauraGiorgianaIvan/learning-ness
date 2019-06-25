package com.lri.learningness.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BottomControlFragment;
import com.lri.learningness.tools.TutorialHelper;
import com.lri.learningness.ui.categories.CategoryFragment;
import com.lri.learningness.ui.tutorial.TutorialFragment;

import static com.lri.learningness.ui.tutorial.TutorialFragment.TUTORIAL_ITEM;

public class HomeFragment extends BottomControlFragment {

    private RecyclerView rvHome;
    private AppCompatTextView tvCategories;
    private HomeAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter(itemListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.home_fragment, container, false);
        bindView(fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        handleListeners();
    }

    private void bindView(View view) {
        rvHome = view.findViewById(R.id.rv_home);
        tvCategories = view.findViewById(R.id.tv_see_category);
    }

    private void handleListeners() {
        tvCategories.setOnClickListener(v -> fragNav().navigate(new CategoryFragment()));
    }

    private void initAdapter() {
        rvHome.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvHome.setAdapter(adapter);
        adapter.updateData(TutorialHelper.get().getAllTutorials());
    }

    private HomeAdapter.HomeItemListener itemListener =
            tutorial -> {
                Bundle args = new Bundle();
                args.putSerializable(TUTORIAL_ITEM, tutorial);
                fragNav().navigate(new TutorialFragment(), args);
            };
}
