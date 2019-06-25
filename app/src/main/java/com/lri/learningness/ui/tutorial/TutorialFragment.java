package com.lri.learningness.ui.tutorial;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BaseFragment;
import com.lri.learningness.model.tutorials.Tutorial;
import com.lri.learningness.model.user.User;
import com.lri.learningness.ui.webview.TutorialWebFragment;

import static com.lri.learningness.ui.webview.TutorialWebFragment.WEB_VIEW_TUTORIAL;

public class TutorialFragment extends BaseFragment {

    public static final String TUTORIAL_ITEM = "TUTORIAL_ITEM";

    private Tutorial tutorial;

    private AppCompatTextView tvStartCourse;
    private AppCompatImageView ivCourse;
    private AppCompatTextView tvTutorialTitle;
    private AppCompatImageView ivFavorite;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readExtra();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.tutorial_fragment, container, false);
        bindView(fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleListeners();
        initData();
    }

    private void bindView(View view) {
        tvStartCourse = view.findViewById(R.id.tv_tutorial_start);
        ivCourse = view.findViewById(R.id.iv_tutorial_image);
        tvTutorialTitle = view.findViewById(R.id.tv_tutorial_title);
        ivFavorite = view.findViewById(R.id.iv_tutorial_favorite);
    }

    private void handleListeners() {
        ivFavorite.setOnClickListener(v -> favoriteClicked());
        tvStartCourse.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putSerializable(WEB_VIEW_TUTORIAL, tutorial);
            fragNav().navigate(new TutorialWebFragment(), args);
        });
    }

    private void readExtra() {
        Bundle args = getArguments();
        if (args != null && args.containsKey(TUTORIAL_ITEM)) {
            tutorial = (Tutorial) args.getSerializable(TUTORIAL_ITEM);
        }
        User user = prefs().getCurrentUser();
        if (user != null) {
            Tutorial dbTutorial = tutorialDb().getUserTutorialByLink(user.getUsername(), tutorial.getLink());
            if (dbTutorial != null) {
                tutorial = dbTutorial;
            }
        }
    }

    private void initData() {
        Context context = getContext();
        if (tutorial != null && context != null) {

            Glide.with(context).load(tutorial.isCertificated() ? R.drawable.ic_diploma : (tutorial.isFavorite()
                    ? R.drawable.ic_star_yellow_24dp : R.drawable.ic_no_favorite_black_24dp)).into(ivFavorite);
            tvTutorialTitle.setText(tutorial.getName());
            Glide.with(context).load(tutorial.getImgRes()).into(ivCourse);

            if (tutorial.isCertificated()) {
                tvStartCourse.setText(R.string.certificate_acquired);
            }
        }
    }

    private void favoriteClicked() {
        if (tutorial.isCertificated()) {
            return;
        }
        User user = userDb().getUser();
        if (user != null) {
            tutorial.setUserName(user.getUsername());
        }
        boolean isFavorite = tutorial.isFavorite();
        tutorial.setFavorite(!isFavorite);

        if (isFavorite) {
            tutorialDb().deleteTutorial(tutorial);
        } else {
            tutorialDb().insertTutorial(tutorial);
        }
        Glide.with(getContext()).load(isFavorite
                ? R.drawable.ic_no_favorite_black_24dp : R.drawable.ic_star_yellow_24dp).into(ivFavorite);
    }
}
