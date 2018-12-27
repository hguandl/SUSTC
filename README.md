# SUSTC
CS309 Project - SUSTech User Script Tools Center

# Dependencies
- Java 1.8+ (Recommand Java 11)
- Apache Maven
- Docker

# Usage
## Configuration
In `application.properties`:
- For first run, change `spring.jpa.hibernate.ddl-auto` to `create`, then change it to `update`.
- Specify your database configuration in `spring.datasource.*`.

## Launch the project
### Use jar release
1. Download the jar in the release.
2. Modify `application.properties` (see [Configuration](#configuration)).
3. Execute the jar archive:
```bash
$ java -jar scriptpro-1.0.0-RELEASE.jar
```

### Build with Apache Maven
1. Clone this repository.
2. Modify `application.properties` (see [Configuration](#configuration)).
3. Execute with Maven:
```bash
$ mvn clean install
$ mvn spring-boot:run
```
