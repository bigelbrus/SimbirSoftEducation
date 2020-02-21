package com.example.simbirsoftapp.ui.auth;


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

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.firebase.RxFirebaseClass;
import com.example.simbirsoftapp.ui.profile.ProfileFragment;
import com.example.simbirsoftapp.utility.AppUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

import io.reactivex.disposables.Disposable;


public class AuthFragment extends Fragment {

    private Button authButton;
    private Disposable disposable;
    private AlphaAnimation inAnim;
    private View background;
    private ProgressBar loadingProgress;
    private TextView errorLogin;


    public static AuthFragment newInstance() {
        return new AuthFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            AppUtils.setActionBar(activity, view, R.string.authorise, false);
        }
        TextView forgotPass = view.findViewById(R.id.forgot_pass);
        TextView registration = view.findViewById(R.id.registration);
        forgotPass.setText(AppUtils.getUnderlineGreenSpan(getContext(),R.string.forgot_pass));
        registration.setText(AppUtils.getUnderlineGreenSpan(getContext(),R.string.registration));
        TextInputEditText email = view.findViewById(R.id.enter_email);
        TextInputEditText password = view.findViewById(R.id.enter_password);
        authButton = view.findViewById(R.id.sign_in_button);
        background = view.findViewById(R.id.progressbar_holder);
        loadingProgress = view.findViewById(R.id.loading_progress);
        errorLogin = view.findViewById(R.id.error_login);
        authButton.setOnClickListener(v -> {
            errorLogin.setVisibility(View.GONE);
            inAnim = new AlphaAnimation(0f, 1f);
            inAnim.setDuration(400);
            background.setAnimation(inAnim);
            background.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.VISIBLE);
            authButton.setEnabled(false);
            if (email.getText()!=null && password.getText()!=null) {
                disposable = RxFirebaseClass.signInWithEmailAndPass(email.getText().toString(), password.getText().toString())
                        .subscribe(this::resultHandler, this::showError);
            }
            else showError(new Exception("Поля E-mail и Пароль должны быть заполнены"));
        });

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
}
