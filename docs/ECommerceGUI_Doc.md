# ECommerceGUI.java — Detailed Guide

## Role
- Swing front-end that drives flows for welcome, login, registration, customer dashboard, and admin dashboard.
- Uses a `CardLayout` to swap views; delegates data operations to `EcommerceSystem` (from ECommerceApp).
- Implements glassmorphism visuals with custom painting and gradient overlays.

## Structure
- Fields: `EcommerceSystem system`, active user, background images, theme constants, card panel.
- Custom `GlassPanel` for frosted containers with rounded corners and subtle borders.
- Table models: `productModel` powering product tables in both dashboards.
- Core UI builders: `buildWelcomePanel()`, `buildLoginPanel()`, `buildRegisterPanel()`, `buildCustomerPanel()`, `buildAdminPanel()`.
- Utility helpers: styling for buttons/fields, placeholder setup, password toggle, background loader, table refreshers.

## Screen Flows
- **Welcome**: CTA buttons → Login or Register.
- **Login**: validates fields; on success, sets `activeUser`, updates welcome label, refreshes products/cart; routes to admin or customer card.
- **Register**: creates customer via `system.register`; inline message feedback for duplicates/success.
- **Customer Dashboard**:
  - Product table view.
  - Add-to-cart (ID + qty) with inline status messages; cart textarea + total label.
  - Checkout button triggers `system.processPayment(total)` (GUI-safe overload), then refreshes products/cart.
  - Logout clears cart and returns to welcome.
- **Admin Dashboard**:
  - Product add/remove forms; rejects duplicate IDs.
  - User list (text area) and remove user (admins protected).
  - Transactions area with formatted history and revenue subtotal; refreshable.
  - Logout returns to welcome.

## Data Interactions (delegated to `EcommerceSystem`)
- Auth: `login`, `register`, `logout`.
- Catalog: `getProducts()`, `addProduct(...)`, `removeProduct(...)`.
- Cart/checkout: `addProductToCart(...)`, `getCartTotal()`, `processPayment(...)`, `getCart().getItems()` for display.
- Users/transactions: `getUsers()`, `getTransactions()` for admin views.
- Persistence: `saveData()` triggered on window close listener.

## UX/Visual Notes
- Gradient overlays and radial blobs to create glassmorphism depth.
- Emoji placeholders for avatars; accent color palette via constants.
- Placeholders in text fields; password toggle with echo char switching.
- Tables configured non-editable; custom header styling via `JTableHeader` tweaks.
- Image loading tries `Images/` and `src/Images/` fallbacks for `login.jfif` and `logic.jfif`.

## Validation & Feedback
- Empty field checks on login/register; inline labels for errors/success.
- Add-to-cart validates presence of product and positive quantity; cart disallows duplicates at system level.
- Admin remove user prevents removing admins; messages reflect success/failure.
- Checkout refuses when cart empty or stock invalid; success clears cart and updates totals.

## Threading & Shutdown
- Launched on EDT via `SwingUtilities.invokeLater` from `main()`.
- Window listener calls `system.saveData()` before dispose, ensuring persistence.

## Viva Talking Points
- Separation of concerns: UI handles rendering/events; `EcommerceSystem` handles logic/state.
- CardLayout for navigation without reopening frames.
- Custom painting for modern UI without external libs.
- Data-binding approach: tables/areas refreshed from `system` getters after each mutation.
- Input validation path vs. business validation path (UI + system checks).

## Demo Tips
- Show welcome → register → login (customer) → add to cart → checkout → see totals update.
- Switch to admin; add/remove products; remove non-admin user; show transactions list and revenue subtotal.
- Close and reopen app to prove persistence of tables and transactions.
