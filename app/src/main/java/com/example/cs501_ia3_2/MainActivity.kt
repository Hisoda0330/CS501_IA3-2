package com.example.cs501_ia3_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
                Surface(Modifier.fillMaxSize()) {
                    ProfileBadgeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBadgeScreen() {
    var showBadge by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile + Badge (Box)") }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile + Badge
            Box(
                modifier = Modifier
                    .size(160.dp),
                contentAlignment = Alignment.Center
            ) {
                // Avatar placeholder (circle with initials)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JH",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                }

                // Notification badge, aligned to bottom-end of the Box
                if (showBadge) {
                    Badge(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                    ) {
                        // badge content
                        Text("🐕+")
                    }
                }
            }

            // Toggle
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { showBadge = !showBadge }) {
                    Text(if (showBadge) "Hide Badge" else "Show Badge")
                }
                AssistChip(
                    onClick = { showBadge = !showBadge },
                    label = { Text(if (showBadge) "Badge: ON" else "Badge: OFF") }
                )
            }
        }
    }
}

// Simple Material3 Badge—kept to customize easily
@Composable
fun Badge(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.error
    ) {
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = 28.dp, minHeight = 28.dp)
                .padding(horizontal = 6.dp, vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            ProvideTextStyle(
                MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onError,
                    fontWeight = FontWeight.Bold
                )
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileBadgeScreen() {
    MaterialTheme { ProfileBadgeScreen() }
}