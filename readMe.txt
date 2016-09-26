readMe
------

Information about running the application
-----------------------------------------

The code is written in java (sdk version 1.8). Please compile before running the application. Below is the command to compile the java application
	javac *.java

Note: Please use -d to direct the output compiled files to different folder.

The application can be run using the below command.
	java ApplicationRunner corpusfile.txt >> output.txt

The application expects the corpus(training) file name as mentioned above as argument to execute the application. 

If you want to test for different sentence, you may need to change the sentence value ApplicationRunner.java file at line 11, compile the application and run.

Information about the output (Please remove word wrap if it is selected in your text editor to view as table format)
----------------------------

The output is showing as below

Analysis of Sentence S1
-----------------------
Count of Bi-grams without Smoothing (Table)
Probability of Bi-grams without Smoothing (Table)
Probabiltiy of Sentence without Smoothing

Count of Bi-grams with Add One Smoothing (Table)
Probability of Bi-grams with Add One Smoothing (Table)
Probabiltiy of Sentence with Add One Smoothing

Count of Bi-grams with Good Turing Smoothing (Table)
Probability of Bi-grams with Good Turing Smoothing (Table)
Probabiltiy of Sentence with Good Turing Smoothing

Analysis of Sentence S2
-----------------------
Count of Bi-grams without Smoothing (Table)
Probability of Bi-grams without Smoothing (Table)
Probabiltiy of Sentence without Smoothing

Count of Bi-grams with Add One Smoothing (Table)
Probability of Bi-grams with Add One Smoothing (Table)
Probabiltiy of Sentence with Add One Smoothing

Count of Bi-grams with Good Turing Smoothing (Table)
Probability of Bi-grams with Good Turing Smoothing (Table)
Probabiltiy of Sentence with Good Turing Smoothing

Sample output file for the given sentences is attached for your reference


