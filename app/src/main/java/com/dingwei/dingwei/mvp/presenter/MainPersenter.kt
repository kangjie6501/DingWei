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

    override fun addAttention(userId: String,otherUserId: String) {
        var disposable = mainModel.addAttention(userId,otherUserId)
                .subscribe(
                        {
                            result ->
                            mRootView?.apply {
                                showAddAttentionSuccess(result.data)
                            }
                        },{
                    t: Throwable? ->
                        mRootView?.apply {
                            showError(t.toString())
                        }

                }
                )
        addSubscription(disposable)
    }

    override fun getAttentions(userId: String) {
        var disposable = mainModel.getAttentions(userId)
                .subscribe(
                        {
                            result->
                            mRootView?.apply {
                                showAttentions(result.data)
                            }
                        },{
                    t:Throwable?->
                    mRootView?.apply { showError(t.toString()) }
                }
                )
        addSubscription(disposable)
    }

    override fun cancleAttention(userId: String,otherUserId:String) {
        var disposable = mainModel.cancleAttention(userId,otherUserId)
                .subscribe(
                        {
                            result ->
                            mRootView?.apply {
                                showCancleAttentionSuccess(result.data)
                            }
                        },{
                    t: Throwable? ->
                    mRootView?.apply {
                        showError(t.toString())
                    }

                }
                )
        addSubscription(disposable)
    }


}