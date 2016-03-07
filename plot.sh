outputfilename='set output '
filename='gnuplotResult'


echo 'set output "out.png"' >> conf.plot
echo 'set grid' >> conf.plot
echo 'set autoscale fix' >> conf.plot
echo 'set title "Performance test for JVM"' >> conf.plot
echo 'set xlabel "runs"' >> conf.plot
echo 'set ylabel "miliseconds"' >> conf.plot
echo 'set terminal png size 1200,900' >> conf.plot
echo 'plot "jvmTests.dat" u 1:2 w lp t "by reference primitive",
           "jvmTests.dat" u 1:3 w lp t "by reflection object",
           "jvmTests.dat" u 1:4 w lp t "by reference primitive",
           "jvmTests.dat" u 1:5 w lp t "by reflection object",
           ' >> conf.plot
gnuplot conf.plot

rm conf.plot


