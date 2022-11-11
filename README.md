# HTML code generátor

### feladat:
Készítsen programot, ami HTML kódot készít. A megoldás tartalmazza:
- Az API-t, amivel a dokumentum létrehozható
- Azt a kódot, ami az API segítségével az elvárt kimenetet hozza létre

Az API adjon lehetőséget a szerkesztett HTML-ből a fő elemek eltávolítására (h1, p , a, table,
tr, td) a szerkesztés befejezése (megjelenítés) előtt. A program kimenete a megfelelően
indentált HTML kód legyen a kijelzőn. A HTML-ben megjelenő név és email paraméterek
parancssori argumentumok legyenek, a repository url paraméter a kódban legyen definiálva.
Az API elegendő, ha az elvárt kimenetben szereplő HTML elemeket kezeli.

Az elvárt kimenet:
```html
<!DOCTYPE html>
<html>
    <head>
        <title>Teszt Feladat</title>
    </head>
    <body>
        <h1>Teszt Feladat</h1>
        <p><a href="repository url paraméter">Megoldás</a></p>
        <p>A feladat elkészítőjének adatai</p>
        <table border="1px solid black">
            <tr>
                <td>Név</td>
                <td>név paraméter</td>
            </tr>
            <tr>
                <td>Elérhetőség</td>
                <td>email paraméter</td>
            </tr>
        </table>
        <a href="http://lpsolutions.hu">L&P Solutions</a>
    </body>
</html>
```
### fordítás:
```shell
mvn clean package
```

### futtatás:
```shell
java -jar target/codegenerator.jar "Csanádi Balázs" "genin83@gmail.com"
```

### használat:
Futtatás után az API a http://localhost:8080/api/test címen érhető el.  
A kimeneti kódot az URL-ben query paraméterekkel lehet módosítani.

- Ha az Api nem kap paramétert, a teljes html kódot adja vissza. Pl:  
  &emsp; http://localhost:8080/api/test


- Ha az API megfelelő paraméter(eke)t kap, és nem adunk meg indexet a paraméter(ek)hez, akkor az összes, a paraméter(ek)nek megfelelő típusú html elem el lesz távolítva a kódból. Pl:  
  &emsp; http://localhost:8080/api/test?td  
  &emsp; http://localhost:8080/api/test?td&a  


- Ha az API megfelelő paraméter(eke)t kap, és megadunk index(ek)et a paraméter(ek)hez, akkor a paraméter(ek)ben kapott, megfelelő indexű html elem(ek) lesz(nek) eltávolítva a kódból. Pl:  
  &emsp; http://localhost:8080/api/test?td=0&td=2&a=1  
  &emsp; http://localhost:8080/api/test?h1=0&a&p=1  


- Ha az API megfelelő paraméter(eke)t kap, és megadunk index(ek)et a paraméter(ek)hez, valamint index nélkül is átadjuk a paraméter(eke)t, akkor csak a paraméter(ek)ben kapott, megfelelő indexű html elem(ek) lesz(nek) eltávolítva a kódból. Pl:  
  &emsp; http://localhost:8080/api/test?td=0&a=1&td&td=2  


- Az API az ismeretlen paramétereket, és az érvénytelen indexeket figyelmen kívűl hagyja. Pl:  
  &emsp; http://localhost:8080/api/test?td=0&td=deleteAll&a=1&p=42&kiscica=0  

#### Eltávolítható html elemek: h1, p, a, table, tr, td
###

A DaoConfiguration fájlban beállítható, hogy az API melyik dao implementációt használja.  

Megvalósított implementációk:  
&emsp; repository dao  
&emsp;&emsp;&emsp; - kódban definiálva (CODE)  
&emsp;&emsp;&emsp; - properties fájlban definiálva (PROPERTIES_FILE)  

&emsp; user dao  
&emsp;&emsp;&emsp; - parancssori argumentumban definiálva (COMMAND_LINE_ARGUMENT)  
