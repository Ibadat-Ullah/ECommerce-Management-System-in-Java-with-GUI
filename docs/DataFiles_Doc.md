# Data Files — Detailed Guide

## Files
- Users: [src/users.txt](src/users.txt)
- Products: [src/products.txt](src/products.txt)
- Transactions: [src/transactions.txt](src/transactions.txt)

## Formats
- `users.txt`: `id,username,password,TYPE` (TYPE = ADMIN or CUSTOMER).
- `products.txt`: `id,name,price,stock` (price as double, stock as int).
- `transactions.txt`: `username||amount||yyyy-MM-dd HH:mm:ss`.

## Lifecycle
- Loaded at app start by `EcommerceSystem.loadData()`.
- Saved on window close via `EcommerceSystem.saveData()` (triggered in GUI window listener).
- Seed behavior: if missing/empty, seeds admin (`admin/admin`) and sample products (P1 laptop, P2 mouse).

## Validation & Defaults
- Parsing is tolerant; on errors, system prints issue and continues.
- Duplicate username/product prevention handled in-memory before writing back.
- Stock deducted only after successful checkout; transactions recorded then persisted on save.

## Viva Talking Points
- Plain-text persistence chosen for portability and simplicity in a semester project.
- Trade-offs: no encryption, no concurrency safety, but easy to inspect/edit for testing.
- Upgrade path: replace with JDBC/ORM and hashed passwords; keep same domain model.

## Quick Checks
- After demo actions, reopen files to confirm updates: new users, adjusted stock, appended transactions.
- If data seems stale, ensure the GUI was closed normally so `saveData()` ran.
