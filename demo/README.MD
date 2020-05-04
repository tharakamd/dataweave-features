# Datawave features comparison demo

## How to run

* Change the value of  **groovy_file_path** property in the application.properties file to the groovy script location in your PC.
* Run the main method in DemoApplication class.
* Send POST requests to localhost:8080 with the request body as required and the script name as a path param.
* No need to restart server after adding or editing a groovy script.
### Example CURL request

``` curl
curl --location --request POST 'localhost:8080/get_json_param' \
--header 'Content-Type: application/json' \
--data-raw '{
	"hello":"world"
}'
``` 