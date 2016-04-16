package com.example.jvmint.tools;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class ClassGenerator {
    public void generateClass(String className) {
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage("com.example.jvmint.generated").setName(className);
        generateCommons(javaClass);
        generateRandomPropsAndClasses(javaClass);
        saveToFile(javaClass);
    }

    private void generateRandomPropsAndClasses(JavaClassSource javaClass) {
        Random rand = new Random();
        int smallRandomNumber = rand.nextInt() % 10 + 1;

        for(int i = 0; i < smallRandomNumber; i++) {
            javaClass.addProperty(Integer.class, "prop" + i)
                    .setMutable(true);
        }
    }

    private void generateCommons(JavaClassSource javaClass) {
        javaClass.addMethod()
                .setConstructor(true)
                .setBody("")
                .setPublic();
        javaClass.addMethod()
                .setName("myTestMethod")
                .setReturnType("void")
                .setBody("System.out.println(this.getClass());")
                .setPublic();
    }

    private void saveToFile(JavaClassSource javaClass) {
        try (PrintStream out = new PrintStream(new FileOutputStream("./src/main/java/com/example/jvmint/generated/" + javaClass.getName() + ".java"))) {
            out.print(javaClass.toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
