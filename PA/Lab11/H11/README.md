# Laborator 11
## Homework 11
[H11 - Rezolvat](Rezolvat) + output
## Features
Am facut un proiect cu siteul dat pe discord si apoi am adaugat un nume de test cu "add" si i-am dat return.
Apoi, in postman am facut un call pe GET de server.port=8081.
Am incercat prima daca cu port default, 8080, dar nu mergea, asa ca am modificat in resouces/application.proprieties urmatorul port, 8081.
Astfel, am obtinut cele 2 nume date ca returnList.
Pe langa C11, am adaugat un jucator nou cu POST (toate cele ce le voi enumera merg si din Lab11Aplication.java, si din Postman), am modificat numele cu PUT si am sters un nume cu DELETE.
Am facut si un service REST care numara jocurile fiecarui jucator. + un client care invoca serviciile springboot si functioneaza asemanator cu Lab11Aplication.java.
Am folosit si Swagger pt documentatie si totul e ok. (am implementat si un fisier de eroare -> tot primeam eroare de fisier inexistent)
