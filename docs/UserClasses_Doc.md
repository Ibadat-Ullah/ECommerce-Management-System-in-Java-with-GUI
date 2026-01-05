# UserClasses.java — Detailed Guide

## Purpose
- Standalone copy of user hierarchy for member testing and reference.
- Mirrors `Manageable`, `User`, `Customer`, and `Admin` also defined in `ECommerceApp.java`.

## Components
- `Manageable`: interface with `display()` contract.
- `User` (abstract): id, username, password; `getType()` abstract; `toFile()` for persistence; `display()` prints user info.
- `Customer`: extends `User`; keeps `orders` list; `addOrder()`, `showOrders()`; `getType()` returns `CUSTOMER`.
- `Admin`: extends `User`; `getType()` returns `ADMIN`.
- `TestUser` main(): quick console harness to instantiate and exercise methods.

## Key Behaviors
- Encapsulates credentials; exposes getters only.
- Demonstrates inheritance and polymorphism via `getType()` and `display()` implementations.
- Simple order tracking list on `Customer` to illustrate collection use.

## Viva Talking Points
- Abstraction (`User`), inheritance (`Customer`/`Admin`), polymorphism (`display()`, `getType()`), encapsulation (private fields + getters).
- File serialization via `toFile()` enabling text persistence elsewhere in the system.
- Console test harness as a minimal verification path.

## Where to Look
- Definitions live in [src/UserClasses.java](src/UserClasses.java).
