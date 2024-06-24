package com.mrms.suppletrack.domain.exception

/*
 * ドメイン例外の元となるクラス
 * 継承して具体的な例外を定義する
 */
open class DomainException(message: String) : Exception(message)
