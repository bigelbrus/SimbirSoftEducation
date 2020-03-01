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
import com.example.simbirsoftapp.databinding.FragmentNewsDetailBinding;
import com.example.simbirsoftapp.utility.AppUtils;

import java.util.List;

import javax.inject.Inject;


public class NewsDetailFragment extends Fragment {
    private static final String KEY_EVENT = "EVENT";
    private static final int MAX_NEWS_IMAGES = 3;
    private static final int MAX_FRIENDS_IMAGES = 5;
    private FragmentNewsDetailBinding newsDetailBinding;
    MainActivity activity;
    @Inject
    AppUtils appUtils;

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
        newsDetailBinding = FragmentNewsDetailBinding.inflate(inflater,container,false);
        View view = newsDetailBinding.getRoot();
        activity = (MainActivity)getActivity();
        activity.getCategoryComponent().inject(this);
        activity.getBottomPanel().setVisibility(View.GONE);
        setupView(view);
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
            activity.findViewById(R.id.bottom_panel).setVisibility(View.VISIBLE);
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
            newsImages[i].setImageDrawable(appUtils.getDrawableByStringRes(activity,
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
            friendsImages[i].setImageDrawable(appUtils.getDrawableByStringRes(activity,
                    friends.get(i).getRoundedLogo()));
        }
    }

    private void setupView(View view) {
        EventModel event = new EventModel();
        if (getArguments() != null) {
            event = (EventModel) getArguments().getSerializable(KEY_EVENT);
        }

        initNewsPhotos(event, view);

        newsDetailBinding.newsLabel.setText(event.getEventName());
        newsDetailBinding.newsDate.setText(event.getEventDate());
        newsDetailBinding.newsOrganisation.setText(event.getEventCompany());
        newsDetailBinding.newsAddress.setText(event.getEventAddress());
        for (int i = 0; i < event.getOrganisationTelephone().size() - 1; i++) {
            newsDetailBinding.newsTelephones.append(event.getOrganisationTelephone().get(i));
            newsDetailBinding.newsTelephones.append("\n");
        }
        newsDetailBinding.newsTelephones.append(event.getOrganisationTelephone().get(event.getOrganisationTelephone().size() - 1));
        newsDetailBinding.newsDescription.setText(event.getEventDescription());

        newsDetailBinding.newsLinkWriteToUs.setText(appUtils.getUnderlineGreenSpan(activity,R.string.write_to_us));
        newsDetailBinding.newsLinkToOrganisation.setOnClickListener(click -> Toast.makeText(getActivity(), getString(R.string.write_to_us), Toast.LENGTH_SHORT).show());
        newsDetailBinding.newsLinkToOrganisation.setText(appUtils.getUnderlineGreenSpan(activity,R.string.go_to_organisation_page));
        final String url = event.getOrganisationSite();
        newsDetailBinding.newsLinkToOrganisation.setOnClickListener(click -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        activity.setSupportActionBar((Toolbar) newsDetailBinding.newsToolbar.getRoot());
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(null);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TextView toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(event.getEventName());

        List<UserModel> friends = event.getEventPerson();
        initFriendsLine(friends, view);
    }

}
