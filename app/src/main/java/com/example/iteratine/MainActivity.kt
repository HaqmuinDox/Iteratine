package com.example.iteratine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toString
import androidx.compose.ui.Alignment.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iteratine.ui.theme.IteratineTheme
import kotlin.time.DurationUnit
import kotlin.time.toDuration

val routines = listOf(
    Routine(1,"Morning routine1", 239084, listOf ( Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY),),
    Routine(2,"Morning routine2", 339084, listOf(Day.TUESDAY, Day.WEDNESDAY),),
    Routine(3,"Morning routine3", 239085, listOf(Day.MONDAY, Day.WEDNESDAY),),
    Routine(4,"Morning routine4", 339084, listOf(Day.MONDAY, Day.TUESDAY),),
    Routine(5,"Morning routine5", 239084, listOf(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY),),
    Routine(6,"Morning routine6", 339084, listOf(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY),),
    Routine(7,"Morning routine7", 239084, listOf(Day.MONDAY),),
    Routine(8,"Morning routine8", 339084, listOf(Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY),),
)

val tasks = listOf(
    Task(1, "Task1", 4578, "\uD83D\uDE05", "Note1", 1),
    Task(2, "Task2", 4578, "\uD83D\uDE07", "Note2", 2),
    Task(3, "Task4", 4578, "\uD83D\uDE08", "Note1", 3),
    Task(4, "Task3", 4578, "\uD83D\uDE09", "Note2", 4),
    Task(5, "Task5", 4578, "\uD83D\uDE0A", "Note1", 5),
    Task(6, "Task6", 4578, "\uD83D\uDE0B", "Note2", 6),
    Task(7, "Task7", 4578, "\uD83D\uDE19", "Note1", 7),
    Task(8, "Task8", 4578, "\uD83D\uDE0D", "Note2", 8),
    Task(9, "Task9", 4578, "\uD83D\uDE0F", "Note1", 1),
    Task(10, "Task10", 4578, "\uD83D\uDE29", "Note2", 2),
    Task(11, "Task11", 4578, "\uD83D\uDE00", "Note1", 3),
    Task(12, "Task12", 4578, "\uD83D\uDE01", "Note2", 4),
    Task(13, "Task13", 4578, "\uD83D\uDE02", "Note1", 5),
    Task(14, "Task14", 4578, "\uD83D\uDE03", "Note2", 6),
    Task(15, "Task15", 4578, "\uD83D\uDE04", "Note1", 7),
    Task(16, "Task16", 4578, "\uD83D\uDE05", "Note2", 8),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        setContent {
            IteratineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "YoMama",
                        modifier = Modifier.padding(innerPadding)
                    )
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 8.dp,
                            vertical = 100.dp
                        ),
                    ) {
                        items(routines) {
                            ListRoutines(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /*Card(

    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }*/

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IteratineTheme {
        Greeting("Android")
    }
}

@Composable
fun ListRoutines(routine: Routine) {
    val taskList = tasks
        .filter { it.parentRoutineID == routine.id }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row (
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center // Centers both vertically and horizontally
            ) {
                Text(
                    text = routine.name,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
            val num = routine.startTimestamp.toDuration(DurationUnit.MILLISECONDS).inWholeMinutes.toInt()
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.Start
            ) {
                Row {
                    for (day in routine.days) {
                        Text(
                            text = day.toString().substring(0,3),
                            modifier = Modifier
                                .padding(4.dp)
                                .wrapContentWidth(androidx.compose.ui.Alignment.Start,  true)
                        )
                    }

                }
                Row {
                    for (task in taskList) {
                        ListTasksInRoutine(task)
                    }
                }
                Text(

                    text = num.toString().plus(" min"),
                    modifier = Modifier.padding(4.dp)
                )

            }
        }
    }
}

@Composable
fun ListTasksInRoutine(task: Task) {
    Text(
        text = task.emoji,
        modifier = Modifier
            .padding(horizontal = 6.dp,),
        fontSize = 24.sp,

    )
}