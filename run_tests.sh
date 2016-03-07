#!/bin/bash

mkdir log
cd log
mkdir refl
mkdir refer
cd ..

for ((i=1000; i <= 2000; i = i + 1000)); do
	for((k=1; k <= 10; k++)); do
		mvn exec:java -q -Dexec.args="$i refer" >> log/refer/attempt_$k_size_$i.log
		mvn exec:java -q -Dexec.args="$i refl" >> log/refl/attempt_$k_size_$i.log
	done

	result_reference="$(python average.py log/refer/attempt_$k_size_$i.log)"
	result_reflection="$(python average.py log/refl/attempt_$k_size_$i.log)"

	echo $i $result_reference $result_reflection >> log/jvmTests.dat

	echo "$i done"
done

	
