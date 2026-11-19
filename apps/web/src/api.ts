const base=import.meta.env.VITE_API_BASE_URL??"http://localhost:8080/api";
let accessToken:string|undefined;
export function setAccessToken(token?:string){accessToken=token}
export async function api<T>(path:string,init:RequestInit={}):Promise<T>{
 const headers=new Headers(init.headers); headers.set("Content-Type","application/json");
 if(accessToken) headers.set("Authorization",`Bearer ${accessToken}`);
 const response=await fetch(`${base}${path}`,{...init,headers,credentials:"include"});
 if(!response.ok) throw new Error("Request failed");
 return response.status===204?undefined as T:response.json() as Promise<T>;
}
