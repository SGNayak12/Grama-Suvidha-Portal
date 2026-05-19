package com.gramasuvidha.portal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GramaSuvidhaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GramaSuvidhaApp()
                }
            }
        }
    }
}

private data class Project(
    val title: String,
    val village: String,
    val status: String,
    val budget: String,
    val progress: Float,
    val satisfaction: String,
    val nextUpdate: String
)

private val sampleProjects = listOf(
    Project(
        title = "Drinking Water Pipeline",
        village = "Basavanahalli",
        status = "Ongoing",
        budget = "₹18.5L",
        progress = 0.68f,
        satisfaction = "4.4/5",
        nextUpdate = "Valve testing scheduled this week"
    ),
    Project(
        title = "Primary School Repairs",
        village = "Kerekatte",
        status = "Completed",
        budget = "₹7.2L",
        progress = 1f,
        satisfaction = "4.8/5",
        nextUpdate = "Final inspection uploaded"
    ),
    Project(
        title = "Village Road Drainage",
        village = "Honnur",
        status = "Delayed",
        budget = "₹24L",
        progress = 0.42f,
        satisfaction = "3.6/5",
        nextUpdate = "Material delivery awaiting approval"
    )
)

@Composable
private fun GramaSuvidhaApp() {
    var adminMode by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8F3))
            .padding(horizontal = 18.dp, vertical = 22.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Header(adminMode = adminMode, onToggleAdmin = { adminMode = !adminMode })
        }
        item {
            MetricsRow()
        }
        items(sampleProjects) { project ->
            ProjectCard(project = project, adminMode = adminMode)
        }
        item {
            ComplaintSummary()
        }
    }
}

@Composable
private fun Header(adminMode: Boolean, onToggleAdmin: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Grama-Suvidha Portal",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF163B2E)
        )
        Text(
            text = "Track village development works, citizen reviews, and official progress in one transparent dashboard.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF46524D)
        )
        Button(onClick = onToggleAdmin, shape = RoundedCornerShape(8.dp)) {
            Text(if (adminMode) "Switch to Citizen View" else "Login as Admin")
        }
    }
}

@Composable
private fun MetricsRow() {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
        MetricTile("12", "Projects", Modifier.weight(1f))
        MetricTile("87%", "Funds Used", Modifier.weight(1f))
        MetricTile("9", "Open Issues", Modifier.weight(1f))
    }
}

@Composable
private fun MetricTile(value: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.labelMedium, color = Color(0xFF5D6964))
        }
    }
}

@Composable
private fun ProjectCard(project: Project, adminMode: Boolean) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(project.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(project.village, style = MaterialTheme.typography.bodySmall, color = Color(0xFF64706B))
                }
                StatusPill(project.status)
            }
            LinearProgressIndicator(
                progress = { project.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = if (project.status == "Delayed") Color(0xFFC4512D) else Color(0xFF287A57),
                trackColor = Color(0xFFE0E5DF)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Budget ${project.budget}", style = MaterialTheme.typography.bodySmall)
                Text("${(project.progress * 100).toInt()}% complete", style = MaterialTheme.typography.bodySmall)
                Text(project.satisfaction, style = MaterialTheme.typography.bodySmall)
            }
            Text(project.nextUpdate, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF33443D))
            if (adminMode) {
                Button(onClick = {}, shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                    Text("Update Progress")
                }
            }
        }
    }
}

@Composable
private fun StatusPill(status: String) {
    val color = when (status) {
        "Completed" -> Color(0xFF287A57)
        "Delayed" -> Color(0xFFC4512D)
        else -> Color(0xFF3C6E9F)
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
        Text(status, style = MaterialTheme.typography.labelMedium, color = color, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun ComplaintSummary() {
    Card(shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF1ED))) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Citizen Issue Desk", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("Reports are categorized by project, reviewed by officials, and marked resolved only after public updates are posted.")
            Spacer(modifier = Modifier.height(2.dp))
            Button(onClick = {}, shape = RoundedCornerShape(8.dp)) {
                Text("Report an Issue")
            }
        }
    }
}

@Composable
private fun GramaSuvidhaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF1E6F50),
            secondary = Color(0xFF365C7D),
            tertiary = Color(0xFF8A5A22),
            background = Color(0xFFF7F8F3),
            surface = Color.White
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    GramaSuvidhaTheme {
        GramaSuvidhaApp()
    }
}
