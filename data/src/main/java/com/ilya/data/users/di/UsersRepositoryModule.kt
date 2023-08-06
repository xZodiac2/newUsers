package com.ilya.data.users.di

import com.ilya.data.users.UsersRepository
import com.ilya.data.users.repositoryInterface.UserAdder
import com.ilya.data.users.repositoryInterface.UserFinderByLogin
import com.ilya.data.users.repositoryInterface.UserFinderById
import com.ilya.data.users.repositoryInterface.UserFinderByAuthenticateParams
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UsersRepositoryModule {
    @Binds fun bindUserFinderByLoginParams(usersRepository: UsersRepository): UserFinderByAuthenticateParams
    @Binds fun bindUserFinderById(usersRepository: UsersRepository): UserFinderById
    @Binds fun bindUserAdder(usersRepository: UsersRepository): UserAdder
    @Binds fun bindUserFinderByLogin(usersRepository: UsersRepository): UserFinderByLogin
}