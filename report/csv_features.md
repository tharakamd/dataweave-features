## Sending csv through email

#### Input

``` csv
orderId,name,units,pricePerUnit
1,T-shirt,2.0,10
2,Jacket,4.15,5
```

#### Output

``` json
[
  {
    "name": "T-shirt",
    "orderId": "1",
    "pricePerUnit": "10",
    "units": "2",
    "totalPrice": 20
  },
  {
    "name": "Jacket",
    "orderId": "2",
    "pricePerUnit": "5",
    "units": "4.15",
    "totalPrice": 20.75
  }
]
```

https://github.com/wso2/integration-studio-examples/tree/master/migration/mule/sending-a-csv-through-email-using-smtp


## Legacy Modernization

#### Input
``` xml
<ord:putShippingOrder>
         <shippingId>1234</shippingId>
         <billingAddress>
            <name>WSO2</name>
            <line1>100 Geary St</line1>
            <line2>Level 4</line2>
            <city>San Francisco</city>
            <stateOrProvinceCode>CA</stateOrProvinceCode>
            <countryCode>USA</countryCode>
            <postalCode>94108</postalCode>
         </billingAddress>
         <shippingAddress>
            <name>WSO2</name>
            <line1>100 Geary St</line1>
            <line2>Level 4</line2>
            <city>San Francisco</city>
            <stateOrProvinceCode>CA</stateOrProvinceCode>
            <countryCode>USA</countryCode>
            <postalCode>94108</postalCode>
         </shippingAddress>
         <order>
            <orderItem>
               <merchantSKU>1234</merchantSKU>
               <quantity>500</quantity>
               <price>1.99</price>
            </orderItem>
            <orderItem>
               <merchantSKU>6789</merchantSKU>
               <quantity>1500</quantity>
               <price>9.59</price>
            </orderItem>
            <orderItem>
               <merchantSKU>9998</merchantSKU>
               <quantity>5000</quantity>
               <price>3.00</price>
            </orderItem>
         </order>
</ord:putShippingOrder>
```

#### Output

``` csv
1234.0,500.0,1.99,WSO2,100 Geary St Level 4,San Francisco,CA,USA,94108.0,WSO2,100 Geary St Level 4,San Francisco,CA,USA,94108.0,1234.0
6789.0,1500.0,9.59,WSO2,100 Geary St Level 4,San Francisco,CA,USA,94108.0,WSO2,100 Geary St Level 4,San Francisco,CA,USA,94108.0,1234.0
9998.0,5000.0,3.00,WSO2,100 Geary St Level 4,San Francisco,CA,USA,94108.0,WSO2,100 Geary St Level 4,San Francisco,CA,USA,94108.0,1234.0

```

https://github.com/wso2/integration-studio-examples/tree/master/migration/mule/legacy-modernization


## Implementing an Exception Strategy

#### Input

``` csv
orderId,name,pricePerUnit,units
1,T-shirt,25.0,2
2,Jacket,40.5,3
```

#### Output

``` json
 [
   {
     "orderId": "1",
     "name": "T-shirt",
     "pricePerUnit": "25.0",
     "units": "2"
   },
   {
     "orderId": "2",
     "name": "Jacket",
     "pricePerUnit": "40.5",
     "units": "3"
   }
 ]
```

https://github.com/wso2/integration-studio-examples/tree/master/migration/mule/implementing-a-choice-exception-strategy


## Import Contacts Into Salesforce

#### Input

``` csv
id,firstname,surname,phone,email
1,John,Doe,096548763,john.doe@texasComp.com
2,Jane,Doe,091558780,jane.doe@texasComp.com
```

#### Output

``` json
{
	"fieldAndValue": {
		"records": [
			{
				"FirstName": "John",
				"LastName": "Doe",
				"Email": "john.doe@texasComp.com",
				"Phone": "096548763",
				"attributes": {
					"type": "Contact",
					"referenceId": "1"
				}
			},
			{
				"FirstName": "Jane",
				"LastName": "Doe",
				"Email": "jane.doe@texasComp.com",
				"Phone": "091558780",
				"attributes": {
					"type": "Contact",
					"referenceId": "2"
				}
			}
		]
	}
}
```

https://github.com/wso2/integration-studio-examples/tree/master/migration/mule/import-contacts-into-salesforce

## Importing a csv file into mongo-db

#### Input

``` csv
First,User,061787878,First@galle.com
ABC,User,0617998989,abc@galle.com
DEF,User,076144123,def@galle.com
```

#### Output

``` json
{
	"customerdetails": [
		{
			"Customer": {
				"firstname": "First",
				"surname": "User",
				"phone": "061787878",
				"email": "First@galle.com"
			}
		},
		{
			"Customer": {
				"firstname": "ABC",
				"surname": "User",
				"phone": "0617998989",
				"email": "abc@galle.com"
			}
		},
		{
			"Customer": {
				"firstname": "DEF",
				"surname": "User",
				"phone": "076144123",
				"email": "def@galle.com"
			}
		}
	]
}
```

https://github.com/wso2/integration-studio-examples/tree/master/migration/mule/Importing-a-csv-file-into-mongo-db