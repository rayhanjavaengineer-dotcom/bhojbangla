import { defineConfig } from "vitest/config";
import react from "@vitejs/plugin-react";
import { VitePWA } from "vite-plugin-pwa";

export default defineConfig({
  plugins: [
    react(),
    VitePWA({
      registerType: "autoUpdate",
      includeAssets: ["icon.svg"],
      manifest: {
        name: "BhojBangla",
        short_name: "BhojBangla",
        description: "Discover food and delivery in Bangladesh",
        start_url: "/",
        display: "standalone",
        background_color: "#fbf8f1",
        theme_color: "#123c2e",
        icons: [{ src: "/icon.svg", sizes: "any", type: "image/svg+xml", purpose: "any maskable" }]
      },
      workbox: { navigateFallback: "/index.html", globPatterns: ["**/*.{js,css,html,svg,webmanifest}"] }
    })
  ],
  test: { environment: "jsdom" }
});
