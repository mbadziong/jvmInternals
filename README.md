# jvmInternals

<h2>Konwerter POJO -> JSON</h2>

<h3>Ograniczenia:</h3>
<li>
<ul>1. konwerter bierze pod uwage wylacznie publiczne pola klasy.</ul>
<ul>2. konwerter bierze pod uwage wylacznie gettery, ktorych prefix to get lub is oraz nie sa prywatne (oraz statyczne).</ul>
<ul>3. dziala dla obiektow zlozonych oraz tablic.</ul>
</li>

