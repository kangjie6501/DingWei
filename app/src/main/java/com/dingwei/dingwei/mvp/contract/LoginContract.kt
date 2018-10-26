package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView
import com.dingwei.dingwei.base.IPresenter

/**
 * Created by kangjie on 2018/10/26.
 */
interface LoginContract {
    interface View: IBaseView {
        /**
         * 设置登录提示
         */

    }

    interface Presenter: IPresenter<View> {

    }
}