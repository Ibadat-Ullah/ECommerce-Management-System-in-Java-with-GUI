# ProductCart.java — Detailed Guide

## Purpose
- Standalone copy of product/cart classes for member testing and reference.
- Mirrors `Product`, `CartItem`, and `ShoppingCart` logic also present inside `ECommerceApp.java`.

## Components
- `Product`: id, name, price, stock; guarded stock mutators; `toFile()` for persistence formatting.
- `CartItem`: pairs a `Product` with a `quantity`; computes line total; simple `display()`.
- `ShoppingCart`:
  - Holds `ArrayList<CartItem>`.
  - `addItem(product, qty)`: validates stock, rejects duplicate product IDs in cart.
  - `removeItem(productId)`: removes matching line.
  - `display()`: prints cart and total; `getTotal()`, `clear()`, `getItems()` for consumers.
- `TestCart` main(): minimal test harness that adds two items and prints cart.

## Key Behaviors
- Prevents duplicate lines in cart; enforces stock availability on add.
- Uses `ArrayList` and streams for totals; prints user-friendly messages.
- Intended for console testing; GUI uses the versions embedded in `ECommerceApp.java`.

## Viva Talking Points
- Demonstrates encapsulation and composition (`ShoppingCart` composed of `CartItem` which wraps `Product`).
- Shows validation before state mutation (stock check, duplicate check).
- Simple unit-style main to verify behavior without full GUI.

## Where to Look
- Definitions live in [src/ProductCart.java](src/ProductCart.java).
