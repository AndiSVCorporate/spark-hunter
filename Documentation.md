#Spark Hunter feature documentation.

# Overview #

This is a list of features and system components for Spark Hunter, please read this before play-testing. The most current release version of Spark Hunter is located on the downloads page.

# Installation #

APK:
http://code.google.com/p/spark-hunter/downloads/detail?name=spark-hunter_final.apk

Overview:
http://code.google.com/p/spark-hunter/source/browse/builds/SparkHunter_Final_Presentation.ppt

Push apk to android device, version 2.3.3 or above.
To use Spark Hunter's Facebook features, have Facebook installed and logged in. Login not required but social features will not be available.

# Known / Report Issues #
http://code.google.com/p/spark-hunter/issues/list

# User Stories #
http://code.google.com/p/spark-hunter/wiki/UserStories

# Features, Instructions, and Catastrophes #

## Final ##
  * Reverse Geocoding
    * Now all latitude and longitude locations on the Battle History is now converted to city names.
    * Directions: Make sure the GPS is on and the phone has a location. Go to Battle->Battle History . Click on the dagger to see the details.
    * Single player battle is now against randomly generated Sparks of moderate difficulty.
    * Inventory has been overhauled and linked into other game segments.
      * A short click on an item will use it on your active spark.
      * A long click on an item displays its properties.
      * A short click on a Spark makes it your active Spark, i.e. the one you fight with.
      * A long click on a Spark displays its stats.
    * Sounds have been overhauled.
      * Music changes as needed in game.
      * Buttons click.

## Build 5 ##
  * Spark-hunter only friend list.
    * Server has a database of anyone who has signed into spark-hunter.
    * Directions: Go to Multiplayer->Friends. If a second player who is also your friend views the friend list, the first player will show up. And vice-versa.
  * Multiplayer directions are the same as before. Initial battle data now syncs.
  * Selecting an item in the inventory displays said item's properties
  * Inventory entities are read in from SQL database, if the database doesn't exist it will be initialized to /data/data/com.sparkhunter.main/ from /assets.


## Build 4 ##
  * The player's game data is persistent.
    * 
  * Players can battle other players.
    * Not fully functional. The players can find each other and start a battle, but battle data hasn't been fully implemented yet on the client side.
    * INSTRUCTIONS
      * Both players click on the Multiplayer menu on the main screen.
      * One player click on the host match.
      * The other player refresh the match list and then select the number that pops up (This is the host's facebook ID)
        * There may be more than one choice on the off chance other's are hosting games.
      * The battle should then commence roughly the same time.
  * The player/their Sparks can level up.
    * 
  * A background service that will listen to the location and will be     used to perform functions when the application is not running.
    * 


## Build 3 ##
  * State changes to actual player sparks are not yet reflected, this is a bug.

  * The player can instead choose the "Adventure Time!" button on the main page, triggering a (not very) random encounter.
    * Selecting "Ignore..." dismisses the screen.
    * Selecting "Fight!" takes the player to the battle screen.

  * Players can now choose different abilities in battle
    * Win/Lose condition is correctly identified
    * Only 2 moves available for now

## Build 1 / 2 ##

  * Spark Hunter now has sound! Both background music and sound effects can now be played. Current instances of this are as follows:
    * The title screen plays music when first (and only first for now) displayed. This is only a placeholder song the sound developer happens to love, and will not be the final theme song.
    * Clicking buttons on the title screen causes a click sound to play.
    * Tapping inventory items (which are all still ducks for now) causes an associated sound to play.
    * The battle screen has music (more temporary sound dev goodness) playing during a fight.
> All this is handled either by scattered MediaPlayer objects (which are being phased out), or the BackgroundMusic service. This service accepts intents from others containing the sound to play, and handles sound state changes accordingly.

  * There is a capability to add markers on the map.
    * For now the capability is to just add markers on the touch of the screen.
    * The plan is to allow the map to have capability to choose whether a touch will add a marker or not.

  * There is now a login screen before the main title screen, where the user can choose to login to Facebook, continue without logging in, or exit.

  * The user can go to the Get Spark screen to get a basic starter Spark to battle with.
    * Three options are available, the player can scroll horizontally to select a Spark.
    * To confirm the selection, the player taps the "Choose Spark" button. This bring a dialog up describing the Spark in question.
    * At this point, the player should hit the back button twice to return to the title screen.

  * Once the player has a Spark, they can go to the battle screen.
    * The player can then fight against an enemy Spark ("Poke-man" for now).
    * Both Sparks are displayed at the top, with their corresponding health bars.
    * The middle of the screen displays messages regarding attacks, abilities, and other relevant information.
    * The four buttons at the bottom correspond to various attacks and abilities the player's Spark can use. The player can select between different attacks.
    * Once the opponent has been defeated, the player can press the back button to return to the title screen.

  * The player can view their item and spark inventories.
    * The item inventory is filled with rubber ducks for now, selecting one makes it quack.
    * The spark inventory is filled with dummy dagger sparks at the moment.

  * If the player has logged in with Facebook, they can view their friends on the friends screen.
    * Not logging in causes a blank screen to be displayed.

Music courtesy of RainbowCrash88, sound effects by Jack.