# E-Commerce Management System — Viva Guide (Detailed)

## 1) Snapshot

- Tech: Java 8+, Swing GUI, text-file storage (no DB), single-jar style app.
- Entrypoint: [src/ECommerceApp.java](src/ECommerceApp.java) launches [src/ECommerceGUI.java](src/ECommerceGUI.java).
- Data files: [src/users.txt](src/users.txt), [src/products.txt](src/products.txt), [src/transactions.txt](src/transactions.txt) (auto-created/updated).
- Default admin: username `admin`, password `admin` (seeded on first run).
- Seed products (if none found): P1 Laptop 999.0 stock 5; P2 Mouse 25.0 stock 10.

## 2) System Requirements

- JDK 8+ on Windows; any JVM that supports Swing is fine.
- No external libraries; no database server required.
- For PDF export from VS Code, ensure a PDF printer (e.g., Microsoft Print to PDF) or `pandoc` if using CLI.

## 3) Build & Run (Windows)

- From project root:
  - Compile to `Classes`:
    ```bash
    javac -d Classes src\*.java
    ```
  - Run GUI:
    ```bash
    java -cp .\Classes ECommerceApp
    ```
- Closing the window triggers `saveData()` to persist users/products/transactions.

## 4) Architecture Overview

- **Manageable**: interface with `display()` for printable entities.
- **User (abstract)**: id, username, password, `getType()`, `toFile()`. Subclasses: **Customer** (keeps `orders` list) and **Admin**.
- **Product**: id, name, price, stock; methods to adjust stock and price; `toFile()` for persistence.
- **CartItem**: couples a `Product` with `quantity`; computes line total.
- **ShoppingCart**: holds `CartItem` list; add/remove/display; `getTotal()`, `clear()`, `getItems()`.
- **Transaction**: username, amount, timestamp; formatted via `toString()`.
- **EcommerceSystem**: core state + business logic; handles auth, catalog, cart, checkout, admin operations, load/save to files.
- **ECommerceGUI**: Swing UI (card layout) driving flows: Welcome → Login/Register → Customer or Admin dashboards; glassmorphism styling.

## 5) Data Flow & Persistence

- Startup: `loadData()` reads three text files; seeds admin/products if files empty/missing.
- Cart: kept in memory per session; stock is deducted only on successful checkout (`applyCartStockDeduction`).
- Checkout: validates quantities, records `Transaction(username, amount, now)`, clears cart.
- Shutdown: window close listener calls `saveData()`; rewrites all three files with current state.

## 6) GUI Walkthrough (for viva demo)

- Welcome: Login / Register buttons on glass background.
- Register: create customer; duplicate usernames blocked; inline status label.
- Login: role-based redirect; clears cart on login for safety.
- Customer dashboard:
  - Product table (ID, Name, Price, Stock) pulled from system list.
  - Add to cart by ID + quantity; cart area shows lines; total label shows sum.
  - Checkout: validates stock, records transaction, decrements stock, clears cart.
  - Logout: clears cart, returns to welcome.
- Admin dashboard:
  - Add product (id/name/price/stock) and remove by ID; prevents duplicate IDs.
  - User list (admins protected from removal); remove user by username.
  - Transactions area with history and revenue subtotal.
  - Logout back to welcome.

## 7) File Formats

- users.txt: `id,username,password,TYPE` where TYPE is ADMIN or CUSTOMER.
- products.txt: `id,name,price,stock`.
- transactions.txt: `username||amount||yyyy-MM-dd HH:mm:ss`.

## 8) Validation & Error Handling

- Duplicate usernames rejected at registration; duplicate product IDs rejected on add.
- Cart add: quantity must be > 0 and ≤ available stock; no duplicate product lines in cart.
- Checkout: aborts if any cart line exceeds stock; cart is untouched on failure.
- Admin removal: cannot remove admins; graceful message.
- CLI helper guards invalid numeric input; GUI uses inline labels for feedback.

## 9) Key Code Touchpoints (cite in viva)

- Auth and data loading/saving: [src/ECommerceApp.java](src/ECommerceApp.java#L109-L198).
- Cart add/checkout and stock deduction: [src/ECommerceApp.java](src/ECommerceApp.java#L210-L291).
- Admin operations (add/remove product, users, history): [src/ECommerceApp.java](src/ECommerceApp.java#L297-L362).
- GUI routing (cards, login/register/customer/admin panels): [src/ECommerceGUI.java](src/ECommerceGUI.java#L1-L450) and onward.

## 10) Demo Script (step-by-step)

1. Launch app; show Welcome aesthetics.
2. Register a customer; see success message; login as that customer.
3. Add P1 (qty 1) and P2 (qty 2) to cart; show cart text and total; checkout; confirm success.
4. Point out stock reduced in product table after checkout.
5. Logout; login as admin (`admin/admin`).
6. Add a new product (e.g., P3 Keyboard 49.0 stock 15); remove a product by ID; show user list; attempt to remove admin (expected fail); show transactions history and revenue total.
7. Close app; restart; show data persisted in tables (proves file I/O).

## 11) Limitations and Talking Points

- Plain-text passwords and files; no hashing or DB.
- No concurrent session handling; single-process assumption.
- Payment is simulated; no gateway; cart lacks in-place quantity edit (remove and re-add instead).
- Potential upgrades: JDBC/ORM DB, password hashing, search/filter, order status, receipts, email notifications, analytics dashboards.

## 12) Viva Q&A Cheat Sheet

- OOP pillars: abstraction (User), inheritance (Admin/Customer), polymorphism (`display()`, `getType()`), encapsulation (private fields + getters/setters/stock mutators).
- Data persistence: text files via `Scanner` + `PrintWriter`; simple CSV/pipe formats.
- Why no DB? Scope and simplicity for semester project; easy portability.
- Error handling: guard rails in cart, checkout, registration, and admin removal; defaults when files missing.
- GUI design: card layout + custom painting for glassmorphism; separation of UI (ECommerceGUI) from logic (EcommerceSystem).

## 13) Troubleshooting Quick Hits

- Classes not found: recompile with `javac -d Classes src\*.java`; ensure run from project root.
- GUI not showing images: check Images folder paths (tries both `Images/` and `src/Images/`).
- Data not saving: ensure the app closes normally so `windowClosing` triggers `saveData()`; verify write permissions.

## 14) How to Export This to PDF

- VS Code UI: open this file → Markdown preview (`Ctrl+Shift+V`) → `Ctrl+P` → pick **Microsoft Print to PDF** (or any PDF printer) → save as `Project_Viva_Guide.pdf`.
- CLI with pandoc (if installed):
  ```bash
  pandoc Project_Viva_Guide.md -o Project_Viva_Guide.pdf
  ```

## 15) File Map

- Main entry & logic: [src/ECommerceApp.java](src/ECommerceApp.java).
- GUI: [src/ECommerceGUI.java](src/ECommerceGUI.java).
- Core class copies (member tests): [src/ProductCart.java](src/ProductCart.java), [src/UserClasses.java](src/UserClasses.java), [src/Transaction.java](src/Transaction.java).
- Data seeds: [src/products.txt](src/products.txt), [src/users.txt](src/users.txt), [src/transactions.txt](src/transactions.txt).
