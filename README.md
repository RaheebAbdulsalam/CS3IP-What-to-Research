# CS3IP-What-to-Research
 <img src="https://github.com/RaheebAbdulsalam/CS3IP-What-to-Research/assets/99501966/c1cee2a4-225f-4e63-92d5-f2faf463259a" width:200  style="max-width=%100">

<h2>About the Tool </h2> 

What to research is designed to meet the needs of final-year Computer Science students’ specialisation projects. Ultimately, the tool will offer an all-encompassing service related to project selection, using student-specific preferences to generate appropriate project recommendations. Moreover, it will provide them with an effective supervisor selection opportunity, ensuring that the student gets the support needed for performing excellently in the chosen project. By adding additional features and developing an AI chatbot that generate more ideas, the student experience will be fundamentally transformed. 


<h2>Setup What-to-Research System</h2>
The following project will work on Eclipse and IntelliJ IDE. Though it is recommended to use IntelliJ Ultimate or the Community Edition of IntelliJ.

- To get IntelliJ go to: https://www.jetbrains.com/idea/

- To get Eclipse IDE for Enterprise Java and Web Developers: https://www.eclipse.org/downloads/packages/


You will also need to download an open-source software package that provides a local web server environment for testing and developing web applications, such as XXAMP which contains tools and utilities, such as phpMyAdmin (for managing databases).

- To get XXAMP go to: https://www.apachefriends.org/download.html


<h3> Clone the repository:</h3>

1. Open the command prompt or terminal on your computer, and navigate to the directory where you want to clone the repository.This can be done by typing "cd" followed by the path of the directory you want to navigate to. For example, if you want to navigate to the "Documents" folder, type "cd Documents" and press Enter.
2. Copy the URL of the repository you want to clone from GitHub. You can find the URL on the repository's page by clicking the green "Code" button as shown below.
  <img src="" width:100  style="max-width=%100">
3. In the command prompt or terminal, enter the following command: git clone (repository URL), replace (repository URL) with the URL of the repository you have copied, and press enter to run the command, and Git will begin cloning the repository to your local machine.

<h3> Database Configration:</h3>
1. Once you downloaded XXAMP you need to launch it and start the Apache and MySQL servers: In the XAMPP Control Panel, click on the "Start" button next to "Apache" and "MySQL" to start the web server and database server <img src="https://user-images.githubusercontent.com/99501966/227725750-9f4ab1e5-45f2-42e0-8e76-79b9b6488611.png" width:100 style="max-width=%100">
2. Open a web browser and navigate to http://localhost/phpmyadmin/. This will open the phpMyAdmin interface for managing MySQL databases.

3. To create a new database, click on the "New" button in the left-hand sidebar to create a new database. Enter a name for the database and select create.

4. Configure the database connection properties in the application.properties as shown below <img src="" width:100 style="max-width=%100">

5. You can replace what_to_research_db, myusername, and mypassword with the actual values for your database.

6. You will also have to replace the API KEY with your actual API key which you can get from: https://openai.com/blog/openai-api
 
<h3> Run the application:</h3>
  
1. Open IntelliJ IDEA and select "file" -> "open" ->. 
2. Navigate to the directory where the Spring Boot project is located, select it and click ok.
3. Once the project is loaded, navigate to the main class of your Spring Boot application.  
4. Right-click on the main class, select the correct Run Configuration, and IntelliJ IDEA will build the application and start it.
 <img src="" width:100  style="max-width=%50">

<h3> Access the application: </h3>

Once the application is running, open a web browser and navigate to http://localhost:8080. This should take you to the home page of the Spring Boot application.


