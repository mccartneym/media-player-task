# Player SDK

This project aims to demonstrate how to provide media player functionality encapsulated within an extensible library module.

There is a demo app: **demoApp** which provides an example of how to use this library.

### Initialisation
The library is initialised by calling start() in the Initialiser class in the package [uk.co.bishopit.player.setup.Initialiser](/player/src/main/java/uk/co/bishopit/player/setup/Initialiser.kt)

Currently this only starts the [Timber](https://github.com/JakeWharton/timber) event logging framework.

### The Player
The player library can be installed as a gradle dependency or a source code module. In either case you need to update the dependencies block in your application modules build.gradle file:

#### Use library as remote gradle dependency
To install the library as a gradle dependency add these lines:
```
    debugApi "uk.co.bishopit:player-debug:0.1.0"
    releaseApi "uk.co.bishopit:player:0.1.0"
```

#### Use library as local module with source code
To install the library as a module add the following line:
```
    implementation project(':player')
```
To your settings.gradle file add the line:
```
    include ':player'
```

#### Adding a player view to your app
The player is responsible for handling configuration changes and so any activity housing it should have the Android manifest attribute:
`android:configChanges="keyboard|keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize|uiMode"` applied to the activity.

The view can then be included in a layout file using the xml tag **<uk.co.bishopit.player.view.CorePlayerView>**.
A customised view control may be used by providing a layout file [player_control_view.xml](/demoApp/src/main/res/layout/player_control_view.xml) as shown in the demoApp

