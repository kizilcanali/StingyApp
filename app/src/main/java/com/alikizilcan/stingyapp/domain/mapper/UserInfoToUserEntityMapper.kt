package com.alikizilcan.stingyapp.domain.mapper

import com.alikizilcan.stingyapp.data.model.UserEntity
import com.alikizilcan.stingyapp.domain.model.UserInformation
import com.alikizilcan.stingyapp.infra.orZero
import javax.inject.Inject

class UserInfoToUserEntityMapper @Inject constructor() {
    fun mapFromUserInfo(userInformation: UserInformation): UserEntity{
        return UserEntity(
            id = userInformation.id.orZero(),
            budget = userInformation.budget.orZero(),
            iban = userInformation.iban.orZero(),
            accountNumber = userInformation.accountNumber.orZero()
        )
    }
}