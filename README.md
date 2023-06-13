# RAF News Backend

## Descrpition
This is the server-side part of a blog application developed using the Jersey implementation of JAX-RS. The first step was designing a database model, which consists of several tables for the blog functionality. I used JDBC to interact with the MySQL database and implemented CRUD operations to manipulate data within the application. I also used more complex queries, such as JOIN operations, to fetch data from different tables, as well as cascading insertion and deletion. JWT provided ability to authenticate users and manage their roles. After successful login, a JWT gets generated, which includes the user's roles. This token is then used in other requests to authenticate the user and provide access based on their roles.

## Structure

This project represents backend component of a full stack application:
* [Backend](https://github.com/lukamilo99/raf-news-back) <br/>
* [Frontend](https://github.com/lukamilo99/raf-news-front) <br/>

## Database model
[raf-news-db.pdf](https://github.com/lukamilo99/raf-news-back/files/11735792/raf-news-db.pdf)


