# Client Report API README

## Overview

This Spring Boot project serves as a backend system for managing client reports which inlcude client details, trade information, regulatory details, and internal contacts. The project includes multiple controllers, services, and a repository for data storage and retrieval. The application exposes various RESTful endpoints for performing CRUD operations and retrieving specific data based on different parameters.

## Table of Contents

- [Overview](#overview)
- [Key Steps](#key-steps)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [API Endpoints](#api-endpoints)
- [Data Conversion](#data-conversion)
- [Swagger Documentation](#swagger-documentation)
- [Exception Handling](#exception-handling)


## Project Structure

The project is organized into several packages, each serving a specific purpose:

- **controllers**: Contains classes responsible for handling incoming HTTP requests, processing them, and returning appropriate responses.

- **services**: Houses service classes that implement business logic and interact with repositories to fetch or manipulate data.

- **repositories**: Includes repository classes responsible for data access and storage. Uses JSON files as a data source.

- **mappers**: Houses mapping class for entities to dto.

- **entities**: Contains classes representing entities used in the database.

- **dto**: Contains classes used for data transfer to the UI.

- **helpers**: Contains utility classes for tasks such as CSV to DTO to JSON conversion. 

- **constants**: Contains a class to initialize constants used through-out the main java program

## Key Steps

### CSV File Ingestion:
The application begins by reading CSV files containing diverse client information.
Utilizes a CSV reader to parse and structure the raw data. The project includes a CSVDataConverter class responsible for converting CSV data to DTOs and then to JSON. This is utilized during the initialization of the JSON repository.
### Entity Representation:
Defines distinct entities such as ClientDetails, TradeDetails, and LegalDetails to accurately model the internal data structure.
### Entity to DTO Mapping:
Implements a mapping layer, leveraging a framework like MapStruct, to convert entities into DTOs.
Enables a clean separation between internal data representation and API exposure.
### JSON Serialization:
Employs Jackson, integrated with Spring, for seamless serialization of DTOs into JSON format.
Ensures compatibility and ease of communication with other systems.
### Repository Operations:
Defines repository methods for CRUD operations and data retrieval. At the core of the repository is the json files and algorithms such as binary search are implemented to search for client details.
### Service Layer:
Establishes a service layer to encapsulate business logic.
Orchestrates interactions with the repository, implementing additional validation and processing steps.
### API Controller:
Implements RESTful API endpoints using Spring controllers. API endpoints are tailored for querying and aggregating client data.
Incorporates parameters for flexible data retrieval, sorting, and aggregation.
Utilizes annotations like @RestController and @RequestMapping for streamlined HTTP request handling.
### Swagger Documentation:
Incorporates Swagger for API documentation, providing insights into available endpoints and their functionalities.
Enhances ease of use and integration for other development teams. Swagger is integrated into the project to provide interactive API documentation. Visit http://localhost:7070/swagger-ui/index.html to explore and test the available APIs.
### Integration for Other Teams:
The API becomes a pivotal integration point for teams like Management Information.
Offers comprehensive documentation for seamless integration, ensuring secure and controlled access to client-related information.
### Outcome:
The Client Information Management System not only centralizes client data but also provides an extensible and well-documented API, catering to the diverse needs of other teams for querying and aggregating critical client information.

## Dependencies

The project uses the following key dependencies:

- **Spring Boot**: Facilitates the development of a standalone, production-grade Spring-based application.

- **Springfox Swagger**: Enables the generation of API documentation using Swagger.

- **Jackson ObjectMapper**: Handles JSON serialization and deserialization.


### API Endpoints

The project exposes the following API endpoints:

#### Client Details: /client-details
#### Client Internal Contacts: /client-internal-contact
#### Client Legal Details: /client-legal-details
#### Client Trade Details: /client-trade-details
Refer to the Swagger documentation for detailed information on each endpoint, request parameters, and response formats.

### Exception Handling

Exception handling is implemented in controllers to provide meaningful error responses in case of issues. Exceptions are logged for debugging purposes.

---
---
---

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/iDPpP-d0)
# **Java API Assessment**

## **Introduction**
Dive into the world of API development using Java and SpringBoot. We're handing over a skeleton codebase; your challenge is to shape a top-notch API from it.

You can build any API of your choosing, but it must include the following:

1. At least one algorithm
1. Unit test at least one class
1. Store the data in a JSON file 
1. Exception handling 
1. Evidence of inheritance
1. Good use of HTTP Protocols - methods, request and response, have full CRUD operations supported 
1. Documentation

### **Learning Outcomes:**

By the end of this assessment, you should be able to:

1. **Design and Architect APIs**: Get to grips with the nitty-gritty of curating a top-quality API, focusing on data flow and endpoint interactions.
1. **Implement Best Practices**: Showcase your adherence to Java & SpringBoot coding standards, error handling, and optimal project structure.
1. **Code Integration**: Seamlessly combine your creations with the provided skeleton codebase.
1. **Exception Management**: Efficiently handle exceptions, ensuring your API remains sturdy and dependable.

Onward with this assessment, you're set for a deep dive into API development with Java and SpringBoot.

## **Design & Requirements**

### **Design Considerations:**
- **API Flow**: Map out your API's progression, from endpoints to their functionalities.

### **Requirements List:**
- **Core**: Make use of Java and SpringBoot.
- **End Points**: Ensure they are detailed and fully operational.
- **Error Handling**: Your API should handle mishaps gracefully and return informative feedback.

### **Learning Outcomes:**
- Acknowledge the pivotal role of a focused design in APIs.
- See firsthand how a detailed requirements list can pave the way for successful development.

## **Repository Management**

- **Consistent Commits**: Commit often, capturing your progress and thought process.
- **README**: Not just an afterthought. Fill it with the essence of your API, setup instructions, and other salient details.

### **Learning Outcomes:**
- Hone your skills in effective version control.
- Recognise the value of a well-curated repository.

## **Code Quality & Structure**

- **Best Practices**: Stick to Java and SpringBoot best practices and conventions.
- **Modularity**: Your code should be modular, reusable, and easily comprehensible.

#### **Learning Outcomes:**
- Craft clean, efficient, and maintainable code.
- Harness Java and SpringBoot to the fullest.

---

## Getting Started

- [Prerequisites](#prerequisites)

- [Setup](#setup)

### Prerequisites

Before you begin, make sure you have the following installed:

1. [JDK 17](https://learn.microsoft.com/en-gb/java/openjdk/download#openjdk-17) (or higher)

2. [Git](https://git-scm.com/downloads)

3. [Visual Studio Code](https://code.visualstudio.com/Download)
   1. [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
   2. [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)

Also make sure you have accounts for the following:

1. [GitHub](https://github.com/signup)

### Setup

#### 1. Clone the Repository

```sh
git clone [REPO_URL]
cd [REPO_NAME]
```

Replace [REPO_URL] with the link to your GitHub repository and [REPO_NAME] with the repository's name.

#### 2. Install Dependencies

Open a terminal at the root of the repo directory and run the following command to install the dependencies:

```sh
./mvnw clean dependency:resolve
```

If you are on a Windows machine, that will be:
```cmd
mvnw clean dependency:resolve
```

You should see console output similar to the following:

```sh
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.cbfacademy:api-assessment >--------------------
[INFO] Building api-assessment 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ api-assessment ---
[INFO] Deleting /Users/user/Dev/cbfacademy/java-api-assessment/target
...
[truncated output]
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.060 s
[INFO] Finished at: 2023-10-03T16:18:25+01:00
[INFO] ------------------------------------------------------------------------
```

#### 3. Running the Application

To start the API in VS Code, press `F5` or tap the 'Play' icon for the `api-assessment` app in the Spring Boot Dashboard.

Alternatively, to start the API from the terminal, run the following command:

```sh
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw spring-boot:run
```

You should see console output similar to the following (press `Ctrl + C` to exit):

```sh
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.cbfacademy:api-assessment >--------------------
[INFO] Building api-assessment 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ api-assessment ---
[INFO] Deleting /Users/gary/Dev/cbfacademy/java-api-assessment/target
[INFO] 
[INFO] >>> spring-boot:3.1.4:run (default-cli) > test-compile @ api-assessment >>>
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ api-assessment ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 0 resource from src/main/resources to target/classes
...
[truncated output]
...
2023-10-03T17:17:34.413+01:00  INFO 35536 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2023-10-03T17:17:34.751+01:00  INFO 35536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-10-03T17:17:34.756+01:00  INFO 35536 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-10-03T17:17:34.756+01:00  INFO 35536 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.13]
2023-10-03T17:17:34.777+01:00  INFO 35536 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-10-03T17:17:34.778+01:00  INFO 35536 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 364 ms
2023-10-03T17:17:34.898+01:00  INFO 35536 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2023-10-03T17:17:34.907+01:00  INFO 35536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-10-03T17:17:34.911+01:00  INFO 35536 --- [  restartedMain] com.cbfacademy.apiassessment.App         : Started App in 0.643 seconds (process running for 0.786)
```

Open your browser and navigate to `http://localhost:7070`.

## **Deliverables**

Ensure that your work is merged to the `main` branch of your GitHub repository by the specified deadline (original or extended). Your solution will assessed based on its state *at that point*; any later commits will **not** be taken into account.

## FAQs

- Q: How can I process JSON in Java?
    
    A: There are a number of open-source packages that you can use to manipulate JSON. We recommend [Gson](https://github.com/google/gson), but you can also investigate alternatives like [json-simple](https://github.com/cliftonlabs/json-simple) or [Jackson](https://github.com/FasterXML/jackson-databind/).

- Q: Can I use another IDE I'm more familiar with instead of VS Code, like IntelliJ or Eclipse?

    A: You can if you wish, but only VS Code is formally supported by CBF Academy staff, so you do so at your own risk.

## Top Tips

- :camera_flash: Commit frequently and use meaningful commit messages. A granular, well-labelled history becomes an increasingly valuable asset over time.
- :cactus: Use feature branches. Build the habit of isolating your changes for specific tasks and merging them into your default branch when complete.
- :vertical_traffic_light: Use consistent naming conventions. Choose easily understandable names and naming patterns for your classes, functions and variables.
- :triangular_ruler: Keep your code tidy. Using the built-in formatting of VS Code or other IDEs makes your code easier to read and mistakes easier to spot.
- :books: Read the docs. Whether via Intellisense in your IDE, or browsing online documentation, build a clear understanding of the libraries your code leverages.
- :calendar: Don't wait until the last minute. Plan your work early and make the most of the time available to complete the assessment and avoid pre-deadline palpitations.
- :sos: Ask. :clap: For. :clap: Help! :clap: Your mentors, instructors and assistants are literally here to support you, so *make use of them* - don't sit and struggle in silence.

Best of luck! Remember, it's not just about the destination; it's the journey. Happy coding! 🚀