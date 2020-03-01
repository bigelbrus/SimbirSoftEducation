package com.example.simbirsoftapp.domain.interactor;

import com.example.simbirsoftapp.domain.Category;
import com.example.simbirsoftapp.domain.executor.PostExecutionThread;
import com.example.simbirsoftapp.domain.executor.ThreadExecutor;
import com.example.simbirsoftapp.domain.repository.CategoryRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class GetCategory extends UseCase<Category,Void> {

    private CategoryRepository categoryRepository;

    @Inject
    GetCategory(CategoryRepository categoryRepository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor,postExecutionThread);
        this.categoryRepository = categoryRepository;
    }

    @Override
    Flowable<Category> buildUseCaseObservable(Void aVoid) {
        return categoryRepository.category();
    }
}
