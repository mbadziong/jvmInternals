<h1>jvmInternals</h1>
<h2>Lab10</h2>
<h3>Uruchomienie:</h3>
dodaæ parametry do uruchomienia:
-Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false
 -Dcom.sun.management.jmxremote.ssl=false com.example.jvmint.Main
 
<h3>Co to robi</h3>
Aplikacja co 5 sekund wysy³a geta do przyk³adowego rest api na adres http://jsonplaceholder.typicode.com/
<br/>
Aby móc wp³yn¹æ na GETa nale¿y odpaliæ jconsole i zmieniæ wartoœci id oraz action
<br/>
dostêpne akcje oraz id:
<br/>
/posts	100 items<br/>
/comments	500 items<br/>
/albums	100 items<br/>
/photos	5000 items<br/>
/todos	200 items<br/>
/users	10 items<br/>
<br/>
dla id > 500 rzucam notyfikacjê. 
<br/>
 