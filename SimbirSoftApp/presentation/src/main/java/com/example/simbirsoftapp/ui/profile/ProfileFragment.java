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
import com.example.simbirsoftapp.ui.auth.AuthFragment;
import com.example.simbirsoftapp.ui.profile.photo.DialogProfileFragment;
import com.example.simbirsoftapp.utility.AppUtils;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;


public class ProfileFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView name = view.findViewById(R.id.profile_name);
        TextView sphere = view.findViewById(R.id.sphere_activity);
        SwitchCompat push = view.findViewById(R.id.push_switch);
        ImageView logo = view.findViewById(R.id.profile_image_button);
        TextView date = view.findViewById(R.id.birthday_date);
        RecyclerView recyclerView = view.findViewById(R.id.friends_recycler_view);
        Button exitProfile = view.findViewById(R.id.exit_profile_button);

        exitProfile.setOnClickListener(v->{
            firebaseAuth.signOut();
            getFragmentManager().beginTransaction().replace(R.id.fragment_main,AuthFragment.newInstance())
                    .commit();
        });

        UserModel user = DataForProfile.getUser();

        recyclerView.setAdapter(new FriendsAdapter(user.getFriends()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        appUtils.setActionBar(activity, view, R.string.profile_label, false);

        name.setText(user.getFullName());
        sphere.setText(user.getActivity());
        push.setChecked(user.isWantPush());
        date.setText(user.getStringDate());
        if (getContext() != null) {
            logo.setImageDrawable(getContext().getResources().getDrawable(user.getLogo()));
        }
        logo.setOnClickListener(l ->
            Toast.makeText(getContext(), "Image", Toast.LENGTH_SHORT).show());

        logo.setOnClickListener(click -> {

            FragmentManager fm = getFragmentManager();
            if (fm != null) {
                DialogProfileFragment dialog = new DialogProfileFragment();
                dialog.show(getFragmentManager(), "Dialog");
            }
        });


        push.setOnCheckedChangeListener((buttonView, isChecked)-> {
                user.setWantPush(isChecked);
            });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
