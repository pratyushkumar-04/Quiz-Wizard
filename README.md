<h1 align="center">🧙‍♂️ Quiz-Wizard</h1>
<p align="center">
  <b>A full-stack Quiz Application built with Angular, Spring Boot & MySQL</b><br/>
  <i>Test your knowledge. Compete. Learn.</i>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Frontend-Angular%2015-DD0031?style=for-the-badge&logo=angular&logoColor=white"/>
  <img src="https://img.shields.io/badge/Backend-Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Auth-JWT%20Secured-orange?style=for-the-badge&logo=jsonwebtokens&logoColor=white"/>
</p>

---

## 📜 Overview

**Quiz-Wizard** is a dynamic quiz management platform featuring **role-based access**, **secure authentication**, and **real-time leaderboards**.  
It enables users to take quizzes by category, review detailed results, and track performance — while admins manage content, users, and scores.

---

## 🚀 Features

### 👤 User Features
- 🔐 **Login / Register** using JWT authentication  
- 🧭 **Select Quiz Category** — attempt 10 random questions per quiz  
- 📊 **View Previous Attempts** with per-question feedback  
  > *Example:* “✅ Correct Answer: Option A — You Selected: Option B”  
- 🏆 **Leaderboard** showing all user rankings  
- ⚙️ **Profile Settings** — update username, password, and details  

### 🛠️ Admin Features
- 👥 **Manage Users** — view, edit, or delete accounts  
- 🧩 **Manage Categories** — create or remove categories  
- 📝 **Add Questions** — associate them with categories  
- 📈 **Access Leaderboard** — track top performers globally  

---

## 🧩 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Frontend** | Angular 15 |
| **Backend** | Spring Boot |
| **Database** | MySQL |
| **Authentication** | JWT (JSON Web Tokens) |
| **Build Tools** | Maven (Backend) / Angular CLI (Frontend) |

---
## 📁 Project Structure
*   **`Quiz-Wizard/`**
    *   **`frontend/`** # Angular Frontend Application
        *   `src/`
        *   `package.json`
        *   `angular.json`
    *   **`backend/`** # Spring Boot Backend Application
        *   `src/main/java/`
        *   `pom.xml`
        *   `application.properties`
    *   `README.md`


---

## ⚙️ Installation & Setup

### 🧱 Prerequisites
Ensure you have the following installed:
- Node.js (v16+)
- Angular CLI
- Java 17+
- Maven
- MySQL Server

---

### 🖥️ Backend Setup (Spring Boot)

1. Navigate to the backend folder:
   ```bash
     cd backend
3. Update your MySQL credentials in application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/quizwizard
  spring.datasource.username=root
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
3.Run the backend:
  mvn spring-boot:run
The backend will start at 👉 http://localhost:8080

### 💻 Frontend Setup (Angular)

  1. Navigate to the frontend folder:
     ``` bash 
       cd frontend
  2. Install dependencies:
       ``` bash
          npm install
  3. Run the app:
       ``` bash
         ng serve
  4. Open your browser at 👉 http://localhost:4200

#### 🔒 Authentication Flow

 - Secure JWT-based login for both users and admins
 - Tokens stored in client-side storage for session management
 - Role-based route protection implemented in Angular
   
#### 🧠 Quiz Logic
    
  - Each quiz randomly fetches 10 questions from the chosen category
  - On submission:
      - Responses and scores are stored in the database
      - User can review each attempt later
  - Leaderboard auto-updates after each attempt

 #### 📊 Leaderboard
 
  - Displays all user rankings
  - Updates in real-time with every quiz submission
  - Accessible to both Users and Admins

#### 🌱 Future Enhancements
    
   ⏱️ Timed quizzes
   📚 Difficulty-level selection
   📧 Email verification & password reset
   📊 Advanced analytics for admin dashboard

#### 🤝 Contributing

  Pull requests are welcome!
  To contribute:
    - Fork this repository
   - Create a new branch:
  ```bash
  git checkout -b feature/your-feature-name
  ```
   - Commit and push your changes
   - Open a pull request 🎉

#### 🧑‍💻 Author
  <p align="left"> <a href="https://github.com/pratyushkumar-04"> <img src="https://img.shields.io/badge/GitHub-Profile-black?style=flat-square&logo=github"/> </a> </p>

<p align="center"> Made with ❤️ using <b>Angular</b> & <b>Spring Boot</b> </p> ```
     


