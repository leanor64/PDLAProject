# PDLA Project
Léa Norgeux et Cléa Esposito

## About the project
This project is an application allowing to beneficiaries to ask help from volunteers. All the functionnalities developped are explained in the report join to the code.

## How to compile, run and test from a terminal

To **test** the core functions of the project :
- `mvn test`

To **compile** the project :
- `mvn compile`

To **compile and test** the project :
- `mvn package`

To **run** the project, you need to be in `YOUR-DIRECTORY/PDLAProject/` :
- `mvn exec:java -Dexec.mainClass="Controller.MainController"`

To **compile and run** from a terminal :
- `mvn compile && mvn exec:java -Dexec.mainClass="Controller.MainController"`