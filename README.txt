To initialize the database on linux use the commands
$ sudo mysql
mysql> source /path/to/gymDatabseInit.sql

These will initialize the database

The overall program is located in the src/gym/fxml folder. It is a JavaFX FXML program that requires all files to run.  I recommend using JavaSE 8.
The program can be run by compiling all the files in the above folder using javac*.java when in the folder directory
the program requires a connection to a database and a jdbc driver it can be run by navigating to the /Gym FXML> directory and  using java -cp /path/to/jdvcDriver.jar:src gym.fxml.GymFXML
This command will open a gui that allows the user to enter the jdbc driver and then will allow the user to input a database url, username and password.  The program will then login to the database and full functionality of the program will be accessable.  Optionally the command line arguments "databaseurl username password jdbcDriver" can be appended to login and access the program directly.
The program requires the database to be running and loaded with the initialization sql to work.
