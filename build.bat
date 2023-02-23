@echo off
javac Game.java View.java Controller.java Model.java Pipe.java 
	  Mario.java Goomba.java Sprite.java Fireball.java Json.java
if %errorlevel% neq 0 (
	echo There was an error; exiting now.	
) else (
	echo Compiled correctly!  Running Game...
	java Game	
)
