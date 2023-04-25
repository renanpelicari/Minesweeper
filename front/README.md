# Minesweeper APP

This project consists in a React.js application for Minesweeper game, which consumes Minesweeper API.

---

## Pre-Requirements

1. Node 18.7
2. Docker and Docker-compose

PS: I can provide the image containing this frontend project, if you want. I just decided to not versioned with the 
code, because the node_modules has around 500 mega.

---

## How to Build

```shell
npm install
npm run build
docker build -t minesweeper-webapp -f Dockerfile .
```

----

## How to Run

```shell
docker run -p 3000:3000 -d minesweeper-webapp
```

----

## Accessing the APP

[http://localhost:3000](http://localhost:3000)