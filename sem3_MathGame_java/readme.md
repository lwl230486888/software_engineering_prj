Math Game
This is an Android application designed to provide a fun and interactive way for users to practice their math skills. The app includes a main menu with four options: Play, Game Ranking, Your Records, and Close. The game challenges players to answer math questions within a time limit, tracks their performance, and displays rankings and personal records.

Features
1. Main Activity
A main menu with four buttons:

Play: Starts the game.

Game Ranking: Displays global rankings fetched from a JSON API.

Your Records: Shows the player's local game history.

Close: Exits the app.

2. Game Play
Players are presented with 10 math questions.

A timer tracks the time taken to complete the game.

Players input their answers in an EditText widget and submit them using the "Done" button.

After each question, the app displays the result and provides a "Next" button to proceed to the next question.

After completing all 10 questions, the final score and time are displayed.

A "Continue" button allows players to start a new game.

3. Game Ranking
Fetches global rankings from a JSON API.

Displays the rankings in a ListView.

4. Your Records
Loads the player's game history from a local SQLite database.

Displays the records in a ListView.

5. Additional Features
Image Buttons: Replaces numeric buttons with image buttons for a more engaging user experience.

Timer: Adds a sense of urgency and challenge to the game.

Dynamic Question Generation: Questions are generated dynamically to ensure variety.

How to Use
1. Main Menu
Launch the app to access the main menu.

Tap on one of the four buttons to proceed.

2. Play
Tap the "Play" button to start the game.

Answer each question by typing your response in the EditText field and tapping "Done".

Review the result and tap "Next" to proceed.

After completing all questions, view your final score and time.

Tap "Continue" to start a new game.

3. Game Ranking
Tap the "Game Ranking" button to view global rankings.

The rankings are fetched from a JSON API and displayed in a ListView.

4. Your Records
Tap the "Your Records" button to view your game history.

The records are loaded from the local database and displayed in a ListView.

5. Close
Tap the "Close" button to exit the app.

Code Structure
Key Components
MainActivity:

Handles the main menu and navigation to other activities.

GameActivity:

Manages the game logic, including question generation, timer, and result display.

RankingActivity:

Fetches and displays global rankings from a JSON API using a ListView.

RecordsActivity:

Loads and displays the player's game history from a local SQLite database using a ListView.

DatabaseHelper:

Manages the local SQLite database, including creating the GamesLog table and handling CRUD operations.

QuestionGenerator:

Dynamically generates math questions for the game.

Timer:

Tracks the time taken to complete the game.

Techniques Used
Activities and Intents:

Uses multiple activities for different screens (main menu, game, rankings, records).

Navigates between activities using Intents.

ListView:

Displays rankings and records in a scrollable list.

Local Database:

Uses SQLite to store and retrieve game records.

JSON Parsing:

Fetches and parses JSON data from an API to display global rankings.

Dynamic UI Updates:

Updates the UI dynamically based on user input and game progress.

Timer:

Implements a countdown timer to track game duration.

Image Buttons:

Enhances the UI by replacing numeric buttons with image buttons.