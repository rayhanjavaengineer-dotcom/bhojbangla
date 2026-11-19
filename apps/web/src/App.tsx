import { useEffect, useState } from "react";
import { ArrowRight, ArrowUpRight, Check, ChevronDown, Clock, Compass, MapPin, Search, ShoppingBag, Sparkles, Utensils } from "lucide-react";
import { copy, type Locale, t } from "./i18n";
import { divisions, districts } from "./coverage";

const categories = [["🍛","Rice & curry"],["🍜","Noodles"],["🥟","Snacks"],["🍰","Desserts"],["☕","Tea & coffee"]];
type CoverageDistrict = { name: string; division: string; enabled: boolean };

export default function App() {
  const [locale,setLocale] = useState<Locale>("en");
  const [role,setRole] = useState<"customer"|"restaurant"|"admin"|"rider">("customer");
  const [division,setDivision] = useState("Dhaka");
  const [district,setDistrict] = useState("Dhaka");
  const [enabledDistricts,setEnabledDistricts] = useState<Set<string>>(new Set());
  const [count,setCount] = useState(0);
  const x=(key:keyof typeof copy.en)=>t(locale,key);

  useEffect(() => {
    const base = import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080/api";
    fetch(`${base}/coverage/districts`)
      .then(r => { if (!r.ok) throw new Error("coverage unavailable"); return r.json() as Promise<CoverageDistrict[]>; })
      .then(rows => setEnabledDistricts(new Set(rows.filter(row => row.enabled).map(row => row.name))))
      .catch(() => setEnabledDistricts(new Set()));
  }, []);

  const chooseDivision=(value:string) => {
    setDivision(value);
    const options=districts(value);
    setDistrict(options.find(name=>enabledDistricts.has(name)) ?? options[0] ?? "");
  };

  return <main>
    <header className="top">
      <a className="brand" href="#"><span className="mark">ভ</span><span>Bhoj<span className="brand-green">Bangla</span><small>{x("tagline")}</small></span></a>
      <div className="loc"><MapPin size={16}/><span><small>{x("location")}</small><select aria-label="Division" value={division} onChange={e=>chooseDivision(e.target.value)}>{divisions.map(d=><option key={d}>{d}</option>)}</select><select className="district" aria-label="District" value={district} onChange={e=>setDistrict(e.target.value)}>{districts(division).map(d=><option key={d} value={d} disabled={!enabledDistricts.has(d)}>{d}{enabledDistricts.has(d)?"":" — unavailable"}</option>)}</select></span><ChevronDown size={14}/></div>
      <div className="search"><Search size={17}/><input aria-label={x("search")} placeholder={x("search")}/><kbd>⌘ K</kbd></div>
      <button className="lang" onClick={()=>setLocale(locale==="en"?"bn":"en")}>{x("language")}</button>
      <button className="signin">{x("signin")} <ArrowRight size={15}/></button>
      <button className="bag" aria-label="Cart"><ShoppingBag size={18}/><i>{count}</i></button>
    </header>
    <nav className="roles">{(["customer","restaurant","admin","rider"] as const).map(r=><button className={role===r?"active":""} key={r} onClick={()=>setRole(r)}>{x(r)}</button>)}</nav>
    <section className="hero">
      <div className="hero-copy"><span className="eyebrow"><Sparkles size={14}/> {x("fresh")}</span><h1>{x("explore")}<em> {locale==="bn"?"আজই।":"Today."}</em></h1><p>{x("browse")} — thoughtfully made, right around the corner.</p><button className="primary" onClick={()=>document.getElementById("menu")?.scrollIntoView({behavior:"smooth"})}>{x("discover")} <ArrowRight size={17}/></button><div className="social"><small>Made with care in Bangladesh</small></div></div>
      <div className="hero-art"><div className="sun"></div><div className="plate"><div className="food">🍲</div></div><div className="floating note"><span>✦</span><b>Freshly<br/>made</b></div><div className="floating time"><Clock size={15}/><b>{x("time")}</b></div><div className="herb h1">✿</div><div className="herb h2">✾</div></div>
      <div className="hero-foot"><span>01 / 03</span><span>{x("zones")}</span></div>
    </section>
    <section className="cats"><div className="section-head"><div><small className="eyebrow">{x("categories")}</small><h2>{x("popular")}</h2></div><button className="text-btn">{x("seeAll")} <ArrowUpRight size={16}/></button></div><div className="cat-grid">{categories.map(([emoji,name],i)=><button className="cat" key={name} onClick={()=>setCount(count+1)}><span className={"cat-art art"+i}>{emoji}</span><b>{locale==="bn"?["ভাত ও তরকারি","নুডলস","নাশতা","মিষ্টি","চা ও কফি"][i]:name}</b><small>Explore <ArrowRight size={12}/></small></button>)}</div></section>
    <section className="discover" id="menu"><div className="section-head"><div><small className="eyebrow">CURATED FOR YOUR CITY</small><h2>{role==="customer"?"A taste of the neighbourhood":role==="restaurant"?"Restaurant workspace":role==="admin"?"Platform overview":"Rider workspace"}</h2></div><span className="coverage"><Check size={14}/> {district}, {division}</span></div>{role==="customer"?<div className="empty"><div className="empty-icon"><Utensils size={24}/></div><h3>{x("empty")}</h3><p>We’re preparing a thoughtful local selection. Check back soon.</p><button className="outline" onClick={()=>setCount(count+1)}><Compass size={15}/> Explore categories</button></div>:<div className="workspace"><div className="workspace-icon">{role==="admin"?"✦":role==="rider"?"↗":"⌂"}</div><div><h3>{role==="restaurant"?"Your kitchen, your rhythm":role==="admin"?"Everything in one place":"The next delivery starts here"}</h3><p>{role==="restaurant"?"Manage menus and incoming orders once your account is provisioned.":role==="admin"?"Review coverage, partners, and platform operations.":"See assigned deliveries and update order progress."}</p></div><button className="outline">{x("signin")} <ArrowRight size={15}/></button></div>}</section>
    <footer><a className="brand foot-brand" href="#"><span className="mark">ভ</span><span>Bhoj<span className="brand-green">Bangla</span></span></a><span>Made for the way Bangladesh eats.</span><span>© 2026 BhojBangla</span></footer>
  </main>;
}
