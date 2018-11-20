package com.dingwei.dingwei.mvp.presenter

import com.dingwei.dingwei.base.BasePresenter
import com.dingwei.dingwei.mvp.contract.LocationContract
import com.dingwei.dingwei.mvp.model.LocationModel

/**
 * Created by kangjie on 2018/11/20.
 */
class LocationPresenter:BasePresenter<LocationContract.View>(),LocationContract.Presenter {
    val locationModel by lazy {
        LocationModel()
    }
    override fun getLocationByUserId(userId: String) {
        var disposable = locationModel.getLocationByUserId(userId)
                .subscribe({
                    locationBean ->
                    mRootView?.apply {
                        showLocation(locationBean)
                    }
                },{
                    throwable ->
                    mRootView?.apply {
                        showError(throwable)
                    }
                })

        addSubscription(disposable)
    }
}