package com.example.simbirsoftapp.ui.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.DataForProfile;
import com.example.simbirsoftapp.data.model.UserModel;
import com.example.simbirsoftapp.databinding.FragmentProfileBinding;
import com.example.simbirsoftapp.ui.auth.AuthFragment;
import com.example.simbirsoftapp.ui.profile.photo.DialogProfileFragment;
import com.example.simbirsoftapp.utility.AppUtils;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding profileBinding;

    @Inject
    FriendsAdapter adapter;
    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    AppCompatActivity activity;
    @Inject
    AppUtils appUtils;


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getCategoryComponent().inject(this);
        profileBinding = FragmentProfileBinding.inflate(inflater,container,false);
        View view = profileBinding.getRoot();

        profileBinding.exitProfileButton.setOnClickListener(v->{
            firebaseAuth.signOut();
            getFragmentManager().beginTransaction().replace(R.id.fragment_main,AuthFragment.newInstance())
                    .commit();
        });
        setupView(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setupView(View view) {
        UserModel user = DataForProfile.getUser();
        appUtils.setActionBar(activity, view, R.string.profile_label, false);
        adapter.setFriends(user.getFriends());
        profileBinding.friendsRecyclerView.setAdapter(adapter);
        profileBinding.friendsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        profileBinding.profileName.setText(user.getFullName());
        profileBinding.sphereActivity.setText(user.getActivity());
        profileBinding.pushSwitch.setChecked(user.isWantPush());
        profileBinding.birthdayDate.setText(user.getStringDate());
        if (getContext() != null) {
            profileBinding.profileImageButton.setImageDrawable(getContext().getResources().getDrawable(user.getLogo()));
        }
        profileBinding.profileImageButton.setOnClickListener(l ->
                Toast.makeText(getContext(), "Image", Toast.LENGTH_SHORT).show());

        profileBinding.profileImageButton.setOnClickListener(click -> {

            FragmentManager fm = getFragmentManager();
            if (fm != null) {
                DialogProfileFragment dialog = new DialogProfileFragment();
                dialog.show(getFragmentManager(), "Dialog");
            }
        });
        
        profileBinding.pushSwitch.setOnCheckedChangeListener((buttonView, isChecked)-> {
            user.setWantPush(isChecked);
        });
    }
}
