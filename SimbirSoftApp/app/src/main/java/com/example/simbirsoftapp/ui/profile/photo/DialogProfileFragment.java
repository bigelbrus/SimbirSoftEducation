package com.example.simbirsoftapp.ui.profile.photo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.simbirsoftapp.R;

public class DialogProfileFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.profile_dialog, null);
        view.findViewById(R.id.take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Take photo", Toast.LENGTH_SHORT).show();
                DialogProfileFragment.this.getDialog().cancel();
            }
        });
        view.findViewById(R.id.delete_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Delete photo", Toast.LENGTH_SHORT).show();
                DialogProfileFragment.this.getDialog().cancel();
            }
        });
        view.findViewById(R.id.choose_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Choose photo", Toast.LENGTH_SHORT).show();
                DialogProfileFragment.this.getDialog().cancel();
            }
        });

        builder.setView(view);

        return builder.create();
    }

}
