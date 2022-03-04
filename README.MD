# Book Borrowing API

## Entity Relationship Diagram

Diagram made using draw.io.

![ER Diagram](https://github.com/SebastianSNZ/DSUBookBorrowingAPI/blob/main/img/ERDiagram.png?raw=true)

## UML State Diagram

Diagram made using draw.io.

![UML State Diagram](https://github.com/SebastianSNZ/DSUBookBorrowingAPI/blob/main/img/StateDiagram.png?raw=true)

## Reservations Service

### Get all reservations

We can make the request:
```
GET http://<url>/v1/reservation
```

And the response is like this:
```json
[
  {
    "id": 0,
    "bookId": 0,
    "bookName": "string",
    "customerId": 0,
    "customerName": "string",
    "reservationDate": "2022-03-03",
    "estimatedDate": "2022-03-03",
    "returnDate": "2022-03-03",
    "penalty": 0,
    "status": 0
  }
]
```

### Add new reservation

We can make the request:
```
POST http://<url>/v1/reservation
```
Body:
```json
{
  "customer": {
    "id": 0,
  },
  "book": {
    "book_id": 0,
  },
  "estimatedDate": "2022-03-03"
}
```

And the response is like this:
```json
{
    "id": 0,
    "bookId": 0,
    "bookName": "string",
    "customerId": 0,
    "customerName": "string",
    "reservationDate": "2022-03-03",
    "estimatedDate": "2022-03-03",
    "returnDate": "2022-03-03",
    "penalty": 0,
    "status": 0
}
```

### Return a book (updating a reservation)
We can make the request:
```
UPDATE http://<url>/v1/reservation/return
```
Body:
```json
{
  "id": 0
}
```

And the response is like this:
```json
{
    "id": 0,
    "bookId": 0,
    "bookName": "string",
    "customerId": 0,
    "customerName": "string",
    "reservationDate": "2022-03-03",
    "estimatedDate": "2022-03-03",
    "returnDate": "2022-03-03",
    "penalty": 0,
    "status": 0
}
```

### Add an extension (updating a reservation)
We can make the request:
```
UPDATE http://<url>/v1/reservation/extension
```
Body:
```json
{
  "id": 0,
  "estimatedDate": "2022-03-03"
}
```

And the response is like this:
```json
{
    "id": 0,
    "bookId": 0,
    "bookName": "string",
    "customerId": 0,
    "customerName": "string",
    "reservationDate": "2022-03-03",
    "estimatedDate": "2022-03-03",
    "returnDate": "2022-03-03",
    "penalty": 0,
    "status": 0
}
```

### Delete a reservation
We can make the request:
```
DELETE http://<url>/v1/reservation
```
Body:
```json
{
  "id": 0
}
```

And the response is like this:
```json
{
    "id": 0,
    "bookId": 0,
    "bookName": "string",
    "customerId": 0,
    "customerName": "string",
    "reservationDate": "2022-03-03",
    "estimatedDate": "2022-03-03",
    "returnDate": "2022-03-03",
    "penalty": 0,
    "status": 0
}
```

## Customer service 

### List all the customers 
We can make the request:
```
GET http://<url>/v1/customer
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "id": 0,
    "username": "string",
    "name": "string",
    "rol": {
      "id": 0,
      "rol": "string"
    },
    "cellphone": 0
  }
]  
```

### Update a customer 
We can make the request:
```
UPDATE http://<url>/v1/
```
Body:
```json
{
  "id": 0,
  "username": "string",
  "name": "string",
  "rol": {
    "id": 0,
    "rol": "string"
  },
  "cellphone": 0
}
```

And the response is like this:
```json
{
  "id": 0,
  "username": "string",
  "name": "string",
  "rol": {
    "id": 0,
    "rol": "string"
  },
  "cellphone": 0
}
```

### Add a new customer
We can make the request:
```
POST http://<url>/v1/customer
```
Body:
```json
{
  "id": 0,
  "username": "string",
  "name": "string",
  "rol": {
    "id": 0,
    "rol": "string"
  },
  "cellphone": 0
}
```

And the response is like this:
```json
{
  "id": 0,
  "username": "string",
  "name": "string",
  "rol": {
    "id": 0,
    "rol": "string"
  },
  "cellphone": 0
}
```

### Find a customer by id 
We can make the request:
```
GET http://<url>/v1/customer/{id}
```
Body:
```json

```

And the response is like this:
```json
{
  "id": 0,
  "username": "string",
  "name": "string",
  "rol": {
    "id": 0,
    "rol": "string"
  },
  "cellphone": 0
}
```

### Delete a customer 
We can make the request:
```
DELETE http://<url>/v1/customer
```
Body:
```json

```

And the response is like this:
```
Customer delete successfull /  Customer was not deleted
```

### Get all user by rol
We can make the request:
```
GET http://<url>/v1/customer/query?rol=1
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "id": 0,
    "username": "string",
    "name": "string",
    "rol": {
      "id": 0,
      "rol": "string"
    },
    "cellphone": 0
  }
]
```

## Book Service 

### Get all books
We can make the request:
```
GET http://<url>/v1/book
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "book_id": 0,
    "name": "string",
    "category": "string",
    "quantity": 0
  }
]
```

### Update a book 
We can make the request:
```
UPDATE http://<url>/v1/book
```
Body:
```json
{
  "book_id": 0,
  "name": "string",
  "category": "string",
  "quantity": 0
}
```

And the response is like this:
```json
{
  "book_id": 0,
  "name": "string",
  "category": "string",
  "quantity": 0
}
```

### Create a new book 
We can make the request:
```
POST http://<url>/v1/book
```
Body:
```json
{
  "name": "string",
  "category": "string",
  "quantity": 0
}
```

And the response is like this:
```json
{
  "book_id": 0,
  "name": "string",
  "category": "string",
  "quantity": 0
}
```

### Get a book by id 
We can make the request:
```
GET http://<url>/v1/book/{id}
```
Body:
```json

```

And the response is like this:
```json
{
  "book_id": 0,
  "name": "string",
  "category": "string",
  "quantity": 0
}
```

### Delete a book by id 
We can make the request:
```
delete http://<url>/v1/book/id
```
Body:
```json

```

And the response is like this:
```json
Book deleted sussecsfull / Book was not deleted 
```

### Get DTO Books 
A dto book is a book without the id. 
We can make the request:
```
get http://<url>/v1/book/bookDTO
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "name": "string",
    "category": "string",
    "quantity": 0
  }
]
```

## Author Service 
### Get all the authors 
We can make the request:
```
GET http://<url>/v1/author
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "author_id": 0,
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
]
```

### Update one outhor 
We can make the request:
```
UPDATE http://<url>/v1/author
```
Body:
```json
  {
    "author_id": 0,
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
```

And the response is like this:
```json
  {
    "author_id": 0,
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
```


### Create a new Author 
We can make the request:
```
POST http://<url>/v1/author
```
Body:
```json
{
  "name": "string",
  "lastName": "string",
  "direction": "string",
  "cellphone": 0
}
```

And the response is like this:
```json
{   
   "author_id": 0,
  "name": "string",
  "lastName": "string",
  "direction": "string",
  "cellphone": 0
}
```

### Get author by id
We can make the request:
```
GET http://<url>/v1/author/{id}
```
Body:
```json

```

And the response is like this:
```json
{
  "author_id": 0,
  "name": "string",
  "lastName": "string",
  "direction": "string",
  "cellphone": 0
}
```

### Delete author by id 
We can make the request:
```
DELETE http://<url>/v1/author/{id}
```
Body:
```json

```

And the response is like this:
```string
"Book deleted sussecsfull / Book was not deleted"
```

### Get all authorDTO
An authorDTO is an Author without the id 
We can make the request:
```
GET http://<url>/v1/author/onlyNames
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
]
```

## Rol Services 

### Get all rols 
We can make the request:
```
GET http://<url>/v1/rol
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "id": 0,
    "rol": "string"
  }
]
```

### Create a new rol 
We can make the request:
```
POST http://<url>/v1/rol
```
Body:
```json
{
  "id": 0,
  "rol": "string"
}
```

And the response is like this:
```json
{
  "id": 0,
  "rol": "string"
}
```


## Author-book controller 
This table is the connection between the author and the books 

### Get all the authors and books relations 
We can make the request:
```
GET http://<url>/v1/authorBook
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "book": {
      "book_id": 0,
      "name": "string",
      "category": "string",
      "quantity": 0
    },
    "author": {
      "author_id": 0,
      "name": "string",
      "lastName": "string",
      "direction": "string",
      "cellphone": 0
    }
  }
]
```

### Create a new relation between author and boook
We can make the request:
```
POST http://<url>/v1/authorBook
```
Body:
```json
{
  "book": {
    "book_id": 0
  },
  "author": {
    "author_id": 0
  }
}
```

And the response is like this:
```json
{
  "book": {
    "book_id": 0,
    "name": "string",
    "category": "string",
    "quantity": 0
  },
  "author": {
    "author_id": 0,
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
}
```

### Delete a relation between authors and books 
We can make the request:
```
POST http://<url>/v1/authorBook/delete
```
Body:
```json
{
  "book": 0,
  "author": 0
}
```

And the response is like this:
```boolean
true/false
```


### Get books by authors 
We can make the request:
```
GET http://<url>/v1/authorBook/booksByAuthorWithID/{id}
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "book_id": 0,
    "name": "string",
    "category": "string",
    "quantity": 0
  }
]
```

### Get booksDTO by author 
We can make the request:
```
GET http://<url>/v1/authorBook/booksByAuthor/{id}
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "name": "string",
    "category": "string",
    "quantity": 0
  }
]
```


### Get all the authors who don't work in a book 
We can make the request:
```
GET http://<url>/v1/AuthorsWhoAreNotIn/{id}
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "author_id": 0,
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
]
```

### Get authors by book id 
We can make the request:
```
 http://<url>/v1/AuthorsByBookWithID/{id}
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "author_id": 0,
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
]
```

### Get authorsDTO by book id 
We can make the request:
```
 http://<url>/v1/AuthorsByBook/{id}
```
Body:
```json

```

And the response is like this:
```json
[
  {
    "name": "string",
    "lastName": "string",
    "direction": "string",
    "cellphone": 0
  }
]
```
