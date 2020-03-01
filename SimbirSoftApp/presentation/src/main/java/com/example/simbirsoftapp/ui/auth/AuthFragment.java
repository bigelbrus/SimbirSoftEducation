package com.example.simbirsoftapp.ui.auth;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.firebase.RxFirebaseClass;
import com.example.simbirsoftapp.databinding.FragmentAuthBinding;
import com.example.simbirsoftapp.ui.profile.ProfileFragment;
import com.example.simbirsoftapp.utility.AppUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


public class AuthFragment extends Fragment {

    private Button authButton;
    private Disposable disposable;
    private AlphaAnimation inAnim;
    private View background;
    private ProgressBar loadingProgress;
    private TextView errorLogin;
    private FragmentAuthBinding authBinding;

    @Inject
    AppUtils appUtils;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        authBinding = FragmentAuthBinding.inflate(inflater,container,false);
        View view = authBinding.getRoot();
        MainActivity activity = (MainActivity) getActivity();
        activity.getCategoryComponent().inject(this);
        appUtils.setActionBar(activity, view, R.string.authorise, false);
        initViews();
        authButton.setOnClickListener(authButtonClickListener);
        return view;
    }

    private void resultHandler(AuthResult result) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_main, ProfileFragment.newInstance())
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void showError(Throwable throwable) {
        AlphaAnimation outAnim = new AlphaAnimation(1f, 0f);
        outAnim.setDuration(200);
        background.setVisibility(View.GONE);
        loadingProgress.setVisibility(View.GONE);
        errorLogin.setVisibility(View.VISIBLE);
        errorLogin.setText(throwable.getMessage());
        authButton.setEnabled(true);
        throwable.printStackTrace();
    }

    private void initViews() {
        authBinding.forgotPass.setText(appUtils.getUnderlineGreenSpan(getActivity(),R.string.forgot_pass));
        authBinding.registration.setText(appUtils.getUnderlineGreenSpan(getActivity(),R.string.registration));
        authButton = authBinding.signInButton;
        background = authBinding.progressbarHolder;
        loadingProgress = authBinding.loadingProgress;
        errorLogin = authBinding.errorLogin;
    }

    private View.OnClickListener authButtonClickListener = view -> {
        errorLogin.setVisibility(View.GONE);
        inAnim = new AlphaAnimation(0f, 1f);
        inAnim.setDuration(400);
        background.setAnimation(inAnim);
        background.setVisibility(View.VISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);
        authButton.setEnabled(false);
        if (authBinding.enterEmail.getText()!=null && authBinding.enterPassword.getText()!=null) {
            disposable = RxFirebaseClass.signInWithEmailAndPass(authBinding.enterEmail.getText().toString(), authBinding.enterPassword.getText().toString())
                    .subscribe(this::resultHandler, this::showError);
        }
        else showError(new Exception("Поля E-mail и Пароль должны быть заполнены"));
    };
}
