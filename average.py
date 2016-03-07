#!/usr/bin/python3.4

import sys

filename =  sys.argv[1]

file = open(filename, "r")
times = []

for value in file:
	times.append(int(value))

file.close()

print reduce(lambda x, y: x + y, times) / len(times)
