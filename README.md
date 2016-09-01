# ConnectFour-Java-AI-Player
####Udacity Intro to Java Final Project
###A full description of the project can be found [here](https://s3.amazonaws.com/content.udacity-data.com/courses/cs046/IntrotoJavaPogrammingFinalProjectDescription.pdf).

Connect 4 is a popular board game, similar to an extended version of Tic-Tac-Toe. The architecture for the game was given. The modification of any classes (other than Main.java) was unnecessary. The main deliverable for this project was the coding implementation of the MyAgent.java object of the Abstract Agent class.

###Rules of the Game
Connect 4 involves two players, a ‘red’ player and a ‘yellow’ player, playing against each other. The game is played on a vertical grid with six rows and seven columns. The players take turns placing pieces of their color, called ‘tokens’, into the board. The goal of each player is to get four of his or her tokens in a row in the board, either horizontally, vertically, or diagonally. For example, below are three winning arrangements of pieces from the game you will be using.
![alt tag](http://1onjea25cyhx3uvxgs4vu325.wpengine.netdna-cdn.com/wp-content/uploads/2014/12/student_projects_connect4.png)

Connect 4 is played on a vertical board, meaning that pieces are “dropped” into the board. So, players may only ever place their token in the top unfilled slot in a column. The above three are examples of valid boards because no token is located above an empty slot. The board below would be an invalid board because there are empty slots beneath some of the tokens:

![alt tag](https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRYfnxvbEAmmKmqRI1LO4VkIg6SoV4npD_WyqxScJmMbIZaxBwpTg)

This project involved the coding of an articially intelligent player called an agent that could play the game to a certain degree.

I completed Udacity's cs046 Intro to Java course. My implementation of MyAgent.java can be found [here] (https://gist.github.com/versatgrant/9961fd2e44bfda633a7833067619e85e).

###After playing the game 100 times; 20 times against each of the given players, I got the following results:
Agents | Results
---|---
MyAgent vs. RandomAgent | 20-1
MyAgent vs. BegginnerAgent | 20-5
MyAgent vs. IntermediateAgent | 20-10
MyAgent vs. AdvancedAgent | 20-12
MyAgent vs. BrilliantAgent | 7-20

