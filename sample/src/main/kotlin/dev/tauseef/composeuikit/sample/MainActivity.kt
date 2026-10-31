package dev.tauseef.composeuikit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tauseef.composeuikit.components.buttons.UIKitButton
import dev.tauseef.composeuikit.components.buttons.UIKitButtonVariant
import dev.tauseef.composeuikit.components.inputs.UIKitPasswordField
import dev.tauseef.composeuikit.components.inputs.UIKitSearchField
import dev.tauseef.composeuikit.components.inputs.UIKitTextField
import dev.tauseef.composeuikit.theme.UIKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIKitTheme {
                var name by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var query by rememberSaveable { mutableStateOf("") }
                var message by rememberSaveable { mutableStateOf("Try the components below.") }

                Scaffold { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "ComposeUIKit",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "Reusable Compose components",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        UIKitTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = "Name",
                            placeholder = "Enter your name",
                            modifier = Modifier.fillMaxWidth()
                        )
                        UIKitPasswordField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                        UIKitSearchField(
                            query = query,
                            onQueryChange = { query = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                        UIKitButton(
                            text = "Primary action",
                            onClick = { message = "Hello, $name!" },
                            modifier = Modifier.fillMaxWidth()
                        )
                        UIKitButton(
                            text = "Outlined action",
                            onClick = { message = "Outlined button clicked." },
                            variant = UIKitButtonVariant.Outlined,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(text = message, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
