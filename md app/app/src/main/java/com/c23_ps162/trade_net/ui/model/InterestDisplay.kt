package com.c23_ps162.trade_net.ui.model

import com.c23_ps162.trade_net.data.model.Interest
import com.c23_ps162.trade_net.util.pattern.BasicDisplayAble
import com.c23_ps162.trade_net.util.pattern.Descendant

data class InterestDisplay(
    override val parent: Interest,
    override val selected: Boolean,
    override val active: Boolean,
) : Descendant<Interest>, BasicDisplayAble
