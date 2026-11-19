export function currentLocation(): Promise<GeolocationPosition> {
  return new Promise((resolve,reject)=>{ if(!navigator.geolocation) return reject(new Error("Location is not available in this browser.")); navigator.geolocation.getCurrentPosition(resolve, e=>reject(new Error(e.code===1?"Location permission was denied.":e.code===2?"Your location could not be determined.":"Location request timed out.")),{enableHighAccuracy:false,timeout:10000,maximumAge:60000}); });
}

