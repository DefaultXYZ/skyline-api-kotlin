package com.defaultxyz.skylineapi.extensions

fun <A, B> Pair<A?, B?>.takeNonEmpty(): Pair<A, B>? = first?.let { a -> second?.let { b -> Pair(a, b) } }
