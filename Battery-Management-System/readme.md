# Battery Management System
Welcome to the Battery Management System! This system provides functionality for managing batteries and their details, such as voltage, temperature, and current. It allows you to associate batteries with vehicles, add battery details, and retrieve battery data within specified time ranges.

## Features
* Add a new vehicle
* Add a new battery to a vehicle
* Delete a battery
* Save battery details from a JSON file
* Save battery details (voltage, temperature, current) for a specific battery
* Retrieve all battery details for a specific battery
* Retrieve battery voltage data for a specific battery
* Retrieve battery temperature data for a specific battery
* Retrieve battery current data for a specific battery
* Retrieve battery details within a specified time range


## Installation & Getting Started
To run the Battery Management System, follow these steps:

1. Clone the project repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Configure your database connection settings in the application.properties file.
4. Run the application.


## API Endpoints
Once the application is up and running, you can use the following API endpoints to interact with it:

### Vehicle Management
* Add a New Vehicle
````
Endpoint: POST /vehicle/add
Description: Add a new vehicle to the system.
JSON body: 
{
  "name": "ElectricCar",
  "modelNo": "EV2023",
  "manufactureYear": 2023
}

````

### Battery Management
* Add a New Battery to a Vehicle
````
Endpoint: POST /battery/add?vehicleId={vehicleId}
Description: Add a new battery to a specific vehicle.
JSON body:
{
  "capacity": 5000,
  "type": "Lithium-ion",
  "warranty": 3
}

````

* Delete a Battery
````
Endpoint: DELETE /battery/{batteryId}
Description: Delete a battery from the system based on its ID.
````

* Save Battery Details from JSON file
````
Endpoint: POST /battery/{batteryId}/save-all-data
Description: Read battery details data from a JSON file, convert it into BatteryDetails, and add the data to the database for a specific battery.
````

* Save Battery Details (Voltage, Temperature, Current)
````
Endpoint: POST /battery/{batteryId}/save-details
Description: Save battery details, including voltage, temperature, current, and time, for a specific battery.
JSON body:
{
  "current": 11,
  "voltage": 47.5,
  "temperature": 31.5,
  "time": "2023-08-21T11:01:00Z"
}
````

* Retrieve All Battery Details
````
Endpoint: GET /battery/{batteryId}/all-details
Description: Retrieve all battery details for a specific battery.
````

* Retrieve Battery Voltage Data
````
Endpoint: GET /battery/{batteryId}/voltage
Description: Retrieve battery voltage data for a specific battery.
````

* Retrieve Battery Temperature Data
````
Endpoint: GET /battery/{batteryId}/temperature
Description: Retrieve battery temperature data for a specific battery.
````

* Retrieve Battery Current Data
````
Endpoint: GET /battery/{batteryId}/current
Description: Retrieve battery current data for a specific battery.
````

* Retrieve Battery Data within a Time Range
````
Endpoint: GET /battery/{batteryId}/time-range/{startTime}/{endTime}
Description: Retrieve battery details within a specified time range for a specific battery.
Example url: http://localhost:8080/battery/1/time-range/2023-08-21%2009:10:00/2023-08-21%2010:00:00
````

## API 

````
POST    http://localhost:8080/vehicle/add
POST    http://localhost:8080/battery/add?vehicleId={vehicleId}
DELETE  http://localhost:8080/battery/{batteryId}
POST    http://localhost:8080/battery/{batteryId}/save-all-data
POST    http://localhost:8080/battery/{batteryId}/save-details
GET     http://localhost:8080/battery/{batteryId}/all-details
GET     http://localhost:8080/battery/{batteryId}/voltage
GET     http://localhost:8080/battery/{batteryId}/temperature
GET     http://localhost:8080/battery/{batteryId}/current
GET     http://localhost:8080/battery/{batteryId}/time-range/{startTime}/{endTime}
````

## Technology Stack
* Java
* Spring Boot
* Spring Data JPA
* MySQL Database