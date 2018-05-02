# MDXMapsBackend
A REST API to provide search and directions services for the Middlesex University London campus space and local area.

All requests sent to endpoints are returned in JSON.

All endpoints respond only to HTTP GET Methods.

## Search Endpoints

**All Categories** | Carries out search queries against campus and nearby locations.
------------ | -------------
**URL** | `/search`
**PARAMETERS** | 1. `q:` [string, integer, combination of both] - The actual search query. **(Required)** <br/> 2. `rows:` [integer] - Number of results to return per category. Default is five. (Optional)
**SAMPLE REQUESTS** | 1. `/search?q=c10` <br/> 2. `/search?q=food&rows=8`

**Campus** | Carries out search queries against campus locations ONLY.
------------ | -------------
**URL** | `/search/campus`
**PARAMETERS** | 1. `q:` [string, integer, combination of both] - The actual search query. **(Required)** <br/> 2. `rows:` [integer] - Number of results to return. Default is five. (Optional)
**SAMPLE REQUESTS** | 1. `/search/campus?q=grove` <br/> 2. `/search/campus?q=g2&rows=8`











