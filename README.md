# RESTful Web Services Consumption from Java

## What is RESTful Web Service?
  - RESTful Web Services are basically REST Architecture based Web Services. 
  - In REST Architecture everything is a resource.

## RESTful Methods
  - GET    : Gets all the data.
  - POST   : Creates a new resource.
  - PUT    : Updates an existing resource.
  - DELETE : Deletes the resource
  - HEAD   : Returns the HTTP Header only, no Body.
  - OPTIONS: Lists out the supported operations in a web service.

  In this post we will create resources about GET, POST, PUT, DELETE.

## Project Structure
![Project Structure](https://github.com/Amarnath510/RestFulConsumingDemo/blob/master/RestFulConsumingInJava.png)

## Project Setup
  - Eclipse -> File -> New -> Dynamic Web Project -> Finish.
  - Open "https://jersey.java.net/download.html" and download "Jersey JAX-RS 2.0 RI bundle" or any latest available version.
  - Extract the zip and check the three directories "api", "ext", "list". Copy all the jars under it to lib folder under "Project-Root-Directory/WebContent/WEB-INF/lib".
  - We also need JSON jar for creating JSON objects. Download it from "https://mvnrepository.com/artifact/org.json/json/20140107".

## Returns types of RESTful resources.
  - JSON - @Produces(MediaType.APPLICATION_JSON)
  - XML  - @Produces(MediaType.TEXT_XML)
  - HTML - @Produces(MediaType.TEXT_HTML)

## Classes and Methods
  - **javax.ws.rs.client.Client** <br />
    Client is the main entry point to the fluent API used to build and execute client requests in order to consume responses returned.

  - **javax.ws.rs.client.ClientBuilder** <br />
    Main entry point to the client API used to bootstrap Client instances.

  - **ClientBuilder.newClient()** <br />
    Create a new Client instance using the default client builder implementation class provided by the JAX-RS implementation provider.

  - **WebTarget target(String uri)** <br />
    Build a new web resource target.

  - **WebTarget path(String path)** <br />
    Create a new WebTarget instance by appending path to the URI of the current target instance.

  - **WebTarget resolveTemplate(String name, Object value)** <br />
    Create a new WebTarget instance by resolving a URI template with a given name in the URI of the current target instance using a supplied value.

  - **Invocation.Builder request(MediaType... acceptedResponseTypes)** <br />
    Start building a request to the targeted web resource and define the accepted response media types.

  - **<T> T get(Class<T> responseType)** <br />
    Invoke HTTP GET method for the current request synchronously.

  - **<T> T post(Entity<?> entity, Class<T> responseType)** <br />
    Invoke HTTP POST method for the current request synchronously.

  - **<T> T put(Entity<?> entity, Class<T> responseType)** <br />
    Invoke HTTP PUT method for the current request synchronously.

  - **<T> T delete(Class<T> responseType)** <br />
    Invoke HTTP DELETE method for the current request synchronously.	

## Implementation
  - Save Employee
    - Create a client object using ClientBuilder.
    - Create a Form object and pass it to the RESTFul resource.
    - Your request should use the same MediaType as RESTful method @Produces Mediatype.

    ```java
      void saveEmployee(int id, String fName, String lName) {
      
        Form form = new Form();
        form.param("id", Integer.toString(id));
        form.param("fName", fName);
        form.param("lName", lName);
        
        String response = client
                .target(REST_URL)
                .path("/save")
                .request(MediaType.TEXT_HTML)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
        
        System.out.println(response);
    }
    ```

  - Get Employee by Id
    - By default the call is GET.
    - The parameters which we need to pass will be passed via URL. Hence we have to resolve the value using resolveTemplate.
    - Your request should use the same MediaType as RESTful method @Produces Mediatype.

    ```java
    void getEmployeeById(int id) {
      String response = client
              .target(REST_URL)
              .path("/id/{id}")
              .resolveTemplate("id", id)
              .request(MediaType.APPLICATION_JSON)
              .get(String.class);
      
      System.out.println(response);
    }
    ```

  - Get All Employees
    - Don't need to pass any parameters.
    - Just call the URL.
    - Your request should use the same MediaType as RESTful method @Produces Mediatype.

    ```java
    void getAllEmployees() {
      String response = client
              .target(REST_URL)
              .path("/all")
              .request(MediaType.APPLICATION_JSON)
              .get(String.class);
      System.out.println(response);
    }
    ```

  - Update Employee By Id
    - We are updating the existing resource using PUT method.
    - Create Form object using the existing Id with new values.
    - Your request should use the same MediaType as RESTful method @Produces Mediatype.

    ```java
    void updateEmployee(int id, String fName, String lName) {
      Form form = new Form();
      form.param("id", Integer.toString(id));
      form.param("fName", fName);
      form.param("lName", lName);
      
      String response = client
                .target(REST_URL)
                .path("/update")
                .request(MediaType.TEXT_HTML)
                .put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
      
      System.out.println(response);
    }
    ```

  - Delete Employee by Id
    - Pass Employee Id via URL.
    - We need to resolve the id using resolveTemplate.
    - Finally call delete method.
    - Your request should use the same MediaType as RESTful method @Produces Mediatype.

    ```java
    void deleteEmployee(int id) {
      String response = client
                .target(REST_URL)
                .path("/delete/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.TEXT_HTML)
                .delete(String.class);
      
      System.out.println(response);
    }
    ```  
 
## Running Application
  - First start the RESTFul application on Tomcat. See the [RESTFul Creation and Running here](https://github.com/Amarnath510/RestFulCreationDemo).
  - To run this application we have to run App.java on standalone. Right Click on the App.java file and Run as Java Application.

## Reference:
  - [RESTFul Web Services](https://www.tutorialspoint.com/restful/restful_methods.htm)
