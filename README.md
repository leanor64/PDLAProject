# PDLA Project
Léa Norgeux et Cléa Esposito

## About the project
This project is an application allowing to beneficiaries to ask help from volunteers. All the functionnalities developped are explained in the report join to the code.

## How to compile and run

To **test** the core functions of the project :
- `mvn test`

To **compile** the project :
- `mvn package`

To **run** the project, you need to be in `YOUR-DIRECTORY/PDLAProject/` :
- `java -cp target/pdlaProject-0.0.1-SNAPSHOT.jar View.ViewMain`

To **compile and run** :
- `mvn package && java -cp target/pdlaProject-0.0.1-SNAPSHOT.jar View.ViewMain`

## Available commands
Once the initialization phase is done, you can :
- change your pseudo : `changepseudo`,
- logout : `logout`.