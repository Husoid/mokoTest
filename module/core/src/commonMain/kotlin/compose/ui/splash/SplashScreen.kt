package compose.ui.splash

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import compose.navigation.NavigationTree
import compose.ui.splash.viewModel.SplashViewModel
import compose.ui.splash.viewModel.models.SplashAction
import compose.ui.splash.viewModel.models.SplashEvent
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
fun SplashScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { SplashViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()

        SplashView(state.value) {
            viewModel.obtainEvent(it)
        }

        when (action.value) {
            is SplashAction.OpenLoginScreen -> rootController
                .present(
                    NavigationTree.Auth.AuthFlow.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
            is SplashAction.OpenRegisterScreen -> println("RegisterScreen в разработке")
            is SplashAction.OpenDemoScreen -> println("DemoScreen в разработке")
            null -> {}
        }
    }
}