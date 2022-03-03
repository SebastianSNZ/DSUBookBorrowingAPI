# Book Borrowing API

## Entity Relationship Diagram

Diagram made using draw.io.

![ER Diagram](https://github.com/SebastianSNZ/DSUBookBorrowingAPI/blob/main/ERDiagram.png?raw=true)

## UML State Diagram

Diagram made using draw.io.

![UML State Diagram](https://github.com/SebastianSNZ/DSUBookBorrowingAPI/blob/main/StateDiagram.png?raw=true)

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

