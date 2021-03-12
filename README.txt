Widchard Faustin
SE 311
3/11/2021


Assignment 4 README

Purpose:
The purpose of this README is to explain how my submission for Assignment 4 of SE 311 works. In this way, graders and other people alike will easily
be able to run my code and understand my logic.


Important Notes to Know:

Due to computer problems and constraints, I was unable to run 2 eclispe workspaces for the development of this project. After talking about 
this issue to Russell and Boris, I was able to get it cleared with them that I could implement the assignment in 1 project and 1 workspace. 
However, while it is in one project, it still uses socket programming correctly, as well as the server running on a separate thread than the 
client. However, the Main class starts them both up at the same time. 

Additionally, I wanted to clarify some additional ways my program works in this section, so less time needs to be spent explaining this in 
the "How It Runs" section. One of the first design decisions I made was what information I sent to the server. Everytime a button on the calculator 
is pressed, that button's label is sent to the server. The server then does the logic of calculation and parsing the information, in which case output 
is sent back to the client to be rendered onto the calculator screen. This is a little bit different than what was dictated in the system, but I made this 
decision because I did not see any requirements on where calculations needed to be done. Therefore, while the client uses MVC, the sever contains the 
classes that deals with the Composite Pattern, Visitor Patterns, and State Pattern. 

I also implemented the extra credit instructions, where everytime a user presses the equal sign for a valid equation, the proper breakdown of the equation 
is printed to a file called OutputFile.txt. 

Finally, a small note, for division by 0, instead of outputting error, I output it as 0. This was due to me not wanting to have to differentiate what the calculation does 
based on the operator given. 


How To Get It Working:

Once you've downloaded the project as a zip file, you can open up eclispe, launch your workspace, and from there click the import button. Navigate to the option
"Existing Projects Into Workspace", switch the choice of select root directory to select archive file, and click on the zip file that contains this project. Once you've
imported the project in, you can click run configurations, select Java Application, and from there select the Main class as the class to run it from. Once done, your program
should be able to run! (This is off of my limited experience with Eclipse, so if there is a better way to import existing eclipse projects, feel free to use that too.)

How It Runs:

The program starts out by launching the Client and the Server at the same time. The client launches the CalculatorModel and the GUI. The GUI 
then creates a graphical user interface of a calculator, which has different buttons which can be pressed. Pressing a button on the calculator passes the 
button's label to the GUI, which is then passed to the controller. The controller then sends the input to the server, in which the Sevrer parses the information, 
changes the state of the equation, and then checks if calculation needs to be done. If not, the server then passes the equation so far back to the client, which then 
passes the information to the model. The model then gives the information to the view to be rendered. 

If a calculation does need to be performed, the server executes its calculate method, which splits and parses the current equation and uses the CalculatorVisitor class, as well as 
the Equation, Expression, Operand, Operator, and EndOperand classes (Composite Pattern) to calculate the proper value. After this is calculated, the answer is then returned to the client. 

If the equal sign was specifically pressed, and the equation is valid (the calculate state has been reached), it also writes out the equation that was typed in full to the console, as well as 
how many full equations are stored from the time the calculator was opened. Finally, an output file named OutputFile.txt is made. It contains the equation written in the format that 
the extra credit assignment wanted it as.


