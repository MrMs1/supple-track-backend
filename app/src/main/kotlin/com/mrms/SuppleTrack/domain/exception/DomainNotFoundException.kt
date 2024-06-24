package com.mrms.suppletrack.domain.exception

/*
 * 存在確認時に対象が見つからなかった場合の例外
 */
class DomainNotFoundException(message: String) : DomainException(message)
