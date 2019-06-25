package com.lri.learningness.ui.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.lri.learningness.R;
import com.lri.learningness.base.fragment.BaseFragment;
import com.lri.learningness.model.tutorials.Tutorial;
import com.lri.learningness.ui.certificated.CertificatedFragment;

import static com.lri.learningness.ui.certificated.CertificatedFragment.CERTIFICATED_ITEM;


public class TutorialWebFragment extends BaseFragment {

    public static String WEB_VIEW_TUTORIAL = "WEB_VIEW_TUTORIAL";

    private Tutorial tutorial;
    private WebView webClient;
    private AppCompatTextView tvFinished;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readExtra();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.web_view_fragment, container, false);
        bindView(fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadWebUrl();
        handleListeners();
    }


    private void readExtra() {
        Bundle args = getArguments();
        if (args != null && args.containsKey(WEB_VIEW_TUTORIAL)) {
            tutorial = (Tutorial) args.getSerializable(WEB_VIEW_TUTORIAL);
        }
    }

    private void bindView(View view) {
        webClient = view.findViewById(R.id.wv_default);
        tvFinished = view.findViewById(R.id.tv_finished_course);

        if (tutorial != null) {
            if (tutorial.isCertificated()) {
                tvFinished.setVisibility(View.GONE);
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebUrl() {
        webClient.getSettings().setJavaScriptEnabled(true);
        webClient.setWebViewClient(new WebViewClient());
        if (tutorial != null) {
            webClient.loadUrl(tutorial.getLink());
        }
    }

    private void handleListeners() {
        tvFinished.setOnClickListener(v -> goCertificate());
    }

    private void goCertificate() {
        Bundle args = new Bundle();
        args.putSerializable(CERTIFICATED_ITEM, tutorial);
        fragNav().navigate(new CertificatedFragment(), args);
    }
}
