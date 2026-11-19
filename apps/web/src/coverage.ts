export const divisions = ["Barishal","Chattogram","Dhaka","Khulna","Mymensingh","Rajshahi","Rangpur","Sylhet"] as const;
export const districtsByDivision: Record<string,string[]> = {
 Barishal:["Barguna","Barishal","Bhola","Jhalokati","Patuakhali","Pirojpur"],
 Chattogram:["Bandarban","Brahmanbaria","Chandpur","Chattogram","Cox's Bazar","Cumilla","Feni","Khagrachhari","Lakshmipur","Noakhali","Rangamati"],
 Dhaka:["Dhaka","Faridpur","Gazipur","Gopalganj","Kishoreganj","Madaripur","Manikganj","Munshiganj","Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail"],
 Khulna:["Bagerhat","Chuadanga","Jashore","Jhenaidah","Khulna","Kushtia","Magura","Meherpur","Narail","Satkhira"],
 Mymensingh:["Jamalpur","Mymensingh","Netrokona","Sherpur"],
 Rajshahi:["Bogura","Joypurhat","Naogaon","Natore","Chapainawabganj","Pabna","Rajshahi","Sirajganj"],
 Rangpur:["Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon"],
 Sylhet:["Habiganj","Moulvibazar","Sunamganj","Sylhet"]
};
export function districts(division:string):string[]{return districtsByDivision[division]??[]}
