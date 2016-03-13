# jvmInternals

Konwerter POJO -> JSON

Ograniczenia:
1. konwerter bierze pod uwage wylacznie publiczne pola klasy.
2. konwerter bierze pod uwage wylacznie gettery, ktorych prefix to get lub is oraz nie sa prywatne (oraz statyczne).
3. dziala dla obiektow zlozonych oraz tablic.

