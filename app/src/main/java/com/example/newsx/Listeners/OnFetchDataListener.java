package com.example.newsx.Listeners;

import com.example.newsx.Models.APIStatus;
import com.example.newsx.Models.NewsHeadline;

import java.util.List;

public interface OnFetchDataListener<T> {
    void onFetchData(List<NewsHeadline> data,  String message);
    void onError(String message);

}
