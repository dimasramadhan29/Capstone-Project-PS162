package com.c23_ps162.trade_net.util.pattern

interface VM<T>
interface Default<T> : VM<T>
interface Success<T> : VM<T> {
    val data: T?
}

interface Failed<T> : VM<T> {
    val message: String?
}

interface Loading<T> : VM<T>