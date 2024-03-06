package compose.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.AuthRepository
import com.tisonline.core.MR
import compose.themes.ThemeColors
import dev.icerock.moko.resources.compose.stringResource
import di.Inject
import di.ktor.ResponseError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Composable
fun LoginView() {
    //TODO Заменить тексты на строковые ресурсы
    val authRepository = Inject.instance<AuthRepository>()
    val responseError = Inject.instance<ResponseError>()
    var name = remember { mutableStateOf("") }


    Box {
//        Image(
//            painterResource("ic_logo.xml"),
//            modifier = Modifier
//                .fillMaxWidth()
//                .size(90.dp)
//                .padding(top = 40.dp),
//            contentDescription = null,
//            contentScale = ContentScale.Fit
//        )
    }
    Column(
        modifier = Modifier.fillMaxSize().background(ThemeColors.color.primaryBackground),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "Введите номер телефона, логин",
                    color = ThemeColors.color.primaryTextAndElement,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Row {
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = "Телефон",
                        color = ThemeColors.color.primaryTextAndElement,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Логин",
                        color = ThemeColors.color.primaryTextAndElement,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    value = "+ 7 ХХХ ХХХ ХХ ХХ",
                    onValueChange = {
                        //    eventHandler.invoke(LoginEvent.EmailChanged(it))
                    })
                Text(
                    modifier = Modifier.clickable {
                        CoroutineScope(context = Dispatchers.IO).launch {
                            try {
                                name.value = authRepository.login("", "").userName
                            } catch (t: Throwable) {
                                responseError.handleError(t)
                            }
                        }
                    },
                    text = stringResource(MR.strings.company_name),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Text(
                    text = name.value
                )
            }
        }
    }
}

