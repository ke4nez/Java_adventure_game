# Survival Island Adventure

## HOW TO RUN THE GAME

To run the game, you can either:

1. **Run in IDE**: Import the project into your preferred IDE and run it from there.
2. **Run the JAR file**: Use the command `java -jar game-final.jar` to launch the game.

> **Note**: Ensure you have Java version 21.0.2 or higher installed to run the JAR file successfully https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html.

**Survival Island Adventure** is an immersive 2D RPG where you journey as a skilled mercenary exploring a mysterious island in search of ancient treasures. With a top-down view, you’ll traverse the island, interact with NPCs, craft items, and uncover the secrets of Calradia.

---

## Table of Contents

- [Game Overview](#game-overview)
- [Getting Started](#getting-started)
- [Game Controls](#game-controls)
- [Gameplay Elements](#gameplay-elements)
  - [Objects](#objects)
  - [Inventory & Crafting](#inventory--crafting)
  - [Environment](#environment)
- [Saving & Loading](#saving--loading)
- [Creating a New Level](#creating-a-new-level)
- [Walkthrough](#walkthrough)
- [Lighting System](#lighting-system)

---

## Game Overview

In **Survival Island Adventure**, you are a mercenary in the fictional world of Calradia. Your goal is to find the legendary treasures hidden in the dungeon of the forgotten temple of Mercuna, the god of the dead. After landing on a mysterious island, you must navigate the wild terrain, locate valuable items, and uncover hidden paths leading to the temple’s riches.

## Getting Started

- **Objective**: Explore the island, interact with NPCs, and complete challenges to locate ancient treasures.
- **Setting**: Begin in a dense forest and navigate your way to the underground dungeon on the second level.

## Game Controls

### Main Menu
- **Navigate**: `W` and `S`
- **Select**: `E`

### Gameplay
- **Movement**: `W`, `A`, `S`, `D`
- **Open Inventory**: `I`
- **Interact / Pick up**: `E`
- **Pause Game**: `P`
- **Open Menu**: `ESC`

---

## Gameplay Elements

### Objects

| Item                    | Interaction                                       |
|-------------------------|---------------------------------------------------|
| **Axe**                 | Pick up with `E` to open doors.                   |
| **Pond**                | Fish out a bulb with `E`, needed for lamp crafting. |
| **Lamp**                | Collect to craft a working lamp.                  |
| **Treasure Chest**      | Main game objective; interact to complete the game. |
| **Door**                | Can be opened with `E` if holding an axe.        |
| **Hole to Next Level**  | Allows transition to the next level.              |
| **Hole to Previous Level** | Allows return to the previous level.          |

### Inventory & Crafting

- **Equip items**: Press `E` to equip items in your inventory.
- **Crafting**: Move the inventory cursor using `A` and `D`, then press `C` to craft items.

### Environment

- **Terrain**: The environment is divided into passable and impassable terrain.
  - **Passable**: Grass, roads, open fields.
  - **Impassable**: Water, trees, and dense forests.

- **NPCs**: 
  - You can encounter knights that you can talk to for hints and guidance.
  - Harmless NPCs such as snails can be found wandering the world, though they do not engage in conversation.

## Saving & Loading

1. **Save**: Open the game menu (ESC) and select the "Save Game" option. This will save your progress and the current level.
2. **Load**: To resume a saved game, select "Load Game" from the main menu when you start the game.

---

## Creating a New Level

1. **Folder Setup**: Create a new folder in the `levels` directory (e.g., `Level_3`). You can also modify existing level folders by opening the JAR file with an archive manager.
2. **File Requirements**:
   - `map_1.txt`: Defines the terrain layout using a 64x64 grid with two-digit codes.
   - `Objects.txt`: Lists objects by name with coordinates (e.g., `Axe 24 51`).
   - `Npcs.txt`: Lists NPCs and their positions on the map.
3. **Adding NPC Dialogues**: For NPCs like "Stranger," add dialogue by creating a folder within the level directory (e.g., `Stranger`) and a `Dialogue.txt` file inside it.
4. **Linking Levels**: To connect levels, ensure that the level numbers are sequential. For example, from level 2, you can transition to level 1 or level 3 only by placing transition objects (e.g., `Hole_to_next_level` and `Hole_to_previous_level`) on the maps. The game always starts from level 1.

### Tile Numbers and Object/NPC Names

| Tile Number | Name                      | Passable |
|-------------|---------------------------|----------|
| 30          | Water                     | No       |
| 10          | Trees                     | No       |
| 11          | Grass                     | Yes      |
| 12          | Forest Road Left          | Yes      |
| 13          | Forest Road Right         | Yes      |
| 14          | Forest Field              | Yes      |
| 15          | Forest Rocks              | Yes      |
| 16          | Forest Stick              | Yes      |
| 17          | Forest Stick              | Yes      |
| 18          | Tree                      | No       |
| 20          | Forest Swamp              | Yes      |
| 21          | Forest Swamp 2            | Yes      |
| 22          | Forest 4 Swamps           | Yes      |
| 39          | Darkness                  | Yes      |
| 40          | Dungeon Wall Top          | No       |
| 41          | Dungeon Wall Bottom       | No       |
| 42          | Dungeon Wall Left         | No       |
| 43          | Dungeon Wall Right        | No       |
| 44          | Dungeon Floor             | Yes      |
| 45          | Dungeon Big Rock          | Yes      |

### Objects

| Item                    | Interaction                                       |
|-------------------------|---------------------------------------------------|
| **Axe**                 | Pick up with `E` to open doors.                   |
| **Pond**                | Fish out a bulb with `E`, needed for lamp crafting. |
| **Lamp**                | Collect to craft a working lamp.                  |
| **Treasure Chest**      | Main game objective; interact to complete the game. |
| **Door**                | Can be opened with `E` if holding an axe.        |
| **Hole to Next Level**  | Allows transition to the next level.              |
| **Hole to Previous Level** | Allows return to the previous level.          |

### NPCs

| NPC Name    | Description                                     |
|-------------|-------------------------------------------------|
| **Stranger**| A knight who can give hints when spoken to.    |
| **Snail**   | A harmless NPC that wanders the environment.   |

## Walkthrough

1. **Find the Axe**: Located in the first clearing; use it to open doors.
2. **Acquire the Bulb**: Fish it from the pond to craft a working lamp.
3. **Craft the Lamp**: Use the axe and bulb in your inventory to create a working lamp.
4. **Enter the Dungeon**: Equip the lamp to proceed into the dark areas of the cave.
5. **Locate the Treasure Chest**: Upon interacting with it, the game will end with your victory.

---

## Lighting System

The game features a dynamic lighting system that enhances the atmosphere of exploration. Players must craft a **Working Lamp** to navigate dark areas, such as caves. To create the lamp, collect an **Axe** and a **Bulb** from the environment. Once you have both items, use the crafting mechanics in your inventory to combine them. Equip the lamp to illuminate your surroundings and reveal hidden treasures, while also ensuring safe passage through dark terrains.

**Survival Island Adventure** is a thrilling mix of exploration, crafting, and puzzle-solving that encourages creativity and strategic thinking. Enjoy your journey to the island’s mysterious depths and uncover the treasures of Mercuna!
