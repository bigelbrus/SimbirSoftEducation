package com.example.simbirsoftapp.ui.help;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<CategoryModel> categories;
    private Context context;
    private CategoryClickHolder clickHolder;
    @Inject
    public CategoryAdapter(Context context) {
        this.categories = new ArrayList<>();
        this.context = context;
    }

    public interface CategoryClickHolder {
        void onCategoryClick();
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.help_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel currentCategory = categories.get(position);
        holder.bind(currentCategory);
    }

    public void setItemClickListener(CategoryClickHolder categoryClickHolder) {
        this.clickHolder = categoryClickHolder;
    }

    public void addCategory(CategoryModel category) {
        this.categories.add(category);
        Log.d("tag","add category " + category.getText());

        notifyDataSetChanged();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView text;
        private ImageView image;

        CategoryViewHolder(View v) {
            super(v);
            text = v.findViewById(R.id.item_category_text);
            image = v.findViewById(R.id.item_category_image);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickHolder.onCategoryClick();
        }

        public void bind(CategoryModel category) {
            text.setText(context.getResources().getIdentifier(category.getText(),
                    "string",
                    context.getPackageName()));
            image.setImageDrawable(context.getResources().getDrawable(context.getResources().getIdentifier(category.getLogo(), "drawable", context.getPackageName())));
        }
    }
}
