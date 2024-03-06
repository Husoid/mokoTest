package di

import api.AuthRepository
import data.AuthRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val loginModule = DI.Module("loginModule") {
    bind<AuthRepository>() with singleton { AuthRepositoryImpl(instance()) }
}