# Calling an API and parsing results

Calling an API in java, among other languages, is fairly simple.

Something Java does not have by default, which languages such as javascript, python do, is a nice JSON parser.

Jackson library is very nice and quite simple to use for parsing JSON in Java.

To build jar: `mvn clean install`

To run tests: in terminal, navigate to this project and run `java -cp target/alteryx.jar org.example.Main 2`

where the `2` at the end is the `limit` desired. This will limit the top results for number of comments for articles


It looks like I may need to include better parsing, some of the titles look like the encoding is incorrect.