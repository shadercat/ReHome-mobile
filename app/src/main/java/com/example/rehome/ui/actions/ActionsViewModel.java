package com.example.rehome.ui.actions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ActionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Actions fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}