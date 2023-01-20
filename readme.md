Create a simple Spring Boot service, that is able to calculate any possible land route from one country to another. The objective is to take a list of country data in JSON format and calculate the route by utilizing individual countries border information.

In order to be efficient I will use Breadth First Search for finding routes.

Instructions on how to build and run the application

1. To build the application you need to use the Maven command: mvn clean install. (Maven needs to be installed on the machine)
2. Building the application will create a target folder which will contain the routing.war file.
3. Now you need to deploy the war file to a web server. I use Tomcat.
   1. Go to Tomcat home directory. Go inside bin directory and execute startup.sh (Linux) or startup.bat (Windows), in order to start Tomcat. It will run by default on port 8080. You can visit http://localhost:8080 to see the Tomcat home page.
   2. To deploy the application you can copy the war inside the webapps directory and it will be deployed automatically. Another way to deploy it would be to access http://localhost:8080/manager/html and use Tomcat's interface to deploy the war.
4. With the application deployed successfully, you can test it using your browser.
   1. Open a browser.
   2. Test different routes and visualize the JSON response:
      1. http://localhost:8080/routing/CZE/ITA
      2. http://localhost:8080/routing/PRT/ROU
      3. http://localhost:8080/routing/IRL/IRN (HTTP Error 400 because IRL borders only GBR and vice versa)