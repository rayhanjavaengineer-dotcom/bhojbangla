# BhojBangla
Bangladesh food discovery and delivery platform foundation.

A pnpm/Vite customer PWA and Java 21 Spring Boot REST API, backed by PostgreSQL. Restaurant, admin, and rider workspaces are represented as role-aware panel entry points in the web app; server endpoints enforce the same roles. No real restaurant listings or credentials are included.

## Quick start
Requirements: Docker Compose, Java 21, Node.js 20+, pnpm 9+.

1. Copy `.env.example` to `.env`, set a long random `JWT_SECRET` (at least 32 characters), and set a local database password.
2. Start PostgreSQL: `docker compose up -d db`
3. API: `cd apps/api && ./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`)
4. Web: `corepack pnpm install && corepack pnpm --filter @bhojbangla/web dev`
5. Open http://localhost:5173. API health: http://localhost:8080/actuator/health.

For container setup use `docker compose up --build`. Configure `VITE_API_BASE_URL` for a remote API.

## Authentication and order foundation
See [docs/authentication.md](docs/authentication.md), [docs/architecture.md](docs/architecture.md), and [docs/coverage.md](docs/coverage.md). Auth uses BCrypt password hashes, short-lived signed JWT access tokens and server-side refresh-session hashes; refresh cookies are HttpOnly, Secure in production, and SameSite=Lax. Set JWT_SECRET outside source control. COD is an order payment method; payment collection/reconciliation is not implemented.

## Tests
`cd apps/api && ./mvnw test`; `corepack pnpm test`. The API tests cover authentication, role boundaries, and COD order validation. Frontend tests cover locale and coverage utilities.

## Status
Foundation only: OTP/SMS, email delivery, payment settlement, live restaurant catalog, dispatch, maps/geocoding, and production observability need integrations and operational configuration.
