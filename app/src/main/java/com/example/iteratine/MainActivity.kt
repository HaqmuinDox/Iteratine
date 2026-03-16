package com.example.iteratine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iteratine.ui.theme.IteratineTheme
import kotlin.time.DurationUnit
import kotlin.time.toDuration

val routines = listOf(
    Routine(
        1,
        "Morning routine1",
        239084,
        listOf(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY)
    ),
    Routine(2, "Morning routine2", 6*60*60*1000, listOf(Day.TUESDAY, Day.WEDNESDAY)),
    Routine(3, "Morning routine3", 8*60*60*1000, listOf(Day.MONDAY, Day.WEDNESDAY)),
    Routine(4, "Morning routine4", 7*60*60*1000, listOf(Day.MONDAY, Day.TUESDAY)),
    Routine(
        5,
        "Morning routine5",
        239084,
        listOf(
            Day.MONDAY,
            Day.TUESDAY,
            Day.WEDNESDAY,
            Day.THURSDAY,
            Day.FRIDAY,
            Day.SATURDAY,
            Day.SUNDAY
        )
    ),
    Routine(
        6,
        "Morning routine6",
        339084,
        listOf(
            Day.MONDAY,
            Day.TUESDAY,
            Day.WEDNESDAY,
            Day.THURSDAY,
            Day.FRIDAY,
            Day.SATURDAY,
            Day.SUNDAY
        )
    ),
    Routine(7, "Morning routine7", 239084, listOf(Day.MONDAY)),
    Routine(
        8,
        "Morning routine8",
        339084,
        listOf(Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY)
    ),
)

val tasks = listOf(
    Task(1, "Task1", 4578, "\uD83E\uDEA5", "Note1", 1),
    Task(2, "Task2", 4578, "\uD83C\uDF05", "Note2", 2),
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
    Task(1, "Task1", 4578, "\uD83E\uDEA5", "Note1", 1),
    Task(2, "Task2", 4578, "\uD83C\uDF05", "Note2", 2),
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
    Task(1, "Task1", 4578, "\uD83E\uDEA5", "Note1", 1),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            IteratineTheme {

                // 1. Create the controller
                val navController = rememberNavController()

                // 2. Define the NavHost
                NavHost(navController = navController, startDestination = "home") {

                    // Route for the Main List
                    composable("home") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            LazyColumn(
                                modifier = Modifier.padding(innerPadding),
                                contentPadding = PaddingValues(horizontal = 30.dp, vertical = 20.dp),
                            ) {
                                items(routines) { routine ->
                                    // Pass a lambda to handle the click
                                    ListRoutines(routine) {
                                        navController.navigate("details/${routine.id}")
                                    }
                                }
                            }
                        }
                    }

                    // Route for the Details Page
                    composable(
                        route = "details/{routineId}",
                        arguments = listOf(navArgument("routineId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val routineId = backStackEntry.arguments?.getInt("routineId")
                        RoutineDetailScreen(routineId, onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /*Card(

    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }*/

}*/
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IteratineTheme {
        Greeting("Android")
    }
}*/

@Composable
fun ListRoutines(routine: Routine, onItemClick: () -> Unit) {
    val taskList = tasks
        .filter { it.parentRoutineID == routine.id }

    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .padding(horizontal = 16.dp)
            .clickable { onItemClick() },

    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.width(250.dp)
            ) {
                Row {
                    ListRoutineDays(routine)
                }
                Row {
                    ListTasksInRoutine(taskList)
                }
            }

            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(100.dp),
                contentAlignment = Alignment.Center // Centers both vertically and horizontally
            ) {
                Text(
                    text = routine.name,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                val totalTime = calcTotalRoutineTime(routine)
                Text(
                    text = totalTime.toString().plus(" min"),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}


@Composable
fun ListRoutineDays(routine: Routine) {
    for (day in listOf(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY)) {
        Text(
            text = day.toString().substring(0,1),
            modifier = Modifier
                .padding(4.dp)
                .width(25.dp)
                .wrapContentWidth(Alignment.Start,  true)
                .wrapContentHeight(Alignment.Top, false),
            color = if (day in routine.days) Color.White else Color.Gray,
            fontSize = 12.sp,
        )
    }



}

@Composable
fun ListTasksInRoutine(taskList: List<Task>) {
    val taskSublist = taskList.subList(0,3)
    val modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 4.dp)
        //.width(25.dp)
        .wrapContentWidth(Alignment.Start, true)
        .wrapContentHeight(Alignment.Top, false)
        .fillMaxWidth()
    for (task in taskSublist) {
        Text(
            text = task.emoji,
            modifier = modifier,
            fontSize = 24.sp,
        )
    }
    if (taskList.size > taskSublist.size)
        Text(
            text = "+".plus(taskList.size - taskSublist.size),
            modifier = modifier,
            fontSize = 24.sp
        )

}

fun calcTotalRoutineTime(routine: Routine): Int {
    val routineTasks = tasks.filter { it.parentRoutineID == routine.id  }
    var totalTime = 0
    for (task in routineTasks) {
        totalTime += task.duration
    }
    return totalTime.toDuration(DurationUnit.SECONDS).inWholeMinutes.toInt()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineDetailScreen(routineId: Int?, onBack: () -> Unit) {
    val routine = routines.find { it.id == routineId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(routine?.name ?: "Unknown Routine") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding).padding(horizontal = 50.dp)
            .fillMaxSize()) {
            if (routine != null) {
                val startTime = routine.startTimestamp.toDuration(DurationUnit.MILLISECONDS)
                val timeTaken = calcTotalRoutineTime(routine).toDuration(DurationUnit.MINUTES).inWholeMilliseconds.toDuration(DurationUnit.MILLISECONDS)
                val endTime = startTime.plus(timeTaken)
                Text(
                    "%02d:%02d".format(startTime.inWholeHours, startTime.inWholeMinutes % 60).plus(" - ${"%02d:%02d".format(endTime.inWholeHours % 24, endTime.inWholeMinutes % 60)} (${calcTotalRoutineTime(routine)} min)"))
                // Add more details here...
            } else {
                Text("Routine not found")
            }
        }
    }
}