# test-task
## Indytskyi Artem 
---
### Stack of technologies :smiley:

- `Spring Boot 3.0.5`
- `PostgreSQL` 
- `Flyway`
- `Testcontainers`
- `Zipkin`

You will like those project!

---
### How to start service locally :construction_worker:

No additional configurations are needed to start any service. 
All configurations are in application.yaml.


#### Docker

In order to start the project you will need to run the docker containers. 

For a guide for usage with Docker, [checkout the docs](https://github.com/maildev/maildev/blob/master/docs/docker.md).

All properties for all required images are in docker-compose.yml file. So, to run the project you just need to select the directory where this file exist and then run the following command:

```
docker-compose up
````

Relax and test :sunglasses:
---
### Usage :star:
The user of this project can perform next actions:
- register `user` 
- login `user`
- add `article` to `user`
- get all `users` with age greater than some value
- get unique names from `users` with more than `3 articles`
- get all `users` from `articles` in which color is some specific value from the enum

#### To trace the microservices:
- [Zipkin](http://localhost:9411/zipkin)

### IMPOTRANT :fire: :fire:
 - Only the administrator has access to all endpoints except (`register`, `login`, `add article to user`).
 - Admin сredentials. email : `superadmin@gmail.com` and password: `Artem2003@`
 - All users have a password - `Artem2003@`
---
### THANKS :heart:
---
