Windows command after compiling all java files in src/gym/fxml using javac *.java
java -cp "lib/mysql-connector-java-8.0.21.jar;src" gym.fxml.GymFXML "jdbc:mysql://localhost/Gym?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&&serverTimezone=America/New_York" user 1234 com.mysql.cj.jdbc.Driver

Linux command after compiling with same command
java -cp "lib/mysql-connector-java-8.0.21.jar:src" gym.fxml.GymFXML "jdbc:mysql://localhost/Gym?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&&serverTimezone=America/New_York" user 1234 com.mysql.cj.jdbc.Driver
