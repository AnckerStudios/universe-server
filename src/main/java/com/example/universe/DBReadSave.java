package com.example.universe;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class DBReadSave {
    public void savePS(PlanetSystemEntity planetSystem) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(planetSystem, writer);
        writer.close();
    }
    public PlanetSystemEntity readPS(String namePlanetSystem) throws FileNotFoundException {
        File file = new File(namePlanetSystem + ".yml");
        PlanetSystemEntity data = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            try{
                data = yaml.load(inputStream);
            }
            catch (ClassCastException e){
                System.out.println("не тот класс");
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
    public void saveSat(SatelliteEntity planetSystem) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(planetSystem, writer);
        writer.close();
    }
    public SatelliteEntity readSat(String name) throws FileNotFoundException {
        File file = new File(name + ".yml");
        SatelliteEntity data = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            try {
                data = yaml.load(inputStream);
            }
            catch (ClassCastException e){
                System.out.println("не тот класс");
            }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
        return data;
    }
}
