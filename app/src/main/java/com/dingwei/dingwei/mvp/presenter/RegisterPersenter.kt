package com.dingwei.dingwei.mvp.presenter

import com.dingwei.dingwei.base.BasePresenter
import com.dingwei.dingwei.mvp.contract.RegisterContract
import com.dingwei.dingwei.mvp.model.RegisterModel

/**
 * Created by kangjie on 2018/11/2.
 */
class RegisterPersenter: BasePresenter<RegisterContract.View>(),RegisterContract.Presenter {
    private val registerModel by lazy {
        RegisterModel()
    }

    override fun register(phone: String,name:String ,password: String) {
        val disposable= registerModel.register(phone,name,password)
                .subscribe({
                    registerBean ->
                    mRootView?.apply {
                        //      nextPageUrl = issue.nextPageUrl
                        setRegisterView(registerBean)
                    }
                },{
                    throwable ->
                    mRootView?.apply {
                        showError(throwable.toString())
                    }
                })

        addSubscription(disposable)
    }
}