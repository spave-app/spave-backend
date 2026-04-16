# spave-backend

Backend API for Spave - a marketplace platform to discover and book sports spaces.

## API Endpoints

### Venues

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/venues` | List all venues |
| `GET` | `/venues/{id}` | Get a single venue with its address |
| `GET` | `/venues/{id}/courts` | List all courts for a specific venue |

### Courts

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/courts` | List all active courts (across all venues) |
| `GET` | `/courts/{id}` | Get a single court (includes bookingLink for redirect) |

### Waitlist

| Method | Endpoint         | Description                                                                                                        |
|--------|------------------|--------------------------------------------------------------------------------------------------------------------|
| `POST` | `/waitlist`      | Join the waitlist with an email (returns 201 on success, 409 if already registered, 429 if rate limit is exceeded) |
| `GET`  | `/waitlist/size` | Returns the size of the waitlist (how many people are currently inside it)                                         |


### Contact

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/contact` | Submit a contact form — saves the entry and sends an email notification to contact@spaveapp.com |

**Request body:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "subject": "Booking inquiry",
  "message": "I'd like to know more about available courts this weekend."
}
```

### Auth

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/auth/register` | Register a new user (returns JWT) |
| `POST` | `/auth/login` | Login with email and password |

## Key Response Fields

### Venue
- id (UUID)
- name, description, phone, email
- website - venue website URL
- address - nested address object with addressLine1, city, province, postalCode, lat, lng

### Court
- id (UUID)
- name, description
- size - court size (FIVE_A_SIDE, SEVEN_A_SIDE, etc.)
- type - court type (INDOOR, OUTDOOR)
- surface - surface type (TURF, GRASS, etc.)
- bookingLink - URL to redirect users for booking
- priceMin, priceMax - price range
- numberAvailable - number of available courts
- venueId - parent venue UUID

## Notes
- Venue and court endpoints are public (no auth required)
- GET /courts only returns courts where isActive = true
- IDs are UUIDs (e.g. 0b121868-c1b5-4f51-94ac-520ce75074f6)
