Here's the reformatted `README.md` for the repository:

# Bus Ticket Management System

## Overview

The Bus Ticket Management System is a RESTful API designed for managing bus routes, booking tickets, and checking seat availability. This system provides endpoints to search for buses, book tickets, and view available seats.

## Technologies Used

- **Spring Boot**: For creating the RESTful API
- **JPA/Hibernate**: For ORM and database interactions
- **H2 Database**: For development and testing
- **Maven**: For project management and build automation
- **Lombok**: For reducing boilerplate code

## Setup Instructions

1. **Clone the Repository**
    ```bash
    git clone https://github.com/yourusername/bus-ticket-management-system.git
    cd bus-ticket-management-system
    ```

2. **Build the Project**
    ```bash
    mvn clean install
    ```

3. **Run the Application**
    ```bash
    mvn spring-boot:run
    ```

4. **Access the Application**
    Open a browser and go to `http://localhost:8080`.

## Endpoints

### 1. Search Buses

- **Endpoint:** `/api/buses/search`
- **Method:** `GET`
- **Description:** Search for buses based on departure location, arrival location, and travel date.
- **Request Parameters:**
  - `from`: Departure location (e.g., CityA)
  - `to`: Arrival location (e.g., CityB)
  - `date`: Date of travel in `YYYY-MM-DD` format

**Example Request:**
```plaintext
GET /api/buses/search?from=CityA&to=CityB&date=2024-07-21
```

**Response Format:**
```json
[
  {
    "busId": 1,
    "busNumber": "MH12AB1234",
    "departure": "CityA",
    "arrival": "CityB",
    "date": "2024-07-21"
  },
  {
    "busId": 2,
    "busNumber": "MH12CD5678",
    "departure": "CityA",
    "arrival": "CityB",
    "date": "2024-07-21"
  }
]
```

### 2. View Available Seats

- **Endpoint:** `/api/buses/{bus_id}/available-seats`
- **Method:** `GET`
- **Description:** View available seats for a specific bus.
- **Path Variable:**
   - `bus_id`: Bus ID (e.g., 1)

**Example Request 1:**
```plaintext
GET /api/buses/1/available-seats
```

**Response Format 1:**
```json
{
  "busNumber": "MH12AB1234",
  "availableSeats": [
    {
      "seatNumber": "A1",
      "isAvailable": true
    },
    {
      "seatNumber": "A2",
      "isAvailable": false
    },
    {
      "seatNumber": "B1",
      "isAvailable": true
    },
    {
      "seatNumber": "B2",
      "isAvailable": false
    }
  ]
}
```

**Example Request 2:**
```plaintext
GET /api/buses/2/available-seats
```

**Response Format 2:**
```json
{
  "busNumber": "MH12CD5678",
  "availableSeats": [
    {
      "seatNumber": "C1",
      "isAvailable": true
    },
    {
      "seatNumber": "C2",
      "isAvailable": true
    },
    {
      "seatNumber": "D1",
      "isAvailable": false
    },
    {
      "seatNumber": "D2",
      "isAvailable": true
    }
  ]
}
```

### 3. Book Ticket

- **Endpoint:** `/api/buses/book`
- **Method:** `POST`
- **Description:** Book a ticket for a specific bus and seat.
- **Request Body:**
    ```json
    {
      "routeBusId": 1,
      "customerId": 2,
      "seatNumber": "A1"
    }
    ```

**Example Request:**
```plaintext
POST /api/buses/book
```

**Response Format:**
```json
{
  "status": "success",
  "message": "Ticket booked successfully",
  "data": {
    "ticketId": 123,
    "busNumber": "MH12AB1234",
    "seatNumber": "A1",
    "bookingTime": "2024-07-20T15:24:53.598+05:30",
    "ticketStatus": "BOOKED"
  }
}
```

**Error Response Format:**
```json
{
  "status": "error",
  "message": "Customer not found"
}
```

### 4. Get User Tickets

- **Endpoint:** `/api/buses/user/{user_id}/tickets`
- **Method:** `GET`
- **Description:** Retrieve all tickets booked by a specific user.
- **Path Variable:**
    - `user_id`: User ID (e.g., 2)

**Example Request:**
```
GET /api/buses/user/2/tickets
```

**Response Format:**
```json
{
  "status": "success",
  "message": "Tickets retrieved successfully",
  "data": [
    {
      "ticketId": 123,
      "busNumber": "MH12AB1234",
      "seatNumber": "A1",
      "bookingTime": "2024-07-20T15:24:53.598+05:30",
      "ticketStatus": "BOOKED"
    },
    {
      "ticketId": 124,
      "busNumber": "MH12CD5678",
      "seatNumber": "B1",
      "bookingTime": "2024-07-21T10:15:00.000+05:30",
      "ticketStatus": "BOOKED"
    }
  ]
}
```

**Error Response Format:**
```json
{
  "status": "error",
  "message": "User not found"
}
```

<<<<<<< HEAD


---

=======
## Status of Completion of Work

- 🛠️  **1.1**: Left in WIP state due to time constraints.
- ✅ **1.2**: Get User Tickets API
- ✅ **2.1 & 2.2**: Search Buses API
- ✅ **3.1**: View Available Seats API
- ✅ **4.1**: Book Ticket API
- ✅ **4.2**: Get User Tickets API


>>>>>>> d3ee5ba4d26ef92ca9c6f276c776aa738ddefe5f
## Contributing

Feel free to submit issues, feature requests, or pull requests. Please follow the standard Git workflow for contributions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

This updated `README.md` now aligns with the provided `TicketController` and maintains proper Markdown formatting.
