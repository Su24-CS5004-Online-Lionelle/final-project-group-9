# Manual 

You will update this manual and add any files here that you need for an 'application' manual for your program. Make sure to include screenshots of various features. 

## Program Description
The NBA Stat Tracker shows you all current active NBA Players in the league and all their stats. You can also build your own roster, export the roster in different file formats of your preference (JSON, XML, or CSV), pull up old rosters you have made in the past, and make modifications to any of your rosters as you please. When in doubt, there is a help button to guide you on how to use the program. When you're done using the program, clicking "Exit Program" will close it out.

## Start Up
- Upon starting the program, this is the window that pops up.

<img src="Manual_Screenshots/img1.png" alt="GitHub Logo" width="375">

## Text Field and Drop Down Menu to Filter
- The text field is there for the user to help with their search for players. This works alongside the drop down menu where the user can pick how they want the players to be filtered.

<img src="Manual_Screenshots/img2.png" alt="GitHub Logo" width="375"> <img src="Manual_Screenshots/img3.png" alt="GitHub Logo" width="375">

- There are a variety of filters that the user can choose from. These include: First name, Last name, Position, Height, Draft year, Draft round, Draft pick, Team, Conference, Points per game, Rebounds per game, Assists per game, Blocks per game, Steals per game, Minutes per game, Field goal percentage, Free throw percentage, and 3 point percentage.

## Sort Order
- Next to the filter drop down menu, is another drop down menu that allows you to pick how you would like the data sorted. You can choose either ascending (first to last) or descending (last to first).

<img src="Manual_Screenshots/img4.png" alt="GitHub Logo" width="375">

## Search
- The 'Search' button uses the parameters the user has given (input in the text field, choice of filter, choice of sort order), and displays the list of players requested

<img src="Manual_Screenshots/img5.png" alt="GitHub Logo" width="375">

## Example 1
- To start, if you want to see the entire list of players filtered by their first name and sorted in ascending order, you can type "all" into the text field, select 'First name' in the filter drop down menu, select 'Ascending' in the sort order drop down menu.

<img src="Manual_Screenshots/img6.png" alt="GitHub Logo" width="375">

- After you click on 'Search' you should get something that looks like this.

<img src="Manual_Screenshots/img7.png" alt="GitHub Logo" width="375">

## Operators
- An operator MUST precede your desired search criteria. 
- Operators that you can choose from include: <br>
  == (Equals) <br> ~= (Contains) <br> != (Not equals) <br> < (Less than) <br> <= (Less than or equals to) <br> >= (more than or equals to) <br> > (more than)

- For example, if I wanted to look for players who have the first name 'John', I would enter '==John' into the text field and select 'First name' in the filter drop down menu. The search should produce something like this.

<img src="Manual_Screenshots/img10.png" alt="GitHub Logo" width="375">

- However, if instead I was looking for players whose first name CONTAINS 'John', I would enter '~=John' into the text field instead and the search would produce something like this.

<img src="Manual_Screenshots/img11.png" alt="GitHub Logo" width="375">

## Example 2
- Lets say you wanted to see the list of all the players who scored more than or equals to 20 points per game, sorted in descending order (who scored the most would be at the top and the least at the bottom), you would enter '>= 20' into the text field, select 'Points per game' in the filter drop down menu, select 'Descending' in the sort order drop down menu.
  
<img src="Manual_Screenshots/img8.png" alt="GitHub Logo" width="375">

- After you click on 'Search' you should get something that looks like this.

<img src="Manual_Screenshots/img9.png" alt="GitHub Logo" width="375">

## Add and Remove
- These buttons allow you to start making your very own roster by adding or removing players.

<img src="Manual_Screenshots/img12.png" alt="GitHub Logo" width="375">

- There are 4 ways that you can add/remove players from a roster.
- 1. A single digit indicating the index of the player (E.g. '1').
- 2. A range indicating multiple players. If I want to add the first five players to my roster, the range would be '1-5'.
- 3. Player's full name (E.g. 'LeBron James').
- 4. 'all' to add all players in the list.

## Show Current Roster
- This button displays the current roster of players you have added thus far.

<img src="Manual_Screenshots/img13.png" alt="GitHub Logo" width="375">

## Example 3
- Lets say I want to add players who scored >30 points per game to my roster.
- First: I'll search for that list of players by typing '>30' into the text field, select 'Points per game' in the filter drop down menu, select 'Descending' in the sort order drop down menu then click 'Search'. You should get something like this.

<img src="Manual_Screenshots/img14.png" alt="GitHub Logo" width="375">

- To add players, the accepted text field inputs can be the player's index, the range of the indexes (if you want to add multiple players at once), or the player's name.
- Second: I decided that I want the top scorer added to my roster. To do so, you can type either their index, or full name into the text field. In this case, I can type either '1' or 'Joel Embiid' then click on the 'Add' button.
- After clicking the 'Add' button, a message should be displayed saying 'Players Successfully Added'.

<img src="Manual_Screenshots/img15.png" alt="GitHub Logo" width="375">

- Third: Clicking on the 'Show Current Roster' button will show you all the players you currently have in your roster.

<img src="Manual_Screenshots/img16.png" alt="GitHub Logo" width="375">

- If I wanted to add the rest of the players that scored >30 points per game, I'd pull up the search again. 

<img src="Manual_Screenshots/img14.png" alt="GitHub Logo" width="375">

- This time, I can add the rest of the players using a range. I'll enter '2-4' into the text field and click the 'Add' button.
- Now, when I click on the 'Show Current Roster' button, it will show my updated roster of players.

<img src="Manual_Screenshots/img17.png" alt="GitHub Logo" width="375">

## Export Current Roster
- This button saves your current roster out into a file with a file name and file type of your choice.

<img src="Manual_Screenshots/img18.png" alt="GitHub Logo" width="375">

- After clicking the 'Export Current Roster' button, you should get a pop up window that looks like this.

<img src="Manual_Screenshots/img19.png" alt="GitHub Logo" width="375">

- You can save the file with any name that you'd like, and choose the file format (JSON, XML, CSV).

## Load Roster
- This button loads any previous rosters you have saved in the past.

<img src="Manual_Screenshots/img20.png" alt="GitHub Logo" width="375">

- After clicking the 'Load Roster' button, you should get a pop up window that looks like this.

<img src="Manual_Screenshots/img21.png" alt="GitHub Logo" width="375">

- You can now choose any of your previously saved files then click 'open'.
- This should take you back to the NBA Stat Tracker and if you click on the 'Show Current Roster' button, it should display your previously saved roster.

<img src="Manual_Screenshots/img22.png" alt="GitHub Logo" width="375">

## Clear Display
- This button clears the display of any information. 

<img src="Manual_Screenshots/img23.png" alt="GitHub Logo" width="375">

## Help
- This button provides a simple guide to using the NBA Stat Tracker.

<img src="Manual_Screenshots/img24.png" alt="GitHub Logo" width="375">

## Exit Program
- This button exits the program and closes the application.

<img src="Manual_Screenshots/img25.png" alt="GitHub Logo" width="375">