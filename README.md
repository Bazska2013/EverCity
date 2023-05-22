# EverCity - dokumentáció

### Bevezetés

> A feladat egy egyjátékos, tycoon jellegű játék, egy valósidejű város építő szimulátor megvalósítása.

### A játék rövid leírása

> A játékos egy jól körülhatárolt területet kap, amely négyzetalakú mezőkből áll.Ezen a területen építheti fel a játékos a saját városát és menedzselheti azt egy szélesjogkörökkel bíró polgármesterként. A város leegyszerűsítve a különböző típusú zónákból (amelyeken a lakók automatikusan építkeznek), a játékos által külön megépítendő kiszolgálóépületekből és az ezeket összekötő utakból áll. A játékos célja, hogy virágzó várost fejlesszen, ahol a polgárok boldogok és a költségvetés kiegyensúlyozott. 
> 
> A kezdeti város építésére a játékos kap egy kezdőtőkét, azonban a további fejlesztésekhez a helyi adóbevételekre kell támaszkodnia. A játékot valósidejűként kell elkészíteni, de a valósághoz képest természetesen az idő jóval gyorsabban fog telni. A játék jelezze ki az időt (év,hónap,nap), és legyen lehetőség a játékot 3 különböző sebességben játszani, valamint a szüneteltetni is. 
> 
> A kész játéknak minimálisan felülnézeti 2dimenziós grafikával kell rendelkeznie, ahol az egyes mezőket statikus képek reprezentálják. Többlet feladatként készíthető haladóbb grafikai megoldás is.

### Osztálydiagram

> Az osztálydiagram megtalálható a Wiki oldalon.

### A játék működése

> - A játék megnyitásakor a következő képernyő fogad, ahol tudunk új játékot kezdeni, betölteni egy korábbi mentést, vagy kilpni a játékból.

![image.png](./image.png)
> 
> - Ha rákattintunk egy zónára bal oldalon láthatjuk az információkat róla.

![image-1.png](./image-1.png)
> 
> - A 'TOGGLE' gombra kattintva tudunk váltani, hogy zónát vagy épületet szeretnénk lehelyezni.

![image-2.png](./image-2.png)
> 
> - Ha kiválasztunk egy zónát vagy épületet, arra kattintva kiválasztjük azt, majd rákattintva a négyzetre ahova szeretnénk letenni lehelyeződik oda.

![image-3.png](./image-3.png)
> 
> - A 'Menü' pontban a 'Nyelv'/'Language' menüpont alatt tudjuk váltani, hogy angol vagy magyar nyelven jelenjenek meg az információk a játékról.

![image-4.png](./image-4.png)![image-5.png](./image-5.png)
> 
> - A játéktábla alatti '||' gombra kattintva meg tudjuk állítani az időt, '>>' gombra kattintva fel tudjuk gyorsítani az időt, '>' gombra kattintva pedig vissza tudjuk állítani az alap gyorsaságra az időt.

![image-6.png](./image-6.png)![image-7.png](./image-7.png)![image-8.png](./image-8.png)
 - Egy lakóépületre kattintva a képernyő jobb oldalán megjelenik egy görgethető felsorolás az abban az épületben lakók adatairól.

 ![image-9.png](./image-9.png)
  - A képernyő alján különböző épületek találhatóak, ezekre kattintva helyezhetjük le a játéktáblára őket.

![image-10.png](./image-10.png)
   - Egy lakó/szolgáltatási/ipari épületre kattintva bal oldalt az információs ablakban megjelenik egy fejlesztés gomb, erre kattintva fejleszthető az adott épület.
   
![image-12.png](./image-12.png)
