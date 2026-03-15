package com.example.root.presentation

import androidx.lifecycle.ViewModel
import com.example.common.domain.model.TopNavigationItems
import com.example.root.domain.BottomBarDestination
import com.example.root.domain.defaultBottomBarDestinations
import whitelabel.core.common.generated.resources.Res
import whitelabel.core.common.generated.resources.livetv
import whitelabel.core.common.generated.resources.picto


class RootScreenViewModel(
) : ViewModel() {
    fun getAppTopBar(): List<TopNavigationItems> {
        return arrayListOf(
            TopNavigationItems(0, "chats list", Res.drawable.picto),
            TopNavigationItems(1, "settings", Res.drawable.livetv)

        )
    }

    fun getAppMenu(): List<BottomBarDestination> {
        return defaultBottomBarDestinations
    }


}