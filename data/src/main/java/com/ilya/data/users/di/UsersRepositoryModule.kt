package com.ilya.data.users.di

import com.ilya.data.users.UsersRepository
import com.ilya.data.users.repositoryInterface.UserAdder
import com.ilya.data.users.repositoryInterface.UserFinderById
import com.ilya.data.users.repositoryInterface.UserFinderByLoginParams
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UsersRepositoryModule {
    @Binds fun bindUserFinderByLoginParams(usersRepository: UsersRepository): UserFinderByLoginParams
    @Binds fun bindUserFinderById(usersRepository: UsersRepository): UserFinderById
    @Binds fun bindUserAdder(usersRepository: UsersRepository): UserAdder
}