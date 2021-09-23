# Player SDK

This library is to demonstrate how to provider media player functionality encapsulated within a library module in an easy to use and extensible way.

There is a demo app: **demoApp** which provides an example of how to use this library.

### Initialisation
The library is initialised by calling start() in the Initialiser class in the package [uk.co.bishopit.player.setup.Initialiser]

Currently this only starts the "Timber" event logging framework.

### The Player
The player can be included in a layout file using the xml tag <uk.co.bishopit.player.view.CorePlayerView>.
A customised view control may be used by providing a layout file "player_control_view.xml" as shown in the demoApp
