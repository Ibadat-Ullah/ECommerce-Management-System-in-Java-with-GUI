# ECommerceApp.java — Detailed Guide

## What This File Contains

- Core domain classes: `Manageable`, `User` (abstract), `Customer`, `Admin`, `Product`, `CartItem`, `ShoppingCart`, `Transaction`.
- Application backbone: `EcommerceSystem` (state + business logic) and `ECommerceApp` (entry point + CLI menus, though GUI is primary).

## Responsibilities by Component

- `Manageable`: contract for `display()` across printable entities.
- `User` hierarchy: encapsulates id/username/password; `Customer` adds `orders` list; `Admin` is type marker.
- `Product`: id, name, price, stock; guarded stock mutators; file serialization via `toFile()`.
- `CartItem`: couples a `Product` and `quantity`; computes line totals.
- `ShoppingCart`: in-memory cart; add/remove/display; prevents duplicate product lines; totals and clearing.
- `Transaction`: username, amount, timestamp; formatted row output for histories.
- `EcommerceSystem`: owns users/products/transactions/cart/currentUser; handles load/save, auth, catalog, cart, checkout, admin operations.
- `ECommerceApp`: sets look-and-feel, launches Swing GUI, still carries legacy CLI menus for reference.

## Lifecycle & Data Flow

- **Startup**: `EcommerceSystem.loadData()` reads `users.txt`, `products.txt`, `transactions.txt`; seeds admin and sample products if empty.
- **Session**: `login()` sets `currentUser` and clears cart; cart ops stay in memory; `processPayment()` validates stock, deducts, records `Transaction`, clears cart.
- **Shutdown**: window close (from GUI) calls `saveData()` to rewrite all data files.

## Key Methods to Call Out

- Auth: `login(username, password)`, `logout()`, `register(username, password)`.
- Catalog: `showProducts()`, `findProduct(id)`, `addProduct(id,name,price,stock)`, `removeProduct(id)`.
- Cart/Checkout: `addProductToCart(id, qty)`, `viewCart()`, `getCartTotal()`, `processPayment(total)`, `recordTransaction(user, amount)`.
- Admin Ops: `showUsers()`, `removeUser(username)` (protects admins), `showPaymentHistory()`, `showUserPurchaseHistory(username)`.
- Persistence: `loadData()`, `saveData()` handle all three text files.

## Validation & Safeguards

- Registration rejects duplicate usernames.
- Adding products rejects duplicate IDs.
- Cart add ensures qty > 0 and ≤ stock; prevents duplicate product lines in cart.
- Checkout aborts if any cart line exceeds current stock (re-check before deducting).
- Admin removal blocked for admin accounts.
- CLI helpers guard invalid numeric input (legacy menus).

## File Format Contracts

- users.txt → `id,username,password,TYPE` (TYPE ∈ {ADMIN, CUSTOMER}).
- products.txt → `id,name,price,stock`.
- transactions.txt → `username||amount||yyyy-MM-dd HH:mm:ss`.

## Viva Talking Points

- OOP pillars: abstraction (`User`), inheritance (`Customer`, `Admin`), polymorphism (`display()`, `getType()`), encapsulation (private fields + guarded mutators).
- Collections: `ArrayList` for all dynamic lists; stream helpers for lookups and sums.
- Persistence rationale: simple text for portability in a semester project.
- Error handling: tolerant load with fallbacks; inline validation before mutations.

## Quick Trace (where to look)

- Class and core logic definitions throughout [src/ECommerceApp.java](src/ECommerceApp.java).
- GUI launch: `main()` in [src/ECommerceApp.java](src/ECommerceApp.java).
- Business logic entry points consumed by GUI: methods on `EcommerceSystem`.

## Demo Cues

- Show that `loadData()` seeds admin/products on first run.
- Perform checkout to illustrate stock deduction and transaction recording.
- Remove attempts on admin should fail gracefully.
- Re-open app to prove persistence written by `saveData()`.
