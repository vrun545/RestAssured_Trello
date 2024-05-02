
# Trello API Testing Framework

This project is an API testing framework built using Java, REST Assured, Maven, and TestNG for testing Trello's API. It provides a comprehensive suite of tests covering various scenarios, ensuring the reliability and functionality of interactions with Trello's API endpoints.

## Key Features

- **REST Assured Framework**: Utilizes the powerful REST Assured framework to streamline API testing in Java.
- **TestNG Approach**: Implements TestNG for test execution and reporting, allowing for easy organization and management of tests.
- **Multiple Independent Tests**: The framework includes multiple independent tests, each verifying different aspects of Trello's API functionality.
- **Scenario Validation**: Covers different scenarios for GET, POST, PUT, and DELETE requests, ensuring thorough validation of API endpoints.
- **Class-based Requests**: Each API request is encapsulated in a separate class, promoting modularity and maintainability.
- **Server Availability Check**: Before executing each test, the framework verifies server availability with a ping request.
- **Logging via Log4j**: Implements logging functionality using Log4j to capture and analyze test execution details.
- **Valid Assertions**: Includes valid assertions in all tests to ensure accurate validation of API responses.
- **Negative Scenario Validation**: Validates negative scenarios to ensure robust error handling and graceful degradation.
- **Payload Separation**: Avoids keeping payloads in test scripts to maintain separation of concerns and enhance readability.
- **Extent Reports**: Implements Extent reports for comprehensive and visually appealing test reports.

## Usage

1. Clone the repository to your local machine.
2. Ensure you have Java and Maven installed.
3. Navigate to the project directory.
4. Run `mvn test` to execute the test suite.
5. Review the generated Extent reports for detailed test results.

## Contributing

Contributions are welcome! Whether it's bug fixes, enhancements, or additional test cases, feel free to fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE), allowing for both personal and commercial use with proper attribution.

## Acknowledgements

Special thanks to the creators and maintainers of REST Assured, TestNG, Log4j, and Extent Reports for providing the tools and libraries necessary for this project.

---

Feel free to customize the README further with specific instructions, installation guides, or any other relevant information for users and contributors!
