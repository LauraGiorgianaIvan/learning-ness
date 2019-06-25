package com.lri.learningness.ui.certificated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BaseFragment;
import com.lri.learningness.model.tutorials.Tutorial;
import com.lri.learningness.model.user.User;

public class CertificatedFragment extends BaseFragment {

    public static final String CERTIFICATED_ITEM = "CERTIFICATED_ITEM";

    private Tutorial tutorial;
    private AppCompatTextView tvTitle;
    private AppCompatImageView ivTutorialCertificate;
    private AppCompatCheckBox cbTerms;
    private MaterialButton btnACertificate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readExtra();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.certificated_fragment, container, false);
        bindView(fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        handleListeners();
    }

    private void bindView(View view) {
        tvTitle = view.findViewById(R.id.tv_certificate_title);
        ivTutorialCertificate = view.findViewById(R.id.iv_certificate_img);
        cbTerms = view.findViewById(R.id.cb_certificate);
        btnACertificate = view.findViewById(R.id.btn_aquire_certificate);

    }

    private void readExtra() {
        Bundle args = getArguments();
        if (args != null && args.containsKey(CERTIFICATED_ITEM)) {
            tutorial = (Tutorial) args.getSerializable(CERTIFICATED_ITEM);
        }
    }

    private void initData() {
        if (tutorial != null) {
            tvTitle.setText(tutorial.getName());
            Glide.with(getContext()).load(tutorial.getImgRes()).into(ivTutorialCertificate);
        }
    }

    private void handleListeners() {
        btnACertificate.setOnClickListener(v -> {
            acquireCertificate();
        });
    }

    private void acquireCertificate() {
        if (!cbTerms.isChecked()) {
            Toast.makeText(getContext(), getString(R.string.terms_and_cond_not_checked), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.certificate_acquired), Toast.LENGTH_LONG).show();
            tutorial.setCertificated(true);
            User user = prefs().getCurrentUser();
            if (user != null) {
                tutorial.setUserName(user.getUsername());
                Tutorial dbTutorial = tutorialDb().getUserTutorialByLink(user.getUsername(), tutorial.getLink());
                if (dbTutorial != null) {
                    tutorial.setFavorite(false);
                    tutorialDb().updateTutorial(tutorial);
                } else {
                    tutorialDb().insertTutorial(tutorial);
                }
                fragNav().navController().popFragments(2);
            }
        }
    }
}
