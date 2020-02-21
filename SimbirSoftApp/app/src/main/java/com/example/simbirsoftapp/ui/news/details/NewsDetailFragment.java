package com.example.simbirsoftapp.ui.news.details;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simbirsoftapp.MainActivity;
import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.EventModel;
import com.example.simbirsoftapp.data.model.UserModel;
import com.example.simbirsoftapp.utility.AppUtils;

import java.util.List;

import javax.inject.Inject;


public class NewsDetailFragment extends Fragment {
    private static final String KEY_EVENT = "EVENT";
    private static final int MAX_NEWS_IMAGES = 3;
    private static final int MAX_FRIENDS_IMAGES = 5;
    @Inject
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static NewsDetailFragment newInstance(EventModel event) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_EVENT, event);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_news_detail,
                container, false);
        ((MainActivity)getActivity()).getCategoryComponent().inject(this);
        getActivity().findViewById(R.id.bottom_panel).setVisibility(View.GONE);
        EventModel event = new EventModel();
        if (getArguments() != null) {
            event = (EventModel) getArguments().getSerializable(KEY_EVENT);
        }
        TextView newsLabel = view.findViewById(R.id.news_label);
        TextView newsDate = view.findViewById(R.id.news_date);
        TextView newsOrganisation = view.findViewById(R.id.news_organisation);
        TextView newsAddress = view.findViewById(R.id.news_address);
        TextView newsTelephones = view.findViewById(R.id.news_telephones);
        TextView newsDescr = view.findViewById(R.id.news_description);


        TextView writeUs = view.findViewById(R.id.news_link_write_to_us);
        TextView goToOrgs = view.findViewById(R.id.news_link_to_organisation);

        initNewsPhotos(event, view);

        newsLabel.setText(event.getEventName());
        newsDate.setText(event.getEventDate());
        newsOrganisation.setText(event.getEventCompany());
        newsAddress.setText(event.getEventAddress());
        for (int i = 0; i < event.getOrganisationTelephone().size() - 1; i++) {
            newsTelephones.append(event.getOrganisationTelephone().get(i));
            newsTelephones.append("\n");
        }
        newsTelephones.append(event.getOrganisationTelephone().get(event.getOrganisationTelephone().size() - 1));
        newsDescr.setText(event.getEventDescription());

        writeUs.setText(AppUtils.getUnderlineGreenSpan(context,R.string.write_to_us));
        writeUs.setOnClickListener(click -> Toast.makeText(getActivity(), getString(R.string.write_to_us), Toast.LENGTH_SHORT).show());
        goToOrgs.setText(AppUtils.getUnderlineGreenSpan(getContext(),R.string.go_to_organisation_page));
        final String url = event.getOrganisationSite();
        goToOrgs.setOnClickListener(click -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(null);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TextView toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(event.getEventName());

        List<UserModel> friends = event.getEventPerson();
        initFriendsLine(friends, view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_news_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().findViewById(R.id.bottom_panel).setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initNewsPhotos(EventModel event, View view) {

        ImageView firstImage = view.findViewById(R.id.news_detail_first_image);
        ImageView secondImage = view.findViewById(R.id.news_detail_second_image);
        ImageView thirdImage = view.findViewById(R.id.news_detail_third_image);
        ImageView[] newsImages = {firstImage, secondImage, thirdImage};

        if (event.getEventPhoto().size() < 3) {
            thirdImage.setVisibility(View.GONE);
            ConstraintSet set = new ConstraintSet();
            ConstraintLayout cl = view.findViewById(R.id.news_images);
            set.clone(cl);
            set.setHorizontalWeight(R.id.news_detail_second_image, 2);
            set.applyTo(cl);
            if (event.getEventPhoto().size() < 2) {
                secondImage.setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < event.getEventPhoto().size(); i++) {
            if (i == MAX_NEWS_IMAGES) break;
            newsImages[i].setImageDrawable(AppUtils.getDrawableByStringRes(context,
                    event.getEventPhoto().get(i)));
        }
    }

    private void initFriendsLine(List<UserModel> friends, View view) {
        int friendsSize = friends.size();
        ImageView firstFriend = view.findViewById(R.id.news_first_friend);
        ImageView secondFriend = view.findViewById(R.id.news_second_friend);
        ImageView thirdFriend = view.findViewById(R.id.news_third_friend);
        ImageView fourthFriend = view.findViewById(R.id.news_fourth_friend);
        ImageView fifthFriend = view.findViewById(R.id.news_fifth_friend);
        TextView friendsCounter = view.findViewById(R.id.news_friends_counter);
        ImageView[] friendsImages = {firstFriend, secondFriend, thirdFriend, fourthFriend, fifthFriend};
        for (int i = 0; i < friendsSize; i++) {
            if (i == MAX_FRIENDS_IMAGES) {
                int f = friendsSize - MAX_FRIENDS_IMAGES;
                friendsCounter.append(String.valueOf(f));
                friendsCounter.setVisibility(View.VISIBLE);
                break;
            }
            friendsImages[i].setImageDrawable(AppUtils.getDrawableByStringRes(context,
                    friends.get(i).getRoundedLogo()));
        }
    }

}
