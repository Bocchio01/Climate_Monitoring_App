# Climate Monitoring App

Simple university project for visualizing and monitoring climate data.

## Project requirements

All the project requirements are listed in the [Climate monitoring Request](./Climate%20monitoring%20Request.pdf) file.

Basically, the project consists of two main areas:

- An open access area where the user can see the current climate data and the historical data about a specific location.
- A private area where only the registered operators can access and manage the climate data.

This means that the app had to implement a login system and a database to store the climate data.

As for the given requirements, database has been avoided and only a local csv file has been used to store both the climate data and the users data.

## Project structure

The project is structured based on the a MVC pattern revisited following the Java Swing logic.

We can still find a model package, but the view and the controller are merged in to a user-interface package.

The application is strongly data-driven, meaning that the user-interface knows everything about the model, but to model knows nothing about the user-interface.

When it comes to folder structure, we can find the following:

```
src
├───assets: contains all the images used in the application
├───CMD: contains the command line interface
├───GUI: contains the graphical user interface
│   ├───layouts
│   ├───mainElements
│   └───panels
├───models: contains the model of the application
│   ├───data
│   ├───file
│   ├───logic
│   └───record
└───utils: contains all the utility classes
```

It's easy to see how the application is splitted between the model and the user-interface.

## Data structure

As we have already said, at this stage we prefered to avoid the use of a database and we used a csv file to store the data. Those files can be found the in the [data](./data) folder.

Each file can be seen as a possible future database table implementation.

Data are stored and saved along with a unique ID that makes easy to retrive and query them inside the application.

### Data handling

Inside the application, data are loaded and saved into appropriate `HashMaps`, whose structure are well described inside the [Record folder](./src/models/record).

Each operation made over the data, is processed in two separate stages:

1. Data are updated inside the `HashMaps`
2. Data are saved into the csv files

By doing so, we can avoid to load the data from the csv files every time we need to perform an operation over them, making the internal queries generally faster.

## User interface

So far, just two user interfaces have been implemented.

### GUI

The graphical user interface is the main interface of the application.

It's based on the use of the `Java Swing` and is composed by multiple `JPanel` that are handled and loaded by a superclass called `GUI`. To simplify the development, as layout manager we used the `CardLayout`.

Two common and repetitive layouts have been implemented to simplify the disposition of the elements inside the panels, i.e. the `TwoColumns` and the `TwoRows` layouts.

### CMD

The command line interface is a simple interface that allows the user to interact with the application through the command line.

At this stage it's not implemented, but I plan to do it soon.

## Future development

The application is still in a very early stage. Among the future development, we can find:

- [ ] Automation of the tests (JUnit)
- [ ] Implement the command line interface
- [ ] Switching from csv files to a relational database
- [ ] Improve the concurrency management of data (synchronization)

As always, suggestions and improvements are always welcome.

Have a nice coding day,

Tommaso :panda_face: