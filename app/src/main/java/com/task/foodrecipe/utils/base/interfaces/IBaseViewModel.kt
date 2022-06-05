package com.task.foodrecipe.utils.base.interfaces

import com.task.foodrecipe.utils.base.SingleClickEvent
import com.task.foodrecipe.utils.base.sealed.Dispatcher
import kotlinx.coroutines.Job

interface IBaseViewModel {
    val clickEvent: SingleClickEvent
    fun onClick(view: android.view.View)
    fun launch(dispatcher: Dispatcher = Dispatcher.Background, block: suspend () -> Unit): Job
}