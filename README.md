# MDXMapsBackend
A REST API to provide search and directions services for the Middlesex University London campus space and local area.

All requests sent to endpoints are returned in JSON.

All endpoints respond only to HTTP GET Methods.

## Search Endpoints

**All Categories** | Carries out search queries against campus and nearby locations.
------------ | -------------
**URL** | `/search`
**PARAMETERS** | 1. `q:` [string, integer, combination of both] - The actual search query. **(Required)** <br/> 2. `rows:` [integer] - Number of results to return per category. Default is five. (Optional) <br/> 3. `directions:` [boolean] - Value of **true** filters the search result to only include locations on campus that the API can provide wayfinding/directions to/from. (Optional)
**SAMPLE REQUESTS** | 1. `/search?q=c10` <br/> 2. `/search?q=food&rows=8`

**Campus** | Carries out search queries against campus locations ONLY.
------------ | -------------
**URL** | `/search/campus`
**PARAMETERS** | 1. `q:` [string, integer, combination of both] - The actual search query. **(Required)** <br/> 2. `rows:` [integer] - Number of results to return. Default is five. (Optional)
**SAMPLE REQUESTS** | 1. `/search/campus?q=grove` <br/> 2. `/search/campus?q=g2&rows=8`

**Nearby** | Carries out search queries against nearby locations ONLY.
------------ | -------------
**URL** | `/search/nearby`
**PARAMETERS** | 1. `q:` [string, integer, combination of both] - The actual search query. **(Required)** <br/> 2. `rows:` [integer] - Number of results to return. Default is five. (Optional) <br/> 3. `t:` [boolean] A value of **true** indicates the API should return all known locations of the specified value in the `q:` parameter. (Optional)
**SAMPLE REQUESTS** | 1. `/search/nearby?q=co` - Returns all known nearby locations that match against *'co'* e.g *Costa, Costcutter, Co-Operative*  <br/> 2. `/search/nearby?q=Costcutter&t=true` - Returns all known Costcutter locations









