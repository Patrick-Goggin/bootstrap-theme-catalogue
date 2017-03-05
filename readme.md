### Usage
- This application allows users to catalogue and store bootstrap themes, and preview any theme in the catalogue in a single-page application.
- Run the application and go on http://localhost:8080/

### Build and run

#### Configurations
The application is configured to use an h2 database when deployed locally and a PostgreSQL database when deployed on heroku.
#### Prerequisites

- Java 8
- Maven 3
- PostgreSQL
- Lombok (may require IDE plugin)

#### From terminal

Go on the project's root directory, then type:

    $ mvn spring-boot:run

### Heroku Deployment

In project root directory:

    $ git init
    $ echo target > .gitignore
    $ git add .
    $ git commit -m"initial commit"
    $ heroku login
    $ heroku create
    $ heroku addons:create heroku-postgresql
    $ git push heroku master
    $ heroku ps:scale web=1
