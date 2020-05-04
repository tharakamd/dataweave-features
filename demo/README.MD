# Datawave features comparison demo

## How to run

* Replace the **groovy_file_path** property in the application.properties file with the groovy script location.
* Send POST requests to localhost:8080 with the required body and the script name as path param.
* No need to restart server after adding or editing a groovy script.
### Example CURL request

``` curl
curl --location --request POST 'localhost:8080/get_json_param' \
--header 'Content-Type: application/json' \
--data-raw '{
	"hello":"world"
}'
``` 