#!/bin/bash

mkdir log
cd log
mkdir refer_int
mkdir refl_int
mkdir refer_obj
mkdir refl_obj
cd ..

for ((i=1000; i <= 51000; i = i + 5000)); do
	for((k=1; k <= 10; k++)); do
		mvn exec:java -q -Dexec.args="$i refer int" >> log/refer_int/attempt_$k_size_$i.log
		mvn exec:java -q -Dexec.args="$i refl int" >> log/refl_int/attempt_$k_size_$i.log
        mvn exec:java -q -Dexec.args="$i refer obj" >> log/refer_obj/attempt_$k_size_$i.log
        mvn exec:java -q -Dexec.args="$i refl obj" >> log/refl_obj/attempt_$k_size_$i.log
	done

	result_reference_int="$(python average.py log/refer_int/attempt_$k_size_$i.log)"
	result_reflection_int="$(python average.py log/refl_int/attempt_$k_size_$i.log)"
	result_reference_obj="$(python average.py log/refer_obj/attempt_$k_size_$i.log)"
    result_reflection_obj="$(python average.py log/refl_obj/attempt_$k_size_$i.log)"

	echo $i $result_reference_int $result_reflection_int $result_reference_obj $result_reflection_obj >> log/jvmTests.dat

	echo "$i done"
done

	
