# EPG Java Challenge

### Brief description

The application has an embedded database (H2) in memory.
In each execution the information is generated with some scripts (scheme, data and test) for its later 
management and testing. \
Repositories are used according to JPA specifications, as well as the service layer pattern. \
Two types of controllers: API REST controllers and a controller for routing the views in the visual part.
For the visual part the template engine thymeleaf is used, relying on the javascript framework vuejs.

### Approaches

* I considered that the entity that should have more prominence is "Rent". For that reason, I focused on the logic of
the RentServiceImp. 
* I tried to leave the controllers and repositories as light as possible to leave all the responsibility of the logic 
to the services. 
* I built a simple visual part to have a more attractive image of the product and to facilitate the interaction.


### How to run

mvn spring-boot:run


