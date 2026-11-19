# BhojBangla
Bangladesh food discovery and delivery platform foundation.

A pnpm/Vite customer PWA and Java 21 Spring Boot REST API, backed by PostgreSQL. Restaurant, admin, and rider workspaces are represented as role-aware panel entry points in the web app; server endpoints enforce role gates. No real restaurant listings or credentials are included.

## Quick start
Requirements: Docker Compose, Java 21, Maven 3.9+, Node.js 20+, pnpm 9+.

1. Copy `.env.example` to `.env`, set a long random `JWT_SECRET` (at least 32 bytes), and set a local database password.
2. Start PostgreSQL: `docker compose up -d db`
3. API: `cd apps/api && mvn spring-boot:run`
4. Web: `corepack pnpm install && corepack pnpm --filter @bhojbangla/web dev`
5. Open http://localhost:5173. API health: http://localhost:8080/actuator/health.

For container setup use `docker compose up --build`. Configure `VITE_API_BASE_URL` for a remote API.

## Customer experience
The customer PWA supports English and Bangla, customer registration/sign-in, refresh-session restoration, and service-area selection from the PostgreSQL-backed coverage API. Browser access tokens stay in memory; refresh tokens use an HttpOnly cookie. GPS access is opt-in and coordinates are not transmitted or matched to a district because geocoding is not integrated. There is no restaurant discovery API or verified menu data yet, so the customer screen shows an honest empty state. Order placement is not exposed and the API keeps it disabled.

See [docs/authentication.md](docs/authentication.md), [docs/architecture.md](docs/architecture.md), and [docs/coverage.md](docs/coverage.md). Do not commit secrets.

## Checks
`cd apps/api && mvn test`; `corepack pnpm install --frozen-lockfile && corepack pnpm --filter @bhojbangla/web test && corepack pnpm --filter @bhojbangla/web build`. GitHub Actions runs frontend tests/build and API integration tests against PostgreSQL on pushes and pull requests.

## Remaining foundation work
OTP/SMS, email delivery, payment settlement, restaurant and rider workflows, order lifecycle, catalog administration, maps/geocoding, security rate limits and production observability need integrations and operational configuration.

