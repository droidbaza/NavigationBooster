![](art/header.png)
# :pill: NavigationBooster :pill:
Jetpack Compose Navigation extensions library for simple easy way for navigation and passing arguments between composable screens 

### Main features
- Simple setup without any boilerplate and autogenerated code
- Easy passing and getting any types arguments
- Navigating back or back to specific route with any types of arguments
- Getting the navigation arguments from the SavedStateHandle (useful in ViewModels) and NavBackStackEntry.

## Samples of usage
You can find samples of usage in sample project directory

1. Put arguments : 
```
// put key value Pair<String,Any> arguments to destination screen route
   FirstScreen {
                //sending multiply arguments 
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
```

2. Get arguments from backStackEntry: 
```

composable(Destination.Second.route) { //backStackEntry
            //getting arguments from first screen
            val id = it.getNonNull<String>(Destination.Second.keyId)
            val item = it.get<ExampleModel>(Destination.Second.keyItem)
            val items = it.get<List<ExampleModel>>(Destination.Second.keyItems)
            val doubleList = it.get<List<Double>>("keyDoubleList")
            val booleanValue = it.get<Boolean>("keyBoolean")

            SecondScreen {
                navController.navigate(Destination.Third)
            }
        }
```

2. Get arguments from viewModel savedStateHandle: 
```

@HiltViewModel
class SecondViewModel @Inject constructor(
    it: SavedStateHandle
) : ViewModel() {

   val id = it.getNonNull<String>(Destination.Second.keyId)
   val item = it.get<ExampleModel>(Destination.Second.keyItem)
   val items = it.get<List<ExampleModel>>(Destination.Second.keyItems)
   val doubleList = it.get<List<Double>>("keyDoubleList")
   val booleanValue = it.get<Boolean>("keyBoolean")

}

```

3. Popback stack with arguments and get it: 
```

composable(Destination.Third.route) {
            //get back args from four screen
            val args = it.get<Boolean>(Destination.Four.keyBack, isBackArgs = true)

            ThirdScreen {
                navController.navigate(Destination.Four)
            }
        }
        composable(Destination.Four.route) {
            FourScreen {
                // pop back stack with boolean args
                navController.popBackStack(Destination.Four.keyBack to true)
            }
        }

```

## Installation

To use this library in your Android project, just add the following dependency into your module's `build.gradle`:

***Use Jitpack implementation***

1. Check Jitpack use : 
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add implementation in project build :
```
implementation 'com.github.droidbaza:navigationbooster:1.0.4'
```

Or you can copy NavigationUtils.kt file (https://github.com/droidbaza/NavigationBooster/blob/master/navigationbooster/src/main/java/com/droidbaza/navigationbooster/NavigationUtils.kt)
into your project


