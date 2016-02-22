Censuses
=========

Censuses application...

Changed at 22/02/2016

[![Build Status](https://travis-ci.org/Arquisoft/censusesI2.svg?branch=master)](https://travis-ci.org/Arquisoft/censusesI2)

AUTHORS
=======

* Santiago Casero
* Alejandro Pazos
* Daniel Ortea

INSTRUCTIONS
============

In order for project to work on your local device you must edit the file src/main/java/es/uniovi/Dbupdater/util/Jdbc.java

In this file you must comment the current URL and remove the comment from the following URL so it will work.

COMMAND DETAILS
===============

When running the compiled program we have the following commands:

 -h,--help               shows help (This same information).
 
 -ic,--input type csv    Declares the type of input file as csv (Default is xlsx) .
 
 -od,--output type doc   Type of the output letters as doc (Default is txt) .
 
 -p,--parse <arg>        Parses the "arg" file .
 
 -test,--test <arg>      TextTest (prints in screen the test message, used mainly for testing puroposes)
 
 
