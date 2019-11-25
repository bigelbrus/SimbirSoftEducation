package com.example.simbirsoftapp.ui.help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simbirsoftapp.R;
import com.example.simbirsoftapp.data.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Category[] categories;
    private Context context;

    public CategoryAdapter(Category[] categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.length;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.help_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category currentCategory = categories[position];
        holder.text.setText(currentCategory.getText());
        holder.image.setImageDrawable(context.getResources().getDrawable(currentCategory.getLogo()));
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
            Toast.makeText(context, text.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
