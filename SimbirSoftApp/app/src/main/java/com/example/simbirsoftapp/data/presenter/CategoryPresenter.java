package com.example.simbirsoftapp.data.presenter;

import com.example.simbirsoftapp.data.mapper.CategoryModelDataMapper;
import com.example.simbirsoftapp.data.model.CategoryModel;
import com.example.simbirsoftapp.domain.Category;
import com.example.simbirsoftapp.domain.interactor.GetCategory;
import com.example.simbirsoftapp.ui.HelpView;

import java.util.Collection;
import java.util.List;

import io.reactivex.subscribers.ResourceSubscriber;

public class CategoryPresenter implements Presenter {

    HelpView helpView;

    private GetCategory getCategory;
    private CategoryModelDataMapper categoryModelDataMapper;


    CategoryPresenter(GetCategory getCategory, CategoryModelDataMapper categoryModelDataMapper){
        this.getCategory = getCategory;
        this.categoryModelDataMapper = categoryModelDataMapper;
    }

    public void setView(HelpView helpView) {
        this.helpView = helpView;
    }
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getCategory.dispose();
        helpView = null;
    }

    public void initialize(){
        loadCategories();
    }

    private void loadCategories(){
        showViewLoading();
    }

    private void showViewData(){
        helpView.showData();
    }

    private void showViewLoading(){
        helpView.showLoading();
    }

    private void showViewError() {
        helpView.showError();
    }

    private void showUsersCollectionInView(Category category) {
        CategoryModel categoryModel = categoryModelDataMapper.transform(category);
        this.helpView.showCategory(categoryModel);
    }

    private void getCategories(){
        getCategory.execute(new CategoriesFlowable(),null);
    }

    private final class CategoriesFlowable extends ResourceSubscriber<Category> {

        @Override public void onComplete() {
            showViewData();
        }

        @Override
        public void onNext(Category categories) {

        }

        @Override public void onError(Throwable e) {
            showViewError();
        }
    }
}
