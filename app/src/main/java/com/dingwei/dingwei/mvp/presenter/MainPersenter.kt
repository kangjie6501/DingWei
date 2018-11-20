package com.dingwei.dingwei.mvp.presenter

import com.dingwei.dingwei.base.BasePresenter
import com.dingwei.dingwei.mvp.contract.MainContract
import com.dingwei.dingwei.mvp.model.MainModel

/**
 * Created by kangjie on 2018/11/9.
 */
class MainPersenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
    private val mainModel by lazy {
        MainModel()
    }
    override fun clearLocationByUser(userId: String) {
        val disposable = mainModel.clearLocationByUserId(userId)
                .subscribe (
                        {
                            string ->
                            mRootView?.apply{
                            setClearLocationView(string)
                        }

                        },{
                    t: Throwable? ->
                        mRootView?.apply {
                            showError(t.toString())
                        }

                })
        addSubscription(disposable)
    }

}