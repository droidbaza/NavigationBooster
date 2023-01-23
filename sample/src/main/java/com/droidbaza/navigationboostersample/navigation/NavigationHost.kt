package com.droidbaza.navigationboostersample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.droidbaza.navigationbooster.NavigationUtils.get
import com.droidbaza.navigationbooster.NavigationUtils.getNonNull
import com.droidbaza.navigationboostersample.model.ExampleModel
import com.droidbaza.navigationboostersample.screens.FirstScreen
import com.droidbaza.navigationboostersample.screens.FourScreen
import com.droidbaza.navigationboostersample.screens.SecondScreen
import com.droidbaza.navigationboostersample.screens.ThirdScreen
import java.util.UUID

@Composable
fun NavigationHost(
    navController: NavigationController,
    startDestination: String = "",
) {
    NavHost(
        navController = navController.hostController,
        startDestination = startDestination
    ) {
        composable(Destination.First.route) {
            //get back args from third screen
            val args = it.get<ExampleModel>(Destination.Third.keyBack, isBackArgs = true)

            FirstScreen(description = "has back args from third screen:${args != null} args is $args") {
                //sending multiply arguments to second screen
                val dest = Destination.Second
                navController.navigate(
                    dest,
                    dest.keyId to "ID:${UUID.randomUUID()}",
                    dest.keyItem to ExampleModel(),
                    dest.keyItems to listOf(
                        ExampleModel("item 1"),
                        ExampleModel("item 2")
                    ),
                    "keyDoubleList" to listOf(1.1, 2.2, 3.3),
                    "keyBoolean" to true
                )
            }
        }
        composable(Destination.Second.route) {
            //getting arguments from first screen
            val id = it.getNonNull<String>(Destination.Second.keyId)
            val item = it.get<ExampleModel>(Destination.Second.keyItem)
            val items = it.get<List<ExampleModel>>(Destination.Second.keyItems)
            val doubleList = it.get<List<Double>>("keyDoubleList")
            val booleanValue = it.get("keyBoolean", defaultValue = false)

            SecondScreen(
                description = "args from first screen:\n" +
                        "id :$id\n" +
                        "item : $item\n" +
                        "items : $items\n" +
                        "doubleList :$doubleList\n" +
                        "booleanValue :$booleanValue"
            ) {
                navController.navigate(Destination.Third)
            }
        }
        composable(Destination.Third.route) {
            //get back args from four screen
            val args = it.get(Destination.Four.keyBack, false, isBackArgs = true)

            ThirdScreen(
                description = "has back args from four screen:$args",
                actionBack = {
                    //pop back to first screen with parcelable args
                    navController.popBackStack(
                        Destination.Third.keyBack to ExampleModel(),
                        Destination.First
                    )
                }) {
                navController.navigate(
                    Destination.Four,
                    Destination.Four.keyItem to ExampleModel()
                )
            }
        }
        composable(Destination.Four.route) {
            FourScreen {
                // pop back stack with boolean args
                navController.popBackStack(Destination.Four.keyBack to true)
            }
        }
    }
}
