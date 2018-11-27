package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView

/**
 * Created by kangjie on 2018/11/27.
 */
interface AddAttentionContract {
    interface View:IBaseView{
        fun addSuss()
        fun showError()
    }


}