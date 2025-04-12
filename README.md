# API Test Automation Framework

This project is a basic API Test Automation Framework built with **Java**, **Rest-Assured**, and **Cucumber**. It targets the endpoints of a hotel booking application and demonstrates structured, maintainable, and scalable API test automation practices.

## 🔗 Application Under Test

- 📘 Swagger Documentation:
    - [BookingService API](https://booking-service-api.azurewebsites.net/swagger/index.html)

---

## 🧰 Tech Stack

- **Java 21**
- **Maven** for dependency management
- **Rest-Assured** for API testing
- **Cucumber** for BDD-style test definition
- **JUnit** as test com.dmytrohont.test.runner
- **SLF4J + Logback** for logging

---

## 📚 Useful Commands
| Maven Command         | Description                     |
|-----------------------|---------------------------------|
| `mvn clean test`      | Run all Cucumber tests          |
| `mvn verify`          | Run tests and verify lifecycle  |
| `mvn dependency:tree` | View dependency hierarchy       |

**CLI run**
To run tests from the command line, execute the following command:

```mvn exec:java                                  ^
    -Dexec.classpathScope=test                 ^
    -Dexec.mainClass=io.cucumber.core.cli.Main ^
    -Dexec.args="--glue com.dmytrohont.test --tags @Smoke"
   ```
This command will run all Cucumber tests with the `@Smoke` tag, using the `com.dmytrohont.test` package as the glue code.


