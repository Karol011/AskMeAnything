<h1>Backend (for now! :)) for stackoverflow clone. Where users can asks questions about various topics.</h1>

![image](https://github.com/Karol011/AskMeAnything/assets/57830019/de1d7fa1-d88d-4d38-887c-497b450e0ea8)
![image](https://github.com/Karol011/AskMeAnything/assets/57830019/e58665c9-b5df-4500-904d-cd5cea36da09)
![image](https://github.com/Karol011/AskMeAnything/assets/57830019/7d239012-35d3-4836-ab37-58ec2c5c6479)

Project roadmap:

**Phase 1: Setup and User Registration**

  ✔  Set up the development environment.  
  ✔Create a Spring Boot project.    
  ✔Create a user entity with fields: id, username, password, email, and display name.  
  ✔setup h2 database connection  
  ✔setting up userController, userservice  
  ✔implement user crud operations  
  ✔Implement user registration functionality.  
  ✔ Store user data in a database.  
  ❌Implement password recovery or reset functionality.

**Phase 2: Question and Category Management**

  ✔ Create entities for questions and categories.    
  ✔ Implement adding and deleting questions.    
  ✔Develop a form for users to submit questions.    
  ✔Implement question deletion.    
  ✔Implement category management.    
  ✔Create a category entity with fields: id, name, and optional parent category.    
  ✔Allow users to assign questions to categories, creating new categories if needed.  
  
**Phase 3: Question Listing and Searching**

  ✔ Develop a page to display a list of questions.  
  ✔Allow users (both logged-in and anonymous) to browse questions by category.  
  ✔Show questions within each category.  
  ✔Implement question details view.  
  ✔Display the question text, asker's name, and date.   
  ⌛Show answers in order of positive ratings.  
  ⌛Allow users to view comments on answers.  
  ⌛Provide a form for logged-in users to submit answers and comments.  
  ⌛Implement upvoting/downvoting answers.  
  ⌛Enable question editing and deletion for the user who posted the question.

**Phase 4: User Activity and Points System**

  ⌛Implement user activity tracking.  
  ⌛Create entities for user activity, such as questions asked, answers given, and comments made.  
  ⌛Track user points based on activities.  
  ⌛Implement popular questions and users.  
  ⌛Sort questions by popularity (e.g., views or ratings) over a specific period.  
  ⌛Show a list of top-rated users.  
  ⌛Allow users to set avatars.  
  ⌛Add user roles (e.g., Administrator and User).  
  ⌛Define admin privileges, such as question and answer deletion, category management, etc.  

**Phase 5: Additional Enhancements**

  ⌛Implement a tagging system for questions.  
  ⌛Allow users to add tags to questions.  
  ⌛Ensure data validation for user inputs.  
  ⌛Validate user-submitted data for security and data integrity.  
  ⌛Create a responsive and user-friendly UI.  
  ⌛Enhance the user interface for a better user experience.  
  ⌛Optimize performance and add caching as needed.  
  ⌛Implement additional features and improvements as required.  

**Phase 6: Testing and Deployment**

  ✔Perform thorough testing, including unit   
  ⌛tests, integration tests, and user acceptance testing.  
  ⌛Deploy the application to a production environment.  
  ✔Configure server and database settings.  
  ⌛Monitor and maintain the deployed system.



