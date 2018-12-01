# MDXMapsBackend
A REST API to provide search and directions services for the Middlesex University London campus space and local area.

All requests sent to endpoints are returned in JSON.

All endpoints respond only to HTTP GET Methods.

## Search Endpoints

**All Categories** | Carries out search queries against campus and nearby locations, and also transport links/entities.
------------ | -------------
**URL** | `/search`
**PARAMETERS** | 1. `q:` [string] - The actual search query. **(Required)** <br/> <br/> 2. `rows:` [integer] - Number of results to return per category. Default is five. (Optional) <br/> <br/> 3. `directions:` [boolean] - Value of **true** filters the search result to only include locations on campus that the API can provide wayfinding/directions to/from. (Optional)
**SAMPLE REQUESTS** | 1. `/search?q=c10` <br/> <br/> 2. `/search?q=food&rows=8`

**Campus** | Carries out search queries against campus locations ONLY.
------------ | -------------
**URL** | `/search/campus`
**PARAMETERS** | 1. `q:` [string] - The actual search query. **(Required)** <br/> <br/> 2. `rows:` [integer] - Number of results to return. Default is five. (Optional)
**SAMPLE REQUESTS** | 1. `/search/campus?q=grove` <br/> <br/> 2. `/search/campus?q=g2&rows=8`

**Nearby** | Carries out search queries against nearby locations ONLY.
------------ | -------------
**URL** | `/search/nearby`
**PARAMETERS** | 1. `q:` [string] - The actual search query. **(Required)** <br/> <br/> 2. `rows:` [integer] - Number of results to return. Default is five. (Optional) <br/> <br/> 3. `t:` [boolean] A value of **true** indicates the API should return all known locations of the specified value in the `q:` parameter. (Optional)
**SAMPLE REQUESTS** | 1. `/search/nearby?q=co` - Returns all known nearby locations that match against *'co'* e.g *Costa, Costcutter, Co-Operative*  <br/> <br/> 2. `/search/nearby?q=Costcutter&t=true` - Returns all known Costcutter locations

**Transport** | Carries out search queries against nearby transport links such as Buses, Trains, Bus Stops, and Train Stations. Also provide real-time public transport information.
------------ | -------------
**URL** | **Not Implemented Yet**.


# Directions

**Directions/Wayfinding** | Provides directions/wayfiding for locations on campus. Locations can be buildings, rooms, portakabins, etc.
------------ | -------------
**URL** | `/directions`
**PARAMETERS** | 1. `s:` [string] - Start location. **(Required)** <br/> <br/> 2. `d:` [string] - Destination location. **(Required)** <br/> <br/>  3. `m:` - Mode of travel. Possible values are **stairs, elevators,** and **disabled** (Optional)
**SAMPLE REQUESTS** | 1. `/directions?s=cg03&d=gg17` - Provides directions from CG03 to GG17 <br/> <br/> 2. `/directions?s=wg41&d=w153&m=elevators` - Provides directions from WG41 to W153 using elevators.
**COMMENTS** | If the `m` parameter isn't provided, the API automatically resolves whether or not it is required and defaults to **stairs** if it is required for the operation (defaults tp **elevators** for differently-abled users). The same applies if an invalid value is passed <br/> <br/> If the `m` parameter is set to **elevators**, the API will default to **stairs** for buildings that do not have an elevator. 









