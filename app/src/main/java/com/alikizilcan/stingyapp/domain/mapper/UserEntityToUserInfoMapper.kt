package com.alikizilcan.stingyapp.domain.mapper

import com.alikizilcan.stingyapp.data.model.UserEntity
import com.alikizilcan.stingyapp.domain.model.UserInformation
import com.alikizilcan.stingyapp.infra.orZero
import javax.inject.Inject


class UserEntityToUserInfoMapper @Inject constructor() {
    fun mapFromUserEntity(userEntity: UserEntity) : UserInformation{
        return UserInformation(
            id = userEntity.id,
            budget = userEntity.budget.orZero(),
            iban = userEntity.iban.orZero(),
            accountNumber = userEntity.accountNumber.orZero()
        )
    }
}