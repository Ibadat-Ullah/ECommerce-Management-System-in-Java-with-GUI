# Transaction.java — Detailed Guide

## Purpose
- Standalone copy of the `Transaction` class for member testing and reference.
- Captures a purchase event (username, amount, timestamp) and formats it for history displays.

## Structure
- Fields: `username` (String), `amount` (double), `date` (Date), static `SimpleDateFormat` for `yyyy-MM-dd HH:mm:ss`.
- Constructor: `Transaction(username, amount, date)`.
- Getters: `getUsername()`, `getAmount()`, `getDate()`.
- `toString()`: formatted pipe-delimited row used in admin payment history displays.

## Usage in the System
- Created during checkout in `EcommerceSystem.recordTransaction(...)` (see [src/ECommerceApp.java](src/ECommerceApp.java)).
- Persisted to `transactions.txt` as `username||amount||timestamp`.
- Displayed in admin dashboard and payment history reports.

## Viva Talking Points
- Illustrates simple DTO with formatting responsibility kept local (toString owns display format).
- Uses `SimpleDateFormat` for human-readable timestamps; thread-safety acceptable here due to single-threaded Swing use.
- Minimal, focused class showing separation between data capture and storage/presentation.

## Where to Look
- Standalone copy: [src/Transaction.java](src/Transaction.java).
- Embedded equivalent inside [src/ECommerceApp.java](src/ECommerceApp.java).
