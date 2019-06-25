package com.lri.learningness.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BottomControlFragment;
import com.lri.learningness.model.tutorials.Tutorial;
import com.lri.learningness.model.tutorials.TutorialSection;
import com.lri.learningness.ui.categories.CategoryFragment;
import com.lri.learningness.ui.tutorial.TutorialFragment;

import java.util.ArrayList;
import java.util.List;

import static com.lri.learningness.ui.tutorial.TutorialFragment.TUTORIAL_ITEM;

public class FavoritesFragment extends BottomControlFragment {

    private RecyclerView rvFavorites;
    private ConstraintLayout clNoFavorites;
    private FloatingActionButton fabFavorites;
    private FavoritesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FavoritesAdapter(itemListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.favorites_fragment, container, false);
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
        rvFavorites = view.findViewById(R.id.rv_favorites);
        clNoFavorites = view.findViewById(R.id.cl_no_favorites);
        fabFavorites = view.findViewById(R.id.fab_favorites);
    }

    private void initAdapter() {
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavorites.setAdapter(adapter);
        adapter.updateData(parseTutorials());
        noFavoriteVisibility();
    }

    private void handleListeners() {
        fabFavorites.setOnClickListener(v -> fragNav().navigate(new CategoryFragment()));
    }

    private void noFavoriteVisibility() {
        clNoFavorites.setVisibility(adapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    private FavoritesAdapter.FavoritesItemListener itemListener =
            tutorial -> {
                Bundle args = new Bundle();
                args.putSerializable(TUTORIAL_ITEM, tutorial);
                fragNav().navigate(new TutorialFragment(), args);
            };

    private List<Tutorial> parseTutorials() {
        String username = prefs().getCurrentUser().getUsername();
        List<Tutorial> tutorials = new ArrayList<>();
        List<Tutorial> dbTutorials = tutorialDb().getUserTutorials(username);
        for (Tutorial tutorial : dbTutorials) {
            if (tutorial.isCertificated()) {
                tutorials.add(tutorial);
            }
        }

        if (!tutorials.isEmpty()) {
            tutorials.add(0, new Tutorial().setSection(
                    new TutorialSection().setTitle(getString(R.string.tutorial_section_title)).setCertificated(true)));
        }

        List<Tutorial> favorites = new ArrayList<>();
        for (Tutorial tutorial : dbTutorials) {
            if (tutorial.isFavorite()) {
                favorites.add(tutorial);
            }
        }

        if (!favorites.isEmpty()) {
            tutorials.add(new Tutorial().setSection(
                    new TutorialSection().setTitle(getString(R.string.favorite_section_title))));
        }

        tutorials.addAll(favorites);

        return tutorials;
    }
}
