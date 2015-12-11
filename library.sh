#!/bin/bash

if [ -z $1 ]
then 
	echo "./library.sh start\n./library.sh stop"

elif [ $1 = "start" ]
then
	echo "compiles server sources"
	javac src/fr/upem/library/server/*.java

	echo "compiles client sources"
	{
		javac src/fr/upem/library/client/*.java
	}&>/dev/null

	cd src/fr/upem/library/server/ 

	echo "rmic ..."
	{
	    rmic BookManager
		rmic BookReference
	    rmic User
		rmic LibraryManager
	}&>/dev/null

	echo "rmiregistry ..."
	rmiregistry&

    sleep 3
	cd ../server/


	echo "executes the library server ..."
	java -Djava.security.policy=securityPolicy.policy LibraryServer&

    sleep 5
	cd ../client/

	echo "executes the library client ..."
	java LibraryClient

	cd ../
    rm */*.class

elif [ $1 = "stop" ]

then
	pkill rmiregistry


fi