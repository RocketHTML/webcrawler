# Webcrawler
Introducing.... The Webcrawler Class 

I discovered that Java had a URL class, and so I built some classes and apps around that URL functionality. It lets you ping any URL, and it returns the HTML source code for the document it finds (assuming it finds HTML). My classes scan the HTML source code, looking for tags, reaping the innerHTML. 

Look at **News.java** and **Dictionary.java** for examples of how the Webcrawler class is used. 
 - The News app takes a query word from the user, and then returns a list of paragraphs it accrued from various sources. 
 - The Dictionary class pings webster's dictionary for a query word, and returns the most popular definitions.
 - Chat.java lets two people on seperate computers have an instant messenger conversation over the commandline.
This is possible because one of my classes (Doll.java) uses the Socket class. If you can follow the code, you'll see that it also uses the Properties class to find the computer's filetree information. If you edit my instant messenger code, you can make the instant messenger participants exchange filetree information over the connection, so that one computer can see and edit the files on some distant computer. Remote control!
