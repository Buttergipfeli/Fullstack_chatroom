# Fullstack_chatroom
Eigenes Chatroom-Projekt von Andre Kocher

# Chatroom
## Über
Dieses Projekt ist ein kleiner Chatroom, welchen ich während den letzten 4 Tagen erstellt habe. Aufs Design habe ich nicht besonders geachtet, da ich eher auf die Funktionalität von diesem Projekt geachtet habe.
## Aufbau
Es ist eine Full-Stack-Applikation. Als Datenbank wird MySQL, als Backend wird Java Spring (Maven-Projekt) und als Frontend React verwendet. Die Datenbank habe ich mit Docker eingerichtet.
## Dateien
Auf diesem Repository gibt es die Backend und die Frontend Applikationen. Vergessen Sie nicht im Frontend die "node_modules" mit dem Befehl "npm install" zu installieren. Für die Datenbank habe ich die .yml-Datei für das Einrichten der MySQL-Datenbank in Docker und eine SQL-Datei für das Erstellen einer Datenbank.
## Was fehlt
Momentan fehlt die gegenseitige Aktualisierung von den Nachrichten. Dafür habe ich als Alternative zu Websockets einen Timer erstellt. Ebenfalls fehlt das Design, die Bildersend-Funktion und die Antwort-Funktion.
## Kompabilität
Diese Applikation ist für Mobiltelefone dargestellt.
## Zugriff
Wenn man alles gestartet hat (Frontend, Backend und Datenbank) kann man auf das Frontend mit localhost:3000 zugreifen. Wenn man auf localhost:3000/ drauf ist, dann kann man oben rechts einen Benutzer und einen Chatraum erstellen. Hat man einen Chatraum erstellt, kann man mit einem Klick auf diesen drauf. Nun kann man mit dem erstellten Benutzer, wenn man diesen oben rechts im Chatraum ausgewählt hat, Nachrichten schreiben. Man kann mehrere Benutzer und Chaträume erstellen.
## Fehler
Falls etwas nicht funktioniert, dann könnt Ihr mir dies gerne auf andre01kocher@gmail.com melden.
