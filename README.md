# Run all tests
mvn test

# Run with a specific profile (e.g., 'dev')
mvn test -Pdev

# Run a specific TestNG suite
mvn test -DsuiteXmlFile=src/test/resources/testng.xml


Right-click testng.xml → Run.

Or use the Maven lifecycle in your IDE.

