# Selenium-Java-Cucumber-Custom-Framework

A test framework that utilizes Java and Cucumber capabilities to integrate key Selenium and TestNG features that can be used to create web-based automation scripts.

**Key Features**

- Supports Parallel test execution and thread management
- Run the parallel Scenario on feature file
- Cucumber Report
- Extent Report
- Allure Report
- Send Mail after the run test (Report information and HTML file attachment)
- Write Log to file
- Record video and Screenshot test case
- Read data test from Excel file (xlsx, csv, json,...)
- Base function in the package: utils, helpers
- Read data test from Json file
- Main keyword is WebUI
- Sample test feature
- Use DataFaker and JavaFaker to generate data
- Javadoc for this source

# Table of contents

- [Installation](#installation)
- [Built With](#built-with)
- [Contributing](#contributing)

# Installation

Clone the repo from GitHub using below command

```git
git clone https://github.com/NhamManhHung/Selenium-Java-Cucumber-Custom-Framework.git
```

Clean and compile the maven project using below commands

```maven
mvn clean
mvn compile
```

# Running the tests

**From Command Prompt**

```maven
mvn test
```

or using

```maven
mvn clean test
```

Parameters can be overridden as follows or updated directly on application.properties file

```maven
mvn clean test -Dbrowser=chrome
```

**Report Location**

1. Navigate to the "target" folder
2. Execute the below allure command

```maven
allure serve -h localhost
```

# Built With

- Java
- Cucumber
- Selenium WebDriver
- TestNG
- Maven
- Allure Reports
- Hamcrest
- Lombok

# Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you want to change.
