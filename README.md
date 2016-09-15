# Webcrawler
Introducing.... The Webcrawler Class 

I discovered Java had a URL class, and so I built some classes and apps around that functionality. It lets you hit any URL and it returns the HTML source code for the document it finds (assuming it finds HTML). The classes merely scan the HTML looking for tags, processing the innerHTML (for all my Javascript developers). 

Look at **News.java** and **Dictionary.java** for examples of how the webcrawler class is used to make webcrawling simple. 
 - The News app takes a query word from the user, and then returns about a paragraph of information from various sources. 
 - The Dictionary class hits webster's dictionary for the query word, and returns the most popular definition.
 
# Easter Egg Time 
If you look closer, you'll see that the webcrawler class inherits from the **Doll** class. 
***The Doll class is the true treasure.***
There is an app in here called *Chat.java* which lets two people on seperate computers have an instant messenger chat.
This is because doll is using Java's Socket class. If you can follow the code, you'll see that it is also using the Properties class to get filetree information about the computer that is running it. If you hack the instant messenger bit, you can have the chatting dolls exchange sensitive information via instant messenger, and if you go further, you can control a remote computer from your laptop (so long as a doll is installed on both).  
Just look at the code... *this class throws itself as an exception.* Because some people just want to see the world burn...
