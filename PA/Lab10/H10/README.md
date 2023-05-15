# Laborator 10
## Homework 10
[H10 - Rezolvat](Rezolvat) + output
## Features
Am implementat cele 2 proiecte diferite:  C10 (care este serverul) SI C10-client (care este clientul). Serverul deschide conexiunea si clientul se poate conecta la aceasta daca are acelasi port. Comenzile immplementate, pe langa trimiterea de mesaje de la client la server, sunt exit, care deconecteaza clientul, si stop, care pe langa deconectarea clientului opreste si serverul.
Am implementat celalte 3 clase necesare: Game, Board, Player. (prin care se face logica jocului Gomoku). Am implementat create si join pentru maxim 2 jucatori, asa cum se joaca acest joc, si logica acestuia: daca se gaseste o linie (indiferent de pozitionare: coloana, linie, diagonala), jucatorul respectiv castiga jocul. Am pus si un timer pentru fiecare jucator de 60 secunde. Totul merge ok, am afisat si matricea de bile dupa fiecare mutare in server.
