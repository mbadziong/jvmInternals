package com.example.jvmint.instr;

import com.example.jvmint.service.App;
import com.example.jvmint.tools.ClassGenerator;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassLoaderWithAgentTest {
    private ArrayList<Long> results = new ArrayList<>();
    private App app = new App();

    @Test
    public void selectedAgentTest() {
        for (int i = 1; i <= 10000; i++) {
            Class c = null;
            try {
                c = Class.forName("com.example.jvmint.generated.Person" + i);
                Method m = c.getDeclaredMethod("myTestMethod");
                Object instance = c.newInstance();

                long launchTime = System.nanoTime();
                m.invoke(instance);
                long elapsedTime = System.nanoTime() - launchTime;
                results.add(elapsedTime);

            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println(calcAverage(results));

        assertTrue(true);
    }

    @Ignore
    public void generateClasses() {
        ClassGenerator cg = new ClassGenerator();

        for (int classNumber = 1; classNumber <= 10000; classNumber++) {
            cg.generateClass("Person" + classNumber);
        }

        assertTrue(true);
    }

    private long calcAverage(ArrayList<Long> results) {
        long sum = 0;
        for (Long result : results) {
            sum += result;
        }

        return sum / results.size();
    }


}
