import { api, setAccessToken } from "./api";
export type CustomerSession = { accessToken: string; role: string };
export async function signIn(email: string, password: string) {
  const session = await api<CustomerSession>("/auth/login", { method: "POST", body: JSON.stringify({ email: email.trim(), password }) });
  if (session.role !== "CUSTOMER") throw new Error("This account cannot access the customer experience.");
  setAccessToken(session.accessToken); return session;
}
export async function register(email: string, password: string) {
  const session = await api<CustomerSession>("/auth/register", { method: "POST", body: JSON.stringify({ email: email.trim(), password }) });
  if (session.role !== "CUSTOMER") throw new Error("Unexpected account role.");
  setAccessToken(session.accessToken); return session;
}
export async function restoreSession() {
  try { const session = await api<CustomerSession>("/auth/refresh", { method: "POST" }); if (session.role !== "CUSTOMER") throw new Error("Not a customer account"); setAccessToken(session.accessToken); return session; }
  catch { setAccessToken(); return undefined; }
}
export async function signOut() { try { await api<void>("/auth/logout", { method: "POST" }); } finally { setAccessToken(); } }

