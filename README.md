<<<<<<< HEAD
# Critter Chronologer Project Starter

Critter Chronologer a Software as a Service application that provides a scheduling interface for a small business that takes care of animals. This Spring Boot project will allow users to create pets, owners, and employees, and then schedule events for employees to provide services for pets.


## Getting Started
=======
# Critter Chronologer Project
Udacity Java Developer Nanodegree Project 3

## Purpose
Critter Chronologer is a Software as a Service application that provides a scheduling interface for a small business that takes care of animals. This Spring Boot project will allow users to create pets, owners, and employees, and then schedule events for employees to provide services for pets.

This project required utilizing data persistence concepts including:

  *  Structuring Data in a Multitier Architeture
  *  Using the Java Persistence API (JPA)
  *  Connecting to multiple data sources
  *  Persistence using SQL and Data Access Objects
  *  Solve persistence performance issues

### [Project Starter Code and Instructions](https://github.com/udacity/nd035-c3-data-stores-and-persistence-project-starter)
>>>>>>> 09913547a9673ab8ea8d377b10e86c47b541f5f0

### Dependencies

* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download) (or Ultimate) recommended 
* [Java SE Development Kit 8+](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi)
* [MySQL Server 8](https://dev.mysql.com/downloads/mysql/) (or another standalone SQL instance)
* [Postman](https://www.getpostman.com/downloads/)

<<<<<<< HEAD
Part of this project involves configuring a Spring application to connect to an external data source. Before beginning this project, you must install a database to connect to. Here are [instructions for installing MySQL 8](https://dev.mysql.com/doc/refman/8.0/en/installing.html).

You should install the Server and Connector/J, but it is also convenient to install the Documentation and Workbench.

Alternately, you may wish to run MySQL in a docker container, using [these instructions](https://hub.docker.com/_/mysql/).

After installing the Server, you will need to create a user that your application will use to perform operations on the server. You should create a user that has all permissions on localhost using the sql command found [here](https://dev.mysql.com/doc/refman/8.0/en/creating-accounts.html).

Another SQL database may be used if desired, but do not use the H2 in-memory database as your primary datasource.

### Installation

1. Clone or download this repository.
2. Open IntelliJ IDEA.
3. In IDEA, select `File` -> `Open` and navigate to the `critter` directory within this repository. Select that directory to open.
4. The project should open in IDEA. In the project structure, navigate to `src/main/java/com.udacity.jdnd.course3.critter`. 
5. Within that directory, click on CritterApplication.java and select `Run` -> `Debug CritterApplication`. 
6. Open a browser and navigate to the url: [http://localhost:8082/test](http://localhost:8082/test)

You should see the message "Critter Starter installed successfully" in your browser.

## Testing

Once you have completed the above installation, you should also be able to run the included unit tests to verify basic functionality as you complete it. To run unit tests:

1. Within your project in IDEA, Navigate to `src/test/java/com.udacity.jdnd.course3.critter`.
2. Within that directory, click on `CritterFunctionalTest.java` and select `Run` -> `Run CritterFunctionalTest`.

A window should open showing you the test executions. All 9 tests should fail and if you click on them they will show `java.lang.UnsupportedOperationeException` as the cause.

As you complete the objectives of this project, you will be able to verify progress by re-running these tests.

### Tested Conditions
Tests will pass under the following conditions:

* `testCreateCustomer` - **UserController.saveCustomer** returns a saved customer matching the request
* `testCreateEmployee` - **UserController.saveEmployee** returns a saved employee matching the request
* `testAddPetsToCustomer` - **PetController.getPetsByOwner** returns a saved pet with the same id and name as the one saved with **UserController.savePet** for a given owner
* `testFindPetsByOwner` - **PetController.getPetsByOwner** returns all pets saved for that owner.
* `testFindOwnerByPet` - **UserController.getOwnerByPet** returns the saved owner used to create the pet.
* `testChangeEmployeeAvailability` - **UserController.getEmployee** returns an employee with the same availability as set for that employee by **UserControler.setAvailability**
* `testFindEmployeesByServiceAndTime` - **UserController.findEmployeesForService** returns all saved employees that have the requested availability and skills and none that do not
* `testSchedulePetsForServiceWithEmployee` - **ScheduleController.createSchedule** returns a saved schedule matching the requested activities, pets, employees, and date
* `testFindScheduleByEntities` - **ScheduleController.getScheduleForEmployee** returns all saved schedules containing that employee. **ScheduleController.getScheduleForPet** returns all saved schedules for that pet. **ScheduleController.getScheduleForCustomer** returns all saved schedules for any pets belonging to that owner.

### Postman
In addition to the included unit tests, a Postman collection has been provided. 

1. Open Postman.
2. Select the `Import` button.
3. Import the file found in this repository under `src/main/resource/Udacity.postman_collection.json`
4. Expand the Udacity folder in postman.

Each entry in this collection contains information in its `Body` tab if necessary and all requests should function for a completed project. Depending on your key generation strategy, you may need to edit the specific ids in these requests for your particular project.
=======
### Resources

#### Spring.io
* [Spring Data JPA - Reference Documentation 2.4.2](https://docs.spring.io/spring-data/jpa/docs/current/reference/html)
* [Spring Data JPA - Reference Documentation 2.1.21](https://docs.spring.io/spring-data/jpa/docs/2.1.x/reference/html/)

#### Postman
* [Postman Javascript Reference](https://learning.postman.com/docs/writing-scripts/script-references/postman-sandbox-api-reference/)
* [Test script examples](https://learning.postman.com/docs/writing-scripts/script-references/test-examples/)

#### Baeldung.com

* [Hibernate Many to Many Annotation Tutorial](https://www.baeldung.com/hibernate-many-to-many)
* [Jackson – Bidirectional Relationships](https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion)
* [The difference between map() and flatMap()](https://www.baeldung.com/java-difference-map-and-flatmap)
* [Iterate over a Map in Java](https://www.baeldung.com/java-iterate-map)
* [Use Criteria Queries in a Spring Data Application](https://www.baeldung.com/spring-data-criteria-queries)
* [Guide to the Hibernate EntityManager](https://www.baeldung.com/hibernate-entitymanager)
* [Guide to the Java 8 forEach](https://www.baeldung.com/foreach-java)
* [Error Handling for REST with Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)
* [Retrieve Fields from a Java Class Using Reflection](https://www.baeldung.com/java-reflection-class-fields)
* [The Order of Test in JUnit by Fatos Morina ](https://www.baeldung.com/junit-5-test-order)

#### StackOverflow.com
* [Pascal Thivent - Querying Relationships Using Joins](https://stackoverflow.com/questions/3424696/jpa-criteria-api-how-to-add-join-clause-as-general-sentence-as-possible)
* [Konstantin Yovkov - What is @StaticMetamodel and SingularAttribute<Obj,Obj>?](https://stackoverflow.com/questions/19704407/what-is-staticmetamodel-and-singularattributeobj-obj)
* [Roland - Hibernate CriteriaBuilder to join multiple tables](https://stackoverflow.com/questions/41982998/hibernate-criteriabuilder-to-join-multiple-tables)
* [Fabian - Hibernate OneToMany java.lang.StackOverflowError](https://cni.castingnetworks.com/talent/confirmation/default.aspx?cid=2989879)
* [Bozho - MultipleBagFetchExceptions and @LazyCollection()](https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl)
* [Ketrox - JPA many-to-many relationship causing infinite recursion and stack overflow error](https://stackoverflow.com/questions/43481353/jpa-many-to-many-relationship-causing-infinite-recursion-and-stack-overflow-erro)
* [jbrookover - Hibernate criteria query for Collection Table?](https://stackoverflow.com/questions/7687409/hibernate-criteria-query-for-collection-table)
* [axtavt - How do I update an entity using spring-data-jpa?](https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa)
* [Taryn - SQL Inner-join with 3 tables?](https://stackoverflow.com/questions/10195451/sql-inner-join-with-3-tables)
* [Vlad Dobrydin - org.hibernate.hql.internal.ast.QuerySyntaxException: table is not mapped](https://stackoverflow.com/questions/23018836/org-hibernate-hql-internal-ast-querysyntaxexception-table-is-not-mapped)
* [Oliver Drotbohm - Spring CrudRepository ... equivalent to IN clause](https://stackoverflow.com/questions/18987292/spring-crudrepository-findbyinventoryidslistlong-inventoryidlist-equivalen)
* [Fuury - Spring Data JPA findBy a collection [duplicate]](https://stackoverflow.com/questions/53619322/spring-data-jpa-findby-a-collection)
* [Counting records in JSON array using javascript and Postman](https://stackoverflow.com/questions/35987043/counting-records-in-json-array-using-javascript-and-postman)
* [brainimus - Hibernate - A collection with cascade=”all-delete-orphan” was no longer referenced by the owning entity instance](https://stackoverflow.com/questions/5587482/hibernate-a-collection-with-cascade-all-delete-orphan-was-no-longer-referenc)
* [Derek Kromm - Only return rows if sum is greater than a value](https://stackoverflow.com/questions/16320362/only-return-rows-if-sum-is-greater-than-a-value)
* [Louis Wasserman - Java 8 method references: provide a Supplier capable of supplying a parameterized result](https://stackoverflow.com/questions/22917138/java-8-method-references-provide-a-supplier-capable-of-supplying-a-parameterize)
* [Marius - Reflection generic get field value](https://stackoverflow.com/questions/13400075/reflection-generic-get-field-value)
* [Retrieving the data type for an object using reflection](https://stackoverflow.com/questions/24670772/retrieving-the-data-type-for-an-object-using-reflection)

#### Oracle
* [The Java EE 6 Tutorial - Criteria Queries](https://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html)
* [Querying JPA Entities wiht JPQL and Native SQL](https://www.oracle.com/technical-resources/articles/vasiliev-jpql.html)
* [Creating Queries Using the Criteria API](https://docs.oracle.com/cd/E19226-01/820-7627/gjitv/index.html)

#### Various Sources
* [Pivotal Engineering Journal - Must-Know Spring Boot Annotations: Controllers](https://engineering.pivotal.io/post/must-know-spring-boot-annotations-controllers/)
* [Callicoder - JPA Many to Many Mappings](https://www.callicoder.com/hibernate-spring-boot-jpa-many-to-many-mapping-example/)
* [ConcretePage.com - JPA EntityManager](https://www.concretepage.com/java/jpa/jpa-entitymanager-and-entitymanagerfactory-example-using-hibernate-with-persist-find-contains-detach-merge-and-remove)
* [ConcretePage.com - Convert Java Stream to String](https://www.concretepage.com/java/java-10/convert-java-stream-to-string)
* [Codata.com - How to use CriteriaBuilder](https://www.codota.com/code/java/classes/javax.persistence.criteria.CriteriaBuilder)
* [JavaCodeExamples - Get Elements by Index from HashSet in Java](https://www.javacodeexamples.com/get-elements-by-index-from-hashset-in-java-example/2772)
* [Hibernate Reference Documentation](https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/index.html)
* [Buggy Bread - Spring Framework List of Exceptions](https://www.buggybread.com/2015/03/spring-framework-list-of-exceptions.html)
* [HowToDoInJava.com - JUnit 5 Expected Exception – assertThrows() Example](https://howtodoinjava.com/junit5/expected-exception-example/)
* [admfactory.com - Get and Set Field Value using Reflection in Java](https://www.admfactory.com/get-and-set-field-value-using-reflection-in-java/)
* [attacomsian.com - Capitalize the first letter of a string in Java](https://attacomsian.com/blog/capitalize-first-letter-of-string-java)
>>>>>>> 09913547a9673ab8ea8d377b10e86c47b541f5f0

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework providing dependency injection, web framework, data binding, resource management, transaction management, and more.
* [Google Guava](https://github.com/google/guava) - A set of core libraries used in this project for their collections utilities.
* [H2 Database Engine](https://www.h2database.com/html/main.html) - An in-memory database used in this project to run unit tests.
* [MySQL Connector/J](https://www.mysql.com/products/connector/) - JDBC Drivers to allow Java to connect to MySQL Server

## License

<<<<<<< HEAD
This project is licensed under the MIT License - see the [LICENSE.md]()
=======
This project is licensed under the [MIT License](https://mit-license.org/)
>>>>>>> 09913547a9673ab8ea8d377b10e86c47b541f5f0
