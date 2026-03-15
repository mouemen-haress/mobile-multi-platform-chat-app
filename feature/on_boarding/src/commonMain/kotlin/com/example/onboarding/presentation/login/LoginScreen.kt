package com.example.onboarding.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.common.presentation.Popup
import com.example.common.theme.AppTheme
import com.example.onboarding.domain.model.LoginState

@Composable
fun LoginScreen(state: LoginState, login: (email: String, password: String) -> Unit) {

    var email by remember { mutableStateOf("abuMuslem") }
    var password by remember { mutableStateOf("123456") }
    var showPassword by remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


                    Text(
                        text = "تذكر الخطأ الأول هو الأخير",
                        style = AppTheme.type.titlePrimary
                    )



            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("احساب", color = Color.Gray) },
                leadingIcon = {
                },
                singleLine = true,
                textStyle = AppTheme.type.labelNormal,
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("كلمة السر", color = Color.Gray) },
                leadingIcon = {
                },
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {

                    }
                },
                singleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = AppTheme.type.labelNormal,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = { login(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colorScheme.third,
                    contentColor = Color.Black
                )
            ) {
                Text("تسجيل الدخول", style = AppTheme.type.labelNormal)
            }
        }

        if (state.dialogText != null) {
            Popup(state.dialogText) {
                state.dialogText = null
            }
        }
    }

}
