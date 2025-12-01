# FS12 — Java Basic Step Project Documentation

## 1. Introduction

### 1.1. Project Purpose
The purpose of this project is to build a console-based Java application that allows users to search for flights, view flight schedules, book tickets, cancel bookings, and view their personal booked flights.

### 1.2. Project Overview
The application uses a three-layer architecture:
- **DAO Layer** — file operations (load/save)
- **Service Layer** — business logic
- **Controller Layer** — orchestrates actions and connects the console with services

The program loads all data from files on startup and saves all changes on exit.

## 2. Development Team

### 2.1. List of members
| Member Name             | GitHub Nickname    |  
|-------------------------|--------------------|
| **Javanshiri Kiyan**    | KiyanJavanshiri    |
| **Havrylenko Yaroslav** | YaroslavHavrylenko |
| **Shewer Timur**        | timurgogogo-ux     | 
| **Kalenyuk Anna**       | Ashtu11            |

### 2.2. Responsibilities of each member

1. **Timur Shewer** — Console Interface Developer
- Implementing `ApplicationConsole`
- Handling all menu navigation and user input
- Communicating with controllers only (no direct DAO/Service usage)
- Managing input validation

---

2. **Yaroslav Havrylenko** — Flights Module Developer
- Implementing `Flight` model
- Creating `FlightDao`, `FlightsService`, `FlightsController`
- Logic for searching flights by date, destination, seat availability
- Generating the initial large flight database

---

3. **Kiyan Javanshiri** — Bookings Module Developer
- Implementing `Booking` and `Passenger` model
- Creating `BookingDao`, `BookingService`, `BookingController`
- Logic for creating and canceling bookings
- Updating available seats in flights
- Handling multi-passenger booking flows

---

4. **Anna Kalenyuk** — Testing and Quality Assurance
- Writing unit tests for all DAO classes
- Writing unit tests for all Service classes
- Writing unit tests for all Controller classes
- Ensuring full coverage of business logic
- Verifying program stability through automated tests

## 3. System Architecture

### 3.1. Main Package Structure
```bash
flightreservation
├── controller
│ ├── BookingController
│ └── FlightsController
├── dao
│ ├── BookingDao
│ └── FlightDao
├── database
│ ├── bookings.dat
│ └── flights.dat
├── enums
│ └── Destination
├── exceptions
│ └── BookingNotFoundException
├── models
│ ├── Booking
│ ├── Flight
│ └── Passenger
├── service 
│ ├── BookingService
│ └── FlightsService  
├── ApplicationConsole
└── Main
```

### 3.2. Tests Package Structure
```bash
test/java
├── ControllerTests
│ └── controller
│   ├── BookingTests
│   └── FlightTests
├── DaoTests
│ ├── BookingDaoTests
│ └── FlightDaoTests
├── ServiceTests
│ ├── BookingServiceTests
│ └── FlightServiceTests
```
## 4. Business Logic

### 4.1. Online board
- Displays all flights departing from Kyiv within the next 24 hours
- Uses FlightDao to retrieve data

### 4.2. View flight information
- User enters a flight ID
- Application displays full flight details:
    - date and time
    - destination
    - available seats

### 4.3. Search and book a flight
1. User enters:
    - destination
    - date
    - number of passengers
2. System searches for flights with enough available seats
3. User selects a flight or returns to menu
4. For booking:
    - enter first and last name of booking owner
    - enter first and last name for each passenger
    - booking(s) are created
    - seats are deducted from the flight

### 4.4. Cancel Booking
- User enters a booking ID
- If found, booking is removed
- Seats are returned to the related flight

### 4.5. My bookings
- User enters last name and first name
- System prints all bookings containing this user as a passenger or as a owner

## 5. File Storage System

### 5.1. Storage Files
- `flights.dat` — flight database
- `bookings.dat` — user bookings

## 6. Additional Information

### 6.1. Support
Mentor - Anton Belyi

### 6.2. License
No license yet

### 6.3. Project status
- Started - 18.11.2025
- Done - 02.12.2025
