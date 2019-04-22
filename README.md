# Block Game
Written by Ryan Gloff
This project is for CS 490 | Kotlin Programming
 
## Description
This repository contains a block traversal game. Select a level in the menu system and then move around the map until all tiles have been activated.

## Building and Running

### Dependencies
- Java
- Kotlin
- Maven

### Building
From the poject root folder run 
```
mvn clean install
```

### Running with default configuration
From the project root folder run
```
java -jar target/game-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/resources/config.json
```

## Creating Levels
Levels as well as many other things are set up and configured in the configuration file that must be passed in the command line arguments.
