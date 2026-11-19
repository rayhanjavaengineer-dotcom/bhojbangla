FROM node:20-alpine AS build
WORKDIR /app
RUN corepack enable
COPY package.json pnpm-workspace.yaml ./
COPY apps/web/package.json apps/web/package.json
RUN pnpm install --filter @bhojbangla/web
COPY apps/web apps/web
ARG VITE_API_BASE_URL=http://localhost:8080/api
ENV VITE_API_BASE_URL=$VITE_API_BASE_URL
RUN pnpm --filter @bhojbangla/web build
FROM nginx:1.27-alpine
COPY --from=build /app/apps/web/dist /usr/share/nginx/html
EXPOSE 80
