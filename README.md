DemoWebShop Automation Testing Project

**Overview**
This project is a Selenium Automation Testing Framework  built to test core functionalities of an E-commerce web application (Demo Web Shop).  
The framework is implemented using Java, Selenium WebDriver, and TestNG,following automation best practices.

 This project focuses on:
- Category verification
- Data-driven testing
- Screenshot capture
- Reporting
- Reusable utility methods


 Tech Stack
- Programming Language: Java (JDK 8)
- Automation Tool: Selenium WebDriver
- Test Framework: TestNG
- Build Tool: Maven
- IDE: Eclipse
- Version Control: Git & GitHub

   
📂 Project Structure
│
├── src/main/java
│ └── Utilimp
│ ├── Base_test.java
│ ├── Csvdata.java
│ ├── Listener_implementation.java
│ ├── registerdataUtil.java
│ ├── ReportUtil.java
│ └── TakeScreen_shot.java
│
├── src/main/resources
│
├── src/test/java
│ └── categoryVerification
│ └── EcomTest.java
│
├── src/test/resources
│ ├── Screenshot
│ │ └── *.png
│ └── Testdata
│ └── Details.csv
│
├── reports
├── test-output
├── pom.xml

  Test Scenarios Covered
- Category navigation validation  
- Product verification  
- Data-driven test execution using CSV  
- Screenshot capture on failure  
- Test execution reporting  
- Listener implementation for TestNG

 Note:
 This project is created for "learning, practice, and interview demonstration purposes" .

