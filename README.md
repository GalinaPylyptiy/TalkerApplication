# Simple Social Media Application

This is a simple social media application that allows users to create and manage posts, follow other users, and interact with posts by liking them.

## Features

- Create, read, update, and delete posts.
- Follow and unfollow other users.
- Like posts created by other users.

## Technologies Used

- Java
- Spring Boot
- Hibernate
- PostgreSQL

## Getting Started

Follow the steps below to set up and run the application locally.

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- PostgreSQL Database

### Setup

1. Clone the repository:

   ```sh
   git clone https://github.com/GalinaPylyptiy/TalkerApplication.git
   cd TalkerApplication
   
2. Create a PostgreSQL database and update the database configuration in src/main/resources/application.properties.
3. Build the application using Maven: mvn clean install

###Running the Application

Run the application using Maven: mvn spring-boot:run

The application will start, and you can access the endpoints at http://localhost:8080.

###API Endpoints

Posts

POST /post - Create a new post.
GET /post/{id} - Get a post by ID.
PUT /post/{id} - Update a post.
DELETE /post/{id} - Delete a post.
POST /post/{postId}/like/{userId} - Like a post.
Users

POST /users - Create a new user.
GET /users/{id} - Get a user by ID.
PUT /users/{id}/{newName} - Update a user's name.
DELETE /users/{id} - Delete a user.
POST /users/{followerId}/follow/{followedId} - Follow a user.
POST /users/{followerId}/unfollow/{followedId} - Unfollow a user.
Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

###Feedback on using ChatGPT to complete the task:
-It appeared to be not so easy to complete this task using chatGPT, I had a lot of difficulties with configuration and code quality,
I had to rephrase my questions and requests, because the answer was not always accurate and not responded to my query.
-It took me almost 8 hours to complete this task.
-The code did not run appropriate after I used the suggested by ChatGPT code, I had to do a lot of additional configurations and additional edits to make it run as it was expected.
Mostly, I had to change additional configuration changes to make it run;
- There were some difficulties and challenges I faced, while working on this task: 
- The responses I got were not always what I wanted to get, that is why I had to rephrase my requests several times;
- The suggestions of chat to fix some bugs and errors I had  were not always worked, and I had to spend 
a lot of time asking for the way to fix some errors in my code;
- To complete the task I mostly used such prompts as attach my code to my request, ask follow up questions, ask for examples and for being more specific;




