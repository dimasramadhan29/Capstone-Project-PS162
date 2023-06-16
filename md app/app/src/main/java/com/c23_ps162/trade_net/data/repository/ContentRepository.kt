package com.c23_ps162.trade_net.data.repository

import arrow.core.Either
import com.c23_ps162.trade_net.data.model.Interest
import com.c23_ps162.trade_net.data.payload.InterestListPLD
import com.c23_ps162.trade_net.util.DummyBackend
import com.c23_ps162.trade_net.util.ErrorMessage

class ContentRepository(
    private val dummyBackend: DummyBackend
) {
    suspend fun getInterestList(
        pld: InterestListPLD
    ): Either<ErrorMessage, List<Interest>> {
        return dummyBackend.getInterestList()
    }
}