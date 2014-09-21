## Where2go Server

This is the server for the Hack the North Project, Where2go. The server is to allow user to store their trips and retrieve them via a unique trip code. Also the sever provide support for some APIs used.


## APIs:
1. Creating a new trip on the server:
/api/user/sync  
Request Type: POST	      	
ContentType:application/json
Response DataType: application/json	
ResponseBody: The posted trip object with id and creationTime filled in

2. Getting a trip by its Id
/api/user/sync/{id}
Request 
Type: GET
Response DataType: application/json	
ResponseBody: The requested trip

3. Updating a trip
/api/user/sync/{id}
Request Type: PUT	      	
ContentType:application/json
Response DataType: application/json	
ResponseBody: The updated trip

4. See others’ trip in a given city
/api/user/recommend?city={cityName}&trip={tripId}
Request Type: GET		
Payload: 
city: the name of the city; 
trip: (optional) the id of the current trip, if trip is new, do not include this parameter
Response DataType: application/json	
ResponseBody: JSON array of the same city others users have planned. Max-length of the JSON array is 10. Min-length is 0; 
