package com.c23_ps162.trade_net.ui.screen.interest

import androidx.lifecycle.ViewModel
import com.c23_ps162.trade_net.data.payload.InterestListPLD
import com.c23_ps162.trade_net.data.repository.ContentRepository
import com.c23_ps162.trade_net.data.repository.ProfileRepository
import com.c23_ps162.trade_net.ui.model.InterestDisplay
import com.c23_ps162.trade_net.util.ViewModelJob
import com.c23_ps162.trade_net.util.collect
import com.c23_ps162.trade_net.util.provider
import com.c23_ps162.trade_net.util.state
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class InterestViewModel(
    private val contentRepository: ContentRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val interestProvider by provider(
        operator = contentRepository::getInterestList
    )

    val selectedInterests by state(listOf<InterestDisplay>())

    val interestListDisplay: Flow<List<InterestDisplay>> by lazy {
        val flow = MutableStateFlow(listOf<InterestDisplay>())

        suspend fun updater() {
            val currentList = interestProvider.success.first().second ?: listOf()
            val selectedItems = selectedInterests.flow.first()

            val list = currentList.map { item ->
                InterestDisplay(
                    parent = item,
                    selected = selectedItems
                        .map { selected -> selected.parent.id }
                        .contains(item.id),
                    active = true
                )
            }

            flow.emit(list)
        }

        /** relations **/
        collect(interestProvider.success) {
            if (it.first)
                updater()
        }

        collect(selectedInterests.flow) {
            updater()
        }

        flow
    }

    val submitSelectionProvider by provider(
        operator = profileRepository::updateUserInterest
    )

    val userProfileProvider by provider(
        operator = profileRepository::getUserProfile
    )

    init {
        ViewModelJob {
            interestProvider.update(
                InterestListPLD()
            )
        }
    }
}