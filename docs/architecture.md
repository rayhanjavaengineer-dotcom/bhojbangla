# Architecture
- `apps/web`: installable, responsive React PWA; bilingual customer storefront and role-aware restaurant, admin, and rider panel entry points.
- `apps/api`: Java 21 / Spring Boot 3 REST API. Controllers delegate to application services and repositories.
- PostgreSQL schema is versioned with Flyway in `apps/api/src/main/resources/db/migration`.
- Boundary: protected API routes require a verified bearer access token and role checks. Never trust a role or price sent by the browser.
- Orders have a COD-only schema and request contract. Creation remains deliberately unavailable until a reviewed catalog and serviceable coverage can be queried and priced server-side.
