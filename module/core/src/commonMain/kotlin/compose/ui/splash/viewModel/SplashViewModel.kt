package compose.ui.splash.viewModel

import com.adeo.kviewmodel.BaseSharedViewModel
import compose.ui.splash.viewModel.models.SplashAction
import compose.ui.splash.viewModel.models.SplashEvent
import compose.ui.splash.viewModel.models.SplashViewState

class SplashViewModel: BaseSharedViewModel<SplashViewState, SplashAction, SplashEvent>(
    initialState = SplashViewState()
) {
    override fun obtainEvent(viewEvent: SplashEvent) {
        when (viewEvent) {
            is SplashEvent.LoginClick -> openLoginScreen()
            is SplashEvent.RegistrationClick -> openRegistrationScreen()
            is SplashEvent.DemoClick -> openDemoScreen()
        }
    }

    private fun openLoginScreen() {
        viewAction = SplashAction.OpenLoginScreen
    }

    private fun openRegistrationScreen() {
        viewAction = SplashAction.OpenRegisterScreen
    }

    private fun openDemoScreen() {
        viewAction = SplashAction.OpenDemoScreen
    }
}