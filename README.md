<h1>Backend (for now! :)) for stackoverflow clone. Where users can asks questions about various topics.</h1>

<h2>Technologies used:</h2>
<div align="center">
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117207493-49665200-adf4-11eb-808e-a9c0fcc2a0a0.png" alt="Hibernate" title="Hibernate"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192109061-e138ca71-337c-4019-8d42-4792fdaa7128.png" alt="Postman" title="Postman"/></code>
</div>


<h2>Endpoints</h2>
<img src="https://github.com/Karol011/AskMeAnything/assets/57830019/de1d7fa1-d88d-4d38-887c-497b450e0ea8" alt="Image 1">
<img src="https://github.com/Karol011/AskMeAnything/assets/57830019/e58665c9-b5df-4500-904d-cd5cea36da09" alt="Image 2">
<img src="https://github.com/Karol011/AskMeAnything/assets/57830019/7d239012-35d3-4836-ab37-58ec2c5c6479" alt="Image 3">

<h2>Project Roadmap</h2>

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



