#!/bin/bash

if [ -z $1 ]
then 
	echo "./library.sh 	start"
	echo "stop"

elif [ $1 = "start" ]
then
	echo "compiles server sources ..."
	javac -d ./bin/ -cp /Users/martine/Library-RMI/bin/ src/fr/upem/library/reference/*.java src/fr/upem/library/element/*.java src/fr/upem/library/client/*.java src/fr/upem/library/library/*.java src/fr/upem/library/main/*.java

#/Users/martine/Library-RMI/src/fr/upem/library/main/ 

	cd bin
	echo "rmiregistry ..."
	rmiregistry&

    sleep 3
	cd ../src/


	echo "executes the library server ..."
	java -cp ./bin/:/Users/martine/Library-RMI/bin/ -Djava.rmi.server.codebase=file:///Users/martine/Library-RMI/bin/ -Djava.security.policy=/Users/martine/Library-RMI/securityPolicy.policy fr.upem.library.main.LibraryServer&

	echo "server ready ..."
    #sleep 4
	#cd ../client/

	#echo "executes the library client ..."
	#java LibraryClient

	#cd ../
    #rm */*.class

elif [ $1 = "stop" ]
then
	pkill rmiregistry

fi