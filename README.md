# Webcrawler
Introducing.... The Webcrawler Class 

I discovered Java had a URL class, and so I built some classes and apps around that functionality. It lets you hit any URL and it returns the HTML source code for the document it finds (assuming it finds HTML). The classes merely scan the HTML looking for tags, processing the innerHTML (for all my Javascript developers). 

Look at **News.java** and **Dictionary.java** for examples of how the webcrawler class is used to make webcrawling simple. 
 - The News app takes a query word from the user, and then returns about a paragraph of information from various sources. 
 - The Dictionary class hits webster's dictionary for the query word, and returns the most popular definition.
 
# Easter Egg   
***The Doll class***
Chat.java lets two people on seperate computers have an instant messenger chat over the commandline, using my Doll class.
This is possible because the Doll class uses the Socket class. If you can follow the code, you'll see that it is also using the Properties class to get the computer's filetree information. If you hack my instant messenger code a bit, you can make the chatting dolls exchange filetree information over the connection, so that one computer can see and edit the files on some distant computer. 
