/**
 * @file WeatherListContract.java
 * @brief This is the contract class, it will have interfaces for model, view and presenter.
 * @author Shrikant
 * @date 14/04/2018
 */

package com.mobilhanem.rxjavamvpproject.model;

import java.util.List;

public interface WeatherListContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Main> movieArrayList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int i);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Main> movieArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

    }
}
