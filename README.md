# FetchRewardsDemo
## Fetch Rewards Coding Exercise - Backend Software Engineering
Web Service that accepts HTTP requests to track and spend user points.
Three Routes:
* Adds transactions for a specific payer and date.
* Spends points using the rules above and return a list of { "payer": <string>, "points": <integer> } for each call.
* Returns all payer point balances


## Code style

[![js-standard-style](https://img.shields.io/badge/code%20style-standard-brightgreen.svg?style=flat)](https://github.com/feross/standard)
 

## Tech/framework used

<b>Built with</b>
- [Spring](https://spring.io)
- JAVA 11
## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Trance-raver/fetchRewardsDemo.git
```
**2. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080/fetch>
## Explore Rest APIs
The app defines following REST APIs.

### Fetch

| Method | Url | Decription | Sample Valid Request Body | Sample Response | 
| ------ | --- | ---------- | --------------------------| -------------| 
| POST   | /fetch/add | Add transcations | [JSON](#add) | 
| POST   | /fetch/spend | Spend Specified Points | [JSON](#spend) | [JSON](#spendres)
| POST   | /fetch/show | Shows Available points |  | [JSON](#showpoints)

Test them using postman or any other rest client.

## Sample Valid JSON Request Bodys
##### <a id="add">Add Transcations -> /fetch/add</a>
```json
{
"payer": "DANNON", 
"points": 1000, 
"timestamp": "2020-11-02T14:00:00Z" 
}
{ 
"payer": "UNILEVER", 
"points": 200, 
"timestamp": "2020-10-31T11:00:00Z" 
}
{ 
"payer": "DANNON", 
"points": -200, 
"timestamp": "2020-10-31T15:00:00Z" 
}
{ 
"payer": "MILLER COORS", 
"points": 10000, 
"timestamp": "2020-11-01T14:00:00Z" 
}
{ 
"payer": "DANNON", 
"points": 300, 
"timestamp": "2020-10-31T10:00:00Z" 
}
```
##### <a id="spend">Spend points-> /fetch/spend</a>
```json
{ 
"points": 5000 
}
```
##### <a id="spendres">Spend points Response
```json
[
{ "payer": "DANNON", "points": -100 },
{ "payer": "UNILEVER", "points": -200 },
{ "payer": "MILLER COORS", "points": -4,700 }
]
```
##### <a id="showpoints">Show points Response
```json
{
"DANNON": 1000,
"UNILEVER": 0,
"MILLER COORS": 5300
}
```
